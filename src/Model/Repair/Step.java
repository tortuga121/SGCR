package Model.Repair;

import java.time.LocalTime;

public class Step {
    private boolean done;
    private double cost;
    private String description;
    private double hours;

    public Step( double cost, String description, double hours) {
        this.cost = cost;
        this.description = description;
        this.hours = hours;
    }

    public Step(Step s) {
        this.cost = s.cost;
        this.description = s.description;
        this.hours = s.hours;
        this.done = s.done;
    }

    public Step clone() {
        return new Step(this);
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setTime(double time) {
        this.hours = time;
    }

    public boolean isUndone() {
        return !done;
    }
    public void done() {
        done = true;
    }
    public double getCost() {
        return cost;
    }
    public double getTime() {
        return hours;
    }
    public String getDescription() {
        return description;
    }

}
