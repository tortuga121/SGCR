package Model;
import Exception.*;
public class SGCR implements ISGCR {
    private IRecords rec;

    @Override
    public void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist {
        rec.addRequest(device, recepcionistId);
    }

    @Override
    public IDevice removeOldestBudgetRequest() throws DeviceNotFoundException{
        return rec.oldestRequest();
    }

    @Override
    public void sendRepairPlan(int techId, IRepairPlan rp, IDevice dev) throws WorkerDoesNotExist {
        if(!rec.existsTechnician(techId)) throw new WorkerDoesNotExist("Technician");
        rec.addRepairPlan(rp);
        //send email
    }
    
}
