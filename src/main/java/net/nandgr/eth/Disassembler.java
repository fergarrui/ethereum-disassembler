package net.nandgr.eth;

import net.nandgr.eth.iterators.StringTwoCharIterator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Disassembler {

    private static final String CONTRACT_METADATA_PREFIX = "a165627a7a72305820"; // 0xa1 0x65 'b' 'z' 'z' 'r' '0' 0x58 0x20 + <32 bytes swarm hash> <2 bytes length of the metadata>
    private String code;
    private String contractMetadata;
    private String disassembledCode = "";
    private final List<Opcode> opcodes = new ArrayList<>();

    public Disassembler(String code) {
        String[] codeStripped = cleanData(code);
        this.code = codeStripped[0];
        if (codeStripped.length > 1) {
            this.contractMetadata = CONTRACT_METADATA_PREFIX + codeStripped[1];
        }
        loadOpcodes();
    }

    public static String[] cleanData(String code) {
        if (code.startsWith("0x")) {
            code = code.substring(2);
        }
        return code.split(CONTRACT_METADATA_PREFIX);
    }

    private void loadOpcodes() {
        StringTwoCharIterator iterator = new StringTwoCharIterator(code);
        StringBuilder disassembledCodeBuilder = new StringBuilder();
        int offset = 0;
        while(iterator.hasNext()) {
            String nextByte = iterator.next();
            Opcode opcode = new Opcode();
            opcode.setOffset(offset);
            Integer opcodeHex = Integer.valueOf(nextByte, 16);
            Opcodes opcodeDefinition = Opcodes.getOpcode(opcodeHex);
            if (opcodeDefinition == null) {
                System.out.println("Unknown opcode: " + opcodeHex);
                opcode.setOpcode(Opcodes.UNKNOWN);
            } else {
                opcode.setOpcode(opcodeDefinition);
                Integer parametersNum = opcodeDefinition.getParametersNum();
                if (parametersNum > 0) {
                    offset += parametersNum;
                    String opParameter = getParameter(parametersNum, iterator);
                    opcode.setParameter(new BigInteger(opParameter.replaceAll("0x", ""),16));
                }
            }
            offset++;
            opcodes.add(opcode);
            disassembledCodeBuilder.append(opcode.toString()).append(System.lineSeparator());
        }
        this.disassembledCode = disassembledCodeBuilder.toString();
    }

    public String getCode() {
        return code;
    }

    public String getContractMetadata() {
        return contractMetadata;
    }

    public String getDisassembledCode() {
        return disassembledCode;
    }

    public List<Opcode> getOpcodes() {
        return opcodes;
    }

    private static String getParameter(int parametersNum, StringTwoCharIterator iterator) {
        StringBuilder sb = new StringBuilder("0x");
        int i = 0;
        while(i < parametersNum && iterator.hasNext()) {
            String next = iterator.next();
            sb.append(next);
            i++;
        }
        return sb.toString();
    }
}
