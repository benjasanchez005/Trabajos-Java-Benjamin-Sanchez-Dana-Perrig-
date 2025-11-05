/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicointegrador;

/**
 *
 * @author Dana
 */
public interface MapaPacientes {
    void put(String dni, Paciente p);
    Paciente get(String dni);
    boolean remove(String dni);
    boolean containsKey(String dni);
    int size();
    Iterable<String> keys();
    int getCapacidad();
}