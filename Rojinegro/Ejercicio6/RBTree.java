/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio6;

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

    //Inserción como ABB (sin balancear inicialmente)
    public RBNode<K, V> insertBST(K key, V val) {
        RBNode<K, V> nuevo = new RBNode<>(key, val, true, NIL); // nuevo nodo rojo
        RBNode<K, V> y = NIL;
        RBNode<K, V> x = root;

        while (x != NIL) {
            y = x;
            if (key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        nuevo.parent = y;

        if (y == NIL) {
            root = nuevo;
        } else if (key.compareTo(y.key) < 0) {
            y.left = nuevo;
        } else {
            y.right = nuevo;
        }

        // Llamamos al caso fácil del fixInsert (recoloreo por tío rojo)
        fixInsertCaseEasy(nuevo);

        return nuevo;
    }
    
    // Caso: TIO ROJO
    public void fixInsertCaseEasy(RBNode<K, V> z) {
        while (z.parent.color == true) { // Mientras el padre sea rojo
            RBNode<K, V> abuelo = z.parent.parent;

            // Caso 1: el padre es hijo izquierdo del abuelo
            if (z.parent == abuelo.left) {
                RBNode<K, V> tio = abuelo.right;

                // Tío rojo → recoloreo
                if (tio.color == true) {
                    z.parent.color = false; // padre negro
                    tio.color = false;      // tío negro
                    abuelo.color = true;    // abuelo rojo
                    z = abuelo;             // subimos el punto de conflicto
                } else {
                    break; // no entra en el caso "tío rojo"
                }
            }
            // Caso 2: el padre es hijo derecho del abuelo
            else {
                RBNode<K, V> tio = abuelo.left;

                if (tio.color == true) {
                    z.parent.color = false;
                    tio.color = false;
                    abuelo.color = true;
                    z = abuelo;
                } else {
                    break;
                }
            }
        }
        root.color = false; // aseguramos que la raíz sea negra
    }
    
    //Recorrido en preorden para mostrar la estructura del árbol
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
