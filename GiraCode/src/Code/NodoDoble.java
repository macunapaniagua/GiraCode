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
public class NodoDoble {

    private NodoDoble anterior;
    private NodoDoble siguiente;
    
    public NodoDoble(){
        anterior = null;
        siguiente = null;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble pAnterior) {
        this.anterior = pAnterior;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble pSiguiente) {
        this.siguiente = pSiguiente;
    }         
    
}
