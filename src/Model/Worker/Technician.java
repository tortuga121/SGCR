package Model.Worker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Technician extends Worker implements ITechnician{
    private HashMap<Integer, HashSet<Integer>> participations;

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

    public Technician(String name, int id) {
        super(name, id);
    }
}
