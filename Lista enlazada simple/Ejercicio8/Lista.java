/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio8;

/**
 *
 * @author Dana
 */

public class Lista {
    private Nodo head; // primer nodo de la lista

    public Lista() {
        this.head = null;
    }
    
    public void insertarEn(int pos, int valor) {
        Nodo nuevo = new Nodo(valor);

        // Caso 1: insertar al inicio
        if (pos == 0) {
            nuevo.siguiente = head;
            head = nuevo;
            return;
        }

        // Recorremos hasta el nodo anterior a la posición deseada
        Nodo actual = head;
        int contador = 0;

        while (actual != null && contador < pos - 1) {
            actual = actual.siguiente;
            contador++;
        }

        // Si la posición no existe (mayor que la cantidad de nodos)
        if (actual == null) {
            System.out.println("Posición fuera de rango");
            return;
        }

        // Caso 2: insertar entre medio o al final
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
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
