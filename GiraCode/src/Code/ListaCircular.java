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

    private NodoCircular raiz;

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
    public void insertar(NodoCircular pNuevoNodo) {
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
     * @param pPalabraReservada palabra reservada en el lenguaje GiraCODE
     * @param pValor equivalencia en Java de la palabra reservada
     */
    public void insertar(String pPalabraReservada, String pValor) {
        NodoCircular pNuevoNodo = new NodoCircular(pPalabraReservada, pValor);
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
                NodoCircular recorrido = raiz;
                while (!recorrido.getClave().equals(pClave) && recorrido.getSiguiente() != raiz) {
                    recorrido = (NodoCircular) recorrido.getSiguiente();
                }
                if (recorrido.getClave().equals(pClave)) {
                    ((NodoCircular) recorrido.getSiguiente()).setAnterior(recorrido.getAnterior());
                    recorrido.getAnterior().setSiguiente(recorrido.getSiguiente());
                }
            }
        }
    }

    /**
     * Metodo utilizado para imprimir los valores clave-valor que se encuentran
     * en la lista circular.
     */
    public void imprimirListaCircular() {
        if (raiz != null) {
            NodoCircular recorrido = raiz;
            do {
                System.out.println(recorrido);
                recorrido = (NodoCircular) recorrido.getSiguiente();
            } while (recorrido != raiz);
        } else {
            System.out.println("La lista circular se encuentra vacía");
        }
    }

    /**
     * Metodo utilizado para verificar si una palabra es palabra reservada
     *
     * @param pClave palabra a verificar si es reservada
     * @return true si es palabra reservada o false en caso contrario.
     */
    public boolean esPalabraReservada(String pClave) {
        if (raiz != null) {
            NodoCircular recorrido = raiz;
            while (!recorrido.getClave().equals(pClave) && !recorrido.getSiguiente().equals(raiz)) {
                recorrido = (NodoCircular) recorrido.getSiguiente();
            }
            if (recorrido.getClave().equals(pClave)) {
                return true;
            }
        }
        return false;
    }
    
    public String getPalabraReservadaJava(String pClave){
        if (raiz != null) {
            NodoCircular recorrido = raiz;
            while (!recorrido.getClave().equals(pClave) && !recorrido.getSiguiente().equals(raiz)) {
                recorrido = (NodoCircular) recorrido.getSiguiente();
            }
            if (recorrido.getClave().equals(pClave)) {
                return recorrido.getValor();
            }
        }
        return "";
    }
}
