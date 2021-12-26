package Model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import Exception.*;

public class DeviceCatalog implements IDeviceCatalog {
    private HashMap<Integer,IDevice> devices;
    private Queue<Integer> budgetRequest;
    private HashMap<Integer, LocalDate> toPickup;
    private HashSet<Integer> abandonedDevices;
    private HashSet<Integer> archivedRequests;

    public IDevice getOldestRequest() throws DeviceNotFoundException {
        if(budgetRequest.size() == 0)
            throw new DeviceNotFoundException("No more budgets requests");
        else return devices.get(budgetRequest.element());
    }
    public void addRequest(IDevice dev) throws InvalidRegistrationCodeException{
       if(existsDevice(dev.getRegCode()))
            throw new InvalidRegistrationCodeException(dev.getRegCode() + " already exists");
       devices.put(dev.getRegCode(),dev);
       budgetRequest.add(dev.getRegCode());
    }
    public void addToPickup(int regCode) throws DeviceNotFoundException {
        if(existsDevice(regCode))
            toPickup.put(regCode,LocalDate.now());
        else
            throw new DeviceNotFoundException("" + regCode);
    }
    public void popOldestRequest() throws DeviceNotFoundException {
        if(budgetRequest.size() == 0)
            throw new DeviceNotFoundException("No more budgets requests");
        else devices.get(budgetRequest.poll());
    }


    public boolean existsDevice(int regCode) {
        return devices.containsKey(regCode);
    }

}
