public class Main {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();
        int[] valores = {30, 10, 20, 40, 35, 37};

        for (int i = 0; i < valores.length; i++) {
            int v = valores[i];
            System.out.println("Insertando " + v + "...");
            arbol.insertar(v);
            arbol.mostrar();
            System.out.println("------------");
        }
    }
}