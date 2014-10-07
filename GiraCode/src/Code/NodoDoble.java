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
public class NodoDoble extends NodoSimple{

    private NodoDoble anterior;
    
    /**
     * Método Constructor
     * @param pClave dato que contiene el nodo doble
     */
    public NodoDoble(String pClave){
        super(pClave);
        anterior = null;
    }

    /**
     * Método utilizado para obtener el nodo anterior
     * @return Nodo anterior del actual
     */
    public NodoDoble getAnterior() {
        return anterior;
    }

    /**
     * Método utilizado para establecer el nodo anterior
     * @param pAnterior nodo anterior.
     */
    public void setAnterior(NodoDoble pAnterior) {
        this.anterior = pAnterior;
    }

    @Override
    public String toString() {
        return "NodoDoble{" + "Dato=" + this.getClave() + '}';
    }
    
}
