package Model.Repair;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import Exception.NoMoreStepsExecption;

public class Stage extends Step{
    private ArrayList<Step> steps;

    public Stage(double cost, String description, double hours, ArrayList<Step> ss) {
        super(cost, description, hours);
        steps = ss.stream().map(Step::clone).collect(Collectors.toCollection(ArrayList::new));
    }
    public Stage(Stage s) {
        super(s.getCost(), s.getDescription(), s.getTime());
        steps = s.steps.stream().map(Step::clone).collect(Collectors.toCollection(ArrayList::new));
    }
    public Stage clone() {
        return new Stage(this);
    }
    public boolean hasSteps() {
        return steps.size() > 0;
    }

    public void repairStep(double cost, double time) throws NoMoreStepsExecption {
        Optional<Step> optionalStep = steps.stream().filter(Step::isUndone).findFirst();
        if(optionalStep.isEmpty()) throw new NoMoreStepsExecption();
        Step s = optionalStep.get();
        s.setCost(cost);
        s.setTime(time);
        s.done();
    }

    public void calculate_costAndTime() {
        setCost(steps.stream().mapToDouble(Step::getCost).sum());
        setTime(steps.stream().mapToDouble(Step::getTime).sum());
    }

}
