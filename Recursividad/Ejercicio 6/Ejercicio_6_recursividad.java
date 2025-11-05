
package ejercicio_6_recursividad;

import java.util.Scanner;

public class Ejercicio_6_recursividad {

    public static int recursividad (int n)
    {
       
        if(n==0 || n==1)
        {
            return 1;
        }
        else
        {
            return recursividad(n-1) + recursividad (n-2);
        }
        
        
    }
    
    public static void main(String[] args) {
        int n=0;
        Scanner scan = new Scanner (System.in);
        System.out.println("Ingrese la n-esimo numero de fibonacci que quiere ver");
        n = scan.nextInt();
        System.out.println("La secuencia " +n+ "  De Fibonacci es = " +recursividad(n));
    }
    
}
