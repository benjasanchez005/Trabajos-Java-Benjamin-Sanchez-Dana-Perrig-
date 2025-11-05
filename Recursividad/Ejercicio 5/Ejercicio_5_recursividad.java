
package ejercicio_5_recursividad;

import java.util.Scanner;

public class Ejercicio_5_recursividad 
{
    public static String recursividad(int n)
    {
        if(n<2)
        {
           return String.valueOf(n);
        }
       
        return recursividad(n/2) + (n%2);
    }

    public static void main(String[] args) 
    {
        int n=0;
        Scanner scan  = new Scanner (System.in);
        System.out.println("Ingrese el numero que quiera pasar a bianrio\n");
        n = scan.nextInt();
        
        System.out.println("El numero en binario de "+ n +" es = " + recursividad(n));
    }
    
}
