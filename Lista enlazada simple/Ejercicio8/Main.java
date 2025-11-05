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
public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertarEn(0, 1);
        lista.insertarEn(1, 2); 
        lista.insertarEn(2, 4); 

        System.out.println("Lista:");
        lista.imprimir(); // 1, 2, 4

        lista.insertarEn(2, 3); // Inserta 3 en la posición 2

        System.out.println("Lista después de insertar 3:");
        lista.imprimir(); // 1, 2, 3, 4
    }
}
