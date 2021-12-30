package Model.Devices;

import java.time.LocalDateTime;

public class Device implements IDevice {
    private final int regCode;
    private final String nifOwner;
    private final String problemDescription;

    public Device(int regCode, String nifOwner, String problemDescription, LocalDateTime date) {
        this.regCode = regCode;
        this.nifOwner = nifOwner;
        this.problemDescription = problemDescription;
    }
    public Device(Device d) {
        this.regCode = d.regCode;
        this.nifOwner = d.nifOwner;
        this.problemDescription = d.problemDescription;
    }

    @Override
    public int getRegCode() {
        return regCode;
    }

    @Override
    public String getNifOwner() {
        return nifOwner;
    }

    @Override
    public String getDescription() {
        return problemDescription;
    }

    @Override
    public IDevice clone() {
        return new Device(this);
    }
}
