package Model;

import java.time.LocalDateTime;

public interface IRepairPlan{
    int getRegCode();
    String getDescription();
    LocalDateTime totalTime();
    double totalCost();
    IRepairPlan clone();
}
