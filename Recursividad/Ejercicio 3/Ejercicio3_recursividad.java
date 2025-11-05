
package ejercicio3_recursividad;


public class Ejercicio3_recursividad {
public static int sumador (int[] array, int n)
{
    if(n==0)
    {
        return array[0];
    }
    
    return array[n] + sumador(array , n-1 );
}


 
    public static void main(String[] args) {
        int suma = 0;
        float promedio;
        
       int[] array = new int[5];
       array [0]  = 5;
       array [1]  = 9;
       array [2]  = 8;
       array [3]  = 1;
       array [4]  = 2;
       
       int n = array.length;

       
       sumador(array,suma);
       
       suma = sumador(array, n-1);
       
       promedio = suma/array.length;
       
        System.out.println("La suma de los elementos es de " +suma+ "\nEl promedio total es de " + promedio);
    }
    
}
