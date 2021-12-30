package View.Device;

import javax.swing.*;
import java.awt.*;

public class VDevice extends JFrame {
    private JPanel mainPanel;
    private JLabel regCode;
    private JTextField regCodeTxt;
    private JTextField nifOwnerTxt;
    private JTextPane probDescriptionTxt;
    private JLabel nifOwner;
    private JLabel probDescription;

    public VDevice(String regCode, String nif, String description) {
        super("Device");
        this.regCodeTxt.setText(regCode);
        this.nifOwnerTxt.setText(nif);
        this.probDescriptionTxt.setText(description);
        this.mainPanel.setPreferredSize(new Dimension(400, 200));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

}
