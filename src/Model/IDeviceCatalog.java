package Model;
import Exception.*;
public interface IDeviceCatalog {
    void addRequest(IDevice dev, int recepId) throws WorkerDoesNotExist;
    void abandonDevice(int regCode) throws DeviceNotFoundException;
    public void rejectBudget(int regCode) throws DeviceNotFoundException, NoRepairException;
}
