package View.Receptionist;

public class VReceptionist {
    private VBudgetRequest requestBudget;
    private VClientNIF deviceByClient;
    private VReceptionistOptions options;
    private VExpressRequest expressRequest;

    public VReceptionist() {
        this.deviceByClient = new VClientNIF();
        this.requestBudget = new VBudgetRequest();
        this.options = new VReceptionistOptions();
        this.expressRequest = new VExpressRequest();
    }

    public VBudgetRequest getRequestBudget() {
        return requestBudget;
    }

    public VClientNIF getClientNIF() {
        return deviceByClient;
    }

    public VReceptionistOptions getReceptionistOptions() {
        return options;
    }

    public VExpressRequest getExpressRequest() {
        return expressRequest;
    }
}
