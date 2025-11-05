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
public class RBTree<K extends Comparable<K>, V> {
    private final RBNode<K, V> NIL;
    private RBNode<K, V> root;

    public RBTree() {
        NIL = new RBNode<>(null, null, false, null); // NIL negro
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

    public RBNode<K, V> getRoot() {
        return root;
    }

    //Verifica si la raíz es negra
    public boolean raizNegra() {
        if (root == NIL) return true; // árbol vacío es válido
        return !root.color; // false = negro
    }

    // Verifica que no haya dos rojos consecutivos
    public boolean sinRojoRojo() {
        return sinRojoRojoRec(root);
    }

    private boolean sinRojoRojoRec(RBNode<K, V> nodo) {
        if (nodo == NIL) return true;

        // Si el nodo es rojo, sus hijos no deben ser rojos
        if (nodo.color) {
            if (nodo.left.color || nodo.right.color)
                return false;
        }

        return sinRojoRojoRec(nodo.left) && sinRojoRojoRec(nodo.right);
    }

    //Altura negra: misma cantidad de nodos negros en todos los caminos
    public int alturaNegra() {
        return alturaNegraRec(root);
    }

    private int alturaNegraRec(RBNode<K, V> nodo) {
        if (nodo == NIL) return 1; // alcanzamos NIL → cuenta como 1 negro

        int izquierda = alturaNegraRec(nodo.left);
        int derecha = alturaNegraRec(nodo.right);

        // Si alguna rama ya está inconsistente
        if (izquierda == -1 || derecha == -1) return -1;

        // Si las alturas negras difieren, el árbol viola la propiedad
        if (izquierda != derecha) return -1;

        // Sumamos 1 si el nodo actual es negro
        return izquierda + (nodo.color ? 0 : 1);
    }

    // Método para imprimir en preorden
    public void preOrder(RBNode<K, V> n) {
        if (n != NIL) {
            System.out.println(
                "Clave: " + n.key +
                " | Color: " + (n.color ? "Rojo" : "Negro") +
                " | Padre: " + (n.parent != NIL ? n.parent.key : "NIL")
            );
            preOrder(n.left);
            preOrder(n.right);
        }
    }
}
