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
public class RBNode<K extends Comparable<K>, V> {
    K key;
    V val;
    RBNode<K, V> left, right, parent;
    boolean color; // true = rojo, false = negro

    // Constructor
    public RBNode(K key, V val, boolean color, RBNode<K, V> NIL) {
        this.key = key;
        this.val = val;
        this.color = color; // Rojo o negro
        this.left = NIL;
        this.right = NIL;
        this.parent = NIL;
    }
}
