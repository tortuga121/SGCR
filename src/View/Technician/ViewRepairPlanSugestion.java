package View.Technician;

import javax.swing.*;
import java.awt.*;

public class ViewRepairPlanSugestion extends JFrame{
    private JPanel mainPanel;
    private JLabel deviceID;
    private JTextField deviceIDtxt;
    private JTextArea problemDescriptionTxt;
    private JLabel problemDescription;
    private JLabel repairSteps;
    private JButton saveButton;
    private JButton addItem;
    private JTextField addToList;
    private JButton removeItem;
    private JTextField removeFromList;
    private JList<String> repairStepsList;
    private JLabel deadline;
    private JTextField deadlineTxt;
    private JLabel planCost;
    private JTextField planCostTxt;
    private JButton editStage;
    private JTextField selecioneNaListaATextField;
    private DefaultListModel<String> listModel;

    public ViewRepairPlanSugestion() {
        super("Repair Plan Sugestion Form");
        listModel = new DefaultListModel<>();
        this.addItem.addActionListener(e -> {
            if (addToList.getText().equals("") || addToList.getText().equals("escreva aqui")) {
                addToList.setText("escreva aqui");
            } else {
                listModel.addElement(addToList.getText());
                repairStepsList.setModel(listModel);
                addToList.setText("");
            }
        });

        this.removeItem.addActionListener(e -> {
            if (addToList.getText().equals("") || addToList.getText().equals("escreva aqui")) {
                addToList.setText("escreva aqui");
            } else {
                for (int i = 0; i < listModel.getSize(); i++){
                    if (listModel.getElementAt(i).equals(removeFromList.getText())) {
                        listModel.removeElementAt(i);
                        repairStepsList.setModel(listModel);
                        removeFromList.setText("");
                    }
                }
            }
        });

        this.mainPanel.setPreferredSize(new Dimension(650, 500));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    //TODO meter as cenas das etapas e passos e distinção entre feitos ou não
    //TODO ideia: meter dentro dum plano, tabela dos feitos e dos por fazer com botoes para editar

    public JTextField getDeviceID() {
        return deviceIDtxt;
    }

    public JTextArea getProblemDescription() {
        return problemDescriptionTxt;
    }

    public DefaultListModel<String> getRepairStepsList() {
        return listModel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getPlanCost() {
        return planCostTxt;
    }

    public JTextField getDeadline() {
        return deadlineTxt;
    }


}
