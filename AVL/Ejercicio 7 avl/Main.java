


public class Main{
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        // Insertar valores que provocan caso RR
        arbol.raiz = arbol.insertar(arbol.raiz, 10);
        arbol.raiz = arbol.insertar(arbol.raiz, 20);
        arbol.raiz = arbol.insertar(arbol.raiz, 30); // Aquí ocurre la rotación izquierda

        System.out.println("\n--- Información final del árbol ---");
        arbol.mostrarInfo(arbol.raiz);
    }
}
