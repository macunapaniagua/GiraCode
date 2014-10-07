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
public class Interprete {

    String codigoGiraCODE;
    ListaSimpleVariables listaDeVariables;

    /**
     * Metodo constructor
     *
     * @param pCodigo codigo a ejecutarse
     * @param pVariables lista de palabras reservadas
     */
    public Interprete(String pCodigo, ListaSimpleVariables pVariables) {
        this.codigoGiraCODE = pCodigo;
        listaDeVariables = pVariables;
    }

////    public String ejecutar() {
////
////    }

////    public double operacionAritmetica(char pOperador, String pVar1, String pVar2) {
////        
////        // Operadores "+", "-", "/", "%", "*", "^"
////        
//////        NodoVariable variable1 = listaDeVariables.getVariable(pVar1);
//////        NodoVariable variable1 = listaDeVariables.getVariable(pVar2);
////    }

////    public boolean operacionBinaria(String pVar, String pOperador) {                  
////        // Operadores GiraCODE "no", ""
////        // Operadores Java      "!", ""
////        
////        // Si el operador es "" quiere decir que pVar es "entero" o "false" y nada
////        // mas tengo que encontrar el valor java de este
////        /*
////            if(pOperador.equals("")){
////                return pVar.equals("verdad");
////        */
////    }

    /**
     * Metodo utilizado para realizar una operacion binaria
     * @param pVariable1 Dato 1 sobre el que se va a realizar la operacion
     * @param pOperador Operador bianario
     * @param pVariable2 Dato 2 sobre el que se va a realizar la operacion
     * @return El resultado de la operacion binaria, el cual puede ser true o false
     */
////    public boolean operacionBinaria(String pVar1, String pOperador, String pVar2) {
////
////        // Operadores GiraCODE "y", "o", "=", "<>", "<=", ">=", "<", ">"
////        // Operadores Java  "&&", "||", "==", "!=", "<=", ">=", "<", ">"
////        
////    }
////
////    public String imprimir(String pLineaDeCodigo) {
////        
////    }
}
