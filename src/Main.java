import Controller.*;
import Model.*;
import Model.Devices.Device;
import Model.Devices.DeviceCatalog;
import Model.Devices.IDevice;
import Model.Devices.*;
import Model.Repair.*;
import Model.Worker.*;
import Exception.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InvalidRegistrationCodeException, WorkerDoesNotExist, DeviceNotFoundException {
        SGCR sgcr = new SGCR();

        ArrayList<IDevice> arr = new ArrayList<>();
        arr.add( new Device(0,"000000000","este é o 1", "pc"));
        arr.add( new Device(1,"000000000","este é o 2", "tele"));
        arr.add( new Device(2,"000000000", "este é o 3","maquina"));
        arr.add( new Device(3,"444444444", "este é o 4","frigorifico"));
        arr.add( new Device(4,"444444444", "este é o 5","mac"));
        arr.add( new Device(5,"666666666", "este é o 6","smartwatch"));
        arr.add( new Device(6,"888888888", "este é o 7","televisoa"));
        arr.add( new Device(7,"888888888", "este é o 8","fones"));
        arr.add( new Device(8,"999999999", "este é o 9","coluna"));


        sgcr.addWorker("shaggy",122,Receptionist.class,"122");
        sgcr.addWorker("noodle",129,Receptionist.class,"129");
        sgcr.addWorker("azula",19,Technician.class,"19");
        sgcr.addWorker("tortuga",121,Technician.class,"121");
        sgcr.addWorker("blanc",157,Manager.class,"157");

        for(IDevice d : arr) sgcr.addBudgetRequest(d,129);
        sgcr.sugestRepairPlan(new RepairPlan(0,"arranja", new ArrayList<>(),200));
        sgcr.refuseBudget(0);
        IController c = new Controller(sgcr);
        c.exec();
    }
}
