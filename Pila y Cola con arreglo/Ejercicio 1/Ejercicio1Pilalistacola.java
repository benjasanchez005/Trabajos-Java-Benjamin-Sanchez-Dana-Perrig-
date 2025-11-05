package ejercicio.pkg1.pilalistacola;

class PilaArreglo 
{
    private int[] elementos; // arreglo de la pila
    private int tope;        // cantidad de elementos en la pila
    private int capacidad;   // tamaño máximo

    // Constructor: recibe el tamaño de la pila
    public PilaArreglo(int capacidad) {
        this.capacidad = capacidad;
        this.elementos = new int[capacidad];
        this.tope = 0; // empieza vacía (0 elementos)
    }

    // Apilar un elemento
    public void push(int dato) {
        if (isFull()) {
            System.out.println("La pila está llena. No se puede apilar " + dato);
        } else {
            elementos[tope] = dato; // guardo en la posición libre
            tope = tope + 1;        // incremento la cantidad de elementos
        }
    }

    // Desapilar un elemento
    public int pop() {
        if (isEmpty()) {
            System.out.println("La pila está vacía. No se puede desapilar.");
            return -1;
        } else {
            tope = tope - 1;            // bajo la cantidad
            return elementos[tope];     // devuelvo el último elemento
        }
    }

    // Consultar el elemento en el tope
    public int top() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return -1;
        } else {
            return elementos[tope - 1]; // último está en tope - 1
        }
    }

    // ¿Está vacía? devuelve true si tope = 0
    public boolean isEmpty() {
        return tope == 0;
    }

    // ¿Está llena? devuelve true si tope = capacidad
    public boolean isFull() {
        return tope == capacidad;
    }
}

public class Ejercicio1Pilalistacola {
    public static void main(String[] args) {
        PilaArreglo pila = new PilaArreglo(5); // pila de tamaño 5

        // Apilar
        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.push(40);

        System.out.println("Tope actual: " + pila.top());

        // Desapilar dos
        System.out.println("Desapilado: " + pila.pop());
        System.out.println("Desapilado: " + pila.pop());

        System.out.println("Tope después de desapilar dos: " + pila.top());
    }
}
