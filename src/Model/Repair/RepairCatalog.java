package Model.Repair;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.TreeSet;


import Exception.*;

public class RepairCatalog implements IRepairCatalog{
    private HashMap<Integer,IRepairPlan> repairs;
    private HashMap<Integer,IExpressRepair> expressRepairs;
    private TreeSet<Integer> toRepair;
    private HashMap<Integer, LocalDateTime> toApprove;

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
