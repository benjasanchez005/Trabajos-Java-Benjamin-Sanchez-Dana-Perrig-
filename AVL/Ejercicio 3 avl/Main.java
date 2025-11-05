package ejercicio.pkg3.avl;


public class Main {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();
        int[] valores = {5, 10, 15, 20, 25, 30, 35};

        for (int i = 0; i < valores.length; i++) {
            int valorActual = valores[i];
            System.out.println("Insertando " + valorActual + "...");
            arbol.insertar(valorActual);
            arbol.mostrar();
            System.out.println("------------");
        }
        System.out.println("\nEn los arboles ABB cuando se insertan los valores hacia la derecha si es mayor al nodo y hacia la izquierda si es menor, al no tener un sistema de organizacion esto provoca que el arbol"
        + " tenga una altura de n = 7\ncomo consecuencia al buscar un valor esto toma hasta 7 pasos o iteraciones, generando una complejidad es de O(n) al igual que en una lista \n\n");
        System.out.println("En los arboles AVL al realizar el calcula del FE si el resultado es mayor a |2| indica que el arbol esta desbalanceado y se aplica ya sea una rotacion hacia izquierda o hacia la derecha balanceandolo");
    }
}
