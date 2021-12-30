package Model;
import Exception.*;
import Model.Devices.IDevice;
import Model.Repair.IRepairPlan;

import java.time.Month;
import java.time.Year;
import java.util.Map;

public interface ISGCR {
    void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist, InvalidRegistrationCodeException;
    void refuseBudget(int regCode) throws DeviceNotFoundException;
    void sugestRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;
    IRepairPlan getMostUrgentRepair() throws NoRepairException;
    void repairNextStep(int regCode, int techId, double cost, double time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsException;
    Map<Integer,Integer> evaluateCenterFunctioning () throws WorkerDoesNotExist;
    void makeMonthEval(Year y, Month m , String eval, int manegerId) throws WorkerDoesNotExist;
    void makeExpressRepair(String type, int regcode, String desrp, int techId) throws DeviceNotFoundException, WorkerDoesNotExist;

    Class<?> getWorkerType(int id) throws WorkerDoesNotExist;
    boolean login(int id, String pass) throws WorkerDoesNotExist;
}
