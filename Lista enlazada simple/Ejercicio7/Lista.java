/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio7;

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
    
    public void invertir() {
        // Si la lista está vacía o tiene un solo elemento, no hay nada que invertir
        if (head == null || head.siguiente == null) {
            return;
        }

        Nodo prev = null;
        Nodo actual = head;
        Nodo next = null;

        while (actual != null) {
            next = actual.siguiente; // guardamos el siguiente nodo
            actual.siguiente = prev;  // invertimos el enlace
            prev = actual;            // avanzamos el puntero anterior
            actual = next;           // avanzamos el puntero actual
        }

        head = prev; // al final, el último nodo se convierte en el nuevo head
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
