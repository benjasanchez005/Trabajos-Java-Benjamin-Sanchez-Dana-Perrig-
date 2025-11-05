/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio5;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertarFinal(5);
        lista.insertarFinal(15);
        lista.insertarFinal(25);
        lista.insertarFinal(35);

        System.out.println("Lista:");
        lista.imprimir();
        
        System.out.println("Buscando número 25..."+ lista.buscarDato(25));
        
        System.out.println("Buscando número 100..."+ lista.buscarDato(100));
        
    }
}

