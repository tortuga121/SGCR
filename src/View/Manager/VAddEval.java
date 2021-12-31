package View.Manager;

import javax.swing.*;
import java.awt.*;

public class VAddEval extends JFrame {
    private JPanel mainPanel;
    private JTextField year;
    private JTextField month;
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JTextArea description;
    private JLabel descriptionLabel;
    private JButton saveButton;

    public VAddEval() {
        super("Add Evaluation");
        this.mainPanel.setPreferredSize(new Dimension(300, 350));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getYear() {
        return year;
    }

    public JTextField getMonth() {
        return month;
    }

    public JTextArea getDescription() {
        return description;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
