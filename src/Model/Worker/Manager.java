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
    }
    public Manager(Manager m) {
        super(m);
        month_eval = new HashMap<>(
                month_eval.entrySet()
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
    public Worker clone() { //TODO
        return null;
    }
}
