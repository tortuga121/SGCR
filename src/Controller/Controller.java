package Controller;

import Exception.*;
import Model.Devices.Device;
import Model.ISGCR;
import Model.SGCR;
import Model.Worker.*;
import View.*;
import View.Device.VDevice;
import View.Device.VDeviceList;
import View.Login.VLogin;
import View.Manager.VManager;
import View.Receptionist.*;
import View.Technician.VTechnician;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
                view.showPopUpMsg(exception.getMessage());
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
            String nif = vrb.getNIF().getText();
            try {
                Set<Integer> regCodes = sgcr.getClientDevices(nif);
                List<String> l = new ArrayList<>();
                for (Integer i : regCodes) {
                    String name = "0";
                    String s = i + " - Código de registo; " + sgcr.getDevice(i).getName() + " - Nome equipamento.";
                    l.add(s);
                }
                VDeviceList vdl = new VDeviceList(nif, l);
                vdl.setVisible(true);
                vdl.getSelectButton().addActionListener(e1 -> {
                    String selectedValue = vdl.getDeviceList().getSelectedValue();
                    String regCodeStr = selectedValue.split(" -")[0];
                    int regCode = Integer.parseInt(regCodeStr);
                    try {
                        Device device = (Device) sgcr.getDevice(regCode);
                        VDevice vDevice = new VDevice(device);
                        vDevice.setVisible(true);
                        view.showPopUpMsg("Para recusar o plano de reparação clique em confirmar.\nCaso contrário feche a janela.");
                        vDevice.getDoneButton().addActionListener(e2 -> {
                            try {
                                LocalDate deadline = sgcr.refuseBudget(regCode);vDevice.dispose();
                                vdl.dispose();
                                view.showPopUpMsg("Reparação cancelada.\nTem até " + deadline.toString() + " para levantar o seu equipamento.");
                            } catch (DeviceNotFoundException | WorkerDoesNotExist ex) {
                                view.showPopUpMsg(ex.getMessage());
                            }
                        });
                    } catch (DeviceNotFoundException ignored) {}
                });
            } catch (DeviceNotFoundException ex) {
                view.showPopUpMsg(ex.getMessage());
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
                sgcr.addBudgetRequest(new Device( sgcr.generateNewregistrationCode(), nif, pd, name), workerID);
                vbr.dispose();
                view.showPopUpMsg("Pedido de orçamento adicionado.");
            } catch (NumberFormatException | WorkerDoesNotExist | InvalidRegistrationCodeException ex) {
                view.showPopUpMsg(ex.getMessage());
            }
        });
    }
}
