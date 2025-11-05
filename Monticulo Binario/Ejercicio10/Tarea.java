/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio10;

/**
 *
 * @author Dana
 */
public class Tarea {
    String descripcion;
    int prioridad; // menor número = más urgente

    public Tarea(String descripcion, int prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return descripcion + " (Prioridad " + prioridad + ")";
    }
}
