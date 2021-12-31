package Model;
import Exception.*;
import Model.Devices.IDevice;
import Model.Repair.IRepairPlan;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

public interface ISGCR {
    /**
     * Método que adiciona um pedido de orçamento ao catálogo dos equipamentos
     * @param device equipamento para o qual foi pedido um orçamento
     * @param recepcionistId identificador do rececionista
     * @throws WorkerDoesNotExist
     * @throws InvalidRegistrationCodeException
     */
    void addBudgetRequest(IDevice device, int recepcionistId) throws WorkerDoesNotExist, InvalidRegistrationCodeException;

    /**
     * Método que remove um pedido de orçamento do catálogo de equipamentos quando o orçamento é recusado pelo cliente
     * @param regCode identificador do equipamento
     * @return retorna a adição do equipamento cujo orçamento foi recusado ao catálogo dos equipamentos por recolher pelo cliente
     * @throws DeviceNotFoundException
     * @throws WorkerDoesNotExist
     */
    LocalDate refuseBudget(int regCode) throws DeviceNotFoundException, WorkerDoesNotExist;

    /**
     * Método que remove um equipamento do mapa de equipamentos com orçamentos por aprovar
     * @param regCode identificador do equipamento
     * @throws DeviceNotFoundException
     */
    void acceptBudget(int regCode) throws DeviceNotFoundException;

    /**
     * Método que encontra  o plano de reparação para um equipamento através de um get do pedido de reparação mais antigo do catálogo
     * @param rp
     * @throws DeviceNotFoundException
     */
    void sugestRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;
    IRepairPlan getMostUrgentRepair() throws NoRepairException;
    void repairNextStep(int regCode, int techId, double cost, double time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsException, OutOfbudgetException;
    Map<Integer,Double> evaluateCenterFunctioning () throws WorkerDoesNotExist;
    double makeExpressRepair(String type, int techId, int recepId) throws DeviceNotFoundException, WorkerDoesNotExist;
    Class<?> getWorkerType(int id) throws WorkerDoesNotExist;
    boolean login(int id, String pass) throws WorkerDoesNotExist;
    IDevice getBudgetRequest() throws DeviceNotFoundException;
    void checkForDealines();
    void deliverDevice(int recepId) throws WorkerDoesNotExist;
    void addWorker(String name, int id, Class<?> c, String password);
    Set<Integer> getClientDevices(String nif) throws DeviceNotFoundException;
    int generateNewregistrationCode();
    int availableTechnician() throws WorkerDoesNotExist;
    Set<String> getExpressTypes();
    IDevice getDevice(int id) throws DeviceNotFoundException;
    Set<Integer> getDeliveredDevicesbyNif(String nif) throws DeviceNotFoundException;
    void devicePickup(int id, int recepid) throws DeviceNotFoundException, WorkerDoesNotExist;
    void makeMonthEval(Year y, Month m , String eval, int manegerId) throws WorkerDoesNotExist;
    HashMap<Integer, ArrayList<Double>> techstats();
    HashMap<Integer,ArrayList<Integer>> recepStats();
    HashMap<Integer,HashMap<Integer, HashSet<Integer>>> techDetailStats();
}
