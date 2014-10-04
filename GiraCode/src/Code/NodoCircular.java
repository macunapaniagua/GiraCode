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
public class NodoCircular extends NodoDoble {
    
    private String valor;

    /**
     * Método Constructor
     * @param pPalabraReservada palabra reservada del lenguaje GiraCode
     * @param pValor significado de la palabra reservada en lenguaje Java
     */
    public NodoCircular(String pPalabraReservada, String pValor) {
        super(pPalabraReservada);
        this.valor = pValor;
    }

    /**
     * Método utilizado para obtener el significado de la palabra reservada, en 
     * el lenguaje Java
     * @return palabra reservada Java 
     */
    public String getValor() {
        return valor;
    }

    /** 
     * Método utilizado para establecer el significado de la palabra reservada,
     * en el lenguaje Java
     * @param pValor valor Java de la palabra reservada
     */
    public void setValor(String pValor) {
        this.valor = pValor;
    }

    @Override
    public String toString() {
        return "NodoCircular{ Clave = " + getClave() + ", Valor = " + valor + '}';
    }       
}
