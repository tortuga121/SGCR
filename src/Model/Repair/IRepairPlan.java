package Model.Repair;

import java.time.LocalDateTime;
import java.time.LocalTime;
import Exception.NoMoreStepsExecption;
public interface IRepairPlan{
    int getRegCode();
    String getDescription();
    LocalDateTime totalTime();
    double totalCost();
    IRepairPlan clone();
    void repairNext(double cost, LocalTime time) throws NoMoreStepsExecption;
}
