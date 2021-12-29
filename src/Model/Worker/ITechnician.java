package Model.Worker;

public interface ITechnician  extends IWorker{
    void addParticipation(int regCode, int stage);
    int getTotalParticipations();
    void setWorking(boolean working);
    boolean isWorking();
}
