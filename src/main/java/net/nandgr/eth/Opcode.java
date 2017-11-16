package net.nandgr.eth;

import java.math.BigInteger;

public class Opcode {

    private int offset;
    private Opcodes opcode;
    private BigInteger parameter;

    public Opcodes getOpcode() {
        return opcode;
    }

    void setOpcode(Opcodes opcode) {
        this.opcode = opcode;
    }

    public BigInteger getParameter() {
        return parameter;
    }

    void setParameter(BigInteger parameter) {
        this.parameter = parameter;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        String toString = "0x" + String.format("%03X", this.offset) + " " + this.opcode.name();
        if (parameter != null) {
            toString += " 0x" + parameter.toString(16);
        }
        return toString;
    }
}
