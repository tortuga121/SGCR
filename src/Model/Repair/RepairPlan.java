package Model.Repair;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import Exception.NoMoreStepsExecption;

public class RepairPlan implements IRepairPlan {
    private int regCode;
    private String generalDescription;
    private ArrayList<Stage> stages;
    private LocalDateTime deadline;
    private double price;


    public LocalDateTime getDeadline() {
        return deadline;
    }

    public RepairPlan(int regCode, String generalDescription, ArrayList<Stage> stages, double price) {
        this.regCode = regCode;
        this.generalDescription = generalDescription;
        this.stages = stages;
        this.price = price;
    }
    public RepairPlan(RepairPlan rp) {
        this.regCode = rp.regCode;
        this.generalDescription = rp.generalDescription;
        this.stages = rp.stages.stream().map(Stage::clone).collect(Collectors.toCollection(ArrayList::new));
        this.deadline = rp.deadline;
        this.price = rp.price;
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
    public int repairNext(double cost, double time) throws NoMoreStepsExecption {
        Optional<Stage> optionalStage = Optional.empty();
        int i;
        for (i = 0; i < stages.size(); i++) {
            Stage s = stages.get(i);
            if (!s.isUndone()) {
                optionalStage = Optional.of(s);
                break;
            }
        }

        if (optionalStage.isEmpty()) throw new NoMoreStepsExecption();
        Stage s = optionalStage.get();
        if(s.hasSteps()) {
            s.repairStep(cost, time);
            if (!s.hasSteps()) {
                s.calculate_costAndTime();
            }
        } else {
            s.setTime(time);
            s.setCost(cost);
        }
        return i;
    }

    @Override
    public double getTimeofRepair() {
       return stages.stream().mapToDouble(Step::getTime).sum();
    }
    @Override
    public int compareTo(Object o) {
        RepairPlan rp = (RepairPlan) o;
        return this.getDeadline().compareTo(rp.getDeadline());
    }
}
