

package ejercicio.pkg4.recursividad;

import java.util.Scanner;


public class Ejercicio4Recursividad {

    public static int mcd (int a, int b)
{
    if(b==0)
            
    {
        return a;
    }
    return mcd(b, a%b);
}
    
    public static void main(String[] args) 
    {
        int a, b, aux = 0;
        Scanner scan = new Scanner (System.in);
        System.out.println("Sacaremos el mcd entre a y b\n");
        
        System.out.println("Ingrese el valor del numero a: ");
        a = scan.nextInt();
        System.out.println("Ingrese el valor del numero b: ");
        b = scan.nextInt();
        
        if(b>a)
        {
            aux = b;
            b = a;
            a = aux;
            
        }
       
        System.out.println("El mcd entre " +a+ " Y " +b+ "\nes igual a = " +mcd(a,b));
    }
    
}
