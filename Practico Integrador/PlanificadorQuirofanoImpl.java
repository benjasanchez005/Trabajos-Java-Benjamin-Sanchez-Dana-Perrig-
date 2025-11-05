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
import java.util.*;

public class PlanificadorQuirofanoImpl implements PlanificadorQuirofano {

    private MinHeapQuirofanos heapQuirofanos; // heap por finOcupado
    private Map<String, Integer> cargaMedicos; // dni/matricula → minutos bloqueados
    private int Q; // cantidad de quirófanos

    public PlanificadorQuirofanoImpl(int cantidadQuirofanos) {
        this.Q = cantidadQuirofanos;
        this.heapQuirofanos = new MinHeapQuirofanos(cantidadQuirofanos);
        this.cargaMedicos = new HashMap<>();
        for (int i = 0; i < cantidadQuirofanos; i++) {
            heapQuirofanos.add(new Quirofano(i));
        }
    }

    @Override
    public void procesar(SolicitudCirugia s) {
        Quirofano q = heapQuirofanos.poll(); // quirófano más libre
        LocalDateTime inicio = q.finOcupado.isBefore(LocalDateTime.now()) ? 
                                LocalDateTime.now() : q.finOcupado;
        LocalDateTime fin = inicio.plusMinutes(s.durMin);

        if (fin.isAfter(s.deadline)) {
            System.out.println("⚠️ No se puede asignar cirugía " + s.id + " dentro del deadline.");
            heapQuirofanos.add(q);
            return;
        }

        // Asignar cirugía
        q.finOcupado = fin;
        heapQuirofanos.add(q);

        // Actualizar carga del médico
        cargaMedicos.put(s.matricula, cargaMedicos.getOrDefault(s.matricula, 0) + s.durMin);
    }

    @Override
    public List<String> topKMedicosBloqueados(int K) {
        // Construir heap de tamaño K manualmente
        MinHeapTopK topK = new MinHeapTopK(K);

        for (var entry : cargaMedicos.entrySet()) {
            topK.add(entry.getKey(), entry.getValue());
        }

        return topK.obtenerOrdenados();
    }
}
