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
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlannerHeap implements Planner {

    private final ArrayList<Recordatorio> heap = new ArrayList<>();

    @Override
    public void programar(Recordatorio r) {
        heap.add(r);
        subir(heap.size() - 1);
    }

    @Override
    public Recordatorio proximo() {
        if (heap.isEmpty()) return null;

        Recordatorio min = heap.get(0);
        Recordatorio ultimo = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, ultimo);
            bajar(0);
        }
        return min;
    }

    @Override
    public void reprogramar(String id, LocalDateTime nuevaFecha) {
        for (int i = 0; i < heap.size(); i++) {
            Recordatorio r = heap.get(i);
            if (r.id.equals(id)) {
                r.fecha = nuevaFecha;
                // Puede haber quedado desordenado en cualquier dirección
                subir(i);
                bajar(i);
                return;
            }
        }
    }

    @Override
    public int size() {
        return heap.size();
    }

    // ---------- Métodos privados ----------

    private void subir(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap.get(i).fecha.isBefore(heap.get(padre).fecha)) {
                swap(i, padre);
                i = padre;
            } else break;
        }
    }

    private void bajar(int i) {
        int n = heap.size();
        while (true) {
            int izq = 2 * i + 1;
            int der = 2 * i + 2;
            int menor = i;

            if (izq < n && heap.get(izq).fecha.isBefore(heap.get(menor).fecha))
                menor = izq;
            if (der < n && heap.get(der).fecha.isBefore(heap.get(menor).fecha))
                menor = der;

            if (menor != i) {
                swap(i, menor);
                i = menor;
            } else break;
        }
    }

    private void swap(int i, int j) {
        Recordatorio temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
