/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestión_tareas_personales;

/**
 *
 * @author Dana
 */
import java.util.ArrayList;
import java.io.*;

public class GestorTareas {
    static ArrayList <Tarea> tareas = new ArrayList <>();
    static final String ARCHIVO = "tareas.txt";

    // Guardar tareas en archivo
    public static void guardarTareas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Tarea t : tareas) {
                bw.write(t.getDescripcion() + ";" + t.getEstado());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error guardando tareas: " + e.getMessage());
        }
    }

    // Cargar tareas desde archivo
    public static void cargarTareas() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return; // No hay archivo todavía
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    tareas.add(new Tarea(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error cargando tareas: " + e.getMessage());
        }
    }
    
}
