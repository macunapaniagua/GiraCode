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
    
    private String clave;
    private String valor;

    public NodoCircular(String pClave, String pValor) {
        super();
        this.clave = pClave;
        this.valor = pValor;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String pClave) {
        this.clave = pClave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String pValor) {
        this.valor = pValor;
    }
    
    
    
    
}
