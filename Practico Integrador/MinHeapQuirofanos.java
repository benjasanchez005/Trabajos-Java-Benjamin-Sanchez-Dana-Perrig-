/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicointegrador;

/**
 *
 * @author Dana
 */
public class MinHeapQuirofanos {
    private Quirofano[] heap;
    private int size;

    public MinHeapQuirofanos(int capacidad) {
        heap = new Quirofano[capacidad];
        size = 0;
    }

    public void add(Quirofano q) {
        heap[size] = q;
        subir(size++);
    }

    public Quirofano poll() {
        if (size == 0) return null;
        Quirofano min = heap[0];
        heap[0] = heap[--size];
        bajar(0);
        return min;
    }

    private void subir(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap[i].compareTo(heap[padre]) >= 0) break;
            swap(i, padre);
            i = padre;
        }
    }

    private void bajar(int i) {
        while (true) {
            int izq = 2 * i + 1, der = 2 * i + 2, menor = i;
            if (izq < size && heap[izq].compareTo(heap[menor]) < 0) menor = izq;
            if (der < size && heap[der].compareTo(heap[menor]) < 0) menor = der;
            if (menor == i) break;
            swap(i, menor);
            i = menor;
        }
    }

    private void swap(int a, int b) {
        Quirofano temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
}
