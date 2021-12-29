package Model.Client;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import Exception.*;

public class DeviceCatalog implements IDeviceCatalog {
    private HashMap<Integer,IDevice> devices;
    private Queue<Integer> budgetRequest;
    private HashMap<Integer, LocalDate> toPickup;
    private HashSet<Integer> abandonedDevices;
    private HashSet<Integer> archivedRequests;

    public IDevice getOldestRequest() throws DeviceNotFoundException {
        if(budgetRequest.size() == 0)
            throw new DeviceNotFoundException("No more budget requests");
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
            toPickup.put(regCode,LocalDate.now().plusDays(90));
        else
            throw new DeviceNotFoundException("" + regCode);
    }
    public void popOldestRequest() throws DeviceNotFoundException {
        if(budgetRequest.size() == 0)
            throw new DeviceNotFoundException("No more budgets requests");
        else devices.get(budgetRequest.poll());
    }

    @Override
    public boolean existsDevice(int regCode) {
        return devices.containsKey(regCode);
    }

    public void checkforAbandonedDevices() {
        for(Map.Entry<Integer,LocalDate> e : toPickup.entrySet())
            if(e.getValue().compareTo(LocalDate.now()) > 0) {
                abandonedDevices.add(e.getKey());
                toPickup.remove(e.getKey());
            }
        toPickup.forEach((key, value) -> {
            if (value.compareTo(LocalDate.now()) > 0)
                abandonedDevices.add(key);
        });
        toPickup = new HashMap<>( toPickup.entrySet().stream()
                .filter(e -> e.getValue().compareTo(LocalDate.now()) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue)));
    }
}
