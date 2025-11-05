/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio8;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        ColaPrioridadPacientes cola = new ColaPrioridadPacientes();

        System.out.println("SIMULACIÓN DE COLA DE PRIORIDAD MÉDICA");

        // Ingresamos 5 pacientes
        cola.ingresar(new Paciente("Dana", 2));  // prioridad media
        cola.ingresar(new Paciente("Benjamin", 3)); // prioridad baja
        cola.ingresar(new Paciente("Belen", 1)); // prioridad alta
        cola.ingresar(new Paciente("Miguel", 2)); // prioridad media
        cola.ingresar(new Paciente("David", 1)); // prioridad alta

        System.out.println("\nCOMIENZA LA ATENCIÓN");

        while (!cola.isEmpty()) {
            Paciente atendido = cola.atender();
            System.out.println("Atendido a " + atendido);
        }
        
        System.out.println("\n¿No quedan pacientes por atender?: " + cola.isEmpty());
        
    }
}
