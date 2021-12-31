package Model.Worker;

import Model.Devices.IDevice;

public interface IReceptionist extends IWorker{
    /**
     * Método que devolve o número total de entregas concluídas de equipamentos aos clientes
     * @return número total de entregas realizadas
     */
    int totalDeliveries();

    /**
     * Método que devolve o número total de aparelhos recebidos pelo funcionário de balcão
     * @return número total de aparelhos recebidos
     */
    int totalReceptions();

    /**
     * Método que adiciona uma entrega de um equipamento ao respetivo cliente
     */
    void addDelivery();

    /**
     * Método que adiciona uma receção de um quipamento pelo rececionista
     */
    void addReception();

}
