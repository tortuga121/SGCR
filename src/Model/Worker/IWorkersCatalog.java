package Model.Worker;
import Exception.*;
public interface IWorkersCatalog {
    void incRecepServiceCount(int id) throws WorkerDoesNotExist;
    void updateTechicianStep(int regCode, int satge, int techId) throws WorkerDoesNotExist;

}
