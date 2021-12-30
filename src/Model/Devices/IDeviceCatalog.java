package Model.Devices;
import Exception.*;

import java.time.LocalDate;
import java.util.List;

public interface IDeviceCatalog {
    void addRequest(IDevice dev) throws InvalidRegistrationCodeException;
    LocalDate addToPickup(int regCode) throws DeviceNotFoundException;
    void popOldestRequest() throws DeviceNotFoundException;
    IDevice getOldestRequest() throws DeviceNotFoundException;
    boolean existsDevice(int regCode);
    void checkforAbandonedDevices();
    int generateNewregistrationCode();
    List<Integer> getdevicesbyNif(String nif);

}
