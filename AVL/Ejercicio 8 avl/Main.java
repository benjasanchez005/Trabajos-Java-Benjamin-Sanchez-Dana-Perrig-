
public class Main {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        // Insertar valores que provocan caso LR: 30 → 10 → 20
        arbol.raiz = arbol.insertar(arbol.raiz, 30);
        arbol.raiz = arbol.insertar(arbol.raiz, 10);
        arbol.raiz = arbol.insertar(arbol.raiz, 20); // Aquí ocurre la rotación doble LR

        System.out.println("\n--- Información final del árbol ---");
        arbol.mostrarInfo(arbol.raiz);
    }
}