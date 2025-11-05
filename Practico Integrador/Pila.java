/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicointegrador;

/**
 *
 * @author Dana
 */

//Pila simple genérica implementada sobre array dinámico.
public class Pila<T> {
    private Object[] datos;
    private int top; // índice de próximo elemento libre (también tamaño)

    public Pila() {
        datos = new Object[16];
        top = 0;
    }

    public void push(T elem) {
        if (top == datos.length) {
            // doble capacidad
            Object[] nuevo = new Object[datos.length * 2];
            System.arraycopy(datos, 0, nuevo, 0, datos.length);
            datos = nuevo;
        }
        datos[top++] = elem;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == 0) return null;
        top--;
        T r = (T) datos[top];
        datos[top] = null;
        return r;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (top == 0) return null;
        return (T) datos[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    public void clear() {
        for (int i = 0; i < top; i++) datos[i] = null;
        top = 0;
    }
}
