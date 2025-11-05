package ejercicio.pkg3.avl;
class Nodo {
    int valor;
    Nodo izquierdo, derecho;
    int altura;

    Nodo(int valor) {
        this.valor = valor;
        this.altura = 1; // Altura inicial
    }
}

// Clase AVLTree
public class AVLTree {
    Nodo raiz;

    // Obtener altura de un nodo
    int altura(Nodo n) {
        if (n == null) {
            return 0;
        } else {
            return n.altura;
        }
    }

    // Calcular el factor de equilibrio
    int factorEquilibrio(Nodo n) {
        if (n == null) {
            return 0;
        } else {
            return altura(n.izquierdo) - altura(n.derecho);
        }
    }

    // Rotación simple derecha (LL)
    Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.izquierdo;
        Nodo T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    // Rotación simple izquierda (RR)
    Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    // Inserción en el árbol AVL
    Nodo insertar(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = insertar(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertar(nodo.derecho, valor);
        } else {
            return nodo; // No se permiten duplicados
        }

        // Actualizar altura
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));

        // Calcular factor de equilibrio
        int fe = factorEquilibrio(nodo);

        // Caso LL
        if (fe > 1 && valor < nodo.izquierdo.valor) {
            System.out.println("Rotación simple derecha (LL) en nodo " + nodo.valor);
            return rotacionDerecha(nodo);
        }

        // Caso RR
        if (fe < -1 && valor > nodo.derecho.valor) {
            System.out.println("Rotación simple izquierda (RR) en nodo " + nodo.valor);
            return rotacionIzquierda(nodo);
        }

        // Caso LR
        if (fe > 1 && valor > nodo.izquierdo.valor) {
            System.out.println("Rotación doble izquierda-derecha (LR) en nodo " + nodo.valor);
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }

        // Caso RL
        if (fe < -1 && valor < nodo.derecho.valor) {
            System.out.println("Rotación doble derecha-izquierda (RL) en nodo " + nodo.valor);
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Método público para insertar
    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
    }

    // Mostrar árbol en orden con FE y altura
    void inOrden(Nodo nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierdo);
            System.out.println("Valor: " + nodo.valor + " | Altura: " + nodo.altura + " | FE: " + factorEquilibrio(nodo));
            inOrden(nodo.derecho);
        }
    }

    public void mostrar() {
        inOrden(raiz);
    }
}
