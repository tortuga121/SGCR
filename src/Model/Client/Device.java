package Model.Client;

import java.time.LocalDateTime;

public class Device implements IDevice {
    private final int regCode;
    private final String nifOwner;
    private final String problemDescription;
    private final LocalDateTime date;

    public Device(int regCode, String nifOwner, String problemDescription, LocalDateTime date) {
        this.regCode = regCode;
        this.nifOwner = nifOwner;
        this.problemDescription = problemDescription;
        this.date = date;
    }
    public Device(Device d) {
        this.regCode = d.regCode;
        this.nifOwner = d.nifOwner;
        this.problemDescription = d.problemDescription;
        this.date = d.date;
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
    public LocalDateTime getdate() {
        return date;
    }

    @Override
    public IDevice clone() {
        return new Device(this);
    }

    @Override
    public int compareTo(Object o) {
        Device d = (Device) o;
        return d.getdate().compareTo(getdate());
    }
}
