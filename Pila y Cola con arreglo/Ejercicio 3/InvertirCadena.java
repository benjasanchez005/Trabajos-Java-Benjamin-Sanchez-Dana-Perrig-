// Clase PilaArreglo
class PilaArreglo {
    char[] datos;
    int tope;
    int capacidad;

    public PilaArreglo(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new char[capacidad];
        this.tope = -1;
    }

    public void push(char dato) {
        if (isFull()) {
            System.out.println("La pila está llena.");
            return;
        }
        tope++;
        datos[tope] = dato;
    }

    public char pop() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return '\0';
        }
        char valor = datos[tope];
        tope--;
        return valor;
    }

    public boolean isEmpty() {
        return tope == -1;
    }

    public boolean isFull() {
        return tope == capacidad - 1;
    }
}

// Clase principal para invertir una cadena
public class InvertirCadena {
    public static String invertir(String cadena) {
        PilaArreglo pila = new PilaArreglo(cadena.length()); //una pila del tamano de la cadena

        // Apilar cada carácter
        for (int i = 0; i < cadena.length(); i++) {
            pila.push(cadena.charAt(i));
        }

        // Desapilar y construir cadena invertida
        String resultado = "";
        while (!pila.isEmpty()) {
            resultado += pila.pop();
        }

        return resultado;
    }

    public static void main(String[] args) {
        String original = "Hola";
        String invertida = invertir(original);
        System.out.println("Cadena original: " + original);
        System.out.println("Cadena invertida: " + invertida);
    }
}