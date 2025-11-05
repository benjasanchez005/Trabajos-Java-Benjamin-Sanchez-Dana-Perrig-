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
import java.util.Optional;

public interface AgendaMedico {
    boolean agendar(Turno t);
    boolean cancelar(String idTurno);
    Optional<Turno> siguiente(LocalDateTime t);
    Optional<LocalDateTime> primerHueco(LocalDateTime t0, int durMin);
}
