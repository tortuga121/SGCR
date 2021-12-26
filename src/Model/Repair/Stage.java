package Model.Repair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import Exception.NoMoreStepsExecption;

public class Stage  extends Step{
    private ArrayList<Step> steps;
    public boolean hasSteps() {
        return steps.size() > 0;
    }
    public void repairStep(double cost, LocalTime time) throws NoMoreStepsExecption {
        Optional<Step> optionalStep = steps.stream().filter(Step::isUndone).findFirst();
        if(optionalStep.isEmpty()) throw new NoMoreStepsExecption();
        Step s = optionalStep.get();
        s.setCost(cost);
        s.setTime(time);
        s.done();
    }

}
