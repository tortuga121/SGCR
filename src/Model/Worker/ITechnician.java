package Model.Worker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface ITechnician  extends IWorker{
    void addParticipation(int regCode, int stage);
    Double getTotalParticipations();
    void setAvailable(boolean available);
    boolean isAvailable();
    Set<Integer> getPlans();
    HashMap<Integer,HashSet<Integer>> getParticipations();
}
