package View.Receptionist;

public class VReceptionist {
    private VBudgetRequest requestBudget;
    private VDeviceByClient deviceByClient;
    private VReceptionistOptions options;

    public VReceptionist() {
        this.deviceByClient = new VDeviceByClient();
        this.requestBudget = new VBudgetRequest();
        this.options = new VReceptionistOptions();
    }

    public VBudgetRequest getRequestBudget() {
        return requestBudget;
    }

    public VDeviceByClient getDeviceByClient() {
        return deviceByClient;
    }

    public VReceptionistOptions getReceptionistOptions() {
        return options;
    }
}
