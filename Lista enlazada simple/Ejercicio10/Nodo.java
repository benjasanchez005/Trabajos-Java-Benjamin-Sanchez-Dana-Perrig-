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
public class Nodo {
    Alumno dato; // almacena el alumno
    Nodo siguiente;  // puntero al siguiente nodo

    // Constructor
    public Nodo(Alumno dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
