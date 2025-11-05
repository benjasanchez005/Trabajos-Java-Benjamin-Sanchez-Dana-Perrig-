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

import java.util.ArrayList;
import java.util.List;

public class RBTree<K extends Comparable<K>, V> {
    private final RBNode<K, V> NIL;
    private RBNode<K, V> root;

    public RBTree() {
        NIL = new RBNode<>(null, null, false, null); // nodo negro NIL
        root = NIL;
    }

    // Inserción como un BST simple (sin balanceo)
    public RBNode<K, V> insertBST(K key, V val) {
        RBNode<K, V> nuevo = new RBNode<>(key, val, true, NIL);
        RBNode<K, V> y = NIL;
        RBNode<K, V> x = root;

        while (x != NIL) {
            y = x;
            if (key.compareTo(x.key) < 0)
                x = x.left;
            else
                x = x.right;
        }

        nuevo.parent = y;

        if (y == NIL)
            root = nuevo;
        else if (key.compareTo(y.key) < 0)
            y.left = nuevo;
        else
            y.right = nuevo;

        return nuevo;
    }

    //Recorrido in-order acotado [a, b]
    public List<K> inOrderRange(K a, K b) {
        List<K> resultado = new ArrayList<>();
        inOrderRangeRec(root, a, b, resultado);
        return resultado;
    }

    // Método recursivo
    private void inOrderRangeRec(RBNode<K, V> nodo, K a, K b, List<K> lista) {
        if (nodo == NIL) return;

        // Si la clave del nodo es mayor que a, hay posibilidad de encontrar claves válidas en la izquierda
        if (nodo.key.compareTo(a) > 0)
            inOrderRangeRec(nodo.left, a, b, lista);

        // Si la clave del nodo está dentro del rango, la agregamos
        if (nodo.key.compareTo(a) >= 0 && nodo.key.compareTo(b) <= 0)
            lista.add(nodo.key);

        // Si la clave del nodo es menor que b, hay posibilidad de encontrar claves válidas en la derecha
        if (nodo.key.compareTo(b) < 0)
            inOrderRangeRec(nodo.right, a, b, lista);
    }

    // Imprimir el árbol en preorden (para verificar estructura)
    public void preOrder(RBNode<K, V> n) {
        if (n != NIL) {
            System.out.println(
                "Clave: " + n.key +
                " | Valor: " + n.val +
                " | Color: " + (n.color ? "Rojo" : "Negro") +
                " | Padre: " + (n.parent != NIL ? n.parent.key : "NIL")
            );
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    public RBNode<K, V> getRoot() {
        return root;
    }
}
