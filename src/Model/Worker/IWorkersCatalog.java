package Model.Worker;
import Exception.*;
public interface IWorkersCatalog {
    void incRecepServiceCount(int id) throws WorkerDoesNotExist;

}
