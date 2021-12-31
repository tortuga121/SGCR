package Model.Worker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface ITechnician  extends IWorker{
    /**
     * Método que adiciona uma reparação ao Set de reparações de um técnico
     * @param regCode código do equipamento
     * @param stage etapa da reparação que o técnico vai realizar nesse equipamento
     */
    void addParticipation(int regCode, int stage);
    Double getTotalParticipations();
    void setAvailable(boolean available);
    boolean isAvailable();
    Set<Integer> getPlans();
    HashMap<Integer,HashSet<Integer>> getParticipations();
}
