package Model.Repair;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import Exception.NoMoreStepsException;

public class RepairPlan implements IRepairPlan {
    private int regCode;
    private String generalDescription;
    private ArrayList<Stage> stages;
    private LocalDateTime deadline;
    private double price;
    private int current_stage;


    public LocalDateTime getDeadline() {
        return deadline;
    }

    public RepairPlan(int regCode, String generalDescription, ArrayList<Stage> stages, double price) {
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
    public double totalCost() {
        return 0;
    }

    @Override
    public IRepairPlan clone() {

        return new RepairPlan(this);
    }

    @Override
    public int repairNext(double cost, double time) throws NoMoreStepsException {
       if(current_stage >= stages.size()) throw new NoMoreStepsException();
        Stage s = stages.get(current_stage);
        s.repairStep(cost, time);
        current_stage++;
        return current_stage-1;
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
