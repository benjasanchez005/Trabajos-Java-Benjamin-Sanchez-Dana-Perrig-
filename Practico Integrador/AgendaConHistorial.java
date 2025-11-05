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

public interface AgendaConHistorial extends AgendaMedico {
    boolean reprogramar(String idTurno, LocalDateTime nuevaFecha);
    boolean undo();
    boolean redo();
}
