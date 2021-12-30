package View.Receptionist;

import javax.swing.*;
import java.awt.*;

public class VBudgetRequest extends JFrame{
    private JPanel mainPanel;
    private JTextField devideIDtxt;
    private JTextField receptionistIDtxt;
    private JTextField clientNIFtxt;
    private JLabel devideID;
    private JLabel clientNIF;
    private JLabel receptionistID;
    private JTextArea problemDescriptionTxt;
    private JButton saveButton;
    private JLabel problemDescription;

    public VBudgetRequest() {
        super("Budget Request Form");
        this.mainPanel.setPreferredSize(new Dimension(500, 400));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getDevideID() {
        return devideIDtxt;
    }

    public JTextField getReceptionistID() {
        return receptionistIDtxt;
    }

    public JTextField getClientNIF() {
        return clientNIFtxt;
    }

    public JTextArea getProblemDescription() {
        return problemDescriptionTxt;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
