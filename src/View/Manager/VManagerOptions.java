package View.Manager;

import javax.swing.*;
import java.awt.*;

public class VManagerOptions extends JFrame {
    private JPanel mainPanel;
    private JLabel optionsLabel;
    private JButton techInfo;
    private JButton receptionistInfo;
    private JButton techInterventions;
    private JButton addInfo;

    public VManagerOptions() {
        super("Manager Options");
        this.mainPanel.setPreferredSize(new Dimension(500, 400));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JButton getAddInfo() {
        return addInfo;
    }

    public JButton getTechInfo() {
        return techInfo;
    }

    public JButton getReceptionistInfo() {
        return receptionistInfo;
    }

    public JButton getTechInterventions() {
        return techInterventions;
    }
}
