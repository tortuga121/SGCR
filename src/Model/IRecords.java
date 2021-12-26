package Model;

public interface IRecords {
    IDevice oldestRequest();
    void addRequest(IDevice dev);
    IRepairPlan oldestRepair();
    void addRepairPlan(IRepairPlan rp);
    boolean login(int id, String password);
    Worker getWorker(int id);
}
