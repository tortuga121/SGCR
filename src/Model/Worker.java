package Model;

public class Worker implements IWorker {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public Worker(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Worker clone() {
        return new Worker(name,id);
    }
}
