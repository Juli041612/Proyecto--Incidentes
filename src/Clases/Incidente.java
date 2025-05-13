/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class Incidente {
    
    private int ID;
    private String Usuario;
    private String Categoria;
    private String Detalle;
    private String Fecha;

    
    
    public Incidente (int _ID, String _Usuario,String _Categoria,String _Detalle,String _Fecha){
        
        this.ID=_ID;
        this.Usuario=_Usuario;
        this.Categoria=_Categoria;
        this.Detalle=_Detalle;
        this.Fecha=_Fecha;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
    @Override
    public String toString(){
        
        return "ID:"+ID+",Usuario"+Usuario+",Categoría:"+Categoria;
                
    }
}


