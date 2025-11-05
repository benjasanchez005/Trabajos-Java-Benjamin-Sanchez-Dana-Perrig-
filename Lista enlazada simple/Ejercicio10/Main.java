/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio10;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();

        lista.agregarAlumno("Dana", 1000);
        lista.agregarAlumno("Benjamin", 1001);
        lista.agregarAlumno("Belen", 1002);

        lista.mostrar();

        System.out.println("\nBuscando legajo 1001:");
        System.out.println(lista.buscarAlumno(1001));
       
        System.out.println("\nLegajo 1002 eliminado:");
        lista.eliminarAlumno(1002);
        lista.mostrar();
    }
}
