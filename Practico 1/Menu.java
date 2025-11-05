/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestión_tareas_personales;

/**
 *
 * @author Dana
 */
import java.util.*;
public class Menu {
    public static void operaciones(){
        int op;
        do{
            Scanner sc = new Scanner (System.in);
            System.out.println("Bienvenido\n¿Qué desea hacer?\n1- Agregar tarea.\n2- Listar todas las tareas.\n3- Marcar una tarea como completada.\n4- Eliminar tareas completadas.\n5- Salir.");
            op = sc.nextInt();
            sc.nextLine();
            
            switch(op){
                case 1:
                    System.out.println("Ingrese la descripción: ");
                    String descripcion = sc.nextLine();
                    int op_estado;
                    String estado = "";
                    do{
                        System.out.println("Ingrese el Estado:\n1- pendiente.\n2- completada.");
                        op_estado = sc.nextInt();
                        sc.nextLine();
                        if(op_estado==1){
                           estado = "pendiente";
                        }
                        if(op_estado==2){
                            estado = "completada";
                        }
                    }while((op_estado != 1) && (op_estado != 2));
                    
                    Tarea tarea = new Tarea (descripcion, estado);
                    
                    GestorTareas.tareas.add(tarea);
                    GestorTareas.guardarTareas();
                    
                    break;
                case 2:
                    System.out.println("Lista de Tareas: ");
                    
                    for(int i=0; i < GestorTareas.tareas.size(); i++){
                        System.out.println((i+1)+"- "+GestorTareas.tareas.get(i));
                    }
                    break;
                case 3:
                    System.out.println("Lista de Tareas: ");
                    
                    for(int i=0; i < GestorTareas.tareas.size(); i++){
                        System.out.println((i+1)+"- "+GestorTareas.tareas.get(i));
                    }
                    System.out.println("¿Cual tarea completó?");
                    int tareaCambiar = sc.nextInt() - 1;
                    sc.nextLine();
                    
                    GestorTareas.tareas.get(tareaCambiar).setEstado("completada");
                    GestorTareas.guardarTareas();
                    
                    break;
                case 4:
                    GestorTareas.tareas.removeIf(t-> t.getEstado().equalsIgnoreCase("completada"));
                    System.out.println("Tareas completadas eliminadas correctamente.");
                    GestorTareas.guardarTareas();
                    break;
                case 5:
                    System.out.println("Adios!");
                    break;
                default:
                    System.out.println("La opción no existe.");
                    break;
            }
        }while(op != 5);
    }
}
