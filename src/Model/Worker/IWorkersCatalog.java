package Model.Worker;
import Exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public interface IWorkersCatalog {
    void incRecepServiceCount(int id) throws WorkerDoesNotExist;
    void updateTechicianStep(int regCode, int satge, int techId) throws WorkerDoesNotExist;
    IWorker getWorker(int id) throws WorkerDoesNotExist;
    boolean existsTechnician(int id);
    Map<Integer, Double> num_rep_technicians();
    boolean existsWorker(int id);
    boolean existsManager(int id);
    boolean login(int id, String password);
    boolean existsReceptionist(int id);
    void incRecepDelivCount(int id) throws WorkerDoesNotExist;
    void addManager(String name, int id);
    void addReceptionist(String name, int id);
    void addTechnician(String name, int id);
    void addLogin(String pass, int id);
    int getAvailableTech() throws WorkerDoesNotExist;
    ArrayList<IWorker> getWorkers();
    HashMap<Integer,ArrayList<Integer>> recepStats();
    HashMap<Integer,HashMap<Integer, HashSet<Integer>>> getParticipations();
}
