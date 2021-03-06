package Model;
import Exception.*;
import Model.Devices.*;
import Model.Repair.*;
import Model.Worker.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;


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
    public void acceptBudget(int regCode) throws DeviceNotFoundException {
        rcat.approveBudget(regCode);
    }

    @Override
    public void sugestRepairPlan(IRepairPlan rp) throws DeviceNotFoundException {
        if(dcat.getOldestRequest() != rp.getRegCode())
            throw new DeviceNotFoundException("Not the oldest device is" + rp.getRegCode() + " shoud be " + dcat.getOldestRequest());
        rcat.addRepairPlan(rp);
        dcat.popOldestRequest();
    }

    @Override
    public IRepairPlan getMostUrgentRepair() throws NoRepairException {
        return rcat.getRepairPlan(rcat.mostUrgentRepair());
    }

    @Override
    public void repairNextStep(int regCode, int techId, double cost, double time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsException, OutOfbudgetException {
        int stage = rcat.getRepairPlan(regCode).repairNext(cost,time);
        wcat.updateTechicianStep(regCode,stage,techId);
    }
    public void suspendRepairPlan(int regcode) {

    }

    @Override
    public Map<Integer,Double> evaluateCenterFunctioning(){
        return wcat.num_rep_technicians();
    }

    @Override
    public void makeMonthEval(Year y, Month m, String eval, int managerId) throws WorkerDoesNotExist {
        if(!wcat.existsManager(managerId)) throw new WorkerDoesNotExist("Manager: " + managerId + "does not exist");
        ((IManager) wcat.getWorker(managerId)).addEval(y,m,eval);
    }

    @Override
    public double makeExpressRepair(String type, int techId, int recepId) throws WorkerDoesNotExist {
        rcat.addExpressrepair(type);
        wcat.updateTechicianStep(-1,-1,techId);
        wcat.incRecepDelivCount(recepId);
        if(ExpressRepair.expressTypes.containsKey(type))
            return ExpressRepair.expressTypes.get(type);
        else throw new WorkerDoesNotExist("Type doesnt exist");
    }

    @Override
    public Class<?> getWorkerType(int id) throws WorkerDoesNotExist {
       return wcat.getWorker(id).getClass();
    }

    @Override
    public boolean login(int id, String pass) throws WorkerDoesNotExist {
        if(!wcat.existsWorker(id)) throw new WorkerDoesNotExist("Worker: " + id + "does not exist");
        return wcat.login(id,pass);
    }

    @Override
    public int getBudgetRequest() throws DeviceNotFoundException {
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
    public Set<Integer> getClientDevices(String nif) throws DeviceNotFoundException {
        return dcat.getdevicesbyNif(nif);
    }
    public Set<Integer> getDeliveredDevicesbyNif(String nif) throws DeviceNotFoundException {
        return dcat.getDeliveredDevicesbyNif(nif);
    }

    @Override
    public void devicePickup(int id, int recepid) throws DeviceNotFoundException, WorkerDoesNotExist {
        deliverDevice(recepid);
        dcat.pickupDevice(id);
    }

    public int generateNewregistrationCode() {
        Random rand = new Random();
        int randomNum = rand.nextInt((10000 - 1) + 1) + 1;
        while(dcat.existsDevice(randomNum))
            randomNum = rand.nextInt((10000 - 1) + 1) + 1;
        return randomNum;
    }

    @Override
    public int availableTechnician() throws WorkerDoesNotExist {
        return wcat.getAvailableTech();
    }

    @Override
    public Set<String> getExpressTypes() {
        return ExpressRepair.expressTypes.keySet();
    }

    @Override
    public IDevice getDevice(int id) throws DeviceNotFoundException {
        return dcat.getdevice(id).clone();
    }

    private double getDurationAverage(int id) {
        try {
            OptionalDouble optionalDouble = ((ITechnician) wcat.getWorker(id)).getPlans().stream()
                    .mapToDouble(regcode -> {
                        try {
                            return rcat.getRepairPlan(regcode).getTimeofRepair();
                        } catch (NoRepairException ignored) {
                            return 0;
                        }
                    })
                    .average();
            if (optionalDouble.isPresent()) return optionalDouble.getAsDouble();
        } catch (WorkerDoesNotExist e){
            return 0;
        }
        return 0;
    }
    private double getDeviationAverage(int id) {
        double deviation = getDurationAverage(id);
        try {
            OptionalDouble optionalDouble = ((ITechnician) wcat.getWorker(id)).getPlans().stream()
                    .mapToDouble(regcode -> {
                        try {
                            return Math.abs(rcat.getRepairPlan(regcode).getTimeofRepair() - deviation);
                        } catch (NoRepairException ignored) {
                            return 0;
                        }
                    })
                    .average();
            if (optionalDouble.isPresent()) return optionalDouble.getAsDouble();
        } catch (WorkerDoesNotExist e){
            return 0;
        }
        return 0;
    }

    public HashMap<Integer, ArrayList<Double>> techstats() {
        HashMap<Integer, ArrayList<Double>> ans = new HashMap<>();
        wcat.getWorkers().stream()
                .filter(w -> w.getClass().equals(Technician.class))
                .forEach(w ->{
                    ArrayList<Double> arr =  new ArrayList<>();
                    arr.add(((Technician) w).getTotalParticipations());
                    arr.add(getDurationAverage(w.getId()));
                    arr.add(getDeviationAverage(w.getId()));
                    ans.put(w.getId(),arr);
                });
        return ans;
    }

    @Override
    public HashMap<Integer, ArrayList<Integer>> recepStats() {
       return wcat.recepStats();
    }

    @Override
    public HashMap<Integer, HashMap<Integer, HashSet<Integer>>> techDetailStats() {
        return wcat.getParticipations();
    }

    @Override
    public void setAvailable(int techID, boolean av) throws WorkerDoesNotExist {
        if(wcat.existsTechnician(techID))
            ((Technician)wcat.getWorker(techID)).setAvailable(av);
        else throw new WorkerDoesNotExist();
    }


}
