
package recursividad.ejercicio.pkg1;


public class RecursividadEjercicio1 {

    public static int Contar (int n)
    {
        if(n<10)
        {  
            return 1;
        }
        
        return 1 + Contar(n/10);
    }
    
    

    public static void main(String[] args) 
    {
     int n = 12345678;
        
        System.out.println("\nEl numero es " + n + "\nY la cantidad de digitos que tiene es: " + Contar(n));
    }
    
}
