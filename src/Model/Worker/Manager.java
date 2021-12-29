package Model.Worker;

import java.time.Month;
import java.time.Year;
import java.util.HashMap;

public class Manager extends Worker{
    HashMap<Year, HashMap<Month ,String>> month_eval;

    public void addEval(Year y, Month m , String eval) {
      if(!month_eval.containsKey(y))
          month_eval.put(y,new HashMap<>());
      month_eval.get(y).put(m,eval);
    }
    public Manager(String name, int id) {
        super(name, id);
    }
}
