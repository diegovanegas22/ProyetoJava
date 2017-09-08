/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajo_final_java;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author PC
 */
public abstract class Inmueble {
    
    private String codigoNacional;
    private String direccion;
    private double area;
    private BigDecimal valorComercial;
    private int estrato;
    TipoInmuebleEnum tipo;
    
    //Constructor

    public Inmueble(String codigoNacional, String direccion, BigDecimal valorComercial, int estrato, TipoInmuebleEnum tipo) {
        this.codigoNacional = codigoNacional;
        this.direccion = direccion;
        this.valorComercial = valorComercial;
        this.estrato = estrato;
        this.tipo = tipo;
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.codigoNacional);
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
        final Inmueble other = (Inmueble) obj;
        if (!Objects.equals(this.codigoNacional, other.codigoNacional)) {
            return false;
        }
        return true;
    }
    
    //Getters y setters

    public String getCodigoNacional() {
        return codigoNacional;
    }
   
    public void setCodigoNacional(String codigoNacional) {
        this.codigoNacional = codigoNacional;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public BigDecimal getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(BigDecimal valorComercial) {
        this.valorComercial = valorComercial;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public TipoInmuebleEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoInmuebleEnum tipo) {
        this.tipo = tipo;
    }
    
    
    
    //Metodo que calcula impuesto
    public abstract BigDecimal CalcularImpuesto();

    @Override
    public String toString() {
        return "Inmueble{" + "codigoNacional=" + codigoNacional +
                ", direccion=" + direccion + 
                ", area=" + area + 
                ", valorComercial=" + valorComercial + 
                ", estrato=" + estrato + 
                ", tipo=" + tipo + '}';
    }


    
}
