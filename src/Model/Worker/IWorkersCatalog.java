package Model.Worker;
import Exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public interface IWorkersCatalog {
    /**
     * Método que verifica se um Worker se trata de um funcionário de balcão e incrementa o contador de equipamentos recebidos pelo mesmo
     * @param id identificador do rececionista
     * @throws WorkerDoesNotExist
     */
    void incRecepServiceCount(int id) throws WorkerDoesNotExist;

    /**
     * Método que verifica se o técnico existe e atualiza as participações desse técnico de reparações, adicionando a participação
     * @param regCode identificador do equipamento
     * @param stage etapa da reparação
     * @param techId identificador do técnico
     * @throws WorkerDoesNotExist
     */
    void updateTechicianStep(int regCode, int stage, int techId) throws WorkerDoesNotExist;
    IWorker getWorker(int id) throws WorkerDoesNotExist;
    boolean existsTechnician(int id);

    /**
     * Método que retorna um mapa com as reparações que um técnico realizou
     * @return mapa com as reparações de um técnico
     */
    Map<Integer, Double> num_rep_technicians();
    boolean existsWorker(int id);
    boolean existsManager(int id);
    boolean login(int id, String password);
    boolean existsReceptionist(int id);

    /**
     * Método que incrementa o contador de equipamentos entregues por um funcionário de balcão
     * @param id identificador do rececionista
     * @throws WorkerDoesNotExist
     */
    void incRecepDelivCount(int id) throws WorkerDoesNotExist;

    /**
     * Método que adiciona um gestor do centro ao mapa de trabalhadores
     * @param name nome do manager
     * @param id identificador do manager
     */
    void addManager(String name, int id);

    /**
     * Método que adiciona um rececionista ao mapa de trabalhadores
     * @param name nome do rececionista
     * @param id identificador do rececionista
     */
    void addReceptionist(String name, int id);

    /**
     * Método que adiciona um técnico de reparações ao mapa de trabalhadores
     * @param name nome do técnico
     * @param id identificador do técnico
     */
    void addTechnician(String name, int id);

    /**
     * Método que adiciona um login de um trabalhador ao mapa de logins
     * @param pass password do trabalhador
     * @param id identificador do trabalhador
     */
    void addLogin(String pass, int id);

    /**
     * Método que encontra um técnico de reparações disponível
     * @return retorna o identificador do primeiro técnico disponível que encontrou
     * @throws WorkerDoesNotExist
     */
    int getAvailableTech() throws WorkerDoesNotExist;
    ArrayList<IWorker> getWorkers();
    HashMap<Integer,ArrayList<Integer>> recepStats();
    HashMap<Integer,HashMap<Integer, HashSet<Integer>>> getParticipations();
}
