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

public class Pedido {
    private static int contadorId = 0;
    
    private int id;
    private String nombreCliente;
    private double precioTotal;
    private int tiempoPreparacion; // en minutos

    public Pedido(String nombreCliente, double precioTotal, int tiempoPreparacion) {
        this.id = ++contadorId; 
        
        this.nombreCliente = nombreCliente;
        this.precioTotal = precioTotal;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public int getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    @Override
    public String toString() {
        return String.format(
            "Pedido %d [%s] - $%.2f - %d min",
            id, nombreCliente, precioTotal, tiempoPreparacion
        );
    }
}