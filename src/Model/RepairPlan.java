package Model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RepairPlan implements IRepairPlan {
    private int regCode;
    private String generalDescription;
    private ArrayList<Step> steps;

    public RepairPlan(int regCode, String generalDescription, ArrayList<Step> steps) {
        this.regCode = regCode;
        this.generalDescription = generalDescription;
        this.steps = steps;
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
        return new RepairPlan(regCode,
                generalDescription,
                new ArrayList<>( this.steps.stream().map(Step::clone).collect(Collectors.toList())));
    }

}
