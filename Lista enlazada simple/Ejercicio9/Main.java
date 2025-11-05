/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio9;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertarFinal(1);
        lista.insertarFinal(2);
        lista.insertarFinal(2);
        lista.insertarFinal(3);
        lista.insertarFinal(1);

        System.out.println("Lista original:");
        lista.imprimir();

        lista.eliminarDuplicados();

        System.out.println("Después de eliminar duplicados:");
        lista.imprimir();  // [1 -> 2 -> 3]
    }
}
