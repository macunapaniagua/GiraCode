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
    public Pila() {
        top = null;
    }

    /**
     * Metodo utilizado para insertar un nuevo nodo en la Pila
     *
     * @param pNuevoNodo nodo a insertar en la Pila
     */
    public void push(NodoSimple pNuevoNodo) {
        pNuevoNodo.setSiguiente(top);
        top = pNuevoNodo;
    }
    
    /**
     * Metodo utilizado para insertar un nuevo elemento a la pila
     * @param pDato dato que se va a insertar a la pila,
     */
    public void push(String pDato){
        NodoSimple nuevoNodo = new NodoDoble(pDato);
        push(nuevoNodo);
    }

    /**
     * Metodo para extraer un elemento de la Pila. Se extrae el que se encuentre
     * en el top de la pila.
     */
    public void pop() {
        if (top != null) {
            if (top.getSiguiente() == null) {
                top = null;
            } else {
                top = top.getSiguiente();
            }
        }
    }

    /**
     * Metodo utilizado para verificar si la lista esta vacia o si contiene algo
     *
     * @return true si esta vacia, false en caso contrario.
     */
    public boolean estaVacia() {
        return (top == null);
    }

    /**
     * Metodo utilizado para obtener la clave del nodo que se encuentra en el
     * top de la pila
     *
     * @return clave del nodo que se encuentra en el Top
     */
    public String getValorEnTop() {
        String valorTop = "";
        if (top != null) {
            valorTop = top.getClave();
        }
        return valorTop;
    }
}
