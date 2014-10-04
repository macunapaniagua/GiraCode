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
public class ListaDoble {

    private NodoDoble raiz;

    /**
     * Método constructor
     */
    public ListaDoble() {
        raiz = null;
    }

    /**
     * Metodo utilizado para insertar un nodo al final de la lista doble
     *
     * @param pNuevoNodo Nodo que se va a insertar al final de la lista
     */
    public void insertarAlFinal(NodoDoble pNuevoNodo) {
        if (raiz == null) {
            raiz = pNuevoNodo;
        } else {
            NodoDoble recorrido = raiz;
            while (recorrido.getSiguiente() != null) {
                recorrido = (NodoDoble) recorrido.getSiguiente();
            }
            pNuevoNodo.setAnterior(recorrido);
            recorrido.setSiguiente(pNuevoNodo);
        }
    }

    /**
     * Metodo utilizado para insertar un nodo al inicio de la lista doble
     *
     * @param pNuevoNodo Nodo que se va a insertar al inicio de la lista
     */
    public void insertarAlInicio(NodoDoble pNuevoNodo) {
        if (raiz == null) {
            raiz = pNuevoNodo;
        } else {
            pNuevoNodo.setSiguiente(raiz);
            raiz.setAnterior(pNuevoNodo);
            raiz = pNuevoNodo;
        }
    }

    /**
     * Metodo utilizado para eliminar el primer nodo de la lista
     */
    public void eliminarAlInicio() {
        if (raiz != null) {
            if (raiz.getSiguiente() == null) {
                raiz = null;
            } else {
                ((NodoDoble) raiz.getSiguiente()).setAnterior(null);
                raiz = (NodoDoble) raiz.getSiguiente();
            }
        }
    }
    
    /**
     * FALTAN METODOS ELIMINAR Y BUSCAR ELEMENTO
     */
    
    
//    public void eliminarNodo(String pClave){
//        if(raiz != null){
//            if(raiz.getSiguiente() == null && raiz.getClave().equals(pClave)){
//                raiz = null;
//            }else{
//                NodoDoble recorrido = (NodoDoble) raiz.getSiguiente();
//                while(!recorrido.getClave().equals(pClave))
//            }
//        }
//    }

}
