package Model;

public interface IReceptionist {
    String getName();
    int getId();
    int totalDeliveries();
    int totalReceptions();
    void addDelivery();
    void addReception();
}
