package View.Receptionist;

public class VReceptionist {
    private VBudgetRequest requestBudget;
    private VRefuseBudget refuseBudget;
    private VReceptionistOptions options;

    public VReceptionist() {
        this.refuseBudget = new VRefuseBudget();
        this.requestBudget = new VBudgetRequest();
        this.options = new VReceptionistOptions();
    }

    public VBudgetRequest getRequestBudget() {
        return requestBudget;
    }

    public VRefuseBudget getRefuseBudget() {
        return refuseBudget;
    }

    public VReceptionistOptions getReceptionistOptions() {
        return options;
    }
}
