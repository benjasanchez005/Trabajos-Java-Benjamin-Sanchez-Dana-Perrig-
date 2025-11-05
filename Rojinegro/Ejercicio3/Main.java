/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        
        RBTree<Integer, String> arbol = new RBTree<>();

        RBTree<Integer, String>.RBNode n = arbol.new RBNode(20, "A", false);
        RBTree<Integer, String>.RBNode x = arbol.new RBNode(10, "B", true);
        RBTree<Integer, String>.RBNode B = arbol.new RBNode(15, "C", true);

        n.left = x;
        x.parent = n;
        x.right = B;
        B.parent = x;

        arbol.setRoot(n);
        
        System.out.println("Antes de rotar:");
        arbol.preOrder(arbol.getRoot());
        arbol.rotateRight(n);
        System.out.println("\nDespués de rotar:");
        arbol.preOrder(arbol.getRoot());
    }
}
