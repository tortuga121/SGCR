package Model.Worker;

public interface ITechnician  extends IWorker{
    /**
     * Método que adiciona uma reparação ao Set de reparações de um técnico
     * @param regCode código do equipamento
     * @param stage etapa da reparação que o técnico vai realizar nesse equipamento
     */
    void addParticipation(int regCode, int stage);
    int getTotalParticipations();
    void setAvailable(boolean available);
    boolean isAvailable();
}
