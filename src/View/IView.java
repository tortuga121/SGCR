package View;

import View.Login.VLogin;
import View.Manager.IVManager;
import View.Receptionist.IVReceptionist;
import View.Technician.IVTechnician;

public interface IView {
    VLogin getLogin();
    IVTechnician getTechnician();
    IVReceptionist getReceptionist();
    IVManager getManager();
    void showPopUpMsg(String msg);
}
