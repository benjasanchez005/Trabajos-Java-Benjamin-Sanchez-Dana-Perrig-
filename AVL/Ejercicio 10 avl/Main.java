
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        int[] creciente = new int[20];
        int[] decreciente = new int[20];
        int[] aleatoria = new int[20];
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            creciente[i] = i + 1;
            decreciente[i] = 20 - i;
            aleatoria[i] = rand.nextInt(15) + 1; // Repetidos posibles
        }

        probarSecuencia("Creciente", creciente);
        probarSecuencia("Decreciente", decreciente);
        probarSecuencia("Aleatoria", aleatoria);
    }

    public static void probarSecuencia(String nombre, int[] secuencia) {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertarSecuencia(secuencia);
        System.out.println("Secuencia " + nombre + ": " + arbol.rotaciones + " rotaciones aplicadas.");
    }
}