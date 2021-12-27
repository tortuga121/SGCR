package Model;
import Exception.*;
import Model.Client.IDevice;
import Model.Client.IDeviceCatalog;
import Model.Repair.IRepairCatalog;
import Model.Repair.IRepairPlan;
import Model.Worker.IWorkersCatalog;

import java.time.LocalTime;

public class SGCR  implements ISGCR{
    private IWorkersCatalog wcat;
    private IDeviceCatalog dcat;
    private IRepairCatalog rcat;

    public void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist, InvalidRegistrationCodeException {
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

    @Override
    public IRepairPlan getMostUrgentRepair() throws NoRepairException {
        return rcat.getRepairPlan(rcat.mostUrgentRepair());
    }

    @Override
    public void repairNextStep(int regCode, int techId, double cost, LocalTime time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsExecption {
        int stage = rcat.getRepairPlan(regCode).repairNext(cost,time);
        wcat.updateTechicianStep(regCode,stage,techId);
    }

}
