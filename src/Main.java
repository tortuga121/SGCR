import Controller.*;
import View.*;
import View.Login.ViewLogin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        ViewLogin login = new ViewLogin();
        login.setVisible(true);
        login.getSaveButton().addActionListener(e -> {
            String user = login.getUserTextField().getText();
            String pass = login.getPassField().getText();
            System.out.println("Username: " + user);
            System.out.println("Password: " + pass);
        });*/
        /*
        ViewBudgetRequest vbr = new ViewBudgetRequest();
        vbr.setVisible(true);
        vbr.getSaveButton().addActionListener(e -> {
            String idd = vbr.getDevideID().getText();
            String idr = vbr.getReceptionistID().getText();
            String nif = vbr.getClientNIF().getText();
            String prb = vbr.getProblemDescription().getText();
            System.out.println("ID Device: " + idd);
            System.out.println("ID Receptionist: " + idr);
            System.out.println("Client NIF: " + nif);
            System.out.println("Problem: " + prb);
        });
        /*
        ViewRefuseBudget vrb = new ViewRefuseBudget();
        vrb.setVisible(true);
        vrb.getSaveButton().addActionListener(e -> {
            String id = vrb.getDeviceID().getText();
            System.out.println("Device ID: " + id);
        });*/
        /*
        ViewRepairPlan vrp = new ViewRepairPlan();
        vrp.setVisible(true);
        vrp.getSaveButton().addActionListener(e -> {
            String idd = vrp.getDeviceID().getText();
            String prb = vrp.getProblemDescription().getText();
            List<String> l = new ArrayList<>();
            for (int i = 0; i < vrp.getRepairStepsList().getSize(); i++)
                l.add(vrp.getRepairStepsList().get(i));
            System.out.println("ID Device: " + idd);
            System.out.println("Problem: " + prb);
            for (int i = 0; i < vrp.getRepairStepsList().getSize(); i++)
                System.out.println("Passo " + i + ": " + l.get(i));
        });*/
        IController c = new Controller();
        c.exec();
    }
}
