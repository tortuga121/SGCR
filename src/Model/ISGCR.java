package Model;
import Exception.*;

public interface ISGCR {
    void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist;
    IDevice removeOldestBudgetRequest() throws DeviceNotFoundException;
    void sendRepairPlan(int techId, IRepairPlan rp, IDevice dev) throws WorkerDoesNotExist;
}
