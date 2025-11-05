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
import java.time.*;
import java.util.Optional;

/**
 * Agenda con AVL y soporte de historial (undo/redo) usando pilas propias.
 */
public class AgendaMedicoAVL implements AgendaConHistorial {
    private NodoAVL raiz;

    // Horario laboral (ajustable)
    private final LocalTime inicioJornada = LocalTime.of(8, 0);
    private final LocalTime finJornada = LocalTime.of(18, 0);

    // Pilas personalizadas para undo/redo
    private final Pila<Action> undoStack = new Pila<>();
    private final Pila<Action> redoStack = new Pila<>();

    // ----------------- Helpers AVL -----------------
    private int altura(NodoAVL n) { return (n == null) ? 0 : n.altura; }
    private int balance(NodoAVL n) { return (n == null) ? 0 : altura(n.izq) - altura(n.der); }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izq;
        NodoAVL T2 = x.der;
        x.der = y;
        y.izq = T2;
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.der;
        NodoAVL T2 = y.izq;
        y.izq = x;
        x.der = T2;
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        return y;
    }

    // ----------------- Inserción pública (agendar) -----------------
    @Override
    public boolean agendar(Turno t) {
        if (hayConflicto(raiz, t)) return false;
        raiz = insertar(raiz, t);
        // registrar acción y limpiar redo
        undoStack.push(new Action(ActionType.AGENDAR, null, copyTurno(t)));
        redoStack.clear();
        return true;
    }

    private NodoAVL insertar(NodoAVL nodo, Turno t) {
        if (nodo == null) return new NodoAVL(t);

        if (t.fechaHora.isBefore(nodo.turno.fechaHora))
            nodo.izq = insertar(nodo.izq, t);
        else
            nodo.der = insertar(nodo.der, t);

        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));
        int balance = balance(nodo);

        if (balance > 1 && t.fechaHora.isBefore(nodo.izq.turno.fechaHora))
            return rotarDerecha(nodo);
        if (balance < -1 && t.fechaHora.isAfter(nodo.der.turno.fechaHora))
            return rotarIzquierda(nodo);
        if (balance > 1 && t.fechaHora.isAfter(nodo.izq.turno.fechaHora)) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && t.fechaHora.isBefore(nodo.der.turno.fechaHora)) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    // ----------------- Conflictos -----------------
    private boolean hayConflicto(NodoAVL nodo, Turno nuevo) {
        if (nodo == null) return false;
        LocalDateTime inicio = nuevo.fechaHora;
        LocalDateTime fin = inicio.plusMinutes(nuevo.duracionMin);
        LocalDateTime inicioNodo = nodo.turno.fechaHora;
        LocalDateTime finNodo = inicioNodo.plusMinutes(nodo.turno.duracionMin);

        boolean seSolapan = !fin.isBefore(inicioNodo) && !inicio.isAfter(finNodo);
        if (seSolapan) return true;

        if (inicio.isBefore(inicioNodo))
            return hayConflicto(nodo.izq, nuevo);
        else
            return hayConflicto(nodo.der, nuevo);
    }

    // ----------------- Búsqueda por id -----------------
    private Turno buscarTurnoPorId(NodoAVL nodo, String id) {
        if (nodo == null) return null;
        if (nodo.turno.id.equals(id)) return nodo.turno;
        Turno iz = buscarTurnoPorId(nodo.izq, id);
        if (iz != null) return iz;
        return buscarTurnoPorId(nodo.der, id);
    }

    // ----------------- Eliminación por id (usando fecha como clave de árbol) -----------------
    private NodoAVL eliminarPorId(NodoAVL nodo, String id) {
        Turno target = buscarTurnoPorId(nodo, id);
        if (target == null) return nodo;
        return eliminarPorFecha(nodo, target.fechaHora, id);
    }

    private NodoAVL eliminarPorFecha(NodoAVL nodo, LocalDateTime fecha, String id) {
        if (nodo == null) return null;

        if (fecha.isBefore(nodo.turno.fechaHora)) {
            nodo.izq = eliminarPorFecha(nodo.izq, fecha, id);
        } else if (fecha.isAfter(nodo.turno.fechaHora)) {
            nodo.der = eliminarPorFecha(nodo.der, fecha, id);
        } else {
            // fecha igual: buscar id en este nodo o en la rama derecha (igual fue a la derecha)
            if (nodo.turno.id.equals(id)) {
                if (nodo.izq == null || nodo.der == null) {
                    NodoAVL temp = (nodo.izq != null) ? nodo.izq : nodo.der;
                    if (temp == null) return null;
                    else nodo = temp;
                } else {
                    NodoAVL sucesor = minimo(nodo.der);
                    nodo.turno = sucesor.turno;
                    nodo.der = eliminarPorFecha(nodo.der, sucesor.turno.fechaHora, sucesor.turno.id);
                }
            } else {
                nodo.der = eliminarPorFecha(nodo.der, fecha, id);
            }
        }

        if (nodo == null) return null;

        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));
        int balance = balance(nodo);

        if (balance > 1 && balance(nodo.izq) >= 0) return rotarDerecha(nodo);
        if (balance > 1 && balance(nodo.izq) < 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && balance(nodo.der) <= 0) return rotarIzquierda(nodo);
        if (balance < -1 && balance(nodo.der) > 0) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    private NodoAVL minimo(NodoAVL nodo) {
        while (nodo.izq != null) nodo = nodo.izq;
        return nodo;
    }

    // ----------------- Cancelar con historial -----------------
    @Override
    public boolean cancelar(String idTurno) {
        Turno t = buscarTurnoPorId(raiz, idTurno);
        if (t == null) return false;
        Turno copia = copyTurno(t);
        raiz = eliminarPorId(raiz, idTurno);
        undoStack.push(new Action(ActionType.CANCELAR, copia, null));
        redoStack.clear();
        return true;
    }

    // ----------------- siguiente / primerHueco (sin cambios funcionales) -----------------
    @Override
    public Optional<Turno> siguiente(LocalDateTime t) {
        NodoAVL actual = raiz;
        Turno candidato = null;
        while (actual != null) {
            if (!actual.turno.fechaHora.isBefore(t)) {
                candidato = actual.turno;
                actual = actual.izq;
            } else actual = actual.der;
        }
        return Optional.ofNullable(candidato);
    }

    @Override
    public Optional<LocalDateTime> primerHueco(LocalDateTime t0, int durMin) {
        LocalDateTime inicio = ajustarAHorarioLaboral(t0);
        Optional<Turno> siguienteOpt = siguiente(inicio);

        while (true) {
            LocalDateTime finPropuesto = inicio.plusMinutes(durMin);
            if (finPropuesto.toLocalTime().isAfter(finJornada)) {
                inicio = LocalDateTime.of(inicio.toLocalDate().plusDays(1), inicioJornada);
                siguienteOpt = siguiente(inicio);
                continue;
            }
            if (siguienteOpt.isEmpty()) return Optional.of(inicio);
            Turno s = siguienteOpt.get();
            if (finPropuesto.isBefore(s.fechaHora)) return Optional.of(inicio);
            inicio = s.fechaHora.plusMinutes(s.duracionMin);
            siguienteOpt = siguiente(inicio);
        }
    }

    private LocalDateTime ajustarAHorarioLaboral(LocalDateTime t) {
        LocalTime hora = t.toLocalTime();
        if (hora.isBefore(inicioJornada)) return LocalDateTime.of(t.toLocalDate(), inicioJornada);
        if (hora.isAfter(finJornada)) return LocalDateTime.of(t.toLocalDate().plusDays(1), inicioJornada);
        return t;
    }

    // ----------------- Reprogramar (con historial) -----------------
    @Override
    public boolean reprogramar(String idTurno, LocalDateTime nuevaFecha) {
        Turno antiguo = buscarTurnoPorId(raiz, idTurno);
        if (antiguo == null) return false;
        Turno copiaAnt = copyTurno(antiguo);

        // quitar temporalmente
        raiz = eliminarPorId(raiz, idTurno);

        Turno nuevo = new Turno(antiguo.id, antiguo.dniPaciente, antiguo.matriculaMedico, nuevaFecha, antiguo.duracionMin, antiguo.motivo);

        if (hayConflicto(raiz, nuevo)) {
            // restaurar
            raiz = insertar(raiz, copiaAnt);
            return false;
        }

        raiz = insertar(raiz, nuevo);
        undoStack.push(new Action(ActionType.REPROGRAMAR, copiaAnt, copyTurno(nuevo)));
        redoStack.clear();
        return true;
    }

    // ----------------- Undo / Redo -----------------
    @Override
    public boolean undo() {
        if (undoStack.isEmpty()) return false;
        Action a = undoStack.pop();

        switch (a.type) {
            case AGENDAR:
                if (a.after != null) raiz = eliminarPorId(raiz, a.after.id);
                break;
            case CANCELAR:
                if (a.before != null) raiz = insertar(raiz, copyTurno(a.before));
                break;
            case REPROGRAMAR:
                if (a.after != null) raiz = eliminarPorId(raiz, a.after.id);
                if (a.before != null) raiz = insertar(raiz, copyTurno(a.before));
                break;
        }

        redoStack.push(a);
        return true;
    }

    @Override
    public boolean redo() {
        if (redoStack.isEmpty()) return false;
        Action a = redoStack.pop();

        switch (a.type) {
            case AGENDAR:
                if (a.after != null) raiz = insertar(raiz, copyTurno(a.after));
                break;
            case CANCELAR:
                if (a.before != null) raiz = eliminarPorId(raiz, a.before.id);
                break;
            case REPROGRAMAR:
                if (a.before != null) raiz = eliminarPorId(raiz, a.before.id);
                if (a.after != null) raiz = insertar(raiz, copyTurno(a.after));
                break;
        }

        undoStack.push(a);
        return true;
    }

    // ----------------- Utilitarios -----------------
    private Turno copyTurno(Turno t) {
        return new Turno(t.id, t.dniPaciente, t.matriculaMedico, t.fechaHora, t.duracionMin, t.motivo);
    }

    private enum ActionType { AGENDAR, CANCELAR, REPROGRAMAR }

    private static class Action {
        ActionType type;
        Turno before; // estado anterior
        Turno after;  // estado posterior

        Action(ActionType type, Turno before, Turno after) {
            this.type = type;
            this.before = before;
            this.after = after;
        }
    }
    
    public void imprimirAgenda() {
        System.out.println("--- Turnos Agendados (Ordenados por Fecha) ---");
        imprimirEnOrden(raiz);
        System.out.println("----------------------------------------------");
    }

    private void imprimirEnOrden(NodoAVL nodo) {
        if (nodo != null) {
            imprimirEnOrden(nodo.izq);
            System.out.println(nodo.turno.toString());
            imprimirEnOrden(nodo.der);
        }
    }
    
    // Y para limpiar el historial después de la carga inicial
    public void limpiarHistorial() {
        undoStack.clear();
        redoStack.clear();
    }
}