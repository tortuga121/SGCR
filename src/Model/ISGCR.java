package Model;
import Exception.*;
import Model.Client.IDevice;
import Model.Repair.IRepairPlan;
import java.util.Map;

public interface ISGCR {
    void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist, InvalidRegistrationCodeException;
    void refuseBudget(int regCode) throws DeviceNotFoundException;
    void sugestRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;
    IRepairPlan getMostUrgentRepair() throws NoRepairException;
    void repairNextStep(int regCode, int techId, double cost, double time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsExecption;
    Map<Integer,Integer> evaluateCenterFunctioning () throws WorkerDoesNotExist;
    int getTechPart(int techId) throws WorkerDoesNotExist;

}
