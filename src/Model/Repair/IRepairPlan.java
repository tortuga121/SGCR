package Model.Repair;

import java.time.LocalDateTime;
import java.time.LocalTime;
import Exception.NoMoreStepsExecption;
public interface IRepairPlan extends Comparable{
    int getRegCode();
    String getDescription();
    LocalDateTime totalTime();
    double totalCost();
    IRepairPlan clone();
    int repairNext(double cost, double time) throws NoMoreStepsExecption;
    double getTimeofRepair();
    LocalDateTime getDeadline();
}
