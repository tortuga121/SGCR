package View;

import View.Login.ViewLogin;
import View.Manager.IManagerView;
import View.Receptionist.IReceptionistView;
import View.Receptionist.ReceptionistView;
import View.Technician.ITechnicianView;

import javax.swing.*;

public class View implements IView {
    private IManagerView manager;
    private IReceptionistView receptionist;
    private ITechnicianView technician;
    private ViewLogin login;

    public View() {
        // this.manager = new ManagerView();
        // this.technician = new TechnicianView();
        this.receptionist = new ReceptionistView();
        this.login = new ViewLogin();
    }

    public ViewLogin getLogin() {
        return login;
    }

    public IManagerView getManager() {
        return manager;
    }

    public IReceptionistView getReceptionist() {
        return receptionist;
    }

    public ITechnicianView getTechnician() {
        return technician;
    }

    public void showPopUpMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
