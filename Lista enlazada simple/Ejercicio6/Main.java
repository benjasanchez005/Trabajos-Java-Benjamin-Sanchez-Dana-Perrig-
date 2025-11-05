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
public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertarFinal(1);
        lista.insertarFinal(2);
        lista.insertarFinal(3);
        lista.insertarFinal(4);
        lista.insertarFinal(5);
        
        System.out.println("Lista:");
        lista.imprimir();
        
        System.out.println("Hay "+ lista.cantidad()+" nodos en la lista");
    }
}
