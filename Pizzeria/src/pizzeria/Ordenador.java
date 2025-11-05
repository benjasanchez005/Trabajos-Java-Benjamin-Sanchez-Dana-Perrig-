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

import java.util.List;

public class Ordenador {

    //Ordena por Tiempo de Preparación usando Inserción.
    //(si dos tienen el mismo tiempo, no intercambian posiciones)
    
    public static void insercionPorTiempo(List<Pedido> pedidos) {
        for (int i = 1; i < pedidos.size(); i++) {
            Pedido key = pedidos.get(i); // El pedido a insertar
            int j = i - 1;

            // Mover elementos que son > key.tiempo
            while (j >= 0 && pedidos.get(j).getTiempoPreparacion() > key.getTiempoPreparacion()) {
                pedidos.set(j + 1, pedidos.get(j));
                j--;
            }
            pedidos.set(j + 1, key);
        }
    }

    //Ordena por Precio Total usando Shellsort. 
    public static void shellsortPorPrecio(List<Pedido> pedidos) {
        int n = pedidos.size();

        // Iniciar con un 'gap' grande y reducirlo
        for (int gap = n / 2; gap > 0; gap /= 2) {
            
            // Recorrer los sub-arrays creados por el gap
            for (int i = gap; i < n; i++) {
                Pedido temp = pedidos.get(i); // Guardar el elemento a insertar
                int j = i;

                // Desplazar elementos anteriores del sub-array si son mayores
                while (j >= gap && pedidos.get(j - gap).getPrecioTotal() > temp.getPrecioTotal()) {
                    pedidos.set(j, pedidos.get(j - gap));
                    j -= gap;
                }

                // Poner temp en su posición correcta
                pedidos.set(j, temp); 
            }
        }
    }

    //Ordena por Nombre del Cliente usando Quicksort.
    public static void quicksortPorNombre(List<Pedido> pedidos) {
        quicksortRecursivo(pedidos, 0, pedidos.size() - 1);
    }

    private static void quicksortRecursivo(List<Pedido> pedidos, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particion(pedidos, inicio, fin);
            quicksortRecursivo(pedidos, inicio, indicePivote - 1); // Izquierda
            quicksortRecursivo(pedidos, indicePivote + 1, fin);  // Derecha
        }
    }

    private static int particion(List<Pedido> pedidos, int inicio, int fin) {
        Pedido pivote = pedidos.get(fin); // Usar el último como pivote
        String nombrePivote = pivote.getNombreCliente();
        int i = (inicio - 1); // Índice del elemento más pequeño

        for (int j = inicio; j < fin; j++) {
            // Compara alfabéticamente ignorando mayúsculas/minúsculas
            if (pedidos.get(j).getNombreCliente().compareToIgnoreCase(nombrePivote) <= 0) {
                i++;
                swap(pedidos, i, j);
            }
        }

        // Poner el pivote en su lugar
        swap(pedidos, i + 1, fin);
        return (i + 1);
    }

    private static void swap(List<Pedido> pedidos, int i, int j) {
        Pedido temp = pedidos.get(i);
        pedidos.set(i, pedidos.get(j));
        pedidos.set(j, temp);
    }
}
