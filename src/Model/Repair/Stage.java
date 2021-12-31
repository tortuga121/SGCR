package Model.Repair;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Exception.NoMoreStepsException;

public class Stage {
    private ArrayList<Step> steps;
    private String description;
    private int current_step;

    public Stage(String description, ArrayList<Step> ss) {
        steps = ss.stream().map(Step::clone).collect(Collectors.toCollection(ArrayList::new));
        current_step = 0;
        this.description = description;
    }
    public Stage(Stage s) {
        steps = s.steps.stream().map(Step::clone).collect(Collectors.toCollection(ArrayList::new));
        description = s.description;
        current_step = s.current_step;
    }
    public Stage clone() {
        return new Stage(this);
    }
    public boolean hasSteps() {
        return current_step < steps.size();
    }
    public double getTime() {
        return steps.stream().mapToDouble(Step::getTime).sum();
    }
    public void repairStep(double cost, double time) throws NoMoreStepsException {
        if(current_step >= steps.size()) throw new NoMoreStepsException("No more stages or steps");
        Step s = steps.get(current_step);
        s.setCost(cost);
        s.setTime(time);
        current_step++;
    }
    public Step getCurrentStep() {
        return steps.get(current_step);
    }
    public double getTotalCost() {
        return  steps.stream().mapToDouble(Step::getCost).sum();
    }

}
