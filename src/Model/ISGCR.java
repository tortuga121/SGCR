package Model;
import Exception.*;

public interface ISGCR {
    void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist;
    void refuseBudget(int regCode) throws DeviceNotFoundException;

    void sugestRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;

}
