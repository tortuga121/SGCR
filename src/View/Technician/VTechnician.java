package View.Technician;

public class VTechnician {
    private VTechnicianOptions options;
    private VRepairPlanSugestion planSugestion;
    private VRepairStep repairStep;

    public VTechnician() {
        this.options = new VTechnicianOptions();
        this.planSugestion = new VRepairPlanSugestion();
        this.repairStep = new VRepairStep();
    }

    public VTechnicianOptions getOptions() {
        return options;
    }

    public VRepairPlanSugestion getPlanSugestion() {
        return planSugestion;
    }

    public VRepairStep getRepairStep() {
        return repairStep;
    }
}
