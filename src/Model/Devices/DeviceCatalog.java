package Model.Devices;

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
    private HashMap<String,HashSet<Integer>> nifs;

    public DeviceCatalog() {
        devices = new HashMap<>() ;
        budgetRequest = new LinkedList<>();
        toPickup = new HashMap<>() ;
        abandonedDevices = new HashSet<>() ;
        archivedRequests = new HashSet<>() ;
        nifs = new HashMap<>();
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
       if(!nifs.containsKey(dev.getNifOwner()))
           nifs.put(dev.getNifOwner(), new HashSet<>());
       nifs.get(dev.getNifOwner()).add(dev.getRegCode());
       budgetRequest.add(dev.getRegCode());
    }
    public LocalDate addToPickup(int regCode) throws DeviceNotFoundException {
        if(existsDevice(regCode))
            toPickup.put(regCode,LocalDate.now().plusDays(90));
        else
            throw new DeviceNotFoundException("" + regCode);
        return LocalDate.now().plusDays(90);
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

    public int generateNewregistrationCode() {
        Random rand = new Random();
        int randomNum = rand.nextInt((10000 - 1) + 1) + 1;
        while(devices.containsKey(randomNum))
            randomNum = rand.nextInt((10000 - 1) + 1) + 1;
        return randomNum;
    }

    @Override
    public Set<Integer> getdevicesbyNif(String nif) throws DeviceNotFoundException {
        if(!nifs.containsKey(nif)) throw new DeviceNotFoundException("no Devices with nif: " + nif);
        return new HashSet<>(nifs.get(nif));
    }
}
