package Clases;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Problema {
    private int Id;
    private String Descripcion;
    private Incidente[]pilaIncidente;
    private int Top;
    
    public Problema(int _Id, String _Descripcion,int maxIncidentes){
        
        this.Id=_Id;
        this.Descripcion=_Descripcion;
        this.pilaIncidente=new Incidente[maxIncidentes];
        this.Top=-1;
    }
public boolean existeIncidente(String usuario, String categoria, String detalle) {
    for (int i = 0; i < pilaIncidente.length; i++) {
        if (pilaIncidente[i] != null &&
            pilaIncidente[i].getUsuario().equalsIgnoreCase(usuario) &&
            pilaIncidente[i].getCategoria().equalsIgnoreCase(categoria) &&
            pilaIncidente[i].getDetalle().equalsIgnoreCase(detalle)) {
            return true; // El incidente ya existe
        }
    }
    return false;
}
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
     public boolean agregarIncidente(Incidente incidente) {
        if (Top < pilaIncidente.length - 1) {
            pilaIncidente[++Top] = incidente;
            return true;
        }
        return false;
    }
     
     public Incidente eliminarUltimoIncidente() {
        if (Top >= 0) {
            return pilaIncidente[Top--];
        }
        return null;
    }

    public boolean tieneIncidentes() {
        return Top >= 0;
    }
    
     public String listarIncidentes() {
        StringBuilder lista = new StringBuilder("Incidentes:\n");
        for (int i = Top; i >= 0; i--) {
            lista.append(pilaIncidente[i].toString()).append("\n");
        }
        return lista.toString();
    }
     
     @Override
    public String toString() {
        return "ID: " + Id + ", Descripción: " + Descripcion + ", Incidentes: " + (Top + 1);
    }

    public Incidente[] getIncidentes() {
    Incidente[] incidentes = new Incidente[Top + 1]; // Crear arreglo del tamaño real
    for (int i = 0; i <= Top; i++) {
        incidentes[i] = pilaIncidente[i];  // Copiar elementos al nuevo arreglo
    }
    return incidentes;
}
}
