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

import java.util.Arrays;
/*
  Clase que implementa un Montículo Binario Mínimo (MinHeap).
 Guarda enteros en un arreglo, manteniendo el menor en la raíz.
 */
public class MinHeap {
    private int[] heap;   // Arreglo donde se guardan los elementos del montículo
    private int size;     // Cantidad actual de elementos

    // Constructor: crea un heap con una capacidad inicial (puede ampliarse)
    public MinHeap(int capacidad) {
        heap = new int[capacidad];
        size = 0;
    }

    
    //Agrega un nuevo valor al heap y reordena para mantener la propiedad mínima.
    public void add(int valor) {
        if (size == heap.length) {
            expandirCapacidad(); // duplicamos el tamaño si se llena
        }

        // Insertamos el nuevo valor al final
        heap[size] = valor;
        size++;

        // Reordenamos hacia arriba
        percolateUp(size - 1);
    }

    
    //Elimina y devuelve el elemento mínimo (la raíz del heap).
    public int poll() {
        if (isEmpty()) {
            throw new IllegalStateException("El heap está vacío.");
        }

        int min = heap[0];          // Guardamos la raíz
        heap[0] = heap[size - 1];   // Movemos el último elemento a la raíz
        size--;                     // Reducimos el tamaño
        percolateDown(0);                   // Reordenamos hacia abajo (heapify-down)
        return min;
    }

    //Devuelve el elemento mínimo sin eliminarlo.
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("El heap está vacío.");
        }
        return heap[0];
    }

    
    //Retorna true si el heap no contiene elementos.
    public boolean isEmpty() {
        return size == 0;
    }
    
    //Reordena hacia arriba: compara con el padre y sube mientras sea menor.
    private void percolateUp(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2;
            if (heap[indice] < heap[padre]) {
                intercambiar(indice, padre);
                indice = padre;
            } else {
                break; // ya cumple la propiedad del heap
            }
        }
    }

    /**
     * Reordena hacia abajo: compara con los hijos y baja mientras sea mayor.
     */
    private void percolateDown(int indice) {
        while (true) {
            int izquierdo = 2 * indice + 1;
            int derecho = 2 * indice + 2;
            int menor = indice;

            if (izquierdo < size && heap[izquierdo] < heap[menor]) {
                menor = izquierdo;
            }
            if (derecho < size && heap[derecho] < heap[menor]) {
                menor = derecho;
            }

            if (menor != indice) {
                intercambiar(indice, menor);
                indice = menor;
            } else {
                break;
            }
        }
    }

    
    //Intercambia dos elementos del arreglo.
    private void intercambiar(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    
    //Duplica el tamaño del arreglo si se llena.
    private void expandirCapacidad() {
        int[] nuevo = new int[heap.length * 2];
        System.arraycopy(heap, 0, nuevo, 0, heap.length);
        heap = nuevo;
    }

    //Muestra el contenido actual del heap (para debug).
    public void mostrarHeap() {
        System.out.print("Heap interno: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    
    public void printTree() {
        System.out.println("Estructura del Heap:");
        int nivel = 0;          // nivel actual (0 = raíz)
        int elementos = 1;      // cantidad de elementos esperados por nivel
        int indice = 0;         // índice del arreglo

        while (indice < size) {
            // imprimir todos los elementos de este nivel
            for (int i = 0; i < elementos && indice < size; i++) {
                System.out.print(heap[indice] + " ");
                indice++;
            }
            System.out.println(); // salto de línea entre niveles
            nivel++;
            elementos *= 2; // cada nivel tiene el doble de nodos que el anterior
        }
    }

}
