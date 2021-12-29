package Model.Worker;

import java.time.Month;
import java.time.Year;

public interface IManager extends IWorker{
    void addEval(Year y, Month m , String eval);
}
