package Exception;

public class OutOfbudgetException extends Exception{
    public OutOfbudgetException() {
        super();
    }

    public OutOfbudgetException(String message) {
        super(message);
    }
}
