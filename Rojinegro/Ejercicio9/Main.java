/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio9;

/**
 *
 * @author Dana
 */
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RBTree<Integer, String> arbol = new RBTree<>();

        arbol.insertBST(10, "A");
        arbol.insertBST(5, "B");
        arbol.insertBST(15, "C");
        arbol.insertBST(3, "D");
        arbol.insertBST(7, "E");
        arbol.insertBST(12, "F");
        arbol.insertBST(18, "G");

        System.out.println("Recorrido en preorden:");
        arbol.preOrder(arbol.getRoot());

        System.out.println("\nClaves en el rango [5, 12]:");
        List<Integer> clavesEnRango = arbol.inOrderRange(5, 12);
        System.out.println(clavesEnRango);
    }
}
