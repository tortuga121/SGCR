package Model;
import Exception.*;
public class SGCR implements ISGCR {
    private IWorkersCatalog rec;
    private IDeviceCatalog dc;

    @Override
    public void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist {
        dc.addRequest(device, recepcionistId);
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

    @Override
    public void abandonDevice(int regCode) throws DeviceNotFoundException {
        dc.abandonDevice(regCode);
    }

   /* @Override
    public void rejectBudget(int regCode) throws DeviceNotFoundException, NoRepairException {
        rec.rejectBudget(regCode);
    }*/


}
