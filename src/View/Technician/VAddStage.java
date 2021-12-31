package View.Technician;

import Model.Repair.Step;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VAddStage extends JFrame {
    private JPanel mainPanel;
    private JLabel descriptionLabel;
    private JTextField description;
    private JList<String> stepList;
    private JButton adicionarPassoButton;
    private JButton saveButton;
    private JTextField stepToAdd;
    private DefaultListModel<String> listModel;

    public VAddStage(String description) {
        super("Add Stage Form");
        this.description.setText(description);
        listModel = new DefaultListModel<>();
        this.mainPanel.setPreferredSize(new Dimension(500, 500));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getStepToAdd() {
        return stepToAdd;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public JList<String> getStepList() {
        return stepList;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getAdicionarPassoButton() {
        return adicionarPassoButton;
    }
}
