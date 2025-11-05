/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

/**
 *
 * @author Dana
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static Pizzeria pizzeria = new Pizzeria();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("-------BIENVENIDO AL SISTEMA DE GESTIÓN DE PIZZERÍA-------");
        
        // Datos de prueba iniciales
        pizzeria.agregarPedido(new Pedido("Dana Perrig", 1500.50, 25));
        pizzeria.agregarPedido(new Pedido("David Perrig", 3200.00, 40));
        pizzeria.agregarPedido(new Pedido("Benjamin Sanchez", 800.75, 15));
        
        while (true) {
            mostrarMenu();
            int opcion = -1;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                continue;
            }

            if (opcion == 0) {
                System.out.println("Adios!");
                break;
            }

            switch (opcion) {
                case 1 -> agregarNuevoPedido();
                case 2 -> pizzeria.mostrarPedidos();
                case 3 -> ordenarPedidos();
                case 4 -> medirTiempos();
                case 5 -> eliminarPedido();
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Todos los Pedidos");
        System.out.println("3. Ordenar Pedidos");
        System.out.println("4. Medir Tiempos de Ordenamiento");
        System.out.println("5. Eliminar Pedido");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarNuevoPedido() {
        try {
            System.out.print("Nombre del Cliente: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Precio Total: ");
            double precio = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Tiempo de Preparación (minutos): ");
            int tiempo = Integer.parseInt(scanner.nextLine());

            pizzeria.agregarPedido(new Pedido(nombre, precio, tiempo));
            
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato del número. Pedido cancelado.");
        }
    }

    private static void eliminarPedido() {
        try {
            pizzeria.mostrarPedidos();
            System.out.print("Ingrese el ID del pedido a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            pizzeria.eliminarPedido(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: ID debe ser un número.");
        }
    }

    private static void ordenarPedidos() {
        System.out.println("\n--- Sub-Menú Ordenar ---");
        System.out.println("1. Por Tiempo de Preparación (Inserción)");
        System.out.println("2. Por Precio Total (Shellsort)");
        System.out.println("3. Por Nombre del Cliente (Quicksort)");
        System.out.print("Seleccione criterio: ");

        String op = scanner.nextLine();
        List<Pedido> pedidos = pizzeria.getPedidos();

        switch (op) {
            case "1" -> {
                System.out.println("Ordenando por Tiempo (Inserción)...");
                Ordenador.insercionPorTiempo(pedidos);
                pizzeria.mostrarPedidos();
            }
            case "2" -> {
                System.out.println("Ordenando por Precio (Shellsort)...");
                Ordenador.shellsortPorPrecio(pedidos);
                pizzeria.mostrarPedidos();
            }
            case "3" -> {
                System.out.println("Ordenando por Nombre (Quicksort)...");
                Ordenador.quicksortPorNombre(pedidos);
                pizzeria.mostrarPedidos();
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void medirTiempos() {
        System.out.println("\n--- PRUEBAS DE RENDIMIENTO ---");
        int[] tamanos = {100, 1000, 10000};

        for (int n : tamanos) {
            System.out.printf("\n--- Midiendo con %d Pedidos ---\n", n);
            
            // Generar listas aleatorias (usamos 3 copias)
            List<Pedido> lista1 = TiempoOrdenamiento.generarPedidosAleatorios(n);
            List<Pedido> lista2 = new ArrayList<>(lista1);
            List<Pedido> lista3 = new ArrayList<>(lista1);

            // Medir Inserción (Tiempo)
            long tInsercion = TiempoOrdenamiento.medirTiempo(() -> 
                Ordenador.insercionPorTiempo(lista1)
            );
            System.out.printf("1. Inserción (Tiempo): \t%d ms\n", tInsercion);

            // Medir Shellsort (Precio)
            long tShell = TiempoOrdenamiento.medirTiempo(() -> 
                Ordenador.shellsortPorPrecio(lista2)
            );
            System.out.printf("2. Shellsort (Precio): \t%d ms\n", tShell);

            // Medir Quicksort (Nombre)
            long tQuick = TiempoOrdenamiento.medirTiempo(() -> 
                Ordenador.quicksortPorNombre(lista3)
            );
            System.out.printf("3. Quicksort (Nombre): \t%d ms\n", tQuick);
        }
    }
}
