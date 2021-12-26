package Model.Client;


public class Client {
    private final String nif;
    private final String email;

    public Client(String nif, String email) {
        this.nif = nif;
        this.email = email;
    }
    public static boolean isValidNIF(String nif) {
        return nif.length() == 9 && nif.matches("[0-9]+");
    }
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
