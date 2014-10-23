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

    private NodoPalabraReservada raiz;

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
    public void insertarAlFinal(NodoPalabraReservada pNuevoNodo) {
        if (raiz == null) {
            raiz = pNuevoNodo;
        } else {
            NodoPalabraReservada recorrido = raiz;
            while (recorrido.getSiguiente() != null) {
                recorrido = (NodoPalabraReservada) recorrido.getSiguiente();
            }
            pNuevoNodo.setAnterior(recorrido);
            recorrido.setSiguiente(pNuevoNodo);
        }
    }
    
    /**
     * Metodo utilizado para insertar un elemento al final de la lista doble
     * @param pClave palabra reservada GiraCODE
     * @param pValor palabra reservada Java
     */
    public void insertarAlFinal(String pClave, String pValor){
        NodoPalabraReservada nuevoNodo = new NodoPalabraReservada(pClave, pValor);
        insertarAlFinal(nuevoNodo);
    }

    /**
     * Metodo utilizado para insertar un nodo al inicio de la lista doble
     *
     * @param pNuevoNodo Nodo que se va a insertar al inicio de la lista
     */
    public void insertarAlInicio(NodoPalabraReservada pNuevoNodo) {
        if (raiz == null) {
            raiz = pNuevoNodo;
        } else {
            pNuevoNodo.setSiguiente(raiz);
            raiz.setAnterior(pNuevoNodo);
            raiz = pNuevoNodo;
        }
    }
    
    /**
     * Metodo utilizado para insertar un elemento al inicio de la lista doble
     * @param pClave palabra reservada GiraCODE
     * @param pValor palabra reservada Java
     */
    public void insertarAlInicio(String pClave, String pValor){
        NodoPalabraReservada nuevoNodo = new NodoPalabraReservada(pClave, pValor);
        insertarAlInicio(nuevoNodo);
    }

    /**
     * Metodo utilizado para eliminar el primer nodo de la lista
     */
    public void eliminarAlInicio() {
        if (raiz != null) {
            if (raiz.getSiguiente() == null) {
                raiz = null;
            } else {
                ((NodoPalabraReservada) raiz.getSiguiente()).setAnterior(null);
                raiz = (NodoPalabraReservada) raiz.getSiguiente();
            }
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
            NodoPalabraReservada recorrido = raiz;
            while (!recorrido.getClave().equals(pClave) && recorrido.getSiguiente() != null) {
                recorrido = (NodoPalabraReservada) recorrido.getSiguiente();
            }
            if (recorrido.getClave().equals(pClave)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodo utilizado para retornar el equivalente Java de una palabra reservada
     * del lenguaje GiraCODE
     * @param pClave palabra reservada GiraCODE
     * @return equivalente Java de la palabra reservada o "" si no existe
     */
    public String getPalabraReservadaJava(String pClave){
        if (raiz != null) {
            NodoPalabraReservada recorrido = raiz;
            while (!recorrido.getClave().equals(pClave) && recorrido.getSiguiente() != null) {
                recorrido = (NodoPalabraReservada) recorrido.getSiguiente();
            }
            if (recorrido.getClave().equals(pClave)) {
                return recorrido.getValor();
            }
        }
        return "";
    }
    
    /**
     * Metodo utilizado para imprimir los valores que se encuentran en la lista 
     * 
     */
    public void imprimirListaDoble() {
        if (raiz != null) {
            NodoPalabraReservada recorrido = raiz;
            while (recorrido != null){
                System.out.println(recorrido);
                recorrido = (NodoPalabraReservada) recorrido.getSiguiente();
            } 
        } else {
            System.out.println("La lista doble se encuentra vacía");
        }
    }
    
}
