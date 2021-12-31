package Model.Devices;
import Exception.*;

import java.time.LocalDate;
import java.util.Set;

public interface IDeviceCatalog {
    /**
     * Método que adiciona um pedido de reparação de um equipamento à queue de pedidos de orçamento e guarda o dispositivo no catálogo.
     * @param dev é um device
     * @throws InvalidRegistrationCodeException
     */
    void addRequest(IDevice dev) throws InvalidRegistrationCodeException;

    /**
     * Método que adiciona um equipamento reparado ao mapa de equipamentos prontos para levantar.
     * @param regCode é o código de registo de um equipamento
     * @return retorna a data limite para o cliente levantar o equipamento (dentro de 90 dias)
     * @throws DeviceNotFoundException
     */
    LocalDate addToPickup(int regCode) throws DeviceNotFoundException;

    /**
     * Método que retira o pedido de orçamento mais antigo do mapa de equipamentos
     * @throws DeviceNotFoundException
     */
    void popOldestRequest() throws DeviceNotFoundException;

    /**
     * Método que devolve o pedido de reparação mais antigo
     * @return retorna um equipamento com as respetivas informações para a reparação
     * @throws DeviceNotFoundException
     */
    int getOldestRequest() throws DeviceNotFoundException;
    boolean existsDevice(int regCode);

    /**
     * Método que verifica se há equipamentos abandonados por algum cliente
     */
    void checkforAbandonedDevices();

    /**
     * Método que devolve um Set com os equipamentos registados no centro encontrados através do NIF de um cliente
     * @param nif o NIF é o identificador de um cliente
     * @return retorna um Set dos equipamentos que um cliente tem no centro de reparações
     * @throws DeviceNotFoundException
     */
    Set<Integer> getdevicesbyNif(String nif) throws DeviceNotFoundException;

    /**
     * Método que devolve um Set com os equipamentos entregues a um cliente encontrados através do NIF do mesmo
     * @param nif o NIF é o identificador de um cliente
     * @return retorna um Set dos equipamentos entregues a um cliente em específico
     * @throws DeviceNotFoundException
     */
    Set<Integer> getDeliveredDevicesbyNif(String nif) throws DeviceNotFoundException;
    IDevice getdevice(int id) throws DeviceNotFoundException;

    /**
     * Método que remove um equipamento do mapa de equipamentos por levantar quando o cliente vai à loja levantar o seu equipamento
     * @param id identificador do equipamento
     * @throws DeviceNotFoundException
     */
    void pickupDevice(int id) throws DeviceNotFoundException;

}
