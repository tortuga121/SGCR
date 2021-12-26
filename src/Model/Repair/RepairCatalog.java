package Model.Repair;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeSet;

import Exception.*;

public class RepairCatalog implements IRepairCatalog{
    private HashMap<Integer,IRepairPlan> repairs;
    private TreeSet<Integer> toRepair;
    private HashMap<Integer, LocalDate> toApprove;


    public int mostUrgentRepair() throws NoRepairException{
       if(toRepair.size() < 1) throw new NoRepairException("No more reapirs to do");
       return toRepair.first();
    }

    @Override
    public IRepairPlan getRepairPlan(int regCode) throws NoRepairException {
        if(repairs.containsKey(regCode))
            return repairs.get(regCode).clone();
        else throw new NoRepairException("Repair: " + regCode + "does not exist");
    }

    public void addRepairPlan(IRepairPlan rp) {
        repairs.put(rp.getRegCode(),rp.clone());
        toApprove.put(rp.getRegCode(),LocalDate.now());
    }
    @Override
    public void unaproveBudget(int regCode) throws DeviceNotFoundException {
        if(toApprove.containsKey(regCode)) toApprove.remove(regCode);
        else
            throw new DeviceNotFoundException("Repair plan with" + regCode + "registration code does not exist");
    }

    public void approveBudget(int regCode) throws DeviceNotFoundException {
        if(toApprove.containsKey(regCode)) toApprove.remove(regCode);
        else
            throw new DeviceNotFoundException("Repair plan with" + regCode + "registration code does not exist");
        toRepair.add(regCode);
    }

}
