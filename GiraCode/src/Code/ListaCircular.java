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
public class ListaCircular {

    private NodoDoble raiz;

    /**
     * Metodo constructor
     */
    public ListaCircular() {
        raiz = null;
    }

    /**
     * Metodo utilizado para insertar un nodo en la lista circular
     *
     * @param pNuevoNodo nodo que se va a insertar en la lista circular
     */
    public void insertar(NodoDoble pNuevoNodo) {
        if (raiz == null) {
            pNuevoNodo.setSiguiente(pNuevoNodo);
            pNuevoNodo.setAnterior(pNuevoNodo);
            raiz = pNuevoNodo;
        } else {
            pNuevoNodo.setSiguiente(raiz);
            pNuevoNodo.setAnterior(raiz.getAnterior());
            raiz.getAnterior().setSiguiente(pNuevoNodo);
            raiz.setAnterior(pNuevoNodo);
        }
    }

    /**
     * Metodo utilizado para insertar un elemento a la lista circular
     *
     * @param pDato error que se va a insertar en la lista circular
     */
    public void insertar(String pDato) {
        NodoDoble pNuevoNodo = new NodoDoble(pDato);
        insertar(pNuevoNodo);
    }

    /**
     * Metodo utilizado para eliminar un nodo especifico de la lista circular
     *
     * @param pClave clave del nodo que se va a eliminar
     */
    public void eliminar(String pClave) {
        if (raiz != null) {
            if (raiz.equals(raiz.getSiguiente()) && raiz.getClave().equals(pClave)) {
                raiz = null;
            } else {
                NodoDoble recorrido = raiz;
                while (!recorrido.getClave().equals(pClave) && recorrido.getSiguiente() != raiz) {
                    recorrido = (NodoDoble) recorrido.getSiguiente();
                }
                if (recorrido.getClave().equals(pClave)) {
                    ((NodoDoble) recorrido.getSiguiente()).setAnterior(recorrido.getAnterior());
                    recorrido.getAnterior().setSiguiente(recorrido.getSiguiente());
                }
            }
        }
    }

    /**
     * Metodo utilizado para imprimir los valores que se encuentran en la lista
     * circular.
     */
    public void imprimirListaCircular() {
        if (raiz != null) {
            NodoDoble recorrido = raiz;
            do {
                System.out.println(recorrido);
                recorrido = (NodoDoble) recorrido.getSiguiente();
            } while (recorrido != raiz);
        } else {
            System.out.println("La lista circular se encuentra vacía");
        }
    }

    /**
     * Metodo utilizado para obtener todos los datos de la lista en un solo Strin
     * @return String con el contenido de la lista
     */
    public String getDatos() {
        String respuesta = "";
        // Recorre la lista circular hacia atras
        if (raiz != null) {
            NodoDoble recorrido = raiz;
            do {
                respuesta += recorrido.getClave();
                recorrido = (NodoDoble) recorrido.getSiguiente();
            } while (!recorrido.equals(raiz));
        }
        return respuesta;
    }

}
