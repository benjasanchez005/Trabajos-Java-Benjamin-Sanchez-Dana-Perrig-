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


import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Clase principal que actúa como controlador del sistema de clínica.
 * Gestiona el menú de usuario y coordina las diferentes estructuras de datos.
 *
 * @author Dana (¡y Gemini!)
 */
public class Main {

    // --- 1. Variables Estáticas (Estructuras de Datos Principales) ---
    
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // (Punto 1) Sistema de carga inicial y almacenamiento base
    private static final SistemaClinica sistema = new SistemaClinica(100, 50, 200); // Tamaños máximos

    // (Punto 6) Índice de Pacientes
    private static final MapaPacientes mapaPacientes = new MapaPacientesHash(50); // Capacidad inicial

    // (Punto 2, 3, 9) Agendas AVL por Médico
    private static final Map<String, AgendaConHistorial> agendasMedicos = new HashMap<>();

    // (Punto 4) Sala de Espera
    private static final SalaEspera salaEspera = new SalaEspera(5); // Capacidad de 5 (según PDF)

    // (Punto 5) Planificador de Recordatorios
    private static final Planner plannerRecordatorios = new PlannerHeap();

    // (Punto 10) Planificador de Quirófanos
    private static final PlanificadorQuirofano planificadorQuirofano = new PlanificadorQuirofanoImpl(3); // 3 quirófanos

    
    // --- 2. Método Principal (main) ---
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CLÍNICA - Turnos Médicos ===");

        try {
            cargarDatosIniciales();
        } catch (IOException e) {
            System.out.println("ERROR CRÍTICO: No se pudieron cargar los archivos CSV. " + e.getMessage());
            return; // Salir si la carga inicial falla
        }

        // Bucle principal del menú
        while (true) {
            mostrarMenu();
            String op = sc.nextLine().trim();
            if (op.equalsIgnoreCase("0") || op.equalsIgnoreCase("q")) break;

            try {
                int opcion = Integer.parseInt(op);
                
                switch (opcion) {
                    case 1 -> sistema.mostrarPacientes(); // Ya estaban cargados
                    case 2 -> sistema.mostrarMedicos();   // Ya estaban cargados
                    case 3 -> sistema.mostrarTurnos();    // Ya estaban cargados
                    
                    // --- Opciones 4 a 12 (delegadas) ---
                    case 4 -> gestionarAgendaMedico();
                    case 5 -> buscarHuecoLibre();
                    case 6 -> simularSalaEspera();
                    case 7 -> gestionarRecordatorios();
                    case 8 -> consultarIndicePacientes();
                    case 9 -> consolidarAgendas();
                    case 10 -> ejecutarReportesOrdenamiento();
                    case 11 -> auditoriaUndoRedo();
                    case 12 -> gestionarPlanificadorQuirofano();
                    
                    default -> System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número para la opción.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                // e.printStackTrace(); // Descomentar para depuración
            }
        }

        sc.close();
        System.out.println("Fin del programa.");
    }

    // --- 3. Métodos Auxiliares (Helper Methods) ---

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Mostrar pacientes cargados");
        System.out.println("2. Mostrar médicos cargados");
        System.out.println("3. Mostrar turnos válidos cargados");
        System.out.println("4. Ver/Gestionar agenda de un médico (AVL)"); // Pto 2
        System.out.println("5. Buscar próximo turno disponible (AVL)"); // Pto 3
        System.out.println("6. Simular sala de espera (Cola Circular)"); // Pto 4
        System.out.println("7. Programar recordatorios (Heap)"); // Pto 5
        System.out.println("8. Consultar índice de pacientes (Hash)"); // Pto 6
        System.out.println("9. Consolidador de agendas (Merge)"); // Pto 7
        System.out.println("10. Reportes de ordenamiento (Sorts)"); // Pto 8
        System.out.println("11. Auditoría Undo/Redo (Pilas)"); // Pto 9
        System.out.println("12. Planificador de quirófano (Heap)"); // Pto 10
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    /**
     * Llama a SistemaClinica para parsear los CSV
     * y luego puebla las estructuras de datos avanzadas (Hash, AVL).
     */
    private static void cargarDatosIniciales() throws IOException {
        System.out.println("Cargando datos iniciales...");
        
        //Cargar datos
        sistema.cargarPacientes("pacientes.csv");
        System.out.printf("... Pacientes cargados: %d\n", sistema.getCantPacientes());

        sistema.cargarMedicos("medicos.csv");
        System.out.printf("... Médicos cargados: %d\n", sistema.getCantMedicos());

        sistema.cargarTurnos("turnos.csv");
        System.out.printf("... Turnos válidos cargados: %d\n", sistema.getCantTurnos());

        //Post-procesamiento: Llenar estructuras
        
        // (Punto 6) Llenar el MapaPacientesHash
        for (int i = 0; i < sistema.getCantPacientes(); i++) {
            Paciente p = sistema.getPacientes()[i];
            mapaPacientes.put(p.dni, p);
        }
        System.out.printf("... Índice de pacientes (Hash) creado. Tamaño: %d\n", mapaPacientes.size());

        // (Punto 2) Crear las agendas AVL para cada médico
        for (int i = 0; i < sistema.getCantMedicos(); i++) {
            Medico m = sistema.getMedicos()[i];
            agendasMedicos.put(m.matricula, new AgendaMedicoAVL());
        }

        // (Punto 2) Distribuir los turnos cargados en sus respectivas agendas
        int turnosAgendados = 0;
        for (int i = 0; i < sistema.getCantTurnos(); i++) {
            Turno t = sistema.getTurnos()[i];
            AgendaConHistorial agenda = agendasMedicos.get(t.matriculaMedico);
            if (agenda != null) {
                if (agenda.agendar(t)) { // Usa el método agendar del AVL
                    turnosAgendados++;
                }
            }
        }
        System.out.printf("... %d turnos distribuidos en las agendas AVL.\n", turnosAgendados);
        
        // (Punto 9) Limpiar el historial de la carga inicial
        for (AgendaConHistorial agenda : agendasMedicos.values()) {
            ((AgendaMedicoAVL) agenda).limpiarHistorial(); // Asumiendo que se agregó el método
        }
        System.out.println("... Historial de Undo/Redo limpiado post-carga.");
        System.out.println("--- Carga inicial completa ---");
    }

    /*
    (Opción 4 - Pto 2)
    Permite ver la agenda, agendar o cancelar un turno.
     */
    private static void gestionarAgendaMedico() {
        System.out.println("\n--- GESTIÓN DE AGENDA (AVL) ---");
        AgendaConHistorial agenda = getAgendaMedicoPorInput();
        if (agenda == null) return;

        System.out.println("Sub-menú: 1. Ver Agenda, 2. Agendar Turno, 3. Cancelar Turno, 0. Volver");
        System.out.print("Opción Agenda: ");
        String op = sc.nextLine();

        try {
            switch (Integer.parseInt(op)) {
                case 1 -> ((AgendaMedicoAVL) agenda).imprimirAgenda(); // Asumiendo método agregado
                case 2 -> {
                    System.out.println("Agendando nuevo turno...");
                    System.out.print("ID Turno (ej: T999): "); String id = sc.nextLine();
                    System.out.print("DNI Paciente: "); String dni = sc.nextLine();
                    System.out.print("Matrícula Médico: "); String mat = sc.nextLine();
                    System.out.print("Fecha/Hora (yyyy-MM-dd HH:mm): "); 
                    LocalDateTime fecha = LocalDateTime.parse(sc.nextLine(), formatoFechaHora);
                    System.out.print("Duración (minutos): "); int dur = Integer.parseInt(sc.nextLine());
                    System.out.print("Motivo: "); String mot = sc.nextLine();

                    Turno t = new Turno(id, dni, mat, fecha, dur, mot);
                    if (agenda.agendar(t)) {
                        System.out.println("¡Turno agendado exitosamente!");
                    } else {
                        System.out.println("Error: Conflicto de horario (doble booking) detectado.");
                    }
                }
                case 3 -> {
                    System.out.print("ID del Turno a cancelar: "); String idTurno = sc.nextLine();
                    if (agenda.cancelar(idTurno)) {
                        System.out.println("¡Turno cancelado exitosamente!");
                    } else {
                        System.out.println("Error: No se encontró el turno con ID " + idTurno);
                    }
                }
                default -> {}
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha incorrecto.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Número inválido.");
        }
    }

    /*
    (Opción 5 - Pto 3)
    Busca el primer hueco disponible en la agenda de un médico.
     */
    private static void buscarHuecoLibre() {
        System.out.println("\n--- BUSCAR HUECO LIBRE (AVL) ---");
        AgendaConHistorial agenda = getAgendaMedicoPorInput();
        if (agenda == null) return;

        try {
            System.out.print("Duración deseada (minutos): ");
            int duracion = Integer.parseInt(sc.nextLine());
            
            // Busca a partir de ahora
            Optional<LocalDateTime> hueco = agenda.primerHueco(LocalDateTime.now(), duracion);
            
            if (hueco.isPresent()) {
                System.out.println("Hueco disponible encontrado: " + hueco.get().format(formatoFechaHora));
            } else {
                System.out.println("No se encontraron huecos disponibles.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Duración debe ser un número.");
        }
    }

    /*
     (Opción 6 - Pto 4)
     Inicia una simulación interactiva de la sala de espera.
     */
    private static void simularSalaEspera() {
        System.out.println("\n--- SIMULACIÓN SALA DE ESPERA (Cola Circular) ---");
        System.out.println("Capacidad máxima: " + salaEspera.getCapacidad()); // Asumiendo getter
        System.out.println("Comandos: 'llega DNI', 'atiende', 'ver', 'salir'");
        
        while(true) {
            System.out.println("Estado actual: " + salaEspera.toString());
            System.out.print("Sala > ");
            String cmd = sc.nextLine().trim();
            String[] partes = cmd.split(" ");
            
            if (partes[0].equalsIgnoreCase("llega") && partes.length > 1) {
                salaEspera.llega(partes[1]);
                System.out.println("Llegó " + partes[1]);
                if (salaEspera.size() == salaEspera.getCapacidad() && partes.length > 1) {
                    System.out.println("(Cola llena, posible desbordamiento si había 5 antes)");
                }
            } else if (partes[0].equalsIgnoreCase("atiende")) {
                String atendido = salaEspera.atiende();
                System.out.println("Atendiendo a: " + (atendido != null ? atendido : "nadie"));
            } else if (partes[0].equalsIgnoreCase("ver")) {
                System.out.println("Próximo en ser atendido: " + salaEspera.peek());
            } else if (partes[0].equalsIgnoreCase("salir")) {
                break;
            } else {
                System.out.println("Comando no válido.");
            }
        }
    }

    /*
    (Opción 7 - Pto 5)
    Permite gestionar el planificador de recordatorios.
    */
    private static void gestionarRecordatorios() {
        System.out.println("\n--- PLANIFICADOR DE RECORDATORIOS (Heap) ---");
        System.out.println("Sub-menú: 1. Agregar Recordatorio, 2. Ver/Sacar Próximo, 3. Reprogramar, 0. Volver");
        System.out.print("Opción Recordatorio: ");
        String op = sc.nextLine();

        try {
            switch (Integer.parseInt(op)) {
                case 1 -> {
                    System.out.print("ID Recordatorio (ej: R01): "); String id = sc.nextLine();
                    System.out.print("DNI Paciente: "); String dni = sc.nextLine();
                    System.out.print("Fecha/Hora (yyyy-MM-dd HH:mm): "); 
                    LocalDateTime fecha = LocalDateTime.parse(sc.nextLine(), formatoFechaHora);
                    System.out.print("Mensaje: "); String msg = sc.nextLine();
                    
                    Recordatorio r = new Recordatorio(id, fecha, dni, msg);
                    plannerRecordatorios.programar(r);
                    System.out.println("Recordatorio programado.");
                }
                case 2 -> {
                    Recordatorio proximo = plannerRecordatorios.proximo();
                    if (proximo != null) {
                        System.out.println("Próximo recordatorio (eliminado del heap): " + proximo);
                    } else {
                        System.out.println("No hay recordatorios pendientes.");
                    }
                }
                case 3 -> {
                    System.out.print("ID del Recordatorio a reprogramar: "); String id = sc.nextLine();
                    System.out.print("Nueva Fecha/Hora (yyyy-MM-dd HH:mm): ");
                    LocalDateTime nuevaFecha = LocalDateTime.parse(sc.nextLine(), formatoFechaHora);
                    
                    plannerRecordatorios.reprogramar(id, nuevaFecha);
                    System.out.println("Recordatorio reprogramado.");
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha incorrecto.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Número inválido.");
        }
    }

    /*
    (Opción 8 - Pto 6)
    Permite consultar el índice de pacientes (Tabla Hash).
     */
    private static void consultarIndicePacientes() {
        System.out.println("\n--- ÍNDICE DE PACIENTES (Hash) ---");
        int capacidad = mapaPacientes.getCapacidad();
        System.out.printf("Tamaño: %d / Capacidad: %d / Factor Carga: %.2f\n",
                mapaPacientes.size(), capacidad, (double) mapaPacientes.size() / capacidad);

        System.out.println("Sub-menú: 1. Buscar Paciente, 2. Ver todas las llaves, 0. Volver");
        System.out.print("Opción Hash: ");
        String op = sc.nextLine();

        switch (op) {
            case "1" -> {
                System.out.print("Ingrese DNI a buscar: ");
                String dni = sc.nextLine();
                Paciente p = mapaPacientes.get(dni);
                if (p != null) {
                    System.out.println("Encontrado: " + p.toString());
                } else {
                    System.out.println("Paciente no encontrado.");
                }
            }
            case "2" -> {
                System.out.println("Listando todas las llaves (DNI):");
                for (String dni : mapaPacientes.keys()) {
                    System.out.println("- " + dni);
                }
            }
        }
    }

    /**
    (Opción 9 - Pto 7)
    Simula la fusión de dos agendas (listas ordenadas).
     */
    private static void consolidarAgendas() {
        System.out.println("\n--- CONSOLIDADOR DE AGENDAS (Merge) ---");
        System.out.println("Simulación: fusionando dos listas de turnos ordenadas.");

        // Crear lista A (Local)
        List<Turno> agendaLocal = new ArrayList<>();
        agendaLocal.add(new Turno("T100", "123", "M001", LocalDateTime.of(2025, 12, 1, 10, 0), 30, "Control"));
        agendaLocal.add(new Turno("T101", "456", "M001", LocalDateTime.of(2025, 12, 1, 11, 0), 30, "Consulta"));
        agendaLocal.add(new Turno("T102", "789", "M002", LocalDateTime.of(2025, 12, 2, 9, 0), 45, "Estudio"));

        // Crear lista B (Nube)
        List<Turno> agendaNube = new ArrayList<>();
        agendaNube.add(new Turno("T101", "456", "M001", LocalDateTime.of(2025, 12, 1, 11, 0), 30, "Consulta Nube")); // Conflicto ID
        agendaNube.add(new Turno("T103", "321", "M002", LocalDateTime.of(2025, 12, 1, 15, 0), 30, "Nuevo"));
        agendaNube.add(new Turno("T104", "654", "M002", LocalDateTime.of(2025, 12, 2, 9, 30), 15, "Revisión"));

        System.out.println("\nAgenda Local:");
        agendaLocal.forEach(System.out::println);
        System.out.println("\nAgenda Nube:");
        agendaNube.forEach(System.out::println);

        // Fusionar
        List<Turno> consolidada = MergeAgendas.merge(agendaLocal, agendaNube);

        System.out.println("\n--- Agenda Consolidada (Merge) ---");
        consolidada.forEach(System.out::println);
    }

    /*
    (Opción 10 - Pto 8)
    Ejecuta los algoritmos de ordenamiento sobre la lista de turnos.
    */
    private static void ejecutarReportesOrdenamiento() {
        System.out.println("\n--- REPORTES DE ORDENAMIENTO (Sorts) ---");
        
        // Crear una copia de la lista de turnos para no alterar la original
        List<Turno> turnosParaReporte = new ArrayList<>();
        for(int i=0; i < sistema.getCantTurnos(); i++) {
            turnosParaReporte.add(sistema.getTurnos()[i]);
        }

        if (turnosParaReporte.isEmpty()) {
            System.out.println("No hay turnos cargados para generar reportes.");
            return;
        }

        System.out.println("Sub-menú: 1. Inserción (Por Hora), 2. Shellsort (Por Duración), 3. Quicksort (Por Apellido Paciente)");
        System.out.print("Opción Reporte: ");
        String op = sc.nextLine();

        List<Turno> copia; // Usamos una copia para cada ejecución
        long tiempo;

        switch (op) {
            case "1" -> {
                copia = new ArrayList<>(turnosParaReporte);
                System.out.println("Ejecutando Inserción (estable) por Hora...");
                tiempo = ReportesTurnos.medirTiempo(() -> ReportesTurnos.insercionPorHora(copia));
                System.out.printf("Tiempo: %d ms\n", tiempo);
                System.out.println("Resultado (primeros 10):");
                copia.stream().limit(10).forEach(System.out::println);
            }
            case "2" -> {
                copia = new ArrayList<>(turnosParaReporte);
                System.out.println("Ejecutando Shellsort por Duración...");
                tiempo = ReportesTurnos.medirTiempo(() -> ReportesTurnos.shellsortPorDuracion(copia));
                System.out.printf("Tiempo: %d ms\n", tiempo);
                System.out.println("Resultado (primeros 10):");
                copia.stream().limit(10).forEach(System.out::println);
            }
            case "3" -> {
                copia = new ArrayList<>(turnosParaReporte);
                System.out.println("Ejecutando Quicksort por Apellido Paciente (requiere MapaPacientes)...");
                tiempo = ReportesTurnos.medirTiempo(() -> 
                    ReportesTurnos.quicksortPorApellido(copia, 0, copia.size() - 1, mapaPacientes)
                );
                System.out.printf("Tiempo: %d ms\n", tiempo);
                System.out.println("Resultado (primeros 10):");
                copia.stream().limit(10).forEach(System.out::println);
            }
        }
    }
    
    /*
    (Opción 11 - Pto 9)
    Permite deshacer o rehacer acciones en la agenda de un médico.
     */
    private static void auditoriaUndoRedo() {
        System.out.println("\n--- AUDITORÍA UNDO/REDO (Pilas) ---");
        AgendaConHistorial agenda = getAgendaMedicoPorInput();
        if (agenda == null) return;

        System.out.println("Sub-menú: 1. Deshacer (Undo), 2. Rehacer (Redo), 0. Volver");
        System.out.print("Opción Auditoría: ");
        String op = sc.nextLine();

        switch (op) {
            case "1" -> {
                if (agenda.undo()) {
                    System.out.println("Acción 'Undo' completada.");
                    ((AgendaMedicoAVL) agenda).imprimirAgenda(); // Mostrar estado
                } else {
                    System.out.println("Nada que deshacer.");
                }
            }
            case "2" -> {
                if (agenda.redo()) {
                    System.out.println("Acción 'Redo' completada.");
                    ((AgendaMedicoAVL) agenda).imprimirAgenda(); // Mostrar estado
                } else {
                    System.out.println("Nada que rehacer.");
                }
            }
        }
    }

    /*
    (Opción 12 - Pto 10)
    Gestiona el planificador de quirófanos.
     */
    private static void gestionarPlanificadorQuirofano() {
        System.out.println("\n--- PLANIFICADOR DE QUIRÓFANO (Heap + Top-K) ---");
        System.out.println("Sub-menú: 1. Procesar Solicitud de Cirugía, 2. Ver Top-K Médicos Ocupados, 0. Volver");
        System.out.print("Opción Quirófano: ");
        String op = sc.nextLine();

        try {
            switch (op) {
                case "1" -> {
                    System.out.println("Nueva Solicitud de Cirugía:");
                    System.out.print("ID Solicitud (ej: C01): "); String id = sc.nextLine();
                    System.out.print("Matrícula Médico: "); String mat = sc.nextLine();
                    System.out.print("Duración (minutos): "); int dur = Integer.parseInt(sc.nextLine());
                    System.out.print("Deadline (yyyy-MM-dd HH:mm): ");
                    LocalDateTime deadline = LocalDateTime.parse(sc.nextLine(), formatoFechaHora);
                    
                    SolicitudCirugia s = new SolicitudCirugia(id, mat, dur, deadline);
                    planificadorQuirofano.procesar(s);
                    // La salida (éxito o fallo) se imprime dentro de PlanificadorQuirofanoImpl
                }
                case "2" -> {
                    System.out.print("¿Cuántos médicos (K) desea ver en el Top?: ");
                    int k = Integer.parseInt(sc.nextLine());
                    List<String> topK = planificadorQuirofano.topKMedicosBloqueados(k);
                    
                    System.out.printf("--- Top %d Médicos (Más minutos asignados en quirófano) ---\n", k);
                    if (topK.isEmpty()) {
                        System.out.println("Aún no hay médicos con cirugías procesadas.");
                    } else {
                        for (int i = 0; i < topK.size(); i++) {
                            System.out.printf("%d. %s\n", (i + 1), topK.get(i));
                        }
                    }
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha incorrecto.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Número inválido.");
        }
    }

    /**
     * Método helper reutilizable para pedir una matrícula y obtener la Agenda.
     */
    private static AgendaConHistorial getAgendaMedicoPorInput() {
        System.out.print("Ingrese matrícula del médico (ej: M001): ");
        String matricula = sc.nextLine();
        
        AgendaConHistorial agenda = agendasMedicos.get(matricula);
        if (agenda == null) {
            System.out.println("Error: Médico no encontrado con matrícula " + matricula);
            return null;
        }
        return agenda;
    }
}