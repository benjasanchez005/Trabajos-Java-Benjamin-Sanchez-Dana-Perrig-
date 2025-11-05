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
public class SalaEspera {
    private final String[] cola;
    private final int capacidad;
    private int front = 0; // índice del próximo en ser atendido
    private int rear = 0;  // índice donde entra el próximo paciente
    private int size = 0;

    public SalaEspera(int capacidad) {
        this.capacidad = capacidad;
        this.cola = new String[capacidad];
    }

    // Llega un nuevo paciente (dni)
    public void llega(String dni) {
        cola[rear] = dni;
        rear = (rear + 1) % capacidad;

        if (size < capacidad) {
            size++;
        } else {
            // Overflow: sobrescribe al más antiguo → mover el frente
            front = (front + 1) % capacidad;
        }
    }

    // Atiende al primero en la cola (FIFO)
    public String atiende() {
        if (size == 0) return null;

        String dni = cola[front];
        cola[front] = null;
        front = (front + 1) % capacidad;
        size--;
        return dni;
    }

    // Consulta el próximo sin sacarlo
    public String peek() {
        if (size == 0) return null;
        return cola[front];
    }

    // Devuelve cuántos hay en la sala
    public int size() {
        return size;
    }

    // (opcional) para debug
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SalaEspera[");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % capacidad;
            sb.append(cola[idx]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public int getCapacidad() {
        return capacidad;
    }
}