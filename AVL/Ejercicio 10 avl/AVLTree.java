import java.util.*;

class NodoAVL {
    int valor;
    int altura;
    NodoAVL izquierdo;
    NodoAVL derecho;

    public NodoAVL(int valor) {
        this.valor = valor;
        this.altura = 1;
    }
}

class ArbolAVL {
    NodoAVL raiz;
    int rotaciones;

    public NodoAVL insertar(NodoAVL nodo, int valor) {
        if (nodo == null) {
            System.out.println("Insertando: " + valor);
            return new NodoAVL(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = insertar(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertar(nodo.derecho, valor);
        } else {
            return nodo; // Ignorar repetidos
        }

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        int fe = factorEquilibrio(nodo);

        // Rotaciones
        if (fe > 1 && valor < nodo.izquierdo.valor) {
            rotaciones++;
            return rotacionDerecha(nodo); // LL
        }
        if (fe < -1 && valor > nodo.derecho.valor) {
            rotaciones++;
            return rotacionIzquierda(nodo); // RR
        }
        if (fe > 1 && valor > nodo.izquierdo.valor) {
            rotaciones += 2;
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo); // LR
            return rotacionDerecha(nodo);
        }
        if (fe < -1 && valor < nodo.derecho.valor) {
            rotaciones += 2;
            nodo.derecho = rotacionDerecha(nodo.derecho); // RL
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void insertarSecuencia(int[] secuencia) {
        for (int valor : secuencia) {
            raiz = insertar(raiz, valor);
            assert esAVL(raiz);
            assert inOrderOrdenado();
        }
    }

    public boolean esAVL(NodoAVL nodo) {
        if (nodo == null) return true;
        int fe = factorEquilibrio(nodo);
        if (Math.abs(fe) > 1) return false;
        return esAVL(nodo.izquierdo) && esAVL(nodo.derecho);
    }

    public boolean inOrderOrdenado() {
        List<Integer> lista = new ArrayList<>();
        inOrder(raiz, lista);
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i) <= lista.get(i - 1)) return false;
        }
        return true;
    }

    public void inOrder(NodoAVL nodo, List<Integer> lista) {
        if (nodo != null) {
            inOrder(nodo.izquierdo, lista);
            lista.add(nodo.valor);
            inOrder(nodo.derecho, lista);
        }
    }

    public int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    public int factorEquilibrio(NodoAVL nodo) {
        return altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    public NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        y.altura = 1 + Math.max(altura(y.izquierdo), altura(y.derecho));
        x.altura = 1 + Math.max(altura(x.izquierdo), altura(x.derecho));
        return x;
    }

    public NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;
        x.altura = 1 + Math.max(altura(x.izquierdo), altura(x.derecho));
        y.altura = 1 + Math.max(altura(y.izquierdo), altura(y.derecho));
        return y;
    }
}
