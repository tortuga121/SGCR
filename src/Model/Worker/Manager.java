package Model.Worker;

import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Manager extends Worker implements IManager {
    HashMap<Year, HashMap<Month ,String>> month_eval;
    public void addEval(Year y, Month m , String eval) {
      if(!month_eval.containsKey(y))
          month_eval.put(y,new HashMap<>());
      month_eval.get(y).put(m,eval);
    }
    public Manager(String name, int id) {
        super(name, id);
        month_eval = new HashMap<>();
    }
    public Manager(Manager m) {
        super(m);
        this.month_eval = new HashMap<>(
                m.month_eval.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry<Year, HashMap<Month ,String>>::getKey,
                               v -> new HashMap<>( v.getValue()
                                       .entrySet()
                                       .stream()
                                       .collect(Collectors.toMap(Map.Entry<Month ,String>::getKey,
                                               Map.Entry<Month ,String>::getValue)))))
        );
    }

    @Override
    public Manager clone() { //TODO
        return new Manager(this);
    }
}
