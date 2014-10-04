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
    
    public static ListaCircular palabrasReservadas;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // SE CREA LA LISTA CIRCULAR
        palabrasReservadas = new ListaCircular();
        
        //******************* INSERCION DE PALABRAS RESERVADAS *****************
        palabrasReservadas.imprimirListaCircular();
        palabrasReservadas.insertar("Texto", "String");
        palabrasReservadas.insertar("entero", "int");
        palabrasReservadas.insertar("decimal", "double");
        palabrasReservadas.insertar("caracter", "char");
        palabrasReservadas.insertar("binario", "boolean");
        palabrasReservadas.insertar("!", ";");
        palabrasReservadas.insertar("programa", "class");
        palabrasReservadas.insertar("#programa", "");
        palabrasReservadas.insertar("imprimirln", "System.out.println");
        palabrasReservadas.insertar("imprimir", "System.out.print");
        palabrasReservadas.insertar("mientras", "while");        
        palabrasReservadas.insertar("#mientras", "");
        palabrasReservadas.insertar("para", "for");
        palabrasReservadas.insertar("#para", "");
        palabrasReservadas.insertar("si", "if");
        palabrasReservadas.insertar("osi", "else if");
        palabrasReservadas.insertar("sino", "else");
        palabrasReservadas.insertar("#si", "");
        palabrasReservadas.insertar("repita", "do");
        palabrasReservadas.insertar("cuando", "while");
        palabrasReservadas.insertar("#repita", "");
        palabrasReservadas.insertar("<", "(");
        palabrasReservadas.insertar(">", ")");
        palabrasReservadas.insertar("$", "");
        palabrasReservadas.insertar("::", "");
        
        // Operadores binarios
        palabrasReservadas.insertar("no", "!");
        
        palabrasReservadas.insertar("o", "||");
        palabrasReservadas.insertar("y", "&&");
        palabrasReservadas.insertar("=", "==");
        palabrasReservadas.insertar("<>", "!=");
        palabrasReservadas.insertar("<", "<");
        palabrasReservadas.insertar(">", ">");
        palabrasReservadas.insertar("<=", "<=");
        palabrasReservadas.insertar(">=", ">=");
        
        palabrasReservadas.insertar("verdad", "true");
        palabrasReservadas.insertar("falso", "false");
        palabrasReservadas.imprimirListaCircular();
        //**********************************************************************
        
        System.out.println("Palabra mientras: " + palabrasReservadas.esPalabraReservada("mientras"));
        System.out.println(palabrasReservadas.getPalabraReservadaJava("mientras"));
        System.out.println("Palabra repita: " + palabrasReservadas.esPalabraReservada("repita"));
        System.out.println(palabrasReservadas.getPalabraReservadaJava("repita"));
        
        
        IDE ventana = new IDE();
        ventana.setVisible(true);
    }

}
