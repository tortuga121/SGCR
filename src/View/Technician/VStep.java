package View.Technician;

import javax.swing.*;
import java.awt.*;

public class VStep extends JFrame {
    private JPanel mainPanel;
    private JLabel descriptionLabel;
    private JTextField description;
    private JTextField cost;
    private JTextField hours;
    private JLabel costLabel;
    private JLabel hoursLabel;
    private JButton saveButton;

    public VStep(String description) {
        super("Step");
        this.description.setText(description);
        this.mainPanel.setPreferredSize(new Dimension(500, 500));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getDescription() {
        return description;
    }

    public JTextField getCost() {
        return cost;
    }

    public JTextField getHours() {
        return hours;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
