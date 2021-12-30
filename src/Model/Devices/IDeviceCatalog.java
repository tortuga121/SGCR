package Model.Devices;
import Exception.*;

import java.time.LocalDate;
import java.util.Set;

public interface IDeviceCatalog {
    void addRequest(IDevice dev) throws InvalidRegistrationCodeException;
    LocalDate addToPickup(int regCode) throws DeviceNotFoundException;
    void popOldestRequest() throws DeviceNotFoundException;
    IDevice getOldestRequest() throws DeviceNotFoundException;
    boolean existsDevice(int regCode);
    void checkforAbandonedDevices();
    Set<Integer> getdevicesbyNif(String nif) throws DeviceNotFoundException;
    Set<Integer> getDeliveredDevicesbyNif(String nif) throws DeviceNotFoundException;
    IDevice getdevice(int id) throws DeviceNotFoundException;
    void pickupDevice(int id) throws DeviceNotFoundException;

}
