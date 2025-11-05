/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        
        RBTree<Integer, String> arbol = new RBTree<>();

        RBTree<Integer, String>.RBNode n = arbol.new RBNode(10, "A", false);
        RBTree<Integer, String>.RBNode aux = arbol.new RBNode(20, "B", true);
        RBTree<Integer, String>.RBNode B = arbol.new RBNode(30, "C", true);

        
        n.right = aux;
        aux.parent = n;
        aux.left = B;
        B.parent = aux;
        arbol.setRoot(n);
        
        System.out.println("Antes de rotar:");
        arbol.preOrder(arbol.getRoot());
        
        // Rotamos en n (10)
        arbol.rotateLeft(n);
        
        System.out.println("\nDespues de rotar:");
        arbol.preOrder(arbol.getRoot());

    }
}

