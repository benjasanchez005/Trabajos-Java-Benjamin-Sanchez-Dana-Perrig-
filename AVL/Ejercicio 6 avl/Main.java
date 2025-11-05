// Clase principal con test
public class Main {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        int[] valores = {10, 100, 20, 80, 40, 70};

        for (int valor : valores) {
            arbol.raiz = arbol.insertar(arbol.raiz, valor);
        }

        System.out.println("\n--- Información final del árbol ---");
        arbol.mostrarInfo(arbol.raiz);
    }
}