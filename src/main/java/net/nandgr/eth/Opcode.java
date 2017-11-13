package net.nandgr.eth;

public class Opcode {

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

    @Override
    public String toString() {
        String toString = this.opcode;
        if (parameter != null) {
            toString += " " + parameter;
        }
        return toString;
    }
}
