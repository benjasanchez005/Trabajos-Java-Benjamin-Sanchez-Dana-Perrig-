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

import java.util.Objects;

public class Medico {
    public String matricula, nombre, especialidad;

    public Medico(String matricula, String nombre, String especialidad) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return matricula + " - " + nombre + " (" + especialidad + ")";
    }
    
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medico)) return false;
        Medico m = (Medico) o;
        return Objects.equals(matricula, m.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
