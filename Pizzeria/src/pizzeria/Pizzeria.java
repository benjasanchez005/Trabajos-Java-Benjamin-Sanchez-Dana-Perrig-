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

//Para gestionar la lista de pedidos
public class Pizzeria {

    private List<Pedido> pedidos;

    public Pizzeria() {
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido agregado: " + pedido);
    }

    public void eliminarPedido(int id) {
        boolean eliminado = pedidos.removeIf(pedido -> pedido.getId() == id);  // Usamos el .getId() que creamos en Pedido.java

        if (eliminado) {
            System.out.println("Pedido " + id + " eliminado.");
        } else {
            System.out.println("Error: Pedido " + id + " no encontrado.");
        }
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en el sistema.");
            return;
        }
        System.out.println("--- Lista de Pedidos Actuales ---");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
        System.out.println("---------------------------------");
    }

    //Devuelve la lista de pedidos para la clase Ordenador.
    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
