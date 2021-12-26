package Model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import Exception.*;

public class DeviceCatalog {
    private HashMap<Integer,IDevice> devices;
    private Queue<IDevice> budgetRequest;
    private HashMap<Integer, LocalDate> toPickup;
    private HashSet<Integer> abandonedDevices;
    private HashSet<Integer> archivedRequests;

    public IDevice oldestRequest() throws DeviceNotFoundException {
        if(budgetRequest.size() == 0)
            throw new DeviceNotFoundException("No more budgets requests");
        else return budgetRequest.poll();
    }

    public void abandonDevice(int regCode) throws DeviceNotFoundException {
        if(!existsDevice(regCode)) throw new DeviceNotFoundException();
        abandonedDevices.add(regCode);
    }

    public boolean existsDevice(int regCode) {
        return devices.containsKey(regCode);
    }

}
