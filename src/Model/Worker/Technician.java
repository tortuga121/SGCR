package Model.Worker;

import java.util.HashMap;
import java.util.HashSet;

public class Technician extends Worker implements ITechnician{
    private HashMap<Integer, HashSet<Integer>> participations;
    private boolean working;

    public Technician(String nome, int id) {
        super(nome,id);
        working = false;
        participations = new HashMap<>();
    }

    public void addParticipation(int regCode, int stage) {
        if(!participations.containsKey(regCode))
            participations.put(regCode, new HashSet<>());
        participations.get(regCode).add(stage);
    }
    @Override
    public int getTotalParticipations() {
        return participations.size();
    }

    @Override
    public boolean isWorking() {
        return working;
    }

    @Override
    public void setWorking(boolean working) {
        this.working = working;
    }

}
