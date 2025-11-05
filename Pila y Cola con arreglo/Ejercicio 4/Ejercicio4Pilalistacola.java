
package ejercicio.pkg4.pilalistacola;

import java.lang.reflect.Array;

class ListaCliente
{
    private int capacidad;
    private String[] Array;
    private int front;
    private int tail;
    
    
    
    public ListaCliente (int capacidad)
    {
        this.capacidad = capacidad;
        this.Array = new String[capacidad];
        this.front = 0;
        this.tail = 0;
    }
    
    public void enqueue  (String dato)
    {
        if(IsFull())
        {
            System.out.println("El array ya esta lleno");
            
        }
        else
        {
            Array[tail] = dato;
            tail ++;
        }
        
    }
    
        public String dequeue  ()
    {
        if(IsEmpty())
        {
            System.out.println("El array ya vacio");
            return null;
        }
        else
        {
            String valor = Array[front];
            front++;
            return valor;
        }
        
    }
    
    
    
    
    public boolean IsFull ()
    {
        return tail == capacidad;
    }
    
        public boolean IsEmpty ()
    {
        return tail == front;
    }

        
   public String top ()
   {
        if(IsEmpty())
        {
            System.out.println("El array ya vacio");
            return null;
        }
        else
        {
                return Array[front];
        }
   }
   
   public String back()
   {
             if(IsEmpty())
        {
            System.out.println("El array ya vacio");
            return null;
        }
             
             else
             {
                 return Array[tail-1];
             }
   }
}




public class Ejercicio4Pilalistacola {
   
 
    public static void main(String[] args) {
        
        ListaCliente cliente = new ListaCliente (4);
        
        cliente.enqueue("Ana");
        cliente.enqueue("Luis");
        cliente.enqueue("Marta");
        cliente.enqueue("Pedro");
        
        
        
        System.out.println("El primeor en la cola es :" + cliente.top());
        
        
        System.out.println("Atendieron dos clientes " +cliente.dequeue() +"  y  "+cliente.dequeue());
        System.out.println("Ahora el primero en la cola es :" + cliente.top());
        System.out.println("El ultimo de la cola es :" + cliente.back());
    }
    
}
