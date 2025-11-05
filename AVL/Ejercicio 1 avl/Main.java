

public class Main {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();
        int[] valores = {30, 20, 10, 40, 50, 60};

        for (int v : valores) {
            arbol.insertar(v);
            System.out.println("Después de insertar " + v + ":");
            arbol.mostrar();
            System.out.println("------------");
        }
    }
}