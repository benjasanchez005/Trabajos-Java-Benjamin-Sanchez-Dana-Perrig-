/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicointegrador;

import java.util.Objects;

/**
 *
 * @author Dana
 */
public class Paciente {
    public String dni, nombre;

    public Paciente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return dni + " - " + nombre;
    }
    
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente p = (Paciente) o;
        return Objects.equals(dni, p.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
    
}
