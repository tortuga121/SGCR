package Model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

public class RepairCatalog {
    private HashMap<Integer,IRepairPlan> repairs;
    private TreeMap<Integer,IRepairPlan> toRepair;
    private HashMap<Integer, LocalDate> toApprove;


    public IRepairPlan oldestRepair() {
        return toRepair.firstEntry().getValue();
    }

    public void addRepairPlan(IRepairPlan rp) {
        repairs.put(rp.getRegCode(),rp.clone());
        toApprove.put(rp.getRegCode(),LocalDate.now());
    }
}
