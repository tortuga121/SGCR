package Controller;

import Exception.*;
import Model.ISGCR;
import Model.SGCR;
import Model.Worker.*;
import View.*;
import View.Login.VLogin;
import View.Manager.VManager;
import View.Receptionist.*;
import View.Technician.VTechnician;

import java.time.LocalDate;

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
        VReceptionist vr = new VReceptionist();
        vr.getReceptionistOptions().setVisible(true);
        vr.getReceptionistOptions().getRequestBudget().addActionListener(e -> {
            vr.getRequestBudget().setVisible(true);
        });
        vr.getReceptionistOptions().getRefuseBudget().addActionListener(e -> {
            execRefuseBudget();
        });
    }

    public void execTechnician() {
        VTechnician vt = new VTechnician();
        vt.getOptions().setVisible(true);
        vt.getOptions().getEditPlan().addActionListener(e -> {
            //TODO forms edit repair plan
        });
        vt.getOptions().getSugestPlan().addActionListener(e -> {
            vt.getPlanSugestion().setVisible(true);
        });
    }

    public void execManager() {
        VManager vm = new VManager();
        //TODO
    }

    public void execRefuseBudget() {
        VRefuseBudget vrb = view.getReceptionist().getRefuseBudget();
        vrb.setVisible(true);
        vrb.getSaveButton().addActionListener(e -> {
            try {
                int id = Integer.parseInt(vrb.getDeviceID().getText());
                LocalDate deadline = sgcr.refuseBudget(id);
                view.showPopUpMsg("Reparação cancelada.\nTem até " + deadline.toString() + " para levantar o seu equipamento.");
            } catch (NumberFormatException | DeviceNotFoundException | WorkerDoesNotExist ex) {
                view.showPopUpMsg("Equipamento não existe");
            }
        });
    }
}
