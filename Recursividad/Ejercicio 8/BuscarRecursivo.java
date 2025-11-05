public class BuscarRecursivo {
    public static boolean buscar(int[] arreglo, int valor, int indice) {
        if (indice >= arreglo.length) {
            return false;
        }

        if (arreglo[indice] == valor) {
            return true;
        }

        return buscar(arreglo, valor, indice + 1);
    }

    public static void main(String[] args) {
        int[] numeros = {3, 5, 7, 9};
        int objetivo = 7;

        boolean encontrado = buscar(numeros, objetivo, 0);
        System.out.println("¿Está el número " + objetivo + "? " + encontrado);
    }
}