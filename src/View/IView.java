package View;

import View.Login.VLogin;
import View.Manager.VManager;
import View.Receptionist.VReceptionist;
import View.Technician.VTechnician;

public interface IView {
    VLogin getLogin();
    VTechnician getTechnician();
    VReceptionist getReceptionist();
    VManager getManager();
    void showPopUpMsg(String msg);
}
