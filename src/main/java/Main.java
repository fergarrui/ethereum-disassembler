import net.nandgr.eth.Disassembler;
import net.nandgr.eth.Opcode;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Disassembler d = new Disassembler("...");
        System.out.println("d.getContractMetadata() = " + d.getContractMetadata());
        //System.out.println("d.getDisassembledCode() = " + d.getDisassembledCode());
        //System.out.println("d.getOpcodes() = " + d.getOpcodes());
        for (Opcode opcode : d.getOpcodes()) {
            String str = opcodeToTermStr(opcode);
            System.out.println(str);
        }
    }

    private static String opcodeToTermStr(Opcode opcode) {
        String name = opcode.getOpcode().name().toLowerCase();
        BigInteger param = opcode.getParameter();
        String offsetStr = String.format("%X", opcode.getOffset());
        String end = ", " + name + ").";
        String begin = "op(0x" + offsetStr + ", ";
        String mid;
        if (param == null) {
            mid = ".";
        } else {
            mid = String.format("0x%x", param);
        }
        return begin + mid + end;
    }
}
