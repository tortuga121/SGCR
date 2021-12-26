package Model;
import Exception.*;
public class SGCR  {
    private IWorkersCatalog wcat;
    private IDeviceCatalog dcat;
    private IRepairCatalog rcat;

    public void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist {
        dcat.addRequest(device);
        wcat.incRecepServiceCount(recepcionistId);
    }

}
