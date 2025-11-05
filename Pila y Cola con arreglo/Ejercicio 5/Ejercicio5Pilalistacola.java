package ejercicio.pkg5.pilalistacola;

import java.util.*;

public class Ejercicio5Pilalistacola {
    public static boolean esPalindromo(String textoOriginal) {
        // eliminar espacios y convertir a minúsculas
        String textoNormalizado = textoOriginal.replaceAll("\\s+", "").toLowerCase();

        Stack<Character> pila = new Stack<>();
        Queue<Character> cola = new LinkedList<>();

        
        for (int i = 0; i < textoNormalizado.length(); i++) {
            char caracterActual = textoNormalizado.charAt(i);
            pila.push(caracterActual);   // Recorrido de derecha a izquierda
            cola.add(caracterActual);   // Recorrido de izquierda a derecha
        }

        // Comparar pila y cola carácter por carácter
        while (!pila.isEmpty() && !cola.isEmpty()) {
            char desdePila = pila.pop();
            char desdeCola = cola.poll();
            System.out.println("Comparando: " + desdePila + " vs " + desdeCola);
            if (desdePila != desdeCola) {
                return false; 
            }
        }

        return true; 
    }

    public static void main(String[] args) {
        
        String[] listaDeTextos = {
            "Radar",
            "Anita lava la tina",
            "Hola mundo",
            "A man a plan a canal Panama"
        };

        for (int i = 0; i < listaDeTextos.length; i++) {
            String textoActual = listaDeTextos[i];
            System.out.println("\nProbando: \"" + textoActual + "\"");
            boolean resultado = esPalindromo(textoActual);
            System.out.println("¿Es palíndromo? " + resultado);
        }
    }
}