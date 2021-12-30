package View.Receptionist;

import javax.swing.*;
import java.awt.*;

public class VRefuseBudget extends JFrame{
    private JPanel mainPanel;
    private JTextField nifCliente;
    private JButton saveButton;
    private JLabel nifLabel;

    public VRefuseBudget() {
        super("Refuse Budget Form");
        this.mainPanel.setPreferredSize(new Dimension(270, 80));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getNIF() {
        return nifCliente;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
