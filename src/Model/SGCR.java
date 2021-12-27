package Model;
import Exception.*;
import Model.Client.IDevice;
import Model.Client.IDeviceCatalog;
import Model.Repair.IRepairCatalog;
import Model.Repair.IRepairPlan;
import Model.Worker.ITechnician;
import Model.Worker.IWorkersCatalog;
import Model.Worker.Receptionist;
import Model.Worker.Technician;

import java.time.LocalTime;
import java.util.ArrayList;

public class SGCR implements ISGCR{
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
    public void repairNextStep(int regCode, int techId, double cost, double time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsExecption {
        int stage = rcat.getRepairPlan(regCode).repairNext(cost,time);
        wcat.updateTechicianStep(regCode,stage,techId);
    }

    @Override
    public void evaluateCenterFunctioning(ArrayList<Technician> technicians, ArrayList<Receptionist> receptionists) throws WorkerDoesNotExist{
        for (Technician t: technicians) {
            t.getId();


        }
    }

    public int getTechPart(int techId) throws WorkerDoesNotExist {
        if(!wcat.existsTechnician(techId)) throw new WorkerDoesNotExist("Technician: " + techId + "does not exist");
        return ((ITechnician) wcat.getWorker(techId)).getTotalParticipations();
    }

}
