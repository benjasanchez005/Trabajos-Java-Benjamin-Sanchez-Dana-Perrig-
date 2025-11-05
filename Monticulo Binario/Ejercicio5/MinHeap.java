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
import java.util.Arrays;

/**
 * Clase que implementa un Montículo Binario Mínimo (MinHeap).
 * - Soporta: add, poll, peek, isEmpty
 * - Muestra intercambios en percolateUp y percolateDown
 * - printTree para ver la estructura por niveles
 * - Constructor alternativo MinHeap(int[] datos) que construye el heap
 *   usando heapify (bottom-up) mostrando paso a paso la reorganización
 */
public class MinHeap {
    private int[] heap; // Arreglo que almacena los elementos
    private int size;   // Cantidad de elementos actuales en el heap

    // Constructor por defecto: capacidad inicial 20
    public MinHeap() {
        heap = new int[20];
        size = 0;
    }

    // Constructor que construye el heap a partir de un arreglo usando heapify (bottom-up)
    // Muestra paso a paso la reorganización.
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

   //Devuelve true si el montículo está vacío
    public boolean isEmpty() {
        return size == 0;
    }

    // Devuelve la raíz sin eliminarla
    public int peek() {
        if (isEmpty()) throw new IllegalStateException("El montículo está vacío");
        return heap[0];
    }

    /*
     Agrega un nuevo valor al heap y lo acomoda usando percolateUp.
     Muestra los intercambios realizados durante la inserción.
     */
    public void add(int valor) {
        if (size == heap.length)
            heap = Arrays.copyOf(heap, heap.length * 2); // redimensionar si es necesario

        heap[size] = valor;
        System.out.println("\nAgregando " + valor + " al heap...");
        percolateUp(size);
        size++;

        System.out.println("Heap actual: " + Arrays.toString(Arrays.copyOf(heap, size)));
        printTree();
    }

    /*
     Extrae el elemento mínimo (raíz) del heap.
     Muestra el arreglo antes y después de eliminar, y reacomoda con percolateDown.
     */
    public int poll() {
        if (isEmpty()) throw new IllegalStateException("El montículo está vacío");

        System.out.println("\nHeap antes de eliminar: " + Arrays.toString(Arrays.copyOf(heap, size)));

        int min = heap[0];
        heap[0] = heap[size - 1]; // reemplazamos la raíz por el último
        size--;

        // Percolate down desde la raíz mostrando intercambios
        percolateDown(0, /*mostrarPasos=*/ true);

        System.out.println("Heap después de eliminar: " + Arrays.toString(Arrays.copyOf(heap, size)));
        printTree();
        System.out.println("-------------------------------------------");

        return min;
    }

    
    private void percolateUp(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2;

            // Si el hijo es menor que el padre, los intercambiamos (y mostramos)
            if (heap[indice] < heap[padre]) {
                System.out.println("Intercambio (up): " + heap[indice] + " - " + heap[padre]);
                intercambiar(indice, padre);
                indice = padre;
            } else {
                break;
            }
        }
    }

    
    private void percolateDown(int indice, boolean mostrarPasos) {
        while (true) {
            int izquierdo = 2 * indice + 1;
            int derecho = 2 * indice + 2;
            int menor = indice;

            // Buscamos el hijo menor
            if (izquierdo < size && heap[izquierdo] < heap[menor]) {
                menor = izquierdo;
            }
            if (derecho < size && heap[derecho] < heap[menor]) {
                menor = derecho;
            }

            // Si el menor no es el actual, intercambiamos
            if (menor != indice) {
                if (mostrarPasos) System.out.println("Intercambio (down): " + heap[indice] + " - " + heap[menor]);
                intercambiar(indice, menor);
                indice = menor; // seguimos bajando
            } else {
                break;
            }
        }
    }

    // Overload simple para llamadas internas donde no se quiera mostrar pasos
    private void percolateDown(int indice) {
        percolateDown(indice, false);
    }

    //Intercambia dos elementos del arreglo
    private void intercambiar(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    // Muestra el contenido actual del heap como arreglo
    private void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public void printTree() {
        System.out.println("Estructura del Heap:");
        int elementos = 1;      // cantidad de elementos esperados por nivel
        int indice = 0;         // índice del arreglo

        while (indice < size) {
            // imprimir todos los elementos de este nivel
            for (int i = 0; i < elementos && indice < size; i++) {
                System.out.print(heap[indice] + " ");
                indice++;
            }
            System.out.println(); // salto de línea entre niveles
            elementos *= 2; // cada nivel tiene el doble de nodos que el anterior
        }
    }
}
