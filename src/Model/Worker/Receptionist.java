package Model.Worker;

import Model.Device;
import Model.IDevice;

import java.time.LocalDateTime;

public class Receptionist extends Worker implements IReceptionist {
    private int totalDeliveries;
    private int totalReceptions;

    public Receptionist(String name, int id) {
        super(name, id);
    }

    @Override
    public IDevice budgetRequest(int regCode, String nif, String descr) {
        IDevice dev = new Device(regCode,nif,descr, LocalDateTime.now());
        return dev;
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

    public static class Manager extends Worker implements IManager {

        public Manager(String name, int id) {
            super(name, id);
        }
    }
}
