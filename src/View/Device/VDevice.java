package View.Device;

import Model.Devices.Device;

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
    private JTextField nameTxt;

    public VDevice(Device d) {
        super("Device");
        this.regCodeTxt.setText(Integer.toString(d.getRegCode()));
        this.nifOwnerTxt.setText(d.getNifOwner());
        this.probDescriptionTxt.setText(d.getDescription());
        this.nameTxt.setText(d.getName());
        this.mainPanel.setPreferredSize(new Dimension(400, 200));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

}
