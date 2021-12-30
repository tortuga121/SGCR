package Controller;

import Exception.*;
import Model.ISGCR;
import Model.SGCR;
import Model.Worker.*;
import View.*;
import View.Login.ViewLogin;
import View.Manager.IManagerView;
import View.Manager.ManagerView;
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
                    System.out.println(id);
                    System.out.println(sgcr.getWorkerType(id));
                    if (sgcr.getWorkerType(id).equals(Manager.class)) execManager();
                    else if (sgcr.getWorkerType(id).equals(Technician.class)) execTechnician();
                    else if (sgcr.getWorkerType(id).equals(Receptionist.class)) execReceptionist();
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
        IManagerView vm = new ManagerView();
        //TODO
    }
}
