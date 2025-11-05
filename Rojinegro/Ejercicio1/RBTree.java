/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

/**
 *
 * @author Dana
 */


// Clase principal del Árbol Rojo-Negro
public class RBTree<K extends Comparable<K>, V> {

    // Nodo NIL compartido por todos (todos los nodos del árbol apuntan al mismo objeto NIL cuando no tienen hijo izquierdo, derecho o padre.)
    private final RBNode NIL = new RBNode (null, null, false);
    /*
    private final asegura que el árbol tenga un único NIL negro inmutable, que no puede ser reemplazado ni modificado desde fuera de la clase
    */
    
    // Raíz del árbol (inicialmente NIL)
    private RBNode root = NIL;

    // Clase interna: representa cada nodo del árbol
    private class RBNode{
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

    // Constructor del árbol
    public RBTree() {
        root = NIL; // la raíz empieza apuntando al NIL
    }
}

