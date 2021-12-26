package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
// CLONE PODE SER INFINITO TESTAR
public class Step {
    private double cost;
    private String description;
    private LocalDateTime time;
    private ArrayList<Step> subSteps;

    public Step(double cost, String description, LocalDateTime time, ArrayList<Step> subSteps) {
        this.cost = cost;
        this.description = description;
        this.time = time;
        this.subSteps = subSteps;
    }

    public double getCost() {
        return cost;
    }


    public LocalDateTime getTime() {
        return time;
    }


    public String getDescription() {
       return description;
    }
    public Step clone() {
        return new Step(cost,
                description,
                time, subSteps == null ?
                new ArrayList<>() :
                new ArrayList<>( subSteps.stream().map(Step::clone).collect(Collectors.toList())));
    }
}
