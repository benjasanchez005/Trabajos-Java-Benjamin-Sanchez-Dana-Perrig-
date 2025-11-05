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

public class Recordatorio {
    String id;
    LocalDateTime fecha;
    String dniPaciente;
    String mensaje;

    public Recordatorio(String id, LocalDateTime fecha, String dniPaciente, String mensaje) {
        this.id = id;
        this.fecha = fecha;
        this.dniPaciente = dniPaciente;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "[" + id + " | " + fecha + " | " + dniPaciente + " | " + mensaje + "]";
    }
}