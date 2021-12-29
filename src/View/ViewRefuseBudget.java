package View;

import javax.swing.*;
import java.awt.*;

public class ViewRefuseBudget extends JFrame{
    private JPanel mainPanel;
    private JTextField deviceID;
    private JButton saveButton;

    public ViewRefuseBudget() {
        super("Refuse Budget Form");
        this.mainPanel.setPreferredSize(new Dimension(270, 80));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getDeviceID() {
        return deviceID;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
