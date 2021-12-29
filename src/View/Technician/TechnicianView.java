package View.Technician;

import Model.Worker.Technician;

public class TechnicianView implements ITechnicianView {
    private ViewTechnicianOptions options;
    private ViewRepairPlanSugestion planSugestion;
    private ViewRepairStep repairStep;

    public TechnicianView() {
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
