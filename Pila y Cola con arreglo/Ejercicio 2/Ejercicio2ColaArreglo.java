package ejercicio.pkg1.pilalistacola;

class ColaArreglo {
    private int[] elementos; // arreglo que guarda los datos
    private int front;       // índice del frente de la cola
    private int tail;        // índice del final de la cola
    private int capacidad;   // tamaño máximo

    // Constructor
    public ColaArreglo(int capacidad) {
        this.capacidad = capacidad;
        this.elementos = new int[capacidad];
        this.front = 0;
        this.tail = 0;
    }

    // Encolar (agregar al final)
    public void enqueue(int dato) {
        if (isFull()) {
            System.out.println("La cola está llena. No se puede encolar " + dato);
        } else {
            elementos[tail] = dato;
            tail++;
        }
    }

    // Desencolar (sacar del frente)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía. No se puede desencolar.");
            return -1;
        } else {
            int valor = elementos[front];
            front++;
            return valor;
        }
    }

    // Consultar el primer elemento sin sacarlo
    public int top() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return -1;
        } else {
            return elementos[front];
        }
    }

    // ¿Está vacía?
    public boolean isEmpty() {
        return front == tail;
    }

    // ¿Está llena?
    public boolean isFull() {
        return tail == capacidad;
    }
}

public class Ejercicio2ColaArreglo {
    public static void main(String[] args) {
        ColaArreglo cola = new ColaArreglo(5);

        // Encolar elementos
        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);
        cola.enqueue(4);

        System.out.println("Frente actual: " + cola.top());

        // Desencolar uno
        System.out.println("Desencolado: " + cola.dequeue());

        System.out.println("Frente después de desencolar: " + cola.top());
    }
}

