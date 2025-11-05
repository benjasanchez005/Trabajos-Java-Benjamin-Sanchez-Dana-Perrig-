/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio7;

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

    // Inserción como ABB (sin balancear inicialmente)
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

        // Llamamos al fix que maneja la rama izquierda (LL y LR)
        fixInsertLeft(nuevo);

        return nuevo;
    }

    // CASO IZQUIERDO: LL y LR
    public void fixInsertLeft(RBNode<K, V> z) {
        // Mientras el padre sea rojo y exista un abuelo
        while (z.parent.color == true) {
            RBNode<K, V> g = z.parent.parent; // abuelo

            // Caso: el padre es hijo izquierdo del abuelo (rama izquierda)
            if (z.parent == g.left) {
                RBNode<K, V> tio = g.right;

                // Si el tío es rojo, es el caso fácil (recoloreo)
                if (tio.color == true) {
                    z.parent.color = false;
                    tio.color = false;
                    g.color = true;
                    z = g; // subimos el conflicto
                } else {
                    // Caso LR: z es hijo derecho del padre
                    if (z == z.parent.right) {
                        z = z.parent;
                        rotateLeft(z); // Convertimos LR en LL
                    }

                    // Caso LL (ya sea natural o convertido desde LR)
                    z.parent.color = false; // padre negro
                    g.color = true;          // abuelo rojo
                    rotateRight(g);
                }
            } else {
                // Si el padre no es hijo izquierdo, este método no lo maneja (lado derecho)
                break;
            }
        }

        root.color = false; // la raíz siempre negra
    }

    //Rotación izquierda
    public void rotateLeft(RBNode<K, V> n) {
        RBNode<K, V> aux = n.right; // guardamos hijo derecho

        n.right = aux.left; // el hijo izquierdo de aux pasa a ser hijo derecho de n
        if (aux.left != NIL) aux.left.parent = n;

        aux.parent = n.parent; // aux toma el lugar de n

        if (n.parent == NIL) root = aux;
        else if (n == n.parent.left) n.parent.left = aux;
        else n.parent.right = aux;

        aux.left = n; // n pasa a ser hijo izquierdo de aux
        n.parent = aux;
    }

    // Rotación derecha
    public void rotateRight(RBNode<K, V> aux) {
        RBNode<K, V> n = aux.left; // guardamos hijo izquierdo

        aux.left = n.right; // el hijo derecho de n pasa a ser hijo izquierdo de aux
        if (n.right != NIL) n.right.parent = aux;

        n.parent = aux.parent; // n toma el lugar de aux

        if (aux.parent == NIL) root = n;
        else if (aux == aux.parent.left) aux.parent.left = n;
        else aux.parent.right = n;

        n.right = aux; // aux pasa a ser hijo derecho de n
        aux.parent = n;
    }

    //Recorrido en preorden para mostrar estructura del árbol
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
