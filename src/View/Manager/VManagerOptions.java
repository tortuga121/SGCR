package View.Manager;

import javax.swing.*;
import java.awt.*;

public class VManagerOptions extends JFrame {
    private JPanel mainPanel;

    public VManagerOptions() {
        super("Manager Options");
        this.mainPanel.setPreferredSize(new Dimension(500, 400));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }
}
