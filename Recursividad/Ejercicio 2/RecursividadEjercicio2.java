/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursividad.ejercicio.pkg2;


public class RecursividadEjercicio2 {

    
public static String Invertir (String cadena)
{
    if(cadena.length() <= 1)
    {
        return cadena;
    }
    
    return Invertir (cadena.substring(1)) + cadena.charAt(0);
}

    public static void main(String[] args) {
        
        String cadena = "Hola_Mundo";
        System.out.println("\nNormal: " +cadena + "\nInvertida: "+Invertir(cadena));
    }
    
}
