/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

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
        Nodo nuevo = new Nodo(dato);
        if(this.head == null){
            this.head = nuevo;
        } else {
            Nodo actual = this.head;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
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
