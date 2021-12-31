package View;

import View.Device.VDevice;
import View.Device.VDeviceList;
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

    public View() {
        // this.manager = new VManager();
        this.technician = new VTechnician();
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

    public void showPopUpMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
