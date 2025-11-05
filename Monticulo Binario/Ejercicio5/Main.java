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
        // Ejemplo de arreglo desordenado
        int[] datos = {20, 15, 30, 5, 10, 25, 40};

        System.out.println("PRUEBA DEL CONSTRUCTOR HEAPIFY\n");
        System.out.println("Arreglo original: ");
        for (int v : datos) System.out.print(v + " ");
        System.out.println("\n");

        // Construcción del heap usando el constructor alternativo
        MinHeap heap = new MinHeap(datos);

        System.out.println("Heap final (como arreglo):");
        heap.printTree();
    }
}
