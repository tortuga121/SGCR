package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Step {
    private double cost;
    private String description;
    private LocalDateTime time;
    private ArrayList<Step> subSteps;
}
