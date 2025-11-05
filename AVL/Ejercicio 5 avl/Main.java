public class Main {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();
        int[] valoresIniciales = {50,  60, 30, 70, 20, 40,  80, 65, 75};

        for (int i = 0; i < valoresIniciales.length; i++) {
            int valorActual = valoresIniciales[i];
            System.out.println("Insertando " + valorActual + "...");
            arbol.insertar(valorActual);
        }

        System.out.println("\nÁrbol tras inserciones:");
        arbol.mostrar();

        System.out.println("\nEliminando 20...");
        arbol.eliminar(20);
        arbol.mostrar();

        System.out.println("\nEliminando 70...");
        arbol.eliminar(70);
        arbol.mostrar();
    }
}
