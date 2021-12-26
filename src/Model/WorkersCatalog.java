package Model;


import java.time.LocalDate;
import java.util.*;

import Exception.*;

public class WorkersCatalog {

    private HashMap<Integer,Worker> workers;
    private HashMap<Integer,String> logins;


    public boolean login(int id, String password) {
        return logins.containsKey(id) && logins.get(id).equals(password);
    }

    public Worker getWorker(int id) throws WorkerDoesNotExist {
        if(!existsWorker(id)) throw new WorkerDoesNotExist();
        return workers.get(id).clone();
    }

    public boolean existsReceptionist(int id) {
        return existsWorker(id) && workers.get(id).getClass() == Receptionist.class;
    }

    public boolean existsManager(int id) {
        return existsWorker(id) && workers.get(id).getClass() == Manager.class;
    }

    public boolean existsTechnician(int id) {
        return existsWorker(id) && workers.get(id).getClass() == Technician.class;
    }

    public boolean existsWorker(int id) {
        return workers.containsKey(id);
    }
}
