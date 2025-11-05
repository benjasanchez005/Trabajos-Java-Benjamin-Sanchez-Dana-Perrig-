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
import java.util.Arrays;
public class AgendaTareas {
    private Tarea[] heap; // arreglo de tareas
    private int size;     // cantidad actual de tareas

    public AgendaTareas() {
        heap = new Tarea[20];
        size = 0;
    }

    // Verifica si el heap está vacío
    public boolean isEmpty() {
        return size == 0;
    }

    // Agrega una tarea nueva a la agenda
    public void agregar(Tarea t) {
        if (size == heap.length)
            heap = Arrays.copyOf(heap, heap.length * 2);

        heap[size] = t;
        System.out.println("\nAgregando tarea: " + t);
        percolateUp(size);
        size++;

        mostrarHeap();
    }

    // Devuelve la tarea más urgente sin quitarla
    public Tarea peek() {
        if (isEmpty()) throw new IllegalStateException("No hay tareas pendientes.");
        return heap[0];
    }

    // Completa (elimina) la tarea más urgente
    public Tarea poll() {
        if (isEmpty()) throw new IllegalStateException("No hay tareas pendientes.");

        System.out.println("\nCompletando tarea más urgente...");
        Tarea min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        percolateDown(0);

        mostrarHeap();
        return min;
    }

    // Muestra todas las tareas actuales sin alterar el heap
    public void mostrarTareas() {
        System.out.println("\nTAREAS PENDIENTES");
        for (int i = 0; i < size; i++) {
            System.out.println("- " + heap[i]);
        }
    }

    // Reacomoda hacia arriba según prioridad
    private void percolateUp(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap[i].prioridad < heap[padre].prioridad) {
                System.out.println("Intercambio (up): " + heap[i] + " ↔ " + heap[padre]);
                intercambiar(i, padre);
                i = padre;
            } else break;
        }
    }

    // Reacomoda hacia abajo según prioridad
    private void percolateDown(int i) {
        while (true) {
            int izq = 2 * i + 1;
            int der = 2 * i + 2;
            int menor = i;

            if (izq < size && heap[izq].prioridad < heap[menor].prioridad)
                menor = izq;
            if (der < size && heap[der].prioridad < heap[menor].prioridad)
                menor = der;

            if (menor != i) {
                System.out.println("Intercambio (down): " + heap[i] + " ↔ " + heap[menor]);
                intercambiar(i, menor);
                i = menor;
            } else break;
        }
    }

    private void intercambiar(int i, int j) {
        Tarea temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Muestra el heap actual (nombre y prioridad)
    private void mostrarHeap() {
        System.out.print("Estado actual del heap: [");
        for (int k = 0; k < size; k++) {
            System.out.print(heap[k].descripcion + ":" + heap[k].prioridad);
            if (k < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

