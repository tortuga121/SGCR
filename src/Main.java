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



        sgcr.addWorker("shaggy",122,Receptionist.class,"122");
        sgcr.addWorker("noodle",129,Receptionist.class,"129");
        sgcr.addWorker("azula",19,Technician.class,"19");
        sgcr.addWorker("tortuga",121,Technician.class,"121");
        sgcr.addWorker("blanc",157,Manager.class,"157");


        IController c = new Controller(sgcr);
        c.exec();
        

    }
}
