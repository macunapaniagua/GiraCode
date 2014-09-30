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
    
    public NodoSimple(String pClave){
        this.clave = pClave;
        this.siguiente = null;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple pSiguiente) {
        this.siguiente = pSiguiente;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String pClave) {
        this.clave = pClave;
    }
            
}
