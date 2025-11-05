// Clase NodoAVL
class NodoAVL {
    int valor;
    int altura;
    NodoAVL izquierdo;
    NodoAVL derecho;

    public NodoAVL(int valor) {
        this.valor = valor;
        this.altura = 1;
        this.izquierdo = null;
        this.derecho = null;
    }
}

// Clase ArbolAVL con rotación doble LR
class ArbolAVL {
    NodoAVL raiz;

    // Método de inserción con detección de caso LR
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
            return nodo;
        }

        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        int fe = obtenerFE(nodo);
        System.out.println("Nodo " + nodo.valor + " tiene FE = " + fe);

        // Caso LR: FE > 1 y valor > hijo izquierdo
        if (fe > 1 && valor > nodo.izquierdo.valor) {
            System.out.println("Rotación doble izquierda-derecha en nodo " + nodo.valor);
            return rotacionDobleIzquierdaDerecha(nodo);
        }

        return nodo;
    }

    // Rotación simple izquierda
    public NodoAVL rotacionIzquierda(NodoAVL nodo) {
        NodoAVL nuevoPadre = nodo.derecho;
        NodoAVL subArbolIzquierdo = nuevoPadre.izquierdo;

        nuevoPadre.izquierdo = nodo;
        nodo.derecho = subArbolIzquierdo;

        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        nuevoPadre.altura = 1 + Math.max(obtenerAltura(nuevoPadre.izquierdo), obtenerAltura(nuevoPadre.derecho));

        return nuevoPadre;
    }

    // Rotación simple derecha
    public NodoAVL rotacionDerecha(NodoAVL nodo) {
        NodoAVL nuevoPadre = nodo.izquierdo;
        NodoAVL subArbolDerecho = nuevoPadre.derecho;

        nuevoPadre.derecho = nodo;
        nodo.izquierdo = subArbolDerecho;

        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        nuevoPadre.altura = 1 + Math.max(obtenerAltura(nuevoPadre.izquierdo), obtenerAltura(nuevoPadre.derecho));

        return nuevoPadre;
    }

    // Rotación doble izquierda-derecha (LR)
    public NodoAVL rotacionDobleIzquierdaDerecha(NodoAVL nodo) {
        System.out.println("Paso 1: Rotación simple izquierda en hijo izquierdo (" + nodo.izquierdo.valor + ")");
        nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);

        System.out.println("Paso 2: Rotación simple derecha en nodo desequilibrado (" + nodo.valor + ")");
        return rotacionDerecha(nodo);
    }

    // Obtener altura
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

    // Mostrar árbol en orden
    public void mostrarInfo(NodoAVL nodo) {
        if (nodo != null) {
            mostrarInfo(nodo.izquierdo);
            int fe = obtenerFE(nodo);
            System.out.println("Valor: " + nodo.valor + ", Altura: " + nodo.altura + ", FE: " + fe);
            mostrarInfo(nodo.derecho);
        }
    }
}

