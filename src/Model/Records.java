package Model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Records implements IRecords {
    private Queue<IDevice> budgetRequest;
    private Queue<IRepairPlan> toRepair;
    private HashMap<Integer,Worker> workers;
    private HashMap<Integer,String> logins;

    public Records(HashMap<Integer, Worker> workers, HashMap<Integer, String> logins) {
        budgetRequest = new LinkedList<>();
        toRepair = new LinkedList<>();
        this.workers = workers;
        this.logins = logins;
    }

    @Override
    public IDevice oldestRequest() {
        return budgetRequest.poll();
    } // null no caso de nao haver mais requests

    @Override
    public void addRequest(IDevice dev) {
        budgetRequest.add(dev.clone());
    }

    @Override
    public IRepairPlan oldestRepair() {
       return toRepair.poll();
    } // null no caso de não existir

    @Override
    public void addRepairPlan(IRepairPlan rp) {
        toRepair.add(rp.clone());
    }

    @Override
    public boolean login(int id, String password) {
         return logins.containsKey(id) && logins.get(id).equals(password);
    }

    @Override // EXCEPTION CASO WORKER NAO EXISTA
    public Worker getWorker(int id) {
       return workers.get(id).clone();
    }
}
