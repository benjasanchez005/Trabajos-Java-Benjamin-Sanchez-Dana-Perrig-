/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

/**
 *
 * @author Dana
 */
public class RBTree<K extends Comparable<K>, V> {

    // Nodo NIL compartido por todos (todos los nodos del árbol apuntan al mismo objeto NIL cuando no tienen hijo izquierdo, derecho o padre.)
    private final RBNode NIL = new RBNode (null, null, false);
    /*
    private final asegura que el árbol tenga un único NIL negro inmutable, que no puede ser reemplazado ni modificado desde fuera de la clase
    */
    
    // Raíz del árbol (inicialmente NIL)
    private RBNode root = NIL;

    // Clase interna: representa cada nodo del árbol
    public class RBNode{
        K key;       // clave (para ordenar)
        V val;       // contenido
        boolean color; // true = rojo, false = negro
        RBNode left, right, parent; 

        // Constructor del nodo
        RBNode(K key, V val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
            
            // Todos los punteros vacíos apuntan al NIL
            this.left = NIL;
            this.right = NIL;
            this.parent = NIL;
        }
    }
    
    // El objetivo es que el nodo aux (hijo izquierdo de n) suba
    // y que n quede como su hijo derecho.
    public void rotateRight(RBNode n) {
        // Guardamos el hijo izquierdo de n en una variable auxiliar.
        // El nodo (aux) pasará a ocupar el lugar de n después de la rotación.
        RBNode aux = n.left;

        // El hijo derecho de aux pasa a ser el nuevo hijo izquierdo de n.
        n.left = aux.right;

        // Si el hijo derecho de aux no es NIL, su nuevo padre será n.
        if (aux.right != NIL) aux.right.parent = n;

        // El nuevo padre de aux será el antiguo padre de n (porque aux sube de nivel).
        aux.parent = n.parent;

        // Si n era la raíz, aux pasa a ser la nueva raíz.
        if (n.parent == NIL) root = aux;

        // Si n era el hijo izquierdo de su padre, ahora aux ocupará ese lugar.
        else if (n == n.parent.left) n.parent.left = aux;

        // Si n era el hijo derecho, aux ocupará ese lugar.
        else n.parent.right = aux;

        // Ahora, n pasa a ser el hijo derecho de aux.
        aux.right = n;

        // Finalmente, el padre de n será aux.
        n.parent = aux;
    }
    
    //Muestra primero la raíz actual del árbol o subárbol
    public void preOrder(RBNode n) {
        if (n != NIL) {
            System.out.print(n.key + " ");
            preOrder(n.left);
            preOrder(n.right);
        }
    }
    // Constructor del árbol
    public RBTree() {
        root = NIL; // la raíz empieza apuntando al NIL
    }
    // Devuelve la raíz del árbol
    public RBNode getRoot() {
        return root;
    }

    // Permite establecer una nueva raíz (solo para pruebas o rotaciones)
    public void setRoot(RBNode r) {
        root = r;
    }
    
}
