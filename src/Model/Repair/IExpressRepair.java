package Model.Repair;

import java.util.List;

public interface IExpressRepair {

    String getGeneralDescription();

    List<String> getExpressServices();

    double getTimeService(String s);
    String getType();
}
