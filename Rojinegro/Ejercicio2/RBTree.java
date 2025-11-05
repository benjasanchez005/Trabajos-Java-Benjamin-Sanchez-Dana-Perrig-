/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

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
    
    //El objetivo es que el nodo aux suba y que n quede como su hijo izquierdo
    public void rotateLeft(RBNode n) {
        // Guardamos el hijo derecho de n en una variable auxiliar.
        // El nodo (aux) pasará a ocupar el lugar de n después de la rotación.
        RBNode aux = n.right;
            
        n.right = aux.left; //El hijo izquierdo de aux pasa a ser el nuevo hijo derecho de n
            
        if (aux.left != NIL) aux.left.parent = n; //El padre del hijo izquierdo de aux pasa a ser n
            
        aux.parent = n.parent; //aux toma el lugar de n, así que su nuevo papá es el viejo papá de n.
            
        if (n.parent == NIL) root = aux; //Si n era la raíz, aux pasa a ser la nueva raíz.
            
        else if (n == n.parent.left) n.parent.left = aux; //aux pasa a ser el hijo izq del padre de n
            
        else n.parent.right = aux; ////aux pasa a ser el hijo dercho del padre de n
            
        aux.left = n; //n pasa a ser el hijo izquierdo de n
            
        n.parent = aux; //el padre de n es aux
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