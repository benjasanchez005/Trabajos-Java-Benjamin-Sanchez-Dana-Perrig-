package ejercicio6pilalistacola;

import java.util.*;

public class Ejercicio6Pilalistacola {
    static Stack<String> pilaDeshacer = new Stack<>();
    static Stack<String> pilaRehacer = new Stack<>();

    public static void realizarAccion(String accionRealizada) {
        pilaDeshacer.push(accionRealizada);
        pilaRehacer.clear();
        System.out.println("Acción realizada: " + accionRealizada);
        mostrarEstadoDePilas();
    }

    public static void deshacerAccion() {
        boolean hayAccionesParaDeshacer = pilaDeshacer.isEmpty() == false;

        if (hayAccionesParaDeshacer) {
            String accionDeshecha = pilaDeshacer.pop();
            pilaRehacer.push(accionDeshecha);
            System.out.println("Deshacer: " + accionDeshecha);
        } else {
            System.out.println("️ No hay acciones para deshacer.");
        }

        mostrarEstadoDePilas();
    }

    public static void rehacerAccion() {
        boolean hayAccionesParaRehacer = pilaRehacer.isEmpty() == false;

        if (hayAccionesParaRehacer) {
            String accionRehecha = pilaRehacer.pop();
            pilaDeshacer.push(accionRehecha);
            System.out.println("Rehacer: " + accionRehecha);
        } else {
            System.out.println(" No hay acciones para rehacer.");
        }

        mostrarEstadoDePilas();
    }

    public static void mostrarEstadoDePilas() {
        System.out.println("Pila Deshacer:");
        for (int i = 0; i < pilaDeshacer.size(); i++) {
            String accionEnDeshacer = pilaDeshacer.get(i);
            System.out.println("  [" + i + "] " + accionEnDeshacer);
        }

        System.out.println("Pila Rehacer:");
        for (int i = 0; i < pilaRehacer.size(); i++) {
            String accionEnRehacer = pilaRehacer.get(i);
            System.out.println("  [" + i + "] " + accionEnRehacer);
        }

        System.out.println("───────────────────────────────");
    }

    public static void main(String[] args) {
        realizarAccion("Escribir 'Hola'");
        realizarAccion("Borrar 'Hola'");
        realizarAccion("Escribir 'Mundo'");
        realizarAccion("Copiar 'Mundo'");
        realizarAccion("Pegar 'Mundo'");

        deshacerAccion();
        deshacerAccion();
        rehacerAccion();
        realizarAccion("Escribir 'Java'");
        deshacerAccion();
        rehacerAccion();
    }
}