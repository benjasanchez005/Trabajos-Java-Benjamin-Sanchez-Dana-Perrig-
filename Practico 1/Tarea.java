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
public class Tarea {
   private String descripcion;
   private String estado;
   
   public Tarea(String descripcion, String estado){
       this.descripcion = descripcion;
       this.estado = estado;
   }
   
   public String getDescripcion(){
       return descripcion;
   }
   
   public String getEstado(){
       return estado;
   }
   
   public void setEstado(String nEstado){
       estado = nEstado;
   }
   
   
   @Override
   public String toString(){
      return "Tarea: "+descripcion+". Estado: "+estado;
   }
}
