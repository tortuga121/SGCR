package Model.Repair;

import java.time.LocalTime;

public class Step {
    private boolean done;
    private double cost;
    private String description;
    private LocalTime time;

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
    public LocalTime getTime() {
        return time;
    }
    public String getDescription() {
        return description;
    }

}
