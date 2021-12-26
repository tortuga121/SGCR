package Model;
import Exception.*;

public interface IRepairCatalog {
    void unaproveBudget(int regCode) throws DeviceNotFoundException;
    void approveBudget(int regCode) throws DeviceNotFoundException;
    void addRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;
}
