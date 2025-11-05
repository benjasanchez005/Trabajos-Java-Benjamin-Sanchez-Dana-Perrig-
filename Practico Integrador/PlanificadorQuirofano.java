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
import java.util.*;
import java.time.LocalDateTime;

public interface PlanificadorQuirofano {
    void procesar(SolicitudCirugia s);
    List<String> topKMedicosBloqueados(int K);
}
