package ejercicio7pilalistacola;

import java.util.*;

public class Ejercicio7Pilalistacola {
    public static void main(String[] args) {
        // Cola para documentos en espera
        Queue<String> colaDeImpresion = new LinkedList<>();

        // Simular llegada de 5 documentos
        for (int i = 1; i <= 5; i++) {
            String nombreDocumento = "Doc" + i;
            colaDeImpresion.add(nombreDocumento);
            System.out.println("Documento recibido: " + nombreDocumento);
        }

        System.out.println("\nEstado inicial de la cola:");
        mostrarCola(colaDeImpresion);

        // Simular impresión de 3 documentos
        int cantidadADesencolar = 3;
        for (int i = 0; i < cantidadADesencolar; i++) {
            boolean hayDocumentosPendientes = colaDeImpresion.isEmpty() == false;

            if (hayDocumentosPendientes) {
                String documentoImpreso = colaDeImpresion.poll();
                System.out.println("Documento impreso: " + documentoImpreso);
            } else {
                System.out.println("No hay más documentos para imprimir.");
            }
        }

        System.out.println("\nEstado final de la cola:");
        mostrarCola(colaDeImpresion);
    }

    public static void mostrarCola(Queue<String> cola) {
        List<String> copiaTemporal = new ArrayList<>(cola);

        for (int i = 0; i < copiaTemporal.size(); i++) {
            String documentoEnCola = copiaTemporal.get(i);
            System.out.println("  [" + i + "] " + documentoEnCola);
        }
    }
}