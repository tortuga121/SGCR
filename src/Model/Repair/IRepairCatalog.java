package Model.Repair;
import Exception.*;

public interface IRepairCatalog {
    void unaproveBudget(int regCode) throws DeviceNotFoundException;
    void approveBudget(int regCode) throws DeviceNotFoundException;
    void addRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;
    int mostUrgentRepair() throws NoRepairException;
    IRepairPlan getRepairPlan(int regCode) throws NoRepairException;
    void addExpressrepair(String type, String desrp);
    void checkForOutdated();

}
