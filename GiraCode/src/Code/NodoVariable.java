/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

/**
 *
 * @author Mario A
 */
public class NodoVariable extends NodoSimple{
    
    private String valor;
    private String tipo;

    /**
     * Metodo constructor
     * @param pNombre Nombre de la variable
     * @param pValor Valor de la variable
     * @param pTipo Tipo de dato que almacena la variable (entero, decimal...)
     */
    public NodoVariable(String pNombre, String pValor, String pTipo) {
        super(pNombre);
        this.valor = pValor;
        this.tipo = pTipo;
    }

    /**
     * Metodo utilizado para obtener el Valor que almacena una variable
     * @return valor de la variable
     */
    public String getValor() {
        return valor;
    }

    /**
     * Metodo utilizado para asignar un valor a la variable
     * @param valor 
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Metodo utilizado para obtener el tipo de variable que almacena
     * @return tipo de dato almacenado. Puede ser: Texto, entero, decimal, binaria o caracter.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo utilizado para asignar un tipo de dato a una variable
     * @param tipo tipo de dato de la variable (Texto, entero, ...)
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    
}
