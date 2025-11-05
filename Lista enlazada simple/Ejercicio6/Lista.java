/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio6;

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
    
    public int cantidad(){
        if (head == null) {
            return 0;
        }
        Nodo actual = this.head;
        int contador = 0;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }  
        return contador;
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
