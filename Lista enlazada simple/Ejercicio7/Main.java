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
public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        lista.insertarFinal(40);
        
        System.out.println("Lista:");
        lista.imprimir();
        
        lista.invertir();
        
        System.out.println("Lista:");
        lista.imprimir();
    }
}
