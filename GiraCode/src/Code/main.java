/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import GUI.IDE;

/**
 *
 * @author Mario A
 */
public class main {

    public static ListaDoble palabrasReservadas;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // SE CREA LA LISTA CIRCULAR
        palabrasReservadas = new ListaDoble();

        //******************* INSERCION DE PALABRAS RESERVADAS *****************
        palabrasReservadas.imprimirListaDoble();

        // Inicio y fin de de programa
        palabrasReservadas.insertarAlInicio("programa", "class");
        palabrasReservadas.insertarAlInicio("#programa", "");
        // Tipos de datos
        palabrasReservadas.insertarAlInicio("Texto", "String");
        palabrasReservadas.insertarAlInicio("entero", "int");
        palabrasReservadas.insertarAlInicio("decimal", "double");
        palabrasReservadas.insertarAlInicio("caracter", "char");
        palabrasReservadas.insertarAlInicio("binario", "boolean");
        // Imprimir
        palabrasReservadas.insertarAlFinal("imprimirln", "System.out.println");
        palabrasReservadas.insertarAlInicio("imprimir", "System.out.print");
        // Condicional If
        palabrasReservadas.insertarAlInicio("si", "if");
        palabrasReservadas.insertarAlInicio("osi", "else if");
        palabrasReservadas.insertarAlInicio("sino", "else");
        palabrasReservadas.insertarAlInicio("#si", "");
        // Ciclos
        palabrasReservadas.insertarAlInicio("mientras", "while");
        palabrasReservadas.insertarAlInicio("#mientras", "");
        palabrasReservadas.insertarAlInicio("para", "for");
        palabrasReservadas.insertarAlInicio("#para", "");
        palabrasReservadas.insertarAlInicio("repita", "do");
        palabrasReservadas.insertarAlInicio("#cuando", "while");

        // Valores binarios
        palabrasReservadas.insertarAlInicio("verdad", "true");
        palabrasReservadas.insertarAlInicio("falso", "false");
        // Operadores Binarios
        palabrasReservadas.insertarAlInicio("o", "||");
        palabrasReservadas.insertarAlInicio("y", "&&");
        palabrasReservadas.insertarAlInicio("no", "!");

//////        // Fin de linea        
//        palabrasReservadas.insertarAlInicio("!", ";");        
//////        
//////        // Caracteres especiales
//////        palabrasReservadas.insertarAlInicio("[", "(");
//////        palabrasReservadas.insertarAlInicio("]", ")");
//////        palabrasReservadas.insertarAlInicio("$", "");
//        palabrasReservadas.insertarAlInicio("::", "");
//////        
//////        // Operadores binarios
//////        palabrasReservadas.insertarAlInicio("=", "==");
//////        palabrasReservadas.insertarAlInicio("<>", "!=");
//////        palabrasReservadas.insertarAlInicio("<", "<");
//////        palabrasReservadas.insertarAlInicio(">", ">");
//////        palabrasReservadas.insertarAlInicio("<=", "<=");
//////        palabrasReservadas.insertarAlInicio(">=", ">=");
        palabrasReservadas.imprimirListaDoble();
        //**********************************************************************

        System.out.println("Palabra mientras: " + palabrasReservadas.esPalabraReservada("mientras"));
        System.out.println(palabrasReservadas.getPalabraReservadaJava("mientras"));
        System.out.println("Palabra repita: " + palabrasReservadas.esPalabraReservada("repita"));
        System.out.println(palabrasReservadas.getPalabraReservadaJava("repita"));

        IDE ventana = new IDE();
        ventana.setVisible(true);
    }

}
