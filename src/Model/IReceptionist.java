package Model;

public interface IReceptionist extends IWorker{
    int totalDeliveries();
    int totalReceptions();
    void addDelivery();
    void addReception();

    IDevice budgetRequest(int regCode, String nif, String descr);

}
