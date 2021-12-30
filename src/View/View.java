package View;

import View.Device.VDevice;
import View.Login.VLogin;
import View.Manager.VManager;
import View.Receptionist.VReceptionist;
import View.Technician.VTechnician;

import javax.swing.*;

public class View implements IView {
    private VManager manager;
    private VReceptionist receptionist;
    private VTechnician technician;
    private VLogin login;
    private VDevice device;

    public View() {
        // this.manager = new ManagerView();
        // this.technician = new TechnicianView();
        this.receptionist = new VReceptionist();
        this.login = new VLogin();
    }

    public VLogin getLogin() {
        return login;
    }

    public VManager getManager() {
        return manager;
    }

    public VReceptionist getReceptionist() {
        return receptionist;
    }

    public VTechnician getTechnician() {
        return technician;
    }

    public VDevice getDevice() {
        return device;
    }

    public void showPopUpMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
