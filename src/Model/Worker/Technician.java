package Model.Worker;

import java.util.HashMap;
import java.util.HashSet;

public class Technician extends Worker implements ITechnician{
    private HashMap<Integer, HashSet<Integer>> participations;
    private boolean working;

    public void addParticipation(int regCode, int stage) {
        if(!participations.containsKey(regCode))
            participations.put(regCode, new HashSet<>());
        participations.get(regCode).add(stage);
    }
    //TODO
    @Override
    public int getTotalParticipations() {
        return 0;
    }

    @Override
    public boolean isWorking() {
        return working;
    }

    @Override
    public void setWorking(boolean working) {
        this.working = working;
    }

    public Technician(String name, int id) {
        super(name, id);
    }
}
