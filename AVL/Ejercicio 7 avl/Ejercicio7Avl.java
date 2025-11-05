

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

// Clase ArbolAVL con rotación izquierda
class ArbolAVL {
    NodoAVL raiz;

    // Método de inserción
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

        // Calcular FE
        int fe = obtenerFE(nodo);
        System.out.println("Nodo " + nodo.valor + " tiene FE = " + fe);

        // Detectar caso RR
        if (fe < -1 && valor > nodo.derecho.valor) {
            System.out.println("Rotación simple izquierda en nodo " + nodo.valor);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Rotación simple a izquierda (caso RR)
    public NodoAVL rotacionIzquierda(NodoAVL nodo) {
        NodoAVL nuevoPadre = nodo.derecho;
        NodoAVL subArbolIzquierdo = nuevoPadre.izquierdo;

        // Mostrar antes de rotar
        System.out.println("Antes de rotación izquierda:");
        System.out.println("Nodo raíz: " + nodo.valor);
        System.out.println("Hijo derecho: " + nuevoPadre.valor);
        if (subArbolIzquierdo != null) {
            System.out.println("Subárbol izquierdo de nuevo padre: " + subArbolIzquierdo.valor);
        }

        // Realizar rotación
        nuevoPadre.izquierdo = nodo;
        nodo.derecho = subArbolIzquierdo;

        // Actualizar alturas
        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        nuevoPadre.altura = 1 + Math.max(obtenerAltura(nuevoPadre.izquierdo), obtenerAltura(nuevoPadre.derecho));

        // Mostrar después de rotar
        System.out.println("Después de rotación izquierda:");
        System.out.println("Nueva raíz del subárbol: " + nuevoPadre.valor);
        System.out.println("Hijo izquierdo: " + nuevoPadre.izquierdo.valor);
        if (nuevoPadre.derecho != null) {
            System.out.println("Hijo derecho: " + nuevoPadre.derecho.valor);
        }

        return nuevoPadre;
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

    // Mostrar información del árbol en orden
    public void mostrarInfo(NodoAVL nodo) {
        if (nodo != null) {
            mostrarInfo(nodo.izquierdo);
            int fe = obtenerFE(nodo);
            System.out.println("Valor: " + nodo.valor + ", Altura: " + nodo.altura + ", FE: " + fe);
            mostrarInfo(nodo.derecho);
        }
    }
}

