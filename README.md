# ethereum-disassembler
Ethereum disassembler from binary code to opcodes version

### Example with Java code

```
Disassembler d = new Disassembler("60606040523415600e57600080fd5b603580601b6000396000f300606060");
System.out.println(d.getDisassembledCode());
```

Outputs:
```
0x000 PUSH1 0x60
0x002 PUSH1 0x40
0x004 MSTORE
0x005 CALLVALUE
0x006 ISZERO
0x007 PUSH1 0x0e
0x009 JUMPI
0x00A PUSH1 0x00
0x00C DUP1
0x00D REVERT
0x00E JUMPDEST
0x00F PUSH1 0x35
0x011 DUP1
0x012 PUSH1 0x1b
0x014 PUSH1 0x00
0x016 CODECOPY
0x017 PUSH1 0x00
0x019 RETURN
0x01A STOP
0x01B PUSH1 0x60
0x01D PUSH1 0x
```

### Executable jar coming soon
