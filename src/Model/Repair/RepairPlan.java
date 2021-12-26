package Model.Repair;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import Exception.NoMoreStepsExecption;

public class RepairPlan implements IRepairPlan {
    private int regCode;
    private String generalDescription;
    private ArrayList<Stage> stages;

    public RepairPlan(int regCode, String generalDescription, ArrayList<Stage> stages) {
        this.regCode = regCode;
        this.generalDescription = generalDescription;
        this.stages = stages;
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
        //TODO
        return null;
    }

    @Override
    public void repairNext(double cost, LocalTime time) throws NoMoreStepsExecption {
        Optional<Stage> optionalStage = stages.stream().filter(Stage::isUndone).findFirst();
        if(optionalStage.isEmpty()) throw new NoMoreStepsExecption();
        Stage s = optionalStage.get();
        if(s.hasSteps()) s.repairStep(cost,time);
        else if(!s.hasSteps()) {
            s.setCost(cost);
            s.setTime(time);
            s.done();
        }
    }

}
