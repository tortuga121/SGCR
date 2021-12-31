package Model.Repair;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Exception.*;

public class RepairPlan implements IRepairPlan {
    private int regCode;
    private String generalDescription;
    private ArrayList<Stage> stages;
    private LocalDate deadline;
    private double price;
    private int current_stage;


    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public double getTotalCost() {
       return stages.stream().mapToDouble(Stage::getTotalCost).sum();
    }

    public RepairPlan(int regCode, String generalDescription, ArrayList<Stage> stages, double price, LocalDate deadline) {
        this.regCode = regCode;
        this.generalDescription = generalDescription;
        this.stages = stages;
        this.price = price;
        current_stage = 0;
    }
    public RepairPlan(RepairPlan rp) {
        this.regCode = rp.regCode;
        this.generalDescription = rp.generalDescription;
        this.stages = rp.stages.stream().map(Stage::clone).collect(Collectors.toCollection(ArrayList::new));
        this.deadline = rp.deadline;
        this.price = rp.price;
        this.current_stage = rp.current_stage;
    }

    @Override
    public int getRegCode() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public LocalDateTime totalTime() {
        return null;
    }


    @Override
    public IRepairPlan clone() {

        return new RepairPlan(this);
    }

    @Override
    public int repairNext(double cost, double time) throws NoMoreStepsException, OutOfbudgetException {
        if(getTotalCost() > price*1.2) throw new OutOfbudgetException("Out of budget");
        if(current_stage >= stages.size()) throw new NoMoreStepsException();
        Stage s = stages.get(current_stage);
        s.repairStep(cost, time);
        if(!s.hasSteps()) current_stage++;
        return current_stage-1;
    }

    @Override
    public Stage getStageCurrent() {
        return stages.get(current_stage);
    }



    @Override
    public double getTimeofRepair() {
       return stages.stream().mapToDouble(Stage::getTime).sum();
    }
    @Override
    public int compareTo(Object o) {
        RepairPlan rp = (RepairPlan) o;
        return this.getDeadline().compareTo(rp.getDeadline());
    }
}
