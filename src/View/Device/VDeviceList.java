package View.Device;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VDeviceList extends JFrame {
    private JPanel mainPanel;
    private JLabel nifLabel;
    private JTextField nifCliente;
    private JList<String> deviceList;
    private JButton selectButton;

    public VDeviceList(String nif, List<String> l) {
        super("Device");
        this.nifCliente.setText(nif);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(l);
        deviceList.setModel(listModel);
        this.mainPanel.setPreferredSize(new Dimension(400, 500));
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public JList<String> getDeviceList() {
        return deviceList;
    }
}
