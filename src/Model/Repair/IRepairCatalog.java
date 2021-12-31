package Model.Repair;
import Exception.*;

public interface IRepairCatalog {
    /**
     * remove um plano de reparação
     * @param regCode identificador do plano
     * @throws DeviceNotFoundException
     */
    void unaproveBudget(int regCode) throws DeviceNotFoundException;

    /**
     * aprova um plano de recuperação
     * @param regCode identificador do plano
     * @throws DeviceNotFoundException
     */
    void approveBudget(int regCode) throws DeviceNotFoundException;

    /**
     * Adiciona um plano de Reparação
     * @param rp Plano de Reparação
     * @throws DeviceNotFoundException
     */
    void addRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;

    /**
     * retorna o id do plano de reparação mais urgente
     * @return Regcode
     * @throws NoRepairException
     */
    int mostUrgentRepair() throws NoRepairException;

    IRepairPlan getRepairPlan(int regCode) throws NoRepairException;

    /**
     * Adiciona um ExpressRepair ao Catalogo
     * @param type Tipo de Reparação
     */
    void addExpressrepair(String type);

    /**
     * Verifica se algum plano que
     * esteja à espera de aprovação
     * já passou do prazo
     */
    void checkForOutdated();

    /**
     * Adiciona um plano de recuperação
     * à coleção dos planos que estão
     * à espera de reparação
     * @param regcode Identificador do plano
     */
    void addToRepair(int regcode);
}
