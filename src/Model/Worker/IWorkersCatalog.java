package Model.Worker;
import Exception.*;

import java.util.Map;

public interface IWorkersCatalog {
    void incRecepServiceCount(int id) throws WorkerDoesNotExist;
    void updateTechicianStep(int regCode, int satge, int techId) throws WorkerDoesNotExist;
    IWorker getWorker(int id) throws WorkerDoesNotExist;
    boolean existsTechnician(int id);
    Map<Integer, Integer> num_rep_technicians();
}
