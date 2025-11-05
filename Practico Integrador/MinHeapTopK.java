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
import java.util.*;

public class MinHeapTopK {
    private static class Entrada {
        String medico;
        int minutos;
        Entrada(String m, int min) { medico = m; minutos = min; }
    }

    private Entrada[] heap;
    private int size;
    private int capacidad;

    public MinHeapTopK(int k) {
        heap = new Entrada[k];
        capacidad = k;
        size = 0;
    }

    public void add(String medico, int minutos) {
        if (size < capacidad) {
            heap[size] = new Entrada(medico, minutos);
            subir(size++);
        } else if (minutos > heap[0].minutos) {
            heap[0] = new Entrada(medico, minutos);
            bajar(0);
        }
    }

    public List<String> obtenerOrdenados() {
        List<Entrada> lista = new ArrayList<>();
        for (int i = 0; i < size; i++) lista.add(heap[i]);
        lista.sort((a, b) -> Integer.compare(b.minutos, a.minutos));
        List<String> nombres = new ArrayList<>();
        for (Entrada e : lista) nombres.add(e.medico + " (" + e.minutos + " min)");
        return nombres;
    }

    private void subir(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap[i].minutos >= heap[padre].minutos) break;
            swap(i, padre);
            i = padre;
        }
    }

    private void bajar(int i) {
        while (true) {
            int izq = 2 * i + 1, der = 2 * i + 2, menor = i;
            if (izq < size && heap[izq].minutos < heap[menor].minutos) menor = izq;
            if (der < size && heap[der].minutos < heap[menor].minutos) menor = der;
            if (menor == i) break;
            swap(i, menor);
            i = menor;
        }
    }

    private void swap(int a, int b) {
        Entrada tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
}
