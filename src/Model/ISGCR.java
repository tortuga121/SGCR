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
     * @param rp plano de reparação
     * @throws DeviceNotFoundException
     */
    void sugestRepairPlan(IRepairPlan rp) throws DeviceNotFoundException;

    /**
     * Método que encontra o plano de reparação mais urgente através de um get no catálogo de reparações
     * @return retorna o plano de reparação mais urgente
     * @throws NoRepairException
     */
    IRepairPlan getMostUrgentRepair() throws NoRepairException;

    /**
     * Método que faz um get do plano de reparação no catálogo de reparações e avança para o passo seguinte dessa reparação, adicionando essa reparação ao mapa de reparações do técnico
     * @param regCode identificador do equipamento
     * @param techId identificador do técnico
     * @param cost custo do passo
     * @param time tempo demora a realizar esse passo
     * @throws WorkerDoesNotExist
     * @throws NoRepairException
     * @throws NoMoreStepsException
     * @throws OutOfbudgetException
     */
    void repairNextStep(int regCode, int techId, double cost, double time) throws WorkerDoesNotExist, NoRepairException, NoMoreStepsException, OutOfbudgetException;

    /**
     * Método que devolve um mapa com o número de reparações que cada técnico realizou
     * @return retorna um mapa com o número de reparações que cada técnico realizou
     * @throws WorkerDoesNotExist
     */
    Map<Integer,Double> evaluateCenterFunctioning () throws WorkerDoesNotExist;

    /**
     * Método que adiciona um tipo de reparação expresso ao catálogo de reparações, adiciona participação do técnico ao catálogo dos trabalhadores e adiciona a entrega do equipamento pelo funcionário de balcão ao cliente ao catálogo dos workers.
     * @param type tipo de reparação expresso
     * @param techId identificador do técnico
     * @pa ram recepId identificador do rececionista
     * @return retorna o custo desse tipo de reparação
     * @throws DeviceNotFoundException
     * @throws WorkerDoesNotExist
     */
    double makeExpressRepair(String type, int techId, int recepId) throws DeviceNotFoundException, WorkerDoesNotExist;

    /**
     * Este método identifica a que classe pertence um trabalhador.
     * @param id identificador do trabalhador
     * @return retorna o tipo de trabalhador encontrado
     * @throws WorkerDoesNotExist
     */
    Class<?> getWorkerType(int id) throws WorkerDoesNotExist;
    boolean login(int id, String pass) throws WorkerDoesNotExist;
    int getBudgetRequest() throws DeviceNotFoundException;

    /**
     * Este método verifica se existem equipamentos abandonados no catálogo dos equipamentos e se há equipamentos por reparar fora do prazo
     */
    void checkForDealines();

    /**
     * Este método incrementa o número de equipamentos entregues a um cliente por um rececionista no catálogo dos trabalhadores
     * @param recepId identificador do rececionista
     * @throws WorkerDoesNotExist
     */
    void deliverDevice(int recepId) throws WorkerDoesNotExist;

    /**
     * Método que adiciona um trabalhador ao catálogo de trabalhadores, verificando a que classe pertence e criando o seu Login.
     * @param name nome do trabalhador
     * @param id identificador do trabalhador
     * @param c classe do trabalhador
     * @param password password do trabalahdor
     */
    void addWorker(String name, int id, Class<?> c, String password);

    /**
     * Método que devolve um Set com os equipamentos de um cliente identificado através do seu NIF.
     * @param nif identificador do cliente
     * @return retorna um Set com os equipamentos pertencentes a um dado cliente
     * @throws DeviceNotFoundException
     */
    Set<Integer> getClientDevices(String nif) throws DeviceNotFoundException;

    /**
     * Método que cria um código de identificação para um equipamento
     * @return retorna um identificador gerado aleatoriamente para o equipamento adicionado ao catálogo dos equipamentos
     */
    int generateNewregistrationCode();

    /**
     * Método que devolve o identificador de um técnico de reparações disponível
     * @return retorna o identificador do técnico de reparações
     * @throws WorkerDoesNotExist
     */
    int availableTechnician() throws WorkerDoesNotExist;
    Set<String> getExpressTypes();
    IDevice getDevice(int id) throws DeviceNotFoundException;
    Set<Integer> getDeliveredDevicesbyNif(String nif) throws DeviceNotFoundException;

    /**
     * Método que incrementa o número de entregas de equipamentos de um rececionista
     * @param id identificador do equipamento
     * @param recepid identificador do rececionista
     * @throws DeviceNotFoundException
     * @throws WorkerDoesNotExist
     */
    void devicePickup(int id, int recepid) throws DeviceNotFoundException, WorkerDoesNotExist;

    /**
     * Método que permite ao gestor do centro efetuar a avaliação mensal, adicionando essa avaliação ao catáloog dos trabalhadores
     * @param y ano
     * @param m mês
     * @param eval descrição da avaliação
     * @param manegerId identificador do gestor
     * @throws WorkerDoesNotExist
     */
    void makeMonthEval(Year y, Month m , String eval, int manegerId) throws WorkerDoesNotExist;

    /**
     * Método que para cada técnico de reparações adiciona a sua avaliação (número total de participações, média da duração das suas reparações e média do desvio em relação às durações previstas.
     * @return retorna um mapa com as avaliações de cada técnico
     */
    HashMap<Integer, ArrayList<Double>> techstats();

    /**
     * Método que para cada rececionista adiciona a sua avaliação (número total de recolha de equipamentos e número total de entrega de equipamentos.
     * @return retorna um mapa com as avaliações de cada técnico
     */
    HashMap<Integer,ArrayList<Integer>> recepStats();

    /**
     * Método que adiciona o número de reparações efetuadas por um técnico no mapa dos trabalhadores.
     * @return retorna um mapa com as participações de cada técnico
     */
    HashMap<Integer,HashMap<Integer, HashSet<Integer>>> techDetailStats();

    void setAvailable(int techID, boolean av) throws WorkerDoesNotExist;

}
