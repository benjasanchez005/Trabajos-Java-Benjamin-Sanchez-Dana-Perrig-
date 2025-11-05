/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

/**
 *
 * @author Dana
 */
public class Lista {
    private Nodo head; // primer nodo de la lista

    public Lista() {
        this.head = null;
    }

    public void insertarFinal(int dato){
        //Crear el nuevo nodo usando el constructor correcto
        Nodo nuevoNodo = new Nodo(dato);
        //Si la lista esta vacia
        if(this.head == null){
            this.head = nuevoNodo; //El nuevo nodo es el head
        } else {
            //Si la lista no esta vacia, recorro hasta el final
            Nodo actual = this.head;
            while(actual.siguiente != null){
                actual = actual.siguiente; //Avanzo al siguiente nodo
            }
            actual.siguiente = nuevoNodo; //El ultimo nodo apunta al nuevo nodo
        }
    }

    public void eliminar(int valor) {
        if (head == null) return; // lista vacía

        // Si el valor está en head
        if (head.dato == valor) {
            head = head.siguiente;
            return;
        }

        // Si el valor esta en el resto de la lista
        Nodo actual = head;
        while (actual.siguiente != null && actual.siguiente.dato != valor) {
            actual = actual.siguiente;
        }

        // Si lo encontró, lo salta
        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
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
}

