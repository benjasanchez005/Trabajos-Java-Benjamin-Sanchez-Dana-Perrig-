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

// Clase auxiliar para devolver dos valores
class ResultadoAVL {
    boolean esAVL;
    int altura;

    ResultadoAVL(boolean esAVL, int altura) {
        this.esAVL = esAVL;
        this.altura = altura;
    }
}

// Clase AVLTree
public class AVLTree {
    Nodo raiz;

    // Método para verificar si el árbol es AVL
    public ResultadoAVL esAVL(Nodo nodo, Integer min, Integer max) {
        if (nodo == null) {
            return new ResultadoAVL(true, 0);
        }

        // Verificar propiedad de ABB
        if (min != null) {
            if (nodo.valor <= min) {
                return new ResultadoAVL(false, 0);
            }
        }
        if (max != null) {
            if (nodo.valor >= max) {
                return new ResultadoAVL(false, 0);
            }
        }

        // Verificar subárbol izquierdo
        ResultadoAVL resultadoIzquierdo = esAVL(nodo.izquierdo, min, nodo.valor);
        if (!resultadoIzquierdo.esAVL) {
            return new ResultadoAVL(false, 0);
        }

        // Verificar subárbol derecho
        ResultadoAVL resultadoDerecho = esAVL(nodo.derecho, nodo.valor, max);
        if (!resultadoDerecho.esAVL) {
            return new ResultadoAVL(false, 0);
        }

        // Verificar balance
        int diferenciaAltura = resultadoIzquierdo.altura - resultadoDerecho.altura;
        if (diferenciaAltura < -1 || diferenciaAltura > 1) {
            return new ResultadoAVL(false, 0);
        }

        // Calcular altura actual
        int alturaActual;
        if (resultadoIzquierdo.altura > resultadoDerecho.altura) {
            alturaActual = resultadoIzquierdo.altura + 1;
        } else {
            alturaActual = resultadoDerecho.altura + 1;
        }

        return new ResultadoAVL(true, alturaActual);
    }

    // Método público para insertar
    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
    }

    // Inserción en AVL
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
            return rotacionDerecha(nodo);
        }
        if (fe < -1 && valor > nodo.derecho.valor) {
            return rotacionIzquierda(nodo);
        }
        if (fe > 1 && valor > nodo.izquierdo.valor) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        if (fe < -1 && valor < nodo.derecho.valor) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
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

    // Mostrar árbol en orden con FE y altura
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
