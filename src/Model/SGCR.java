package Model;
import Exception.*;
import Model.Client.IDevice;
import Model.Client.IDeviceCatalog;
import Model.Repair.IRepairCatalog;
import Model.Repair.IRepairPlan;
import Model.Worker.*;

import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public Map<Integer,Integer> evaluateCenterFunctioning(){
        return wcat.num_rep_technicians();
    }

    @Override
    public void makeMonthEval(Year y, Month m, String eval, int managerId) throws WorkerDoesNotExist {
        if(!wcat.existsManager(managerId)) throw new WorkerDoesNotExist("Manager: " + managerId + "does not exist");
        ((IManager) wcat.getWorker(managerId)).addEval(y,m,eval);
    }

    @Override
    public void makeExpressRepair(String type, int regcode, String desrp, int techId) throws DeviceNotFoundException, WorkerDoesNotExist {
        if(!dcat.existsDevice(regcode)) throw new DeviceNotFoundException("Device: " + regcode + "does not exist");
        rcat.addExpressrepair(type,regcode,desrp);
        wcat.updateTechicianStep(regcode,-1,techId);

    }

}
