package View;

import View.Login.VLogin;
import View.Manager.IVManager;
import View.Receptionist.IVReceptionist;
import View.Receptionist.VReceptionist;
import View.Technician.IVTechnician;

import javax.swing.*;

public class View implements IView {
    private IVManager manager;
    private IVReceptionist receptionist;
    private IVTechnician technician;
    private VLogin login;

    public View() {
        // this.manager = new ManagerView();
        // this.technician = new TechnicianView();
        this.receptionist = new VReceptionist();
        this.login = new VLogin();
    }

    public VLogin getLogin() {
        return login;
    }

    public IVManager getManager() {
        return manager;
    }

    public IVReceptionist getReceptionist() {
        return receptionist;
    }

    public IVTechnician getTechnician() {
        return technician;
    }

    public void showPopUpMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
