package Model.Worker;

import java.time.Month;
import java.time.Year;

public interface IManager extends IWorker{
    /**
     * Método que adiciona a avaliação do gestor do centro ao mapa de avaliações
     * @param y ano da avaliação
     * @param m mês da avaliação
     * @param eval descrição da avaliação
     */
    void addEval(Year y, Month m , String eval);
}
