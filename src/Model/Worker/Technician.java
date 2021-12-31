package Model.Worker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class Technician extends Worker implements ITechnician{
    private HashMap<Integer, HashSet<Integer>> participations;
    private boolean available;

    public Technician(String nome, int id) {
        super(nome,id);
        available = true;
        participations = new HashMap<>();
    }

    public Technician(Technician t) {
        super(t);
        participations =  new HashMap<>(t.participations
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry<Integer,HashSet<Integer>>::getKey,
                        v -> new HashSet<>( v.getValue()))));
        available = t.available;
    }

    @Override
    public Worker clone() {
        return new Technician(this);
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
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

}
