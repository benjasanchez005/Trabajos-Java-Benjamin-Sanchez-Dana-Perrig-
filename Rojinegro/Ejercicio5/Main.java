/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio5;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        RBTree<Integer, String> arbol = new RBTree<>();

        // Insertamos nodos (solo como BST, sin balancear)
        RBNode<Integer, String> n1 = arbol.insertBST(10, "A");
        RBNode<Integer, String> n2 = arbol.insertBST(5, "B");
        RBNode<Integer, String> n3 = arbol.insertBST(15, "C");
        RBNode<Integer, String> n4 = arbol.insertBST(2, "D");
        RBNode<Integer, String> n5 = arbol.insertBST(8, "E");

        System.out.println("Recorrido en preorden:");
        arbol.preOrder(arbol.getRoot());

        // Ejemplo: Clasificar el caso del último nodo insertado
        String caso = arbol.clasificarCaso(n5);
        System.out.println("\nCaso clasificado para nodo 8: " + caso);
    }
}
