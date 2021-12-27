package Model.Repair;

import java.time.LocalTime;

public class Step {
    private boolean done;
    private double cost;
    private String description;
    private double hours;

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
