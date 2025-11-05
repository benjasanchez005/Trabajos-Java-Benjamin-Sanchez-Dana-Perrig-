/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio10;

/**
 *
 * @author Dana
 */
public class Main {
    public static void main(String[] args) {
        RBTree<Integer, String> arbol = new RBTree<>();

        // Inserciones básicas
        arbol.insertBST(10, "A");
        arbol.insertBST(5, "B");
        arbol.insertBST(15, "C");
        arbol.insertBST(3, "D");
        arbol.insertBST(7, "E");

        System.out.println("\nÁrbol:");
        arbol.preOrder(arbol.getRoot());
        
        System.out.println("\nVerificaciones de invariantes:");

        System.out.println("Raíz negra: " + arbol.raizNegra());
        System.out.println("Sin rojo-rojo: " + arbol.sinRojoRojo());
        System.out.println("Altura negra consistente: " + (arbol.alturaNegra() != -1));
        System.out.println("\nAltura negra total: " + arbol.alturaNegra());
    }
}
