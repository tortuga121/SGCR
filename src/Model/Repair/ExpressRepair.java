package Model.Repair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpressRepair implements IExpressRepair {
    private int regCode;
    private String generalDescription;
    private String type;
    private static final HashMap<String,Double> expressTypes;
    static {
        expressTypes = new HashMap<>();
        expressTypes.put("tipo", 1.0);
        // TODO
        //adicionar mais exemplos
    }

    public ExpressRepair(int regCode, String generalDescription, String type) {
        this.regCode = regCode;
        this.generalDescription = generalDescription;
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getRegCode() {
        return regCode;
    }
    @Override
    public String getGeneralDescription() {
        return generalDescription;
    }
    
    @Override
    public List<String> getExpressServices() {
        return new ArrayList<>(expressTypes.keySet());
    }
    @Override
    public double getTimeService(String s){
        return expressTypes.get(s);
    }
}
