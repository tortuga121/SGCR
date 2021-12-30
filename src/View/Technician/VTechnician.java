package View.Technician;

public class VTechnician implements IVTechnician {
    private ViewTechnicianOptions options;
    private ViewRepairPlanSugestion planSugestion;
    private ViewRepairStep repairStep;

    public VTechnician() {
        this.options = new ViewTechnicianOptions();
        this.planSugestion = new ViewRepairPlanSugestion();
        this.repairStep = new ViewRepairStep();
    }

    public ViewTechnicianOptions getOptions() {
        return options;
    }

    public ViewRepairPlanSugestion getPlanSugestion() {
        return planSugestion;
    }

    public ViewRepairStep getRepairStep() {
        return repairStep;
    }
}
