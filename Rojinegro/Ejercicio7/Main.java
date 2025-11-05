/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio7;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        RBTree<Integer, String> arbol = new RBTree<>();

        // Caso LL: inserciones alineadas a la izquierda
        System.out.println("Caso LL");
        arbol.insertBST(10, "A");
        arbol.insertBST(5, "B");
        arbol.insertBST(2, "C");
        arbol.preOrder(arbol.getRoot());

        System.out.println("\nCaso LR ");
        RBTree<Integer, String> arbol2 = new RBTree<>();
        arbol2.insertBST(10, "A");
        arbol2.insertBST(5, "B");
        arbol2.insertBST(8, "C"); // LR (requiere rotación doble)
        arbol2.preOrder(arbol2.getRoot());
    }
}
