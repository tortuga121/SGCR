package Controller;

import Exception.*;
import Model.Devices.Device;
import Model.ISGCR;
import Model.Repair.IRepairPlan;
import Model.Repair.RepairPlan;
import Model.Repair.Stage;
import Model.Repair.Step;
import Model.SGCR;
import Model.Worker.*;
import View.*;
import View.Device.VDevice;
import View.Device.VDeviceList;
import View.Login.VLogin;
import View.Manager.VAddEval;
import View.Manager.VManagerOptions;
import View.Manager.VTable;
import View.Receptionist.*;
import View.Technician.VAddStage;
import View.Technician.VStep;
import View.Technician.VSugestRepairPlan;
import View.Technician.VTechnician;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

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
        VReceptionist vr = this.view.getReceptionist();
        vr.getReceptionistOptions().setVisible(true);
        vr.getReceptionistOptions().getRequestBudget().addActionListener(e -> {
            execRequestBudget(workerID);
        });
        vr.getReceptionistOptions().getRefuseBudget().addActionListener(e -> {
            execEditBudget(false);
        });
        vr.getReceptionistOptions().getAproveBudget().addActionListener(e -> {
            execEditBudget(true);
        });
        vr.getReceptionistOptions().getExpressRequestButton().addActionListener(e -> {
            execExpressRequest(workerID);
        });
        vr.getReceptionistOptions().getPickupButton().addActionListener(e -> {
            execPickUp(workerID);
        });
        vr.getReceptionistOptions().getUpdateOutdatedButton().addActionListener(e -> {
            sgcr.checkForDealines();
            view.showPopUpMsg("Pedidos expirados atualizados.");
        });
    }

    public void execTechnician(int workerID) {
        VTechnician vt = this.view.getTechnician();
        vt.getOptions().setVisible(true);
        vt.getOptions().getEditPlan().addActionListener(e -> {
            execExecPlan(workerID);
        });
        vt.getOptions().getSugestPlan().addActionListener(e -> {
            execSugestPlan();
        });
        vt.getOptions().getUnvailable().addActionListener(e -> {
            try {
                sgcr.setAvailable(workerID, false);
                view.showPopUpMsg("Indisponível");
            } catch (WorkerDoesNotExist ex) {
                view.showPopUpMsg(ex.getMessage());
            }
        });
        vt.getOptions().getAvailable().addActionListener(e -> {
            try {
                sgcr.setAvailable(workerID, true);
                view.showPopUpMsg("Disponível");
            } catch (WorkerDoesNotExist ex) {
                view.showPopUpMsg(ex.getMessage());
            }
        });
    }

    private void execExecPlan(int workerID) {
        try {
            IRepairPlan rp = sgcr.getMostUrgentRepair();
            VStep step = new VStep(rp.getStageCurrent().getCurrentStep().getDescription());
            step.setVisible(true);
            step.getSaveButton().addActionListener(e -> {
                try {
                    sgcr.repairNextStep(
                            rp.getRegCode(), workerID,
                            Double.parseDouble(step.getCost().getText()),
                            Double.parseDouble(step.getHours().getText()));
                    step.dispose();
                    view.showPopUpMsg("Passo Atualizado.");
                } catch (WorkerDoesNotExist | OutOfbudgetException | NoMoreStepsException | NoRepairException ex1) {
                    view.showPopUpMsg(ex1.getMessage());
                }
            });
        } catch (NoRepairException | NoMoreStepsException ex2) {
            view.showPopUpMsg(ex2.getMessage());
        }
    }

    private void execSugestPlan() {
        try {
            Device device = (Device) sgcr.getDevice(sgcr.getBudgetRequest());
            VDevice vDevice = new VDevice(device);
            VSugestRepairPlan vsrp = new VSugestRepairPlan(Integer.toString(device.getRegCode()));
            vDevice.setVisible(true);
            vDevice.getDoneButton().addActionListener(e -> {vDevice.dispose(); vsrp.setVisible(true);});

            ArrayList<Stage> stages = new ArrayList<>();
            vsrp.getAddStageButton().addActionListener(e -> {
                if (vsrp.getStageToAdd().getText().equals("") || vsrp.getStageToAdd().getText().equals("escreva descrição da etapa"))
                    vsrp.getStageToAdd().setText("escreva descrição da etapa");
                else {
                    String description = vsrp.getStageToAdd().getText();
                    vsrp.getStageToAdd().setText("");
                    VAddStage addStage = new VAddStage(description);
                    addStage.setVisible(true);

                    addStage.getAdicionarPassoButton().addActionListener(e1 -> {
                        if (addStage.getStepToAdd().getText().equals("") || addStage.getStepToAdd().getText().equals("escreva a descrição do passo"))
                            addStage.getStepToAdd().setText("escreva a descrição do passo");
                        else {
                            String s = addStage.getStepToAdd().getText();
                            addStage.getStepToAdd().setText("");
                            addStage.getListModel().addElement(s);
                            addStage.getStepList().setModel(addStage.getListModel());
                        }
                    });

                    addStage.getSaveButton().addActionListener(e2 -> {
                        ArrayList<Step> steps = new ArrayList<>();
                        for (int i = 0; i < addStage.getListModel().getSize(); i++) {
                            Step s = new Step(addStage.getListModel().get(i));
                            steps.add(s);
                        }
                        stages.add(new Stage(description, steps));
                        addStage.dispose();
                    });
                }
            });

            vsrp.getSaveButton().addActionListener(e3 -> {
                try {
                    this.sgcr.sugestRepairPlan(new RepairPlan(
                            device.getRegCode(),
                            vsrp.getDescription().getText(),
                            stages,
                            Double.parseDouble(vsrp.getRepairCost().getText()),
                            LocalDate.parse(vsrp.getDeadline().getText())
                    ));
                    view.showPopUpMsg("Sugestão pronta.");
                } catch (DeviceNotFoundException | NumberFormatException ex) {
                    view.showPopUpMsg(ex.getMessage());
                }
            });
        } catch (DeviceNotFoundException ex) {
            view.showPopUpMsg(ex.getMessage());
        }
    }

    public void execManager(int workerID) {
        VManagerOptions vm = this.view.getManager().getOptions();
        vm.setVisible(true);
        vm.getReceptionistInfo().addActionListener(e -> {
            execRecepStats();
        });
        vm.getTechInfo().addActionListener(e -> {
            execTechStats();
        });
        vm.getTechInterventions().addActionListener(e -> {
            execTechDetailStats();
        });
        vm.getAddInfo().addActionListener(e -> {
            VAddEval vae = new VAddEval();
            vae.setVisible(true);
            vae.getSaveButton().addActionListener(e1 -> {
                try {
                    sgcr.makeMonthEval(
                            Year.parse(vae.getYear().getText()),
                            Month.of(Integer.parseInt(vae.getMonth().getText())),
                            vae.getDescription().getText(),
                            workerID);
                    vae.dispose();
                    view.showPopUpMsg("Avaliação adicionada.");
                } catch (WorkerDoesNotExist ex) {
                    view.showPopUpMsg(ex.getMessage());
                }
            });
        });
    }

    private void execTechDetailStats() {
        HashMap<Integer, HashMap<Integer, HashSet<Integer>>> m = sgcr.techDetailStats();
        Vector<String> header = new Vector<>();
        header.add("ID do Técnico");
        header.add("ID de Reparação");
        header.add("Número do Stage");
        Vector<Vector<String>> data = new Vector<>();
        m.forEach((k,v) -> {
            Vector<String> tmp = new Vector<>();
            tmp.add(k.toString());
            v.forEach((k1, v1) -> {
                tmp.add(k1.toString());
                v1.forEach(v2 -> {
                    tmp.add(v2.toString());
                });
            });
            data.add(tmp);
        });
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setDataVector(data, header);
        VTable vt = new VTable("Technician Detail Stats", tableModel);
        vt.setVisible(true);
    }

    private void execTechStats() {
        HashMap<Integer, ArrayList<Double>> m = sgcr.techstats();
        Vector<String> header = new Vector<>();
        header.add("ID do Técnico");
        header.add("Número Participações");
        header.add("Duração Média");
        header.add("Desvio Médio");
        Vector<Vector<String>> data = new Vector<>();
        m.forEach((k,v) -> {
            Vector<String> tmp = new Vector<>();
            tmp.add(k.toString());
            v.forEach(v1 -> {
                tmp.add(v1.toString());
            });
            data.add(tmp);
        });
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setDataVector(data, header);
        VTable vt = new VTable("Technician Stats", tableModel);
        vt.setVisible(true);
    }

    private void execRecepStats() {
        HashMap<Integer, ArrayList<Integer>> m = sgcr.recepStats();
        Vector<String> header = new Vector<>();
        header.add("ID do Rececionista");
        header.add("Receções Efetuadas");
        header.add("Entregas Realizadas");
        Vector<Vector<String>> data = new Vector<>();
        m.forEach((k,v) -> {
            Vector<String> tmp = new Vector<>();
            tmp.add(k.toString());
            v.forEach(v1 -> {
                tmp.add(v1.toString());
            });
            data.add(tmp);
        });
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setDataVector(data, header);
        VTable vt = new VTable("Receptionist Stats", tableModel);
        vt.setVisible(true);
    }

    public void execEditBudget(boolean aprove) {
        VClientNIF vrb = view.getReceptionist().getClientNIF();
        vrb.setVisible(true);
        vrb.getSaveButton().addActionListener(e -> {
            String nif = vrb.getNIF().getText();
            vrb.getNIF().setText("");
            try {
                Set<Integer> regCodes = sgcr.getClientDevices(nif);
                List<String> l = new ArrayList<>();
                for (Integer i : regCodes) {
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
                        if (!aprove) view.showPopUpMsg("Para recusar o plano de reparação clique em confirmar.\nCaso contrário feche a janela.");
                        else view.showPopUpMsg("Para aprovar o plano de reparação clique em confirmar.\nCaso contrário feche a janela.");
                        vDevice.getDoneButton().addActionListener(e2 -> {
                            try {
                                if (!aprove){
                                    LocalDate deadline = sgcr.refuseBudget(regCode);
                                    vDevice.dispose();
                                    vdl.dispose();
                                    vDevice.dispose();
                                    vrb.dispose();
                                    view.showPopUpMsg("Reparação cancelada.\nTem até " + deadline.toString() + " para levantar o seu equipamento.");
                                }
                                else {
                                    sgcr.acceptBudget(regCode);
                                    vDevice.dispose();
                                    vdl.dispose();
                                    vDevice.dispose();
                                    vrb.dispose();
                                    view.showPopUpMsg("Orçamento aprovado.");
                                }
                            } catch (DeviceNotFoundException | WorkerDoesNotExist ex) {
                                view.showPopUpMsg(ex.getMessage());
                            }
                        });
                    } catch (DeviceNotFoundException ex) {
                        view.showPopUpMsg(ex.getMessage());
                    }
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

    private void execPickUp(int workerID) {
        VClientNIF clientNIF = this.view.getReceptionist().getClientNIF();
        clientNIF.setVisible(true);
        clientNIF.getSaveButton().addActionListener(e -> {
            String nif = clientNIF.getNIF().getText();
            clientNIF.getNIF().setText("");
            try {
                Set<Integer> regCodes = sgcr.getDeliveredDevicesbyNif(nif);
                List<String> l = new ArrayList<>();
                for (Integer i : regCodes) {
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
                        view.showPopUpMsg("Para levantar o equipamento clique em confirmar, caso contrário feche a janela.");
                        vDevice.getDoneButton().addActionListener(e2 -> {
                            try {
                                this.sgcr.devicePickup(regCode, workerID);
                                vDevice.dispose();
                                vdl.dispose();
                                vDevice.dispose();
                                clientNIF.dispose();
                                view.showPopUpMsg("Equipamento levantado.");
                            } catch (DeviceNotFoundException | WorkerDoesNotExist ex) {
                                view.showPopUpMsg(ex.getMessage());
                            }
                        });
                    } catch (DeviceNotFoundException ex) {
                        view.showPopUpMsg(ex.getMessage());
                    }
                });
            } catch (DeviceNotFoundException ex) {
                view.showPopUpMsg(ex.getMessage());
            }
        });
    }

    private void execExpressRequest(int workerID) {
        VExpressRequest ve = this.view.getReceptionist().getExpressRequest();
        ve.setVisible(true);
        ve.getSaveButton().addActionListener(e -> {
            String type = ve.getRepairType().getText();
            try {
                int techID = sgcr.availableTechnician();
                double cost = sgcr.makeExpressRepair(type,techID,workerID);
                ve.dispose();
                view.showPopUpMsg("Reparação Expresso efetuada com um custo de " + cost + ".");
            }
            catch (WorkerDoesNotExist | DeviceNotFoundException ex) {
                view.showPopUpMsg(ex.getMessage());
            }
        });
    }


}
