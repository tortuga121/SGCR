package View;

import View.Device.VDevice;
import View.Login.VLogin;
import View.Manager.VManager;
import View.Receptionist.VReceptionist;
import View.Technician.VTechnician;

public interface IView {
    VLogin getLogin();
    VTechnician getTechnician();
    VReceptionist getReceptionist();
    VManager getManager();
    VDevice getDevice();
    void showPopUpMsg(String msg);
}
