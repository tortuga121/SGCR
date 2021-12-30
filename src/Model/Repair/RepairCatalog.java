package Model.Repair;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;


import Exception.*;

public class RepairCatalog implements IRepairCatalog{
    private HashMap<Integer,IRepairPlan> repairs;
    private HashMap<Integer,IExpressRepair> expressRepairs;
    private TreeSet<Integer> toRepair;
    private HashMap<Integer, LocalDateTime> toApprove;

    public RepairCatalog() {
        repairs = new HashMap<>();
        expressRepairs = new HashMap<>();
        toRepair = new TreeSet<>();
        toApprove = new HashMap<>();
        repairs.put(3,null);
        toApprove.put(3,LocalDateTime.now().plusDays(2));

    }

    public void checkForOutdated() {
        toApprove =  new HashMap<>( toApprove.entrySet()
                .stream()
                .filter(e -> e.getValue().compareTo(LocalDateTime.now()) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        toRepair = toRepair.stream()
                .filter(id -> repairs.get(id).getDeadline().compareTo(LocalDateTime.now()) < 0)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public int mostUrgentRepair() throws NoRepairException{
       if(toRepair.size() < 1) throw new NoRepairException("No more repairs to do");
       return toRepair.first();
    }

    @Override
    public IRepairPlan getRepairPlan(int regCode) throws NoRepairException {
        if(repairs.containsKey(regCode))
            return repairs.get(regCode).clone();
        else throw new NoRepairException("Repair: " + regCode + "does not exist");
    }

    @Override
    public void addExpressrepair(String type, int regcode, String desrp) {
        expressRepairs.put(regcode,new ExpressRepair(regcode,desrp,type));
    }

    public void addRepairPlan(IRepairPlan rp) {
        repairs.put(rp.getRegCode(),rp.clone());
        toApprove.put(rp.getRegCode(),LocalDateTime.now().plusDays(30));
    }
    @Override
    public void unaproveBudget(int regCode) throws DeviceNotFoundException {
        if(toApprove.containsKey(regCode)) toApprove.remove(regCode);
        else
            throw new DeviceNotFoundException("Repair plan with" + regCode + "registration code does not exist");
    }
    @Override
    public void approveBudget(int regCode) throws DeviceNotFoundException {
        if(toApprove.containsKey(regCode)) toApprove.remove(regCode);
        else
            throw new DeviceNotFoundException("Repair plan with" + regCode + "registration code does not exist");
        toRepair.add(regCode);
    }

}
