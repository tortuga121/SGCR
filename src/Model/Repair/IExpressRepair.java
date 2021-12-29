package Model.Repair;

import java.util.List;

public interface IExpressRepair {
    int getRegCode();

    String getGeneralDescription();

    List<String> getExpressServices();

    double getTimeService(String s);
}
