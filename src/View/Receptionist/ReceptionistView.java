package View.Receptionist;

public class ReceptionistView implements IReceptionistView{
    private ViewBudgetRequest requestBudget;
    private ViewRefuseBudget refuseBudget;
    private ViewReceptionistOptions options;

    public ReceptionistView() {
        this.refuseBudget = new ViewRefuseBudget();
        this.requestBudget = new ViewBudgetRequest();
        this.options = new ViewReceptionistOptions();
    }

    public ViewBudgetRequest getRequestBudget() {
        return requestBudget;
    }

    public ViewRefuseBudget getRefuseBudget() {
        return refuseBudget;
    }

    public ViewReceptionistOptions getReceptionistOptions() {
        return options;
    }
}
