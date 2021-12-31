package Model.Repair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpressRepair implements IExpressRepair {

    private String type;
    public static final HashMap<String,Double> expressTypes;
    static {
        expressTypes = new HashMap<>();
        expressTypes.put("substituir ecrã de um telemóvel", 45.0);
        expressTypes.put("instalar um sistema operativo",70.0);
        expressTypes.put("formatar um computador",35.0);
        expressTypes.put("trocar a bateria de um computador",60.0);
        // TODO
    }

    public ExpressRepair(String type) {

        this.type = type;
    }

    @Override
    public String getType() {
        return type;
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
