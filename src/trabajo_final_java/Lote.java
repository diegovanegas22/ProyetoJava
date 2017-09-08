/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajo_final_java;

import java.math.BigDecimal;

/**
 *
 * @author PC
 */
public class Lote extends Inmueble{

    public Lote(String codigoNacional, String direccion, BigDecimal valorComercial, int estrato, TipoInmuebleEnum tipo) {
        super(codigoNacional, direccion, valorComercial, estrato, tipo);
    }
    
      @Override
      public BigDecimal CalcularImpuesto(){
    
        double c=0.9;
          BigDecimal areaA = new BigDecimal(super.getArea());
          
        return areaA.multiply(super.getValorComercial()) ;
    }
    
}
