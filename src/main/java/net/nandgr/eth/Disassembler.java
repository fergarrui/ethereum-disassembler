package net.nandgr.eth;

import net.nandgr.eth.iterator.StringTwoCharIterator;

import java.util.ArrayList;
import java.util.List;

public class Disassembler {

    private final String code;
    private String disassembledCode = "";
    private final List<Opcode> opcodes = new ArrayList<>();

    public Disassembler(String code) {
        this.code = code;
        loadOpcodes();
    }

    private void loadOpcodes() {
        StringTwoCharIterator iterator = new StringTwoCharIterator(code);
        StringBuilder disassembledCodeBuilder = new StringBuilder();
        while(iterator.hasNext()) {
            String nextByte = iterator.next();
            Opcode opcode = new Opcode();
            Integer opcodeHex = Integer.valueOf(nextByte, 16);
            Opcodes opcodeDefinition = Opcodes.getOpcode(opcodeHex);
            if (opcodeDefinition == null) {
                opcode.setOpcode("Unknown Opcode: " + nextByte);
            } else {
                opcode.setOpcode(opcodeDefinition.toString());
                Integer parametersNum = opcodeDefinition.getParametersNum();
                if (parametersNum > 0) {
                    String opParameter = getParameter(parametersNum, iterator);
                    opcode.setParameter(opParameter);
                }
            }
            opcodes.add(opcode);
            disassembledCodeBuilder.append(opcode.toString()).append("\n");
        }
        this.disassembledCode = disassembledCodeBuilder.toString();
    }

    public String getCode() {
        return code;
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
