package Controller;

import Exception.*;
import Model.ISGCR;
import Model.SGCR;
import Model.Worker.*;
import View.*;
import View.Login.VLogin;
import View.Manager.IVManager;
import View.Manager.VManager;
import View.Receptionist.*;
import View.Technician.IVTechnician;
import View.Technician.VTechnician;

public class Controller implements IController{
    IView view;
    ISGCR sgcr;

    public Controller() {
        view = new View();
        sgcr = new SGCR();
    }

    public void exec() {
        VLogin vl = view.getLogin();
        vl.setVisible(true);
        vl.getSaveButton().addActionListener(e -> {
            try {
                int id = Integer.parseInt(vl.getUserTextField().getText());
                String pass = vl.getPassField().getText();
                if (sgcr.login(id, pass)) {
                    vl.dispose();
                    System.out.println(id);
                    System.out.println(sgcr.getWorkerType(id));
                    if (sgcr.getWorkerType(id).equals(CManager.class)) execManager();
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
        IVReceptionist vr = new VReceptionist();
        vr.getReceptionistOptions().setVisible(true);
        vr.getReceptionistOptions().getRequestBudget().addActionListener(e -> {
            vr.getRequestBudget().setVisible(true);
        });
        vr.getReceptionistOptions().getRefuseBudget().addActionListener(e -> {
            vr.getRequestBudget().setVisible(true);
        });
    }

    public void execTechnician() {
        IVTechnician vt = new VTechnician();
        vt.getOptions().setVisible(true);
        vt.getOptions().getEditPlan().addActionListener(e -> {
            //TODO forms edit repair plan
        });
        vt.getOptions().getSugestPlan().addActionListener(e -> {
            vt.getPlanSugestion().setVisible(true);
        });
    }

    public void execManager() {
        IVManager vm = new VManager();
        //TODO
    }
}
