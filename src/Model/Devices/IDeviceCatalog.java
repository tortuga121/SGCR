package Model.Devices;
import Exception.*;

import java.time.LocalDate;

public interface IDeviceCatalog {
    void addRequest(IDevice dev) throws InvalidRegistrationCodeException;
    LocalDate addToPickup(int regCode) throws DeviceNotFoundException;
    void popOldestRequest() throws DeviceNotFoundException;
    IDevice getOldestRequest() throws DeviceNotFoundException;
    boolean existsDevice(int regCode);
    void checkforAbandonedDevices();

}
