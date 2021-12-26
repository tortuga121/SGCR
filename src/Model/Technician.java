package Model;

import java.util.HashSet;

public class Technician extends Worker{
    private HashSet<RepairPlan> repairs;

    public Technician(String name, int id) {
        super(name, id);
    }
}
