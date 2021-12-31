package Model.Repair;

import java.util.List;

public interface IExpressRepair {
    List<String> getExpressServices();
    double getTimeService(String s);
    String getType();
}
