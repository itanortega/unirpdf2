/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import Utilidades.Utilidades;

/**
 *
 * @author itanortegaortega
 */
public class Dato {
    private String clave;
    private Object valor;
    
    public Dato() {

    }

    public Dato(String clave, Object valor) {
        this.clave = clave;
        this.valor = valor;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return Utilidades.convertirTextoSql(valor);
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }
}
