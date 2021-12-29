package View;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ViewRepairPlan extends JFrame{
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
    private DefaultListModel<String> listModel;

    public ViewRepairPlan() {
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
}
