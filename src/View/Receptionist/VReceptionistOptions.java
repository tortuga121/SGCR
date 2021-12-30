package View.Receptionist;

import javax.swing.*;
import java.awt.*;

public class VReceptionistOptions extends JFrame {
    private JPanel mainPanel;
    private JButton refuseBudget;
    private JButton requestBudget;
    private JLabel optionsLabel;

    public VReceptionistOptions() {
        super("Receptionist Option Menu");
        this.mainPanel.setPreferredSize(new Dimension(500, 400));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JButton getRefuseBudget() {
        return refuseBudget;
    }

    public JButton getRequestBudget() {
        return requestBudget;
    }
}
