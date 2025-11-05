package ejercicio.pkg8pilalistacola;

class ColaCircular {
    private int capacidad;
    private String[] arreglo;
    private int front;
    private int tail;
    private int size;
    
    public ColaCircular(int capacidad) {
        this.capacidad = capacidad;
        this.arreglo = new String[capacidad];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }
    
    public void enqueue(String dato) {
        arreglo[tail] = dato;
        tail = (tail + 1) % capacidad;
        
        if (size == capacidad) {
            // Cola llena → avanzar también el front (pisando el más viejo)
            front = (front + 1) % capacidad;
            System.out.println("Se reemplazó la llamada más antigua");
        } else {
            size++;
        }
    }
    
    public String dequeue() {
        if (IsEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        String dato = arreglo[front];
        front = (front + 1) % capacidad;
        size--;
        return dato;
    }
    
    public boolean IsEmpty() {
        return size == 0;
    }
    
    public boolean IsFull() {
        return size == capacidad;
    }
    
    public String top() {
        if (IsEmpty()) return null;
        return arreglo[front];
    }
    
    public void mostrarCola() {
        System.out.print("Estado de la cola: ");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % capacidad;
            System.out.print(arreglo[idx] + " ");
        }
        System.out.println();
    }
}


public class Ejercicio8pilalistacola {
    public static void main(String[] args) {
        ColaCircular llamadas = new ColaCircular(5);

        llamadas.enqueue("Juan");
        llamadas.enqueue("Maria");
        llamadas.enqueue("Nico");
        llamadas.enqueue("Facundo");
        llamadas.enqueue("Jovan");
        
        llamadas.mostrarCola();
        
        // Ahora entran 3 llamadas más → se deben reemplazar las más antiguas
        llamadas.enqueue("Hugo");
        llamadas.enqueue("Ana");
        llamadas.enqueue("Carlos");
        
        llamadas.mostrarCola();  // Aquí vas a ver el estado final
    }
}
