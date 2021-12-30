package Model.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import Exception.*;

public class DeviceCatalog implements IDeviceCatalog {
    private HashMap<Integer,IDevice> devices;
    private Queue<Integer> budgetRequest;
    private HashMap<Integer, LocalDate> toPickup;
    private HashSet<Integer> abandonedDevices;
    private HashSet<Integer> archivedRequests;

    public DeviceCatalog() {
        devices = new HashMap<>() ;
        budgetRequest = new LinkedList<>();
        toPickup = new HashMap<>() ;
        abandonedDevices = new HashSet<>() ;
        archivedRequests = new HashSet<>() ;
        IDevice a = new Device(1,"123456789","este é o 1", LocalDateTime.now());
        IDevice b = new Device(2,"987654321","este é o 2", LocalDateTime.now());
        IDevice c = new Device(3,"333333333", "este é o 3",LocalDateTime.now());
        IDevice d = new Device(4,"444444444", "este é o 4",LocalDateTime.now());
        IDevice e = new Device(5,"555555555", "este é o 5",LocalDateTime.now());
        devices.put(a.getRegCode(),a);
        devices.put(b.getRegCode(),b);
        devices.put(c.getRegCode(),c);
        devices.put(d.getRegCode(),d);
        devices.put(e.getRegCode(),e);

        budgetRequest.add(a.getRegCode());
        budgetRequest.add(b.getRegCode());
        budgetRequest.add(d.getRegCode());
        budgetRequest.add(e.getRegCode());


        toPickup.put(c.getRegCode(),LocalDate.now().plusDays(2));

    }

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
