package Controller;

import Exception.*;
import Model.ISGCR;
import Model.SGCR;
import Model.Worker.*;
import View.*;
import View.Login.ViewLogin;
import View.Receptionist.*;
import View.Technician.ITechnicianView;
import View.Technician.TechnicianView;

public class Controller implements IController{
    IView view;
    ISGCR sgcr;

    public Controller() {
        view = new View();
        sgcr = new SGCR();
    }

    public void exec() {
        ViewLogin vl = view.getLogin();
        vl.setVisible(true);
        vl.getSaveButton().addActionListener(e -> {
            try {
                int id = Integer.parseInt(vl.getUserTextField().getText());
                String pass = vl.getPassField().getText();
                if (sgcr.login(id, pass)) {
                    vl.dispose();
                    IManager m = new Manager("0", 0);
                    IReceptionist r = new Receptionist("0", 0);
                    ITechnician t = new Technician("0", 0);
                    if (sgcr.getWorkerType(id).equals(m.getClass())) execManager();
                    else if (sgcr.getWorkerType(id).equals(t.getClass())) execTechnician();
                    else if (sgcr.getWorkerType(id).equals(r.getClass())) execReceptionist();
                }
                else view.showPopUpMsg("Credenciais inválidas.");
            } catch (NumberFormatException | WorkerDoesNotExist exception) {
                view.showPopUpMsg("Credenciais inválidas.");
            }
        });
    }

    public void execReceptionist() {
        IReceptionistView vr = new ReceptionistView();
        vr.getReceptionistOptions().setVisible(true);
        vr.getReceptionistOptions().getRequestBudget().addActionListener(e -> {
            vr.getRequestBudget().setVisible(true);
        });
        vr.getReceptionistOptions().getRefuseBudget().addActionListener(e -> {
            vr.getRequestBudget().setVisible(true);
        });
    }

    public void execTechnician() {
        ITechnicianView vt = new TechnicianView();
        vt.getOptions().setVisible(true);
        vt.getOptions().getEditPlan().addActionListener(e -> {
            //TODO forms edit repair plan
        });
        vt.getOptions().getSugestPlan().addActionListener(e -> {
            vt.getPlanSugestion().setVisible(true);
        });
    }

    public void execManager() {

    }
}
