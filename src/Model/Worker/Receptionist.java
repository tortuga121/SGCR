package Model.Worker;

import Model.Devices.Device;
import Model.Devices.IDevice;

import java.time.LocalDateTime;

public class Receptionist extends Worker implements IReceptionist {
    private int totalDeliveries;
    private int totalReceptions;

    public Receptionist(String name, int id) {
        super(name, id);
        totalDeliveries = 0;
        totalReceptions = 0;
    }
    public Receptionist(Receptionist r) {
        super(r);
        totalDeliveries = r.totalDeliveries;
        totalReceptions = r.totalReceptions;
    }


    @Override
    public Worker clone() { //TODO
        return new Receptionist(this);
    }



    @Override
    public int totalDeliveries() {
        return totalDeliveries;
    }

    @Override
    public int totalReceptions() {
        return totalReceptions;
    }

    @Override
    public void addDelivery() {
        totalDeliveries++;
    }

    @Override
    public void addReception() {
        totalReceptions++;
    }

}
