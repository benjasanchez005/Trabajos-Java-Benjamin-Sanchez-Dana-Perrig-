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
        int[] arr = {9, 4, 7, 1, 6, 2};

        System.out.print("Arreglo original: ");
        for (int v : arr) System.out.print(v + " ");
        System.out.println("\n");

        MinHeap.heapsort(arr);

        System.out.print("Resultado final (ascendente): ");
        for (int v : arr) System.out.print(v + " ");
        System.out.println();
    }  
}
