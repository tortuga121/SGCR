package View.Technician;

import javax.swing.*;
import java.awt.*;

public class VSugestRepairPlan extends JFrame {
    private JPanel mainPanel;
    private JTextField description;
    private JLabel descriptionLabel;
    private JTextField deadline;
    private JLabel deadlineLabel;
    private JTextField repairCost;
    private JLabel repaircostLabel;
    private JButton addStageButton;
    private JButton saveButton;
    private JTextField stageToAdd;
    private JTextField regCode;
    private JLabel regCodeLabel;

    public VSugestRepairPlan(String regCode) {
        super("Repair Plan Sugestion");
        this.deadline.setText("Formato: yyyy-mm-dd");
        this.regCode.setText(regCode);
        this.getStageToAdd().setText("escreva descrição da etapa");
        this.mainPanel.setPreferredSize(new Dimension(500, 400));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getDescription() {
        return description;
    }

    public JTextField getDeadline() {
        return deadline;
    }

    public JTextField getRepairCost() {
        return repairCost;
    }

    public JButton getAddStageButton() {
        return addStageButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getStageToAdd() {
        return stageToAdd;
    }
}
