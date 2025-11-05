// Clase Nodo para AVL
class NodoAVL {
    int valor;
    int altura;
    NodoAVL izquierdo;
    NodoAVL derecho;

    public NodoAVL(int valor) {
        this.valor = valor;
        this.altura = 1; // Altura inicial de un nodo hoja
        this.izquierdo = null;
        this.derecho = null;
    }
}

// Clase Árbol AVL
class ArbolAVL {
    NodoAVL raiz;

    // Método principal de inserción
    public NodoAVL insertar(NodoAVL nodo, int valor) {
        if (nodo == null) {
            System.out.println("Insertando " + valor);
            return new NodoAVL(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = insertar(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertar(nodo.derecho, valor);
        } else {
            return nodo; // No se permiten duplicados
        }

        // Actualizar altura
        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));

        // Calcular factor de equilibrio
        int fe = obtenerFE(nodo);
        System.out.println("Nodo " + nodo.valor + " tiene FE = " + fe);

        // Detectar y corregir desequilibrio
        if (fe > 1 && valor < nodo.izquierdo.valor) {
            System.out.println("Rotación simple derecha en nodo " + nodo.valor);
            return rotacionDerecha(nodo); // Caso LL
        }

        if (fe < -1 && valor > nodo.derecho.valor) {
            System.out.println("Rotación simple izquierda en nodo " + nodo.valor);
            return rotacionIzquierda(nodo); // Caso RR
        }

        if (fe > 1 && valor > nodo.izquierdo.valor) {
            System.out.println("Rotación doble izquierda-derecha en nodo " + nodo.valor);
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo); // Caso LR
        }

        if (fe < -1 && valor < nodo.derecho.valor) {
            System.out.println("Rotación doble derecha-izquierda en nodo " + nodo.valor);
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo); // Caso RL
        }

        return nodo;
    }

    // Rotación derecha
    public NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        y.altura = 1 + Math.max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho));
        x.altura = 1 + Math.max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho));

        return x;
    }

    // Rotación izquierda
    public NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        x.altura = 1 + Math.max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho));
        y.altura = 1 + Math.max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho));

        return y;
    }

    // Obtener altura de un nodo
    public int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    // Calcular FE
    public int obtenerFE(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }

    // Mostrar árbol en orden con altura y FE
    public void mostrarInfo(NodoAVL nodo) {
        if (nodo != null) {
            mostrarInfo(nodo.izquierdo);
            int fe = obtenerFE(nodo);
            System.out.println("Valor: " + nodo.valor + ", Altura: " + nodo.altura + ", FE: " + fe);
            mostrarInfo(nodo.derecho);
        }
    }
}

