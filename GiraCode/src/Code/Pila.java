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
public class Pila {
    
    private NodoSimple top;
    
    /**
     * Metodo constructor de la pila. Inicializa el Top en null
     */
    public Pila(){
        top = null;
    }
    
    /**
     * Metodo utilizado para insertar un nuevo nodo en la Pila
     * @param pNuevoNodo nodo a insertar en la Pila
     */
    public void push(NodoSimple pNuevoNodo){        
        pNuevoNodo.setSiguiente(top);
        top = pNuevoNodo;
    }
    
    /**
     * Metodo para extraer un elemento de la Pila. Se extrae
     * el que se encuentre en el top de la pila.
     */
    public void pop(){
        if(top != null){
            if(top.getSiguiente() == null){
                top = null;
            }else{
                top = top.getSiguiente();
            }
        }
    }   
}
