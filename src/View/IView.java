package View;

import View.Login.ViewLogin;
import View.Manager.IManagerView;
import View.Receptionist.IReceptionistView;
import View.Technician.ITechnicianView;

public interface IView {
    ViewLogin getLogin();
    ITechnicianView getTechnician();
    IReceptionistView getReceptionist();
    IManagerView getManager();
    void showPopUpMsg(String msg);
}
