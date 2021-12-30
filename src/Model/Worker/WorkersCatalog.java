package Model.Worker;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Exception.*;

public class WorkersCatalog implements IWorkersCatalog{

    private HashMap<Integer,IWorker> workers;
    private HashMap<Integer,String> logins;

    public WorkersCatalog() {
        workers = new HashMap<>();
        logins = new HashMap<>();
    }

    @Override
    public Map<Integer, Integer> num_rep_technicians() {
      return workers.values().stream()
              .filter(e -> e.getClass() == Technician.class)
              .collect(Collectors.toMap
                      (IWorker::getId,
                       e -> ((ITechnician) workers.get(e)).getTotalParticipations()));
    }
    @Override
    public boolean login(int id, String password) {
        return logins.containsKey(id) && logins.get(id).equals(password);
    }
    @Override
    public IWorker getWorker(int id) throws WorkerDoesNotExist {
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
   @Override
    public boolean existsWorker(int id) {
        return workers.containsKey(id);
    }

    @Override
    public void incRecepServiceCount(int id) throws WorkerDoesNotExist {
        if(existsReceptionist(id))
            ((IReceptionist )workers.get(id)).addReception();
        else throw new WorkerDoesNotExist("Receptionist " + id);
    }
    public void incRecepDelivCount(int id) throws WorkerDoesNotExist {
        if(existsReceptionist(id))
            ((IReceptionist )workers.get(id)).addDelivery();
        else throw new WorkerDoesNotExist("Receptionist " + id);
    }

    @Override
    public void updateTechicianStep(int regCode, int stage, int techId) throws WorkerDoesNotExist{
        if(existsTechnician(techId))
            ((ITechnician)workers.get(techId)).addParticipation(regCode,stage);
        else throw new WorkerDoesNotExist("Technician: " + regCode);
    }


    @Override
    public void addManager(String name, int id, String password) {
        workers.put(id,new Manager(name,id));
    }

    @Override
    public void addReceptionist(String name, int id, String password) {
        workers.put(id,new Receptionist(name,id));
    }

    @Override
    public void addTechnician(String name, int id, String password) {
        workers.put(id,new Technician(name,id));
    }

    @Override
    public void addLogin(String pass, int id) {
        logins.put(id,pass);
    }

}
