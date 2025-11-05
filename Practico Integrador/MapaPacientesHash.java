/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicointegrador;

/**
 *
 * @author Dana
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapaPacientesHash implements MapaPacientes {

    @Override
    public int getCapacidad() {
        return this.capacidad;
    }

    private static class Entrada {
        String dni;
        Paciente paciente;
        Entrada(String dni, Paciente paciente) {
            this.dni = dni;
            this.paciente = paciente;
        }
    }

    private List<LinkedList<Entrada>> tabla;
    private int capacidad;
    private int cantidad;
    private static final double LOAD_FACTOR_MAX = 0.75;

    public MapaPacientesHash(int capacidadInicial) {
        this.capacidad = capacidadInicial;
        this.tabla = new ArrayList<>(capacidadInicial);
        for (int i = 0; i < capacidadInicial; i++) {
            tabla.add(new LinkedList<>());
        }
    }

    public MapaPacientesHash() {
        this(16); // capacidad por defecto
    }

    // --------------------- Operaciones ---------------------

    @Override
    public void put(String dni, Paciente p) {
        if ((double) (cantidad + 1) / capacidad > LOAD_FACTOR_MAX) {
            rehash();
        }

        int index = hash(dni);
        LinkedList<Entrada> bucket = tabla.get(index);

        for (Entrada e : bucket) {
            if (e.dni.equals(dni)) {
                e.paciente = p; // Reemplazo
                return;
            }
        }

        bucket.add(new Entrada(dni, p));
        cantidad++;
    }

    @Override
    public Paciente get(String dni) {
        int index = hash(dni);
        for (Entrada e : tabla.get(index)) {
            if (e.dni.equals(dni)) return e.paciente;
        }
        return null;
    }

    @Override
    public boolean remove(String dni) {
        int index = hash(dni);
        LinkedList<Entrada> bucket = tabla.get(index);

        for (Entrada e : bucket) {
            if (e.dni.equals(dni)) {
                bucket.remove(e);
                cantidad--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(String dni) {
        return get(dni) != null;
    }

    @Override
    public int size() {
        return cantidad;
    }

    @Override
    public Iterable<String> keys() {
        List<String> claves = new ArrayList<>();
        for (LinkedList<Entrada> bucket : tabla) {
            for (Entrada e : bucket) {
                claves.add(e.dni);
            }
        }
        return claves;
    }

    // --------------------- Internos ---------------------

    private int hash(String dni) {
        // Hash de String distribuido (usa mezcla tipo DJB2)
        int h = 5381;
        for (char c : dni.toCharArray()) {
            h = ((h << 5) + h) + c; // h * 33 + c
        }
        return Math.abs(h) % capacidad;
    }

    private void rehash() {
        int nuevaCapacidad = capacidad * 2;
        List<LinkedList<Entrada>> nuevaTabla = new ArrayList<>(nuevaCapacidad);
        for (int i = 0; i < nuevaCapacidad; i++) {
            nuevaTabla.add(new LinkedList<>());
        }

        for (LinkedList<Entrada> bucket : tabla) {
            for (Entrada e : bucket) {
                int nuevoIndex = Math.abs(hashRehash(e.dni, nuevaCapacidad));
                nuevaTabla.get(nuevoIndex).add(e);
            }
        }

        this.tabla = nuevaTabla;
        this.capacidad = nuevaCapacidad;
    }

    private int hashRehash(String dni, int nuevaCapacidad) {
        int h = 5381;
        for (char c : dni.toCharArray()) {
            h = ((h << 5) + h) + c;
        }
        return Math.abs(h) % nuevaCapacidad;
    }
}
