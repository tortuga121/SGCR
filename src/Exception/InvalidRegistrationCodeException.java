package Exception;

public class InvalidRegistrationCodeException extends Exception{
    public InvalidRegistrationCodeException() {
        super();
    }

    public InvalidRegistrationCodeException(String message) {
        super(message);
    }
}
