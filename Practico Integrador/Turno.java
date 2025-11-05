/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicointegrador;

import java.time.LocalDateTime;

/**
 *
 * @author Dana
 */
public class Turno implements Comparable<Turno> {
    public String id;
    public String dniPaciente;
    public String matriculaMedico;
    public LocalDateTime fechaHora;
    public int duracionMin;
    public String motivo;

    public Turno(String id, String dniPaciente, String matriculaMedico,LocalDateTime fechaHora, int duracionMin, String motivo) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.matriculaMedico = matriculaMedico;
        this.fechaHora = fechaHora;
        this.duracionMin = duracionMin;
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id='" + id + '\'' +
                ", dniPaciente='" + dniPaciente + '\'' +
                ", matriculaMedico='" + matriculaMedico + '\'' +
                ", fechaHora=" + fechaHora +
                ", duracionMin=" + duracionMin +
                ", motivo='" + motivo + '\'' +
                '}';
    }

    //Compara dos turnos por fecha y hora (más temprano primero).
    @Override
    public int compareTo(Turno otro) {
        return this.fechaHora.compareTo(otro.fechaHora);
    }
}
