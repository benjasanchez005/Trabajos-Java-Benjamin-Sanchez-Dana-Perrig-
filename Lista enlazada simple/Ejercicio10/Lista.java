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
public class Lista {
    private Alumno head;

    public Lista() {
        head = null;
    }

    
    public void agregarAlumno(String nombre, int legajo) {
        Alumno nuevo = new Alumno(nombre, legajo);
        if (head == null) {
            head = nuevo;
        } else {
            Alumno actual = head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    
    public Alumno buscarAlumno(int legajo) {
        Alumno actual = head;
        while (actual != null) {
            if (actual.getLegajo() == legajo) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    
    public void eliminarAlumno(int legajo) {
        if (head == null) return;

        if (head.getLegajo() == legajo) {
            head = head.siguiente;
            return;
        }

        Alumno actual = head;
        while (actual.siguiente != null && actual.siguiente.getLegajo() != legajo) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }

    // Mostrar alumnos
    public void mostrar() {
        if(head == null) return;
        Alumno actual = head;
        while (actual != null) {
            System.out.println(actual);
            actual = actual.siguiente;
        }
    }
}
