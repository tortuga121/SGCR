package View.Technician;

import javax.swing.*;
import java.awt.*;

public class ViewRepairStep extends JFrame {
    private JPanel mainPanel;
    private JTextField deviceIDtxt;
    private JTextField technicianIDtxt;
    private JTextField repairCostTxt;
    private JTextField timeToRepairTxt;
    private JLabel deviceID;
    private JLabel technicianID;
    private JLabel repairCost;
    private JLabel timeToRepair;

    public ViewRepairStep() {
        super("Repair Step Form");
        this.mainPanel.setPreferredSize(new Dimension(400, 500));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getDeviceID() {
        return deviceIDtxt;
    }

    public JTextField getTechnicianID() {
        return technicianIDtxt;
    }

    public JTextField getRepairCost() {
        return repairCostTxt;
    }

    public JTextField getTimeToRepair() {
        return timeToRepairTxt;
    }
}
