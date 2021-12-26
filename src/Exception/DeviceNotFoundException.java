package Exception;

public class DeviceNotFoundException extends Exception{
    public DeviceNotFoundException() {
        super();
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
