package Model;


import java.util.*;

import Exception.*;

public class Records implements IRecords {
    private Queue<IDevice> budgetRequest;
    private TreeMap<Integer,IRepairPlan> toRepair;
    private HashMap<Integer, IDevice> toPickup;
    private HashSet<Integer> toApprove;
    private HashMap<Integer,IRepairPlan> repairs;
    private HashMap<Integer,Worker> workers;
    private HashMap<Integer,String> logins;

    public Records(HashMap<Integer, Worker> workers, HashMap<Integer, String> logins) {
        budgetRequest = new LinkedList<>();
        toRepair = new TreeMap<>();
        this.workers = workers;
        this.logins = logins;
    }

    @Override
    public IDevice oldestRequest() throws DeviceNotFoundException {
       if(budgetRequest.size() == 0)
            throw new DeviceNotFoundException("No more budgets requests");
       else return budgetRequest.poll();
    }

    @Override
    public void addRequest(IDevice dev, int recepId) throws WorkerDoesNotExist {
        budgetRequest.add(dev.clone());
        if(!existsReceptionist(recepId)) throw new WorkerDoesNotExist("No Recepcionist matches the gived ID");
    }

    @Override
    public IRepairPlan oldestRepair() {
       return toRepair.firstEntry().getValue();
    } // null no caso de não existir

    @Override
    public void addRepairPlan(IRepairPlan rp) {
        repairs.put(rp.getRegCode(),rp.clone());
        toApprove.add(rp.getRegCode());
    }

    @Override
    public boolean login(int id, String password) {
         return logins.containsKey(id) && logins.get(id).equals(password);
    }

    @Override // EXCEPTION CASO WORKER NAO EXISTA
    public Worker getWorker(int id) throws WorkerDoesNotExist {
        if(!existsWorker(id)) throw new WorkerDoesNotExist();
       return workers.get(id).clone();
    }

    @Override
    public boolean existsReceptionist(int id) {
        return existsWorker(id) && workers.get(id).getClass() == Receptionist.class;
    }

    @Override
    public boolean existsManager(int id) {
        return existsWorker(id) && workers.get(id).getClass() == Manager.class;
    }

    @Override
    public boolean existsTechnician(int id) {
        return existsWorker(id) && workers.get(id).getClass() == Technician.class;
    }

    public boolean existsWorker(int id) {
        return workers.containsKey(id);
    }
}
