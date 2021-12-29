package View.Login;

import javax.swing.*;

public class ViewLogin extends JFrame{
    private JPanel mainPanel;
    private JButton saveButton;
    private JLabel username;
    private JLabel password;
    private JTextField userTextField;
    private JPasswordField passwordField;

    public ViewLogin() {
        super("Login Form");
        this.mainPanel.setSize(500, 200);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public JTextField getUserTextField() {
        return userTextField;
    }

    public JTextField getPassField() {
        return passwordField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
