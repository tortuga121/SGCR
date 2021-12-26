package Model.Client;
import Exception.*;
public interface IDeviceCatalog {
    void addRequest(IDevice dev) throws InvalidRegistrationCodeException;
    void addToPickup(int regCode) throws DeviceNotFoundException;
    void popOldestRequest() throws DeviceNotFoundException;
    IDevice getOldestRequest() throws DeviceNotFoundException;

}
