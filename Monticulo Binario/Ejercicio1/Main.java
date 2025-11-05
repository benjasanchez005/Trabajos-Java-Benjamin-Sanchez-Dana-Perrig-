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
public class Main {
    public static void main(String[] args) {
        // Creamos un heap con capacidad inicial 10
        MinHeap heap = new MinHeap(10);

        // Agregamos los valores indicados
        int[] valores = {20, 5, 15, 3, 11};
        System.out.println("Insertando valores:");
        for (int v : valores) { //para cada elemento v dentro del arreglo valores
            System.out.println("Insertar: " + v);
            heap.add(v);
            heap.mostrarHeap(); // mostramos el heap después de cada inserción
        }
        
        // Mostramos el menor sin eliminar
        System.out.println("\nMenor elemento actual (peek): " + heap.peek());

        // Extraemos los valores en orden de prioridad
        System.out.println("\nExtrayendo en orden (poll):");
        while (!heap.isEmpty()) {
            System.out.println("Sacado: " + heap.poll());
            heap.mostrarHeap();
        }
    }
}