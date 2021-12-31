package View.Technician;

import javax.swing.*;
import java.awt.*;

public class VTechnicianOptions extends JFrame {
    private JPanel mainPanel;
    private JLabel options;
    private JButton sugestPlan;
    private JButton editPlan;
    private JButton unavailable;
    private JButton available;

    public VTechnicianOptions() {
        super("Technician Option Menu");
        this.mainPanel.setPreferredSize(new Dimension(500, 400));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JButton getSugestPlan() {
        return sugestPlan;
    }

    public JButton getEditPlan() {
        return editPlan;
    }

    public JButton getUnvailable() {
        return unavailable;
    }

    public JButton getAvailable() {
        return available;
    }
}
