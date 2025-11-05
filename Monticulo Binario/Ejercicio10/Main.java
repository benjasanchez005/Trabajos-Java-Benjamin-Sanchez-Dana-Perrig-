/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio10;

/**
 *
 * @author Dana
 */


import java.util.Scanner;
public class Main {
        public static void main(String[] args) {
        AgendaTareas agenda = new AgendaTareas();
        Scanner sc = new Scanner(System.in);
        int opcion;

        System.out.println("AGENDA DE TAREAS CON PRIORIDAD");

        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver próxima tarea urgente (peek)");
            System.out.println("3. Completar tarea más urgente (poll)");
            System.out.println("4. Mostrar todas las tareas pendientes");
            System.out.println("5. Salir");
            System.out.print("Elegí una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar salto

            switch (opcion) {
                case 1:
                    System.out.print("Descripción: ");
                    String desc = sc.nextLine();
                    System.out.print("Prioridad (1 = alta, 2 = media, 3 = baja): ");
                    int prio = sc.nextInt();
                    sc.nextLine();
                    agenda.agregar(new Tarea(desc, prio));
                    break;

                case 2:
                    if (!agenda.isEmpty())
                        System.out.println("Próxima tarea: " + agenda.peek());
                    else
                        System.out.println("No hay tareas pendientes.");
                    break;

                case 3:
                    if (!agenda.isEmpty()) {
                        Tarea t = agenda.poll();
                        System.out.println("Completada: " + t);
                    } else {
                        System.out.println("No hay tareas por completar.");
                    }
                    break;

                case 4:
                    agenda.mostrarTareas();
                    break;

                case 5:
                    System.out.println("Adios");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
        
    }
}
