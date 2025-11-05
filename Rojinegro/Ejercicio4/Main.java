/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        RBTree<Integer, String> arbol = new RBTree<>();

        
        arbol.insertBST(10, "A");
        arbol.insertBST(5, "B");
        arbol.insertBST(15, "C");
        arbol.insertBST(2, "D");
        arbol.insertBST(8, "E");

        // Mostramos recorrido en orden
        System.out.println("Recorrido en orden del árbol:");
        arbol.preOrder(arbol.getRoot());
    }

}
