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
import java.util.List;

public class ReportesTurnos {

    //INSERCIÓN (estable) — ordenar por hora del turno
    public static void insercionPorHora(List<Turno> turnos) {
        for (int i = 1; i < turnos.size(); i++) {
            Turno key = turnos.get(i);
            int j = i - 1;
            while (j >= 0 && turnos.get(j).fechaHora.isAfter(key.fechaHora)) {
                turnos.set(j + 1, turnos.get(j));
                j--;
            }
            turnos.set(j + 1, key);
        }
    }

    //SHELLSORT — ordenar por duración (gap estándar)
    public static void shellsortPorDuracion(List<Turno> turnos) {
        int n = turnos.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Turno temp = turnos.get(i);
                int j = i;
                while (j >= gap && turnos.get(j - gap).duracionMin > temp.duracionMin) {
                    turnos.set(j, turnos.get(j - gap));
                    j -= gap;
                }
                turnos.set(j, temp);
            }
        }
    }
    
    //QUICKSORT — por apellido del paciente
    public static void quicksortPorApellido(List<Turno> turnos, int inicio, int fin, MapaPacientes mapa) {
        if (inicio < fin) {
            int p = particion(turnos, inicio, fin, mapa);
            quicksortPorApellido(turnos, inicio, p - 1, mapa);
            quicksortPorApellido(turnos, p + 1, fin, mapa);
        }
    }

    private static int particion(List<Turno> turnos, int inicio, int fin, MapaPacientes mapa) {
        Turno pivote = turnos.get(fin);
        String apellidoPivote = apellidoDe(turnos.get(fin).dniPaciente, mapa);
        int i = inicio;

        for (int j = inicio; j < fin; j++) {
            String apellidoJ = apellidoDe(turnos.get(j).dniPaciente, mapa);
            if (apellidoJ.compareToIgnoreCase(apellidoPivote) <= 0) {
                swap(turnos, i, j);
                i++;
            }
        }
        swap(turnos, i, fin);
        return i;
    }

    private static void swap(List<Turno> turnos, int i, int j) {
        Turno temp = turnos.get(i);
        turnos.set(i, turnos.get(j));
        turnos.set(j, temp);
    }

    private static String apellidoDe(String dni, MapaPacientes mapa) {
        Paciente p = mapa.get(dni);
        if (p == null || p.nombre == null) return "";
        String[] partes = p.nombre.split(" ");
        return partes[partes.length - 1]; // último token como "apellido"
    }
    
    //Medir tiempo de ejecución
    public static long medirTiempo(Runnable metodo) {
        long inicio = System.nanoTime();
        metodo.run();
        return (System.nanoTime() - inicio) / 1_000_000; // ms
    }
}