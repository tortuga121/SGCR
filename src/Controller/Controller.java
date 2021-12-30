package Controller;

import Exception.*;
import Model.Devices.Device;
import Model.ISGCR;
import Model.SGCR;
import Model.Worker.*;
import View.*;
import View.Login.VLogin;
import View.Manager.VManager;
import View.Receptionist.*;
import View.Technician.VTechnician;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Controller implements IController{
    IView view;
    ISGCR sgcr;

    public Controller() {
        view = new View();
        sgcr = new SGCR();
    }
    public Controller(SGCR sgcr) {
        this.view = new View();
        this.sgcr = sgcr;
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
                    if (sgcr.getWorkerType(id).equals(Manager.class)) execManager(id);
                    else if (sgcr.getWorkerType(id).equals(Technician.class)) execTechnician(id);
                    else if (sgcr.getWorkerType(id).equals(Receptionist.class)) execReceptionist(id);
                }
                else view.showPopUpMsg("Credenciais inválidas.");
            } catch (NumberFormatException | WorkerDoesNotExist exception) {
                view.showPopUpMsg("Credenciais inválidas.");
            }
        });
    }

    public void execReceptionist(int workerID) {
        VReceptionist vr = new VReceptionist();
        vr.getReceptionistOptions().setVisible(true);
        vr.getReceptionistOptions().getRequestBudget().addActionListener(e -> {
            execRequestBudget(workerID);
        });
        vr.getReceptionistOptions().getRefuseBudget().addActionListener(e -> {
            execRefuseBudget();
        });
    }

    public void execTechnician(int workerID) {
        VTechnician vt = new VTechnician();
        vt.getOptions().setVisible(true);
        vt.getOptions().getEditPlan().addActionListener(e -> {
            //TODO forms edit repair plan
        });
        vt.getOptions().getSugestPlan().addActionListener(e -> {
            vt.getPlanSugestion().setVisible(true);
        });
    }

    public void execManager(int workerID) {
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
                vrb.dispose();
                view.showPopUpMsg("Reparação cancelada.\nTem até " + deadline.toString() + " para levantar o seu equipamento.");
            } catch (NumberFormatException | DeviceNotFoundException | WorkerDoesNotExist ex) {
                view.showPopUpMsg("Equipamento não existe");
            }
        });
    }

    private void execRequestBudget(int workerID) {
        VBudgetRequest vbr = view.getReceptionist().getRequestBudget();
        vbr.setVisible(true);
        vbr.getSaveButton().addActionListener(e -> {
            try {
                String nif = vbr.getClientNIF().getText();
                vbr.getClientNIF().setText("");
                String name = vbr.getDeviceName().getText();
                vbr.getDeviceName().setText("");
                String pd = vbr.getProblemDescription().getText();
                vbr.getProblemDescription().setText("");
                sgcr.addBudgetRequest(new Device( 0 , nif, pd, name), workerID);
                vbr.dispose();
                view.showPopUpMsg("Pedido de orçamento adicionado.");
            } catch (NumberFormatException | WorkerDoesNotExist | InvalidRegistrationCodeException ex) {
                view.showPopUpMsg("Formulário inválido.");
            }
        });
    }
}
