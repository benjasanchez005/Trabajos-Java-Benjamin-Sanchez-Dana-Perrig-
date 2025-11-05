/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();

        lista.insertarInicio(30);
        lista.insertarInicio(20);
        lista.insertarInicio(10);

        // Imprimir la lista completa
        lista.imprimir();
    }
}
