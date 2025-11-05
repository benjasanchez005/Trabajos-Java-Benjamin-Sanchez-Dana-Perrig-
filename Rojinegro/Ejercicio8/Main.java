/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio8;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        RBTree<Integer, String> arbol = new RBTree<>();

        // Insertamos las claves dadas
        RBNode<Integer, String> n5 = arbol.insertBST(5, "A");
        RBNode<Integer, String> n10 = arbol.insertBST(10, "B");
        RBNode<Integer, String> n15 = arbol.insertBST(15, "C");

        System.out.println("Árbol en preorden");
        arbol.preOrder(arbol.getRoot());

        System.out.println("\nPruebas de successor y predecessor");

        RBNode<Integer, String> s5 = arbol.successor(n5);
        System.out.println("Successor de 5:" + (s5 != null ? s5.key : "Ninguno"));

        RBNode<Integer, String> p10 = arbol.predecessor(n10);
        System.out.println("Predecessor de 10: " + (p10 != null ? p10.key : "Ninguno"));

        RBNode<Integer, String> s10 = arbol.successor(n10);
        System.out.println("Successor de 10:" + (s10 != null ? s10.key : "Ninguno"));

        RBNode<Integer, String> p15 = arbol.predecessor(n15);
        System.out.println("Predecessor de 15:" + (p15 != null ? p15.key : "Ninguno"));
    }
}
