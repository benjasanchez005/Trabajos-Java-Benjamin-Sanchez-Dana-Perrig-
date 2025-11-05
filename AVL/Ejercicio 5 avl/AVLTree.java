// Clase Nodo
class Nodo {
    int valor;
    Nodo izquierdo, derecho;
    int altura;

    Nodo(int valor) {
        this.valor = valor;
        this.altura = 1;
    }
}

// Clase AVLTree
public class AVLTree {
    Nodo raiz;

    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
    }

    public void eliminar(int valor) {
        raiz = eliminar(raiz, valor);
    }

    Nodo insertar(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = insertar(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertar(nodo.derecho, valor);
        } else {
            return nodo;
        }

        int alturaIzq = altura(nodo.izquierdo);
        int alturaDer = altura(nodo.derecho);
        nodo.altura = alturaMayor(alturaIzq, alturaDer) + 1;

        int fe = factorEquilibrio(nodo);

        if (fe > 1 && valor < nodo.izquierdo.valor) {
            System.out.println("Rotación a la derecha en nodo " + nodo.valor + " (LL)");
            return rotacionDerecha(nodo);
        }

        if (fe < -1 && valor > nodo.derecho.valor) {
            System.out.println("Rotación a la izquierda en nodo " + nodo.valor + " (RR)");
            return rotacionIzquierda(nodo);
        }

        if (fe > 1 && valor > nodo.izquierdo.valor) {
            System.out.println("Rotación a la izquierda en nodo " + nodo.izquierdo.valor + " (LR paso 1)");
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            System.out.println("Rotación a la derecha en nodo " + nodo.valor + " (LR paso 2)");
            return rotacionDerecha(nodo);
        }

        if (fe < -1 && valor < nodo.derecho.valor) {
            System.out.println("Rotación a la derecha en nodo " + nodo.derecho.valor + " (RL paso 1)");
            nodo.derecho = rotacionDerecha(nodo.derecho);
            System.out.println("Rotación a la izquierda en nodo " + nodo.valor + " (RL paso 2)");
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    Nodo eliminar(Nodo nodo, int valor) {
        if (nodo == null) {
            return null;
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = eliminar(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = eliminar(nodo.derecho, valor);
        } else {
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            } else if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            } else {
                Nodo sucesor = obtenerMinimo(nodo.derecho);
                nodo.valor = sucesor.valor;
                nodo.derecho = eliminar(nodo.derecho, sucesor.valor);
            }
        }

        int alturaIzq = altura(nodo.izquierdo);
        int alturaDer = altura(nodo.derecho);
        nodo.altura = alturaMayor(alturaIzq, alturaDer) + 1;

        int fe = factorEquilibrio(nodo);

        if (fe > 1 && factorEquilibrio(nodo.izquierdo) >= 0) {
            System.out.println("Rotación a la derecha en nodo " + nodo.valor + " (LL tras eliminación)");
            return rotacionDerecha(nodo);
        }

        if (fe > 1 && factorEquilibrio(nodo.izquierdo) < 0) {
            System.out.println("Rotación a la izquierda en nodo " + nodo.izquierdo.valor + " (LR paso 1 tras eliminación)");
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            System.out.println("Rotación a la derecha en nodo " + nodo.valor + " (LR paso 2 tras eliminación)");
            return rotacionDerecha(nodo);
        }

        if (fe < -1 && factorEquilibrio(nodo.derecho) <= 0) {
            System.out.println("Rotación a la izquierda en nodo " + nodo.valor + " (RR tras eliminación)");
            return rotacionIzquierda(nodo);
        }

        if (fe < -1 && factorEquilibrio(nodo.derecho) > 0) {
            System.out.println("Rotación a la derecha en nodo " + nodo.derecho.valor + " (RL paso 1 tras eliminación)");
            nodo.derecho = rotacionDerecha(nodo.derecho);
            System.out.println("Rotación a la izquierda en nodo " + nodo.valor + " (RL paso 2 tras eliminación)");
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    Nodo obtenerMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    int altura(Nodo n) {
        if (n == null) {
            return 0;
        } else {
            return n.altura;
        }
    }

    int alturaMayor(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    int factorEquilibrio(Nodo n) {
        if (n == null) {
            return 0;
        } else {
            int alturaIzq = altura(n.izquierdo);
            int alturaDer = altura(n.derecho);
            return alturaIzq - alturaDer;
        }
    }

    Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.izquierdo;
        Nodo T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        y.altura = alturaMayor(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = alturaMayor(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        x.altura = alturaMayor(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = alturaMayor(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    void inOrden(Nodo nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierdo);
            int fe = factorEquilibrio(nodo);
            System.out.println("Valor: " + nodo.valor + " | Altura: " + nodo.altura + " | FE: " + fe);
            inOrden(nodo.derecho);
        }
    }

    public void mostrar() {
        inOrden(raiz);
    }
}

