/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajo_final_java;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class Ciudadano {
    
    private String id;
    private String nombres;
    private String apellidos;
   // private Inmueble[] List;
    ArrayList<Inmueble> Lista;
    
    public Ciudadano(){
        
    }

    public Ciudadano(String id, String nombres, String apellidos, ArrayList<Inmueble> Lista) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.Lista = Lista;
    }
        

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ciudadano other = (Ciudadano) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    //Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public ArrayList<Inmueble> getLista() {
        return Lista;
    }

    public void setLista(ArrayList<Inmueble> Lista) {
        this.Lista = Lista;
    }

    @Override
    public String toString() {
        return "Ciudadano{" + "id=" + id + 
               ", nombres=" + nombres + 
               ", apellidos=" + apellidos + 
               ", Lista=" + Lista + '}';
    }
    
}
