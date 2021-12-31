package Model.Repair;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Exception.*;
public interface IRepairPlan extends Comparable{
    int getRegCode();
    String getDescription();
    LocalDateTime totalTime();
    IRepairPlan clone();
    int repairNext(double cost, double time) throws NoMoreStepsException, OutOfbudgetException;
    Stage getStageCurrent();
    /**
     * Calcula a soma de toda de todos os tempos dos steps
     * @return tempo total de reparação
     */
    double getTimeofRepair();
    LocalDate getDeadline();
    /**
     * calcula o a soma do custo de todos so steps
     * @return preço total
     */
    double getTotalCost();
}
