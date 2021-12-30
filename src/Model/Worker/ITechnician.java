package Model.Worker;

public interface ITechnician  extends IWorker{
    void addParticipation(int regCode, int stage);
    int getTotalParticipations();
    void setAvailable(boolean available);
    boolean isAvailable();
}
