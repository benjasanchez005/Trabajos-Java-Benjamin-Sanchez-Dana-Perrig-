/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

/**
 *
 * @author Dana
 */
public class Main{
    public static void main(String[] args) {
        // Crear tres nodos
        Nodo nodo1 = new Nodo(10);
        Nodo nodo2 = new Nodo(20);
        Nodo nodo3 = new Nodo(30);
    
        // Enlazarlos manualmente
        nodo1.siguiente = nodo2;
        nodo2.siguiente = nodo3;
    
        // Imprimir la lista completa
        Nodo actual = nodo1;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}

