package org.openhab.io.transport.modbus;

/**
 *
 * @author Sami Salonen
 *
 */
public class ModbusWriteRegisterRequestBlueprintImpl implements ModbusWriteRegisterRequestBlueprint {
    private int slaveId;
    private int reference;
    private ModbusRegisterArray registers;
    private boolean writeMultiple;

    public ModbusWriteRegisterRequestBlueprintImpl(int slaveId, int reference, ModbusRegisterArray registers,
            boolean writeMultiple) throws IllegalArgumentException {
        super();
        this.slaveId = slaveId;
        this.reference = reference;
        this.registers = registers;
        this.writeMultiple = writeMultiple;

        if (!writeMultiple && registers.size() > 1) {
            throw new IllegalArgumentException("With multiple registers, writeMultiple must be true");
        }
    }

    @Override
    public int getReference() {
        return reference;
    }

    @Override
    public int getUnitID() {
        return slaveId;
    }

    @Override
    public ModbusWriteFunctionCode getFunctionCode() {
        return writeMultiple ? ModbusWriteFunctionCode.WRITE_MULTIPLE_REGISTERS
                : ModbusWriteFunctionCode.WRITE_SINGLE_REGISTER;

    }

    @Override
    public ModbusRegisterArray getRegisters() {
        return registers;
    }
}