package Exception;

public class WorkerDoesNotExist extends Exception{
    public WorkerDoesNotExist(String msg) {
        super(msg);
    }
    public WorkerDoesNotExist() {
        super();
    }
}
