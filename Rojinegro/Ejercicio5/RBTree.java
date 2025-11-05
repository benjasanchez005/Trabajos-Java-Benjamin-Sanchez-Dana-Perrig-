/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio5;

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
        RBNode<K, V> nuevo = new RBNode<>(key, val, true, NIL); // Nodo rojo nuevo
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

    /*Clasifica el tipo de caso que ocurre luego de insertar el nodo z.
     Casos posibles: TIO_ROJO, LL, RR, LR, RL.
     */
    public String clasificarCaso(RBNode<K, V> z) {
        // Si z o su padre son NIL, no hay caso que analizar.
        if (z == NIL || z.parent == NIL || z.parent.parent == NIL)
            return "NINGUNO";

        RBNode<K, V> p = z.parent; // padre
        RBNode<K, V> g = p.parent; // abuelo

        // Determinar quién es el tío (el otro hijo del abuelo).
        RBNode<K, V> tio;
        if (p == g.left) tio = g.right;
        else tio = g.left;

        // CASO 1: El tío es rojo, entonces recoloreo sin rotaciones
        if (tio.color) return "TIO_ROJO";

        // CASO 2: Padre es hijo izquierdo del abuelo
        if (p == g.left) {
            // Subcaso LL: z también es hijo izquierdo, entonces rotación simple derecha
            if (z == p.left) return "LL";
            // Subcaso LR: z es hijo derecho, entonces rotación doble (izquierda en p, derecha en g)
            else return "LR";
        }
        // CASO 3: Padre es hijo derecho del abuelo
        else {
            // Subcaso RR: z también es hijo derecho, entonces rotación simple izquierda
            if (z == p.right) return "RR";
            // Subcaso RL: z es hijo izquierdo, entonces rotación doble (derecha en p, izquierda en g)
            else return "RL";
        }
    }

    // Recorrido en preorden para mostrar el árbol con color y padres
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