/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicointegrador;

/**
 *
 * @author Dana
 */
import java.time.LocalDateTime;

public interface Planner {
    void programar(Recordatorio r);
    Recordatorio proximo(); // Saca el más próximo
    void reprogramar(String id, LocalDateTime nuevaFecha);
    int size();
}
