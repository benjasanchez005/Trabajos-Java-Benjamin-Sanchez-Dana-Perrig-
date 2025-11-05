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

public class SolicitudCirugia {
    public String id;
    public String matricula; // médico
    public int durMin;
    public LocalDateTime deadline;

    public SolicitudCirugia(String id, String matricula, int durMin, LocalDateTime deadline) {
        this.id = id;
        this.matricula = matricula;
        this.durMin = durMin;
        this.deadline = deadline;
    }
}
