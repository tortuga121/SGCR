package Model;
import Exception.*;
public interface IRecords {
    IDevice oldestRequest() throws DeviceNotFoundException;
    void addRequest(IDevice dev, int recepId) throws WorkerDoesNotExist;
    IRepairPlan oldestRepair();
    void addRepairPlan(IRepairPlan rp);
    boolean login(int id, String password);
    Worker getWorker(int id) throws WorkerDoesNotExist;
    boolean existsReceptionist(int id);
    boolean existsManager(int id);
    boolean existsTechnician(int id);
}
