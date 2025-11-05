/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio9;

/**
 *
 * @author Dana
 */
import java.util.HashSet;

public class Lista {
    private Nodo head;

    public Lista() {
        this.head = null;
    }

    public void insertarFinal(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (this.head == null) {
            this.head = nuevoNodo;
        } else {
            Nodo actual = this.head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public void imprimir() {
        if (head == null) {
            System.out.println("Vacía");
        }else{
            Nodo actual = this.head;
            while (actual != null) {
                System.out.println(actual.dato);
                actual = actual.siguiente;
            } 
        }
    } 


    public void eliminarDuplicados() {
        if (head == null) return; // lista vacía

        HashSet<Integer> vistos = new HashSet<>();
        Nodo actual = head;
        Nodo anterior = null;

        while (actual != null) {
            if (vistos.contains(actual.dato)) {
                // Dato duplicado: eliminar el nodo
                anterior.siguiente = actual.siguiente;
            } else {
                // Dato nuevo: lo agrego al conjunto
                vistos.add(actual.dato);
                anterior = actual;
            }
            actual = actual.siguiente;
        }
    }
}
