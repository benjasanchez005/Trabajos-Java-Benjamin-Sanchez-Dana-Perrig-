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
public class RBTree<K extends Comparable<K>, V> {
    private final RBNode<K, V> NIL;  // Nodo NIL negro
    private RBNode<K, V> root;

    public RBTree() {
        NIL = new RBNode<>(null, null, false, null); // negro
        root = NIL;
    }
    
    // Inserción como un Árbol Binario de Búsqueda (ABB), sin balancear ni recolorear.
    public RBNode<K, V> insertBST(K key, V val) {
        /*Creamos un nuevo nodo con la clave y el valor dados.
        Por convención, en los árboles rojinegros, todo nodo nuevo se inserta como rojo.
        Además, sus hijos iniciales apuntan a NIL.*/
        RBNode<K, V> nuevo = new RBNode<>(key, val, true, NIL);

        // Variable auxiliar que actuará como el padre del nodo en el recorrido.
        RBNode<K, V> y = NIL;

        // Empezamos desde la raíz del árbol.
        RBNode<K, V> x = root;

        /*Recorremos el árbol como en un BST normal.
        Buscamos la posición correcta donde insertar el nuevo nodo.*/
        while (x != NIL) {
            y = x; // guardamos el posible padre
            if (key.compareTo(x.key) < 0) {
                // Si la clave es menor, vamos hacia el hijo izquierdo.
                x = x.left;
            } else {
                // Si la clave es mayor o igual, vamos hacia el hijo derecho.
                x = x.right;
            }
        }

        // Establecemos el padre del nuevo nodo como el último nodo visitado.
        nuevo.parent = y;

        // Si y sigue siendo NIL, significa que el árbol estaba vacío:
        // el nuevo nodo pasa a ser la raíz.
        if (y == NIL) {
            root = nuevo;
        }
        // Si la clave del nuevo nodo es menor que la del padre,
        // se inserta como hijo izquierdo.
        else if (key.compareTo(y.key) < 0) {
            y.left = nuevo;
        }
        // En caso contrario, se inserta como hijo derecho.
        else {
            y.right = nuevo;
        }

        // Devolvemos el nodo recién insertado (rojo),
        // que luego se usará en el proceso de balanceo del árbol rojinegro.
        return nuevo;
    }

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