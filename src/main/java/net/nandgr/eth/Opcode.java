package net.nandgr.eth;

public class Opcode {

    private int offset;
    private String opcode;
    private String parameter;

    public String getOpcode() {
        return opcode;
    }

    void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getParameter() {
        return parameter;
    }

    void setParameter(String parameter) {
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
        String toString = "0x" + String.format("%03X", this.offset) + " " + this.opcode;
        if (parameter != null) {
            toString += " " + parameter;
        }
        return toString;
    }
}
