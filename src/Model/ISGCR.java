package Model;
import Exception.*;
import Model.Client.IDevice;
import Model.Repair.IRepairPlan;
import Model.Worker.Receptionist;
import Model.Worker.Technician;

import java.time.LocalTime;
import java.util.ArrayList;

public interface ISGCR {
    void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist, InvalidRegistrationCodeException;
    void refuseBudget(int regCode) throws DeviceNotFoundException;
    void sugestRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;
    IRepairPlan getMostUrgentRepair() throws NoRepairException;
    void repairNextStep(int regCode, int techId, double cost, double time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsExecption;
<<<<<<< HEAD
    void evaluateCenterFunctioning (ArrayList<Technician> technicians, ArrayList<Receptionist> receptionists) throws WorkerDoesNotExist;
=======
    int getTechPart(int techId) throws WorkerDoesNotExist;
>>>>>>> 2937800669990f6ecf4605a53de96975b83a4997
}
