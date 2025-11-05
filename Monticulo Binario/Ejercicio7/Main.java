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

        // Creamos un heap con capacidad inicial de 10
        MaxHeap heap = new MaxHeap(10);

        System.out.println("PRUEBA DE MAXHEAP\n");

        // Insertamos varios valores
        int[] valores = {12, 7, 25, 15, 3, 18, 10, 30};

        for (int v : valores) {
            System.out.println("\nAgregando: " + v);
            heap.add(v);
            heap.mostrarHeap();
        }

        System.out.println("\nESTRUCTURA FINAL DEL HEAP");
        heap.printTree();

        System.out.println("\nElemento máximo actual (peek): " + heap.peek());

        System.out.println("\nEXTRAYENDO ELEMENTOS DEL MAXHEAP");
        while (!heap.isEmpty()) {
            int eliminado = heap.poll();
            System.out.println("Eliminado: " + eliminado);
            heap.mostrarHeap();
        }

        System.out.println("\n¿Heap vacío?: " + heap.isEmpty());
        
    }
}
