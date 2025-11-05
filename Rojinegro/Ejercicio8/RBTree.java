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
public class RBTree<K extends Comparable<K>, V> {
    private final RBNode<K, V> NIL;  // Nodo NIL negro
    private RBNode<K, V> root;

    public RBTree() {
        NIL = new RBNode<>(null, null, false, null); // negro
        root = NIL;
    }

    // Inserción como ABB (sin balancear)
    public RBNode<K, V> insertBST(K key, V val) {
        RBNode<K, V> nuevo = new RBNode<>(key, val, true, NIL);
        RBNode<K, V> y = NIL;
        RBNode<K, V> x = root;

        while (x != NIL) {
            y = x;
            if (key.compareTo(x.key) < 0) x = x.left;
            else x = x.right;
        }

        nuevo.parent = y;

        if (y == NIL) root = nuevo;
        else if (key.compareTo(y.key) < 0) y.left = nuevo;
        else y.right = nuevo;

        return nuevo;
    }

    //SUCCESSOR
    // Devuelve el nodo con la menor clave mayor a la del nodo dado
    public RBNode<K, V> successor(RBNode<K, V> nodo) {
        if (nodo.right != NIL) {
            return minimo(nodo.right); // el sucesor está en el subárbol derecho
        }

        // si no tiene hijo derecho, subimos hasta que el nodo sea hijo izquierdo
        RBNode<K, V> padre = nodo.parent;
        while (padre != NIL && nodo == padre.right) {
            nodo = padre;
            padre = padre.parent;
        }

        return (padre != NIL) ? padre : null;
    }

    //PREDECESSOR
    // Devuelve el nodo con la mayor clave menor a la del nodo dado
    public RBNode<K, V> predecessor(RBNode<K, V> nodo) {
        if (nodo.left != NIL) {
            return maximo(nodo.left); // el predecesor está en el subárbol izquierdo
        }

        // si no tiene hijo izquierdo, subimos hasta que el nodo sea hijo derecho
        RBNode<K, V> padre = nodo.parent;
        while (padre != NIL && nodo == padre.left) {
            nodo = padre;
            padre = padre.parent;
        }

        return (padre != NIL) ? padre : null;
    }

    //Mínimo (el más chico del subárbol)
    private RBNode<K, V> minimo(RBNode<K, V> n) {
        while (n.left != NIL) {
            n = n.left;
        }
        return n;
    }

    //Máximo (el más grande del subárbol)
    private RBNode<K, V> maximo(RBNode<K, V> n) {
        while (n.right != NIL) {
            n = n.right;
        }
        return n;
    }

    //Recorrido preorden para mostrar el árbol
    public void preOrder(RBNode<K, V> n) {
        if (n != NIL) {
            System.out.println(
                "Clave: " + n.key +
                " | Valor: " + n.val +
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
