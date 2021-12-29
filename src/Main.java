import View.Login.ViewLogin;

public class Main {
    public static void main(String[] args) {
        ViewLogin login = new ViewLogin();
        login.setVisible(true);
        login.getSaveButton().addActionListener(e -> {
            String user = login.getUserTextField().getText();
            String pass = login.getPassField().getText();
            System.out.println("Username: " + user);
            System.out.println("Password: " + pass);
            //TODO descobrir como nao mostrar a pass
        });
    }
}
