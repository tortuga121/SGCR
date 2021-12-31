package View.Receptionist;

import javax.swing.*;
import java.awt.*;

public class VExpressRequest extends JFrame {
    private JPanel mainPanel;
    private JLabel typeLabel;
    private JTextField repairtype;
    private JButton saveButton;

    public VExpressRequest() {
        super("Express Request Form");
        this.mainPanel.setPreferredSize(new Dimension(300, 100));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getRepairType() {
        return repairtype;
    }
}
