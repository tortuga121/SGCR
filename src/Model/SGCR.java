package Model;
import Exception.*;
public class SGCR  implements ISGCR{
    private IWorkersCatalog wcat;
    private IDeviceCatalog dcat;
    private IRepairCatalog rcat;

    public void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist {
        dcat.addRequest(device);
        wcat.incRecepServiceCount(recepcionistId);
    }

    @Override
    public void refuseBudget(int regCode) throws DeviceNotFoundException {
        rcat.unaproveBudget(regCode);
        dcat.addToPickup(regCode);
    }

    @Override
    public void sugestRepairPlan(IRepairPlan rp) throws DeviceNotFoundException {
        if(dcat.getOldestRequest().getRegCode() != rp.getRegCode())
            throw new DeviceNotFoundException("Not the oldest device" + rp.getRegCode());
        rcat.addRepairPlan(rp);
        dcat.popOldestRequest();

    }

}
