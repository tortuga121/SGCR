package Model.Repair;

import java.time.LocalDateTime;

import Exception.NoMoreStepsException;
public interface IRepairPlan extends Comparable{
    int getRegCode();
    String getDescription();
    LocalDateTime totalTime();
    double totalCost();
    IRepairPlan clone();
    int repairNext(double cost, double time) throws NoMoreStepsException;
    double getTimeofRepair();
    LocalDateTime getDeadline();
}
