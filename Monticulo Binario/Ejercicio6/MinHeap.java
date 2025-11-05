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
import java.util.Arrays;
public class MinHeap {
    private int[] heap;  // arreglo que contiene los elementos
    private int size;    // cantidad actual de elementos

    // Constructor por defecto
    public MinHeap() {
        heap = new int[20];
        size = 0;
    }

    // Constructor alternativo: construye el heap desde un arreglo (heapify)
    public MinHeap(int[] datos) {
        heap = new int[datos.length];
        size = datos.length;

        // Copiamos los elementos
        for (int i = 0; i < datos.length; i++) {
            heap[i] = datos[i];
        }

        System.out.println("Heapify inicial: ");
        printArray();

        // Reordenamos desde el último nodo no hoja hacia arriba
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(i);
            System.out.print("Después de ajustar índice " + i + ": ");
            printArray();
        }

        System.out.println("Heap final tras heapify:");
        printArray();
        System.out.println();
    }

    /** Inserta un nuevo valor y mantiene la propiedad del heap */
    public void add(int valor) {
        if (size == heap.length) {
            expandir();
        }

        heap[size] = valor;
        System.out.println("Insertando: " + valor);
        percolateUp(size);
        size++;
        printTree();
    }

    /** Devuelve y elimina el elemento mínimo (raíz) */
    public int poll() {
        if (isEmpty()) throw new IllegalStateException("El heap está vacío");

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;

        System.out.println("Eliminando raíz: " + min);
        System.out.print("Heap antes de percolar hacia abajo: ");
        printArray();

        percolateDown(0);

        System.out.print("Heap después de percolar: ");
        printArray();
        System.out.println();

        return min;
    }

    /** Devuelve el valor mínimo sin eliminarlo */
    public int peek() {
        if (isEmpty()) throw new IllegalStateException("El heap está vacío");
        return heap[0];
    }

    /** Verifica si el heap está vacío */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Hace que un nodo suba si viola la propiedad del heap */
    private void percolateUp(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2;
            if (heap[indice] < heap[padre]) {
                System.out.println("Intercambio: " + heap[indice] + " - " + heap[padre]);
                intercambiar(indice, padre);
                indice = padre;
            } else {
                break;
            }
        }
    }

    /** Hace que un nodo baje si viola la propiedad del heap */
    private void percolateDown(int indice) {
        while (true) {
            int izquierdo = 2 * indice + 1;
            int derecho = 2 * indice + 2;
            int menor = indice;

            if (izquierdo < size && heap[izquierdo] < heap[menor]) menor = izquierdo;
            if (derecho < size && heap[derecho] < heap[menor]) menor = derecho;

            if (menor != indice) {
                System.out.println("Intercambio: " + heap[indice] + " - " + heap[menor]);
                intercambiar(indice, menor);
                indice = menor;
            } else break;
        }
    }

    /** Intercambia dos elementos del heap */
    private void intercambiar(int i, int j) {
        int aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
    }

    /** Expande el tamaño del arreglo cuando está lleno */
    private void expandir() {
        int[] nuevo = new int[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            nuevo[i] = heap[i];
        }
        heap = nuevo;
    }

    /** Muestra el contenido actual del heap como arreglo */
    private void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    /** Muestra el heap como árbol jerárquico (una línea por nivel) */
    public void printTree() {
        System.out.println("Estructura del heap:");
        int nivel = 0;
        int elementosEnNivel = 1;
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
            if ((i + 1) == elementosEnNivel) {
                System.out.println();
                nivel++;
                elementosEnNivel += Math.pow(2, nivel);
            }
        }
        System.out.println();
    }

    /** Método estático que ordena un arreglo con Heapsort */
    public static void heapsort(int[] arr) {
        System.out.println("HEAPSORT");

        MinHeap heap = new MinHeap();
        for (int valor : arr) {
            heap.add(valor);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.poll();
        }

        System.out.print("Arreglo ordenado: ");
        for (int v : arr) System.out.print(v + " ");
        System.out.println("\n");
    }
}
