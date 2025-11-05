public class Main {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();
        int[] valores = {20, 10, 30, 5, 15, 25, 35};

        for (int i = 0; i < valores.length; i++) {
            int valorActual = valores[i];
            arbol.insertar(valorActual);
        }

        arbol.mostrar();

        ResultadoAVL resultado = arbol.esAVL(arbol.raiz, null, null);
        System.out.println("¿Es AVL?: " + resultado.esAVL + " | Altura: " + resultado.altura);
    }
}



/* Árbol manualmente construido (inválido como ABB)
Nodo raiz = new Nodo(20);
raiz.izquierdo = new Nodo(10);
raiz.derecho = new Nodo(30);

// Nodo mal ubicado: 25 está en el subárbol izquierdo de 10, pero es mayor que 20
raiz.izquierdo.derecho = new Nodo(25); //  Error: 25 > 20 pero está a la izquierda

AVLTree arbol = new AVLTree();
arbol.raiz = raiz;

ResultadoAVL resultado = arbol.esAVL(arbol.raiz, null, null);
System.out.println("¿Es AVL?: " + resultado.esAVL + " | Altura: " + resultado.altura);
*/


