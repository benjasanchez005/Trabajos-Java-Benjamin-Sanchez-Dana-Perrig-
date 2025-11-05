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
import java.util.List;

public class MergeAgendas {
    //Fusiona dos listas de turnos ordenadas por fechaHora.
    public static List<Turno> merge(List<Turno> A, List<Turno> B) {
        List<Turno> resultado = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.size() && j < B.size()) {
            Turno tA = A.get(i);
            Turno tB = B.get(j);

            // Comparar fechas
            if (tA.fechaHora.isBefore(tB.fechaHora)) {
                resultado.add(tA);
                i++;
            } else if (tB.fechaHora.isBefore(tA.fechaHora)) {
                resultado.add(tB);
                j++;
            } else {
                // Misma fecha, posible conflicto
                if (conflicto(tA, tB)) {
                    logConflicto(tA, tB);
                    // Agregar solo uno de los dos
                    resultado.add(tA);
                } else {
                    // Si no hay conflicto real, agregamos ambos
                    resultado.add(tA);
                    resultado.add(tB);
                }
                i++;
                j++;
            }
        }

        // Agregar los que falten
        while (i < A.size()) resultado.add(A.get(i++));
        while (j < B.size()) resultado.add(B.get(j++));

        return resultado;
    }

    private static boolean conflicto(Turno t1, Turno t2) {
        boolean mismoId = t1.id.equals(t2.id);
        boolean mismoMedicoYHora = t1.matriculaMedico.equals(t2.matriculaMedico)
                && t1.fechaHora.equals(t2.fechaHora);
        return mismoId || mismoMedicoYHora;
    }

    private static void logConflicto(Turno t1, Turno t2) {
        System.out.println("Conflicto detectado entre turnos:");
        System.out.println("-> " + t1);
        System.out.println("-> " + t2);
    }
}
