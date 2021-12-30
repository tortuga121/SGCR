package Model.Devices;

import java.time.LocalDateTime;

public class Device implements IDevice {
    private final int regCode;
    private String name;
    private final String nifOwner;
    private final String problemDescription;

    public Device(int regCode, String nifOwner, String problemDescription, String name) {
        this.regCode = regCode;
        this.name = name;
        this.nifOwner = nifOwner;
        this.problemDescription = problemDescription;
    }
    public Device(Device d) {
        this.name = d.name;
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
