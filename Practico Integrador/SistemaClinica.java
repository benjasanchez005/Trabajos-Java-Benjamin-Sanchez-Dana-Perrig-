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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SistemaClinica {
    private Paciente[] pacientes;
    private Medico[] medicos;
    private Turno[] turnos;
    private int cantPacientes;
    private int cantMedicos;
    private int cantTurnos;

    public SistemaClinica(int maxPacientes, int maxMedicos, int maxTurnos) {
        pacientes = new Paciente[maxPacientes];
        medicos = new Medico[maxMedicos];
        turnos = new Turno[maxTurnos];
        cantPacientes = cantMedicos = cantTurnos = 0;
    }
    
    public void cargarPacientes(String archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String dni = datos[0].trim();
                String nombre = datos[1].trim();
                if (!existePaciente(dni)) {
                    pacientes[cantPacientes++] = new Paciente(dni, nombre);
                }
            }
        }
    }
    
    public void cargarMedicos(String archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String matricula = datos[0].trim();
                String nombre = datos[1].trim();
                String especialidad = datos[2].trim();
                if (!existeMedico(matricula)) {
                    medicos[cantMedicos++] = new Medico(matricula, nombre, especialidad);
                }
            }
        }
    }
    
    public void cargarTurnos(String archivo) throws IOException {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String id = datos[0].trim();
                String dniPaciente = datos[1].trim();
                String matriculaMedico = datos[2].trim();
                LocalDateTime fechaHora = LocalDateTime.parse(datos[3].trim(), formato);
                int duracion = Integer.parseInt(datos[4].trim());
                String motivo = datos[5].trim();

                // VALIDACIONES:
                if (existeTurno(id)) continue; // Duplicado → ignorar
                if (!existePaciente(dniPaciente)) continue; // Paciente inexistente
                if (!existeMedico(matriculaMedico)) continue; // Médico inexistente
                if (fechaHora.isBefore(LocalDateTime.now())) continue; // Fecha pasada
                if (duracion <= 0) continue; // Duración inválida

                turnos[cantTurnos++] = new Turno(id, dniPaciente, matriculaMedico, fechaHora, duracion, motivo);
            }
        }
    }
    
    private boolean existePaciente(String dni) {
        for (int i = 0; i < cantPacientes; i++) {
            if (pacientes[i].dni.equals(dni)) return true;
        }
        return false;
    }

    private boolean existeMedico(String matricula) {
        for (int i = 0; i < cantMedicos; i++) {
            if (medicos[i].matricula.equals(matricula)) return true;
        }
        return false;
    }

    private boolean existeTurno(String id) {
        for (int i = 0; i < cantTurnos; i++) {
            if (turnos[i].id.equals(id)) return true;
        }
        return false;
    }
    
    public void mostrarPacientes() {
        for (int i = 0; i < cantPacientes; i++) {
            System.out.println(pacientes[i]);
        }
    }

    public void mostrarMedicos() {
        for (int i = 0; i < cantMedicos; i++) {
            System.out.println(medicos[i]);
        }
    }

    public void mostrarTurnos() {
        for (int i = 0; i < cantTurnos; i++) {
            System.out.println(turnos[i]);
        }
    } 
    
    public Paciente[] getPacientes() {
        return pacientes;
    }

    public Medico[] getMedicos() {
        return medicos;
    }

    public Turno[] getTurnos() {
        return turnos;
    }

    public int getCantPacientes() {
        return cantPacientes;
    }

    public int getCantMedicos() {
        return cantMedicos;
    }

    public int getCantTurnos() {
        return cantTurnos;
    }

}
