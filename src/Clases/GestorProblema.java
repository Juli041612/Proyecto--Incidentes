/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class GestorProblema {
    private Problema[] colaProblemas;
    private int frente; // Índice del primer problema en la cola
    private int fin;    // Índice del último problema en la cola
    private int contadorIdProblema = 1;  // Generador de ID para problemas
    private int contadorIdIncidente = 1; // Generador de ID para incidentes

    public GestorProblema(int maxProblemas) {
        colaProblemas = new Problema[maxProblemas];
        frente = 0;
        fin = -1;
    }
    
    public boolean agregarProblema(String descripcion, int maxIncidentes) {
        
         // Verificar si ya existe un problema con la misma descripción
    for (int i = 0; i <= fin; i++) {
        if (colaProblemas[i] != null && colaProblemas[i].getDescripcion().equalsIgnoreCase(descripcion)) {
            return false; // Ya existe un problema con esta descripción
        }
    }
        if (fin < colaProblemas.length - 1) {
            
            colaProblemas[++fin] = new Problema(contadorIdProblema++, descripcion, maxIncidentes);
            return true;
        }
        return false;
    }

    // Eliminar el primer problema (solo si no tiene incidentes)
    public boolean eliminarProblema() {
    if (frente <= fin) {
        Problema problema = colaProblemas[frente];

        // Agregar impresión para verificar número de incidentes
        System.out.println("Incidentes en problema a eliminar: " + problema.getIncidentes().length);

        if (problema.getIncidentes().length > 0) {
            return false; // Si hay incidentes, no eliminar
        }

        // Avanzar la cola
        colaProblemas[frente] = null;
        frente++;

        if (frente > fin) {
            frente = 0;
            fin = -1;
        }

        return true;
    }
    return false;
}

    // Obtener lista de problemas
    public String listarProblemas() {
        StringBuilder lista = new StringBuilder("Problemas en la cola:\n");
        for (int i = frente; i <= fin; i++) {
            lista.append(colaProblemas[i].toString()).append("\n");
        }
        return lista.toString();
    }

    // Agregar incidente a un problema específico
    public boolean agregarIncidente(int idProblema, String usuario, String categoria, String detalle, String fecha) {
        for (int i = frente; i <= fin; i++) {
        if (colaProblemas[i].getId() == idProblema) {
            // Verificar si ya existe un incidente con los mismos datos
            if (colaProblemas[i].existeIncidente(usuario, categoria, detalle)) {
                return false; // Ya existe un incidente igual
            }

            // Si no está duplicado, agregarlo
            Incidente incidente = new Incidente(contadorIdIncidente++, usuario, categoria, detalle, fecha);
            return colaProblemas[i].agregarIncidente(incidente);
        }
    }
    return false;
    }

    // Eliminar el último incidente de un problema
    public boolean eliminarIncidente(int idProblema) {
        for (int i = frente; i <= fin; i++) {
            if (colaProblemas[i].getId() == idProblema) {
                return colaProblemas[i].eliminarUltimoIncidente() != null;
            }
        }
        return false;
    }
    public Problema obtenerProblemaPorId(int id) {
    for (Problema problema : colaProblemas) { 
        if (problema.getId() == id) { 
            return problema; // Retorna el problema si el ID coincide
        }
    }
    return null; // Retorna null si no encuentra el problema
}

    // Trasladar incidentes de un problema a otro
    public boolean trasladarIncidentes(int idOrigen, int idDestino) {
    Problema origen = obtenerProblemaPorId(idOrigen);
    Problema destino = obtenerProblemaPorId(idDestino);

    if (origen == null || destino == null || origen == destino) {
        return false;
    }

    if (!origen.tieneIncidentes()) {
        return false; // No hay incidentes para trasladar
    }

    while (origen.tieneIncidentes()) {
        Incidente incidente = origen.eliminarUltimoIncidente(); // POP del problema origen
        destino.agregarIncidente(incidente); // PUSH al problema destino
    }
    
    return true;
}
    

   public Problema[] getListaProblemas() {
    return Arrays.stream(colaProblemas)
                 .filter(Objects::nonNull)
                 .toArray(Problema[]::new);
}

    public boolean estaVacia() {
         return frente > fin;
    }

    public Problema obtenerPrimerProblema() {
       if (estaVacia()) {
        return null; // Retorna null si la cola está vacía
    }
    return colaProblemas[frente];  
    }

    public boolean eliminarPrimerProblema() {
      if (estaVacia()) {
        return false; // No se puede eliminar si está vacía
    }

    Problema primerProblema = colaProblemas[frente]; // Obtener el primer problema
    if (primerProblema.tieneIncidentes()) { 
        return false; // No se puede eliminar si tiene incidentes
    }

    colaProblemas[frente] = null; // Elimina el primer problema
    frente++; // Avanza en la cola
    return true;
}
}
