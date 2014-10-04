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
public class ListaSimpleVariables {

    private NodoVariable raiz;

    /**
     * Metodo constructor
     */
    public ListaSimpleVariables() {
        raiz = null;
    }

    /**
     * Metodo utilizado para insertar una nueva variable a la lista
     *
     * @param pNuevoNodo Nodo que contiene los datos de la variable
     * @return true si la variable se pudo insertar o false en caso contrario
     */
    public boolean insertar(NodoVariable pNuevoNodo) {
        if (!existeVariable(pNuevoNodo.getClave())) {
            if (raiz == null) {
                raiz = pNuevoNodo;
            } else {
                NodoVariable recorrido = raiz;
                while (recorrido.getSiguiente() != null) {
                    recorrido = (NodoVariable) recorrido.getSiguiente();
                }
                recorrido.setSiguiente(pNuevoNodo);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo utilizado para insertar una nueva variable a la lista
     *
     * @param pNombreVariable Nombre de la variable
     * @param pValor Valor de la variable
     * @param pTipo Tipo de dato de la variable
     * @return true si la variable fue creada o false si no se puede crear
     */
    public boolean insertar(String pNombreVariable, String pValor, String pTipo) {
        if (!existeVariable(pNombreVariable)) {
            NodoVariable pNuevoNodo = new NodoVariable(pNombreVariable, pValor, pTipo);
            insertar(pNuevoNodo);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo utilizado para verificar si ya existe una variable con un nombre
     * dado
     *
     * @param pNombre Nombre de la variable a buscar
     * @return true si la variable existe o false en caso contrario
     */
    private boolean existeVariable(String pNombre) {
        if (raiz != null) {
            NodoVariable recorrido = raiz;
            while (!recorrido.getClave().equals(pNombre) && recorrido.getSiguiente() != null) {
                recorrido = (NodoVariable) recorrido.getSiguiente();
            }
            return recorrido.getClave().equals(pNombre);
        }
        return false;
    }

    /**
     * Metodo que busca una variable y retorna toda la informacion de ella, como
     * lo es el nombre, el valor y el tipo de dato
     * @param pNombre nombre de la variable a buscar
     * @return NodoVariable con toda la informacion o null en caso que no existe la variable
     */
    public NodoVariable getVariable(String pNombre) {
        NodoVariable respuesta = null;

        if (raiz != null) {
            NodoVariable recorrido = raiz;
            while (!recorrido.getClave().equals(pNombre) && recorrido.getSiguiente() != null) {
                recorrido = (NodoVariable) recorrido.getSiguiente();
            }
            if (recorrido.getClave().equals(pNombre)) {
                // Crea un nuevo nodo con los datos de la variable buscada
                respuesta = new NodoVariable(recorrido.getClave(), recorrido.getValor(), recorrido.getTipo());
            }
        }

        return respuesta;
    }

}
