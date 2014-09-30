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
public class NodoSimple {
    
    private NodoSimple siguiente;
    private String clave;
    
    /**
     * Método constructor
     * @param pClave valor que almacenara el Nodo
     */
    public NodoSimple(String pClave){
        this.clave = pClave;
        this.siguiente = null;
    }

    /**
     * Método utilizado para obtener un nodo siguiente
     * @return nodo siguiente
     */
    public NodoSimple getSiguiente() {
        return siguiente;
    }

    /**
     * Método utilizado para establecer un nodo siguiente
     * @param pSiguiente nuevo nodo siguiente
     */
    public void setSiguiente(NodoSimple pSiguiente) {
        this.siguiente = pSiguiente;
    }

    /**
     * Método utilizado para obtener el valor del dato que contiene el nodo
     * @return dato almacenado por el nodo
     */
    public String getClave() {
        return clave;
    }

    /**
     * Método utilizado para establecer el dato que almacenará el nodo
     * @param pClave dato que almacenará el nodo
     */
    public void setClave(String pClave) {
        this.clave = pClave;
    }
            
}
