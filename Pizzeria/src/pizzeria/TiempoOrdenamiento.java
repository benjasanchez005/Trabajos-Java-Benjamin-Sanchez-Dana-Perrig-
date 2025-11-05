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
import java.util.Random;

//Clase para medir el tiempo de ejecución de los algoritmos.

public class TiempoOrdenamiento {

    private static final Random RND = new Random();

    /*
    Mide el tiempo de ejecución de un método de ordenamiento.
    Usa 'Runnable' para pasar el algoritmo como parámetro.
     */
    public static long medirTiempo(Runnable algoritmo) {
        long inicio = System.nanoTime();
        algoritmo.run(); // Ejecuta el algoritmo
        long fin = System.nanoTime();
        
        return (fin - inicio) / 1_000_000; // Devuelve en milisegundos
    }

    //Genera una lista de pedidos aleatorios para las pruebas.
    public static List<Pedido> generarPedidosAleatorios(int cantidad) {
        List<Pedido> pedidos = new ArrayList<>(cantidad);
        String[] nombres = {"Ana", "Lucia", "Daniela", "David", "Moira", "Benjamin", "Teo", "Dana"};

        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[RND.nextInt(nombres.length)] + " " + RND.nextInt(100);
            double precio = 500 + (RND.nextDouble() * 5000);
            int tiempo = 10 + RND.nextInt(50);
            
            pedidos.add(new Pedido(nombre, precio, tiempo));
        }
        return pedidos;
    }
}
