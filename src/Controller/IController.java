package Controller;

public interface IController {
    void exec();
    void execReceptionist(int workerID);
    void execTechnician(int workerID);
    void execManager(int workerID);
}
