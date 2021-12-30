package Model;
import Exception.*;
import Model.Devices.*;
import Model.Repair.*;
import Model.Worker.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class SGCR implements ISGCR{
    private IWorkersCatalog wcat;
    private IDeviceCatalog dcat;
    private IRepairCatalog rcat;

    public SGCR() {
        wcat = new WorkersCatalog();
        dcat = new DeviceCatalog();
        rcat = new RepairCatalog();
    }

    public SGCR(IWorkersCatalog wcat, IDeviceCatalog dcat, IRepairCatalog rcat) {
        this.wcat = wcat;
        this.dcat = dcat;
        this.rcat = rcat;
    }

    @Override
    public void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist, InvalidRegistrationCodeException {
        dcat.addRequest(device);
        wcat.incRecepServiceCount(recepcionistId);
    }

    @Override
    public LocalDate refuseBudget(int regCode) throws DeviceNotFoundException, WorkerDoesNotExist {
        rcat.unaproveBudget(regCode);
        return dcat.addToPickup(regCode);
    }

    @Override
    public void aceptBudget(int regCode) throws DeviceNotFoundException {
        rcat.approveBudget(regCode);
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
    public void repairNextStep(int regCode, int techId, double cost, double time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsException {
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

    @Override
    public Class<?> getWorkerType(int id) throws WorkerDoesNotExist {
       return wcat.getWorker(id).getClass();
    }

    @Override
    public boolean login(int id, String pass) throws WorkerDoesNotExist {
        return wcat.login(id,pass);
    }

    @Override
    public IDevice getBudgetRequest() throws DeviceNotFoundException {
        return dcat.getOldestRequest();
    }

    @Override
    public void checkForDealines() {
        dcat.checkforAbandonedDevices();
        rcat.checkForOutdated();
    }

    @Override
    public void deliverDevice(int recepId) throws WorkerDoesNotExist {
        wcat.incRecepDelivCount(recepId);
    }

    @Override
    public void addWorker(String name, int id, Class<?> c, String password) {
        if(c.equals(Manager.class)) wcat.addManager(name,id);
        else if(c.equals(Technician.class)) wcat.addTechnician(name,id);
        else if(c.equals(Receptionist.class)) wcat.addReceptionist(name,id);
        wcat.addLogin(password,id);
    }

    @Override
    public Set<Integer> getClientDevices(String nif) {
        return dcat.getdevicesbyNif(nif);
    }


}
