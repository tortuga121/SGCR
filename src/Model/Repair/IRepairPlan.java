package Model.Repair;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Exception.*;
public interface IRepairPlan extends Comparable{
    int getRegCode();
    String getDescription();
    LocalDateTime totalTime();
    double totalCost();
    IRepairPlan clone();
    int repairNext(double cost, double time) throws NoMoreStepsException, OutOfbudgetException;
    double getTimeofRepair();
    LocalDate getDeadline();
    double getTotalCost();
}
