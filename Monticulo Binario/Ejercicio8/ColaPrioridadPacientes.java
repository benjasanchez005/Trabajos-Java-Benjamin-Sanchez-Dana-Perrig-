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

import java.util.Arrays;
public class ColaPrioridadPacientes {
    private Paciente[] heap; // Arreglo de pacientes
    private int size;        // Cantidad actual de pacientes

    public ColaPrioridadPacientes() {
        heap = new Paciente[20];
        size = 0;
    }

    // Inserta un nuevo paciente en el heap (percolateUp)
    public void ingresar(Paciente p) {
        if (size == heap.length)
            heap = Arrays.copyOf(heap, heap.length * 2); //(arrayoriginal, tamañonuevo)

        heap[size] = p;
        System.out.println("\nIngresando paciente: " + p);
        percolateUp(size);
        size++;

        mostrarHeap();
    }

    // Atiende al paciente con prioridad más alta (menor número)
    public Paciente atender() {
        if (isEmpty()) throw new IllegalStateException("No hay pacientes para atender.");

        System.out.println("\nAtendiendo siguiente paciente...");
        Paciente min = heap[0];
        heap[0] = heap[size - 1];
        size--;

        percolateDown(0);
        mostrarHeap();

        return min;
    }

    // Retorna true si no hay pacientes
    public boolean isEmpty() {
        return size == 0;
    }

    // Mueve hacia arriba según prioridad
    private void percolateUp(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap[i].prioridad < heap[padre].prioridad) {
                System.out.println("Intercambio (up): " + heap[i] + " - " + heap[padre]);
                intercambiar(i, padre);
                i = padre;
            } else break;
        }
    }

    // Mueve hacia abajo según prioridad
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
                System.out.println("Intercambio (down): " + heap[i] + " - " + heap[menor]);
                intercambiar(i, menor);
                i = menor;
            } else break;
        }
    }

    private void intercambiar(int i, int j) {
        Paciente temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Muestra el heap actual (solo prioridades)
    private void mostrarHeap() {
        System.out.print("Estado actual del heap: [");
        for (int k = 0; k < size; k++) {
            System.out.print(heap[k].nombre + ":" + heap[k].prioridad);
            if (k < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}