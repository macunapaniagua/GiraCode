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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // SE CREA LA LISTA CIRCULAR
        ListaCircular listaCircular = new ListaCircular();
        
        //******************* INSERCION DE PALABRAS RESERVADAS *****************
        listaCircular.imprimirListaCircular();
        listaCircular.insertar("Texto", "String");
        listaCircular.insertar("entero", "int");
        listaCircular.insertar("decimal", "double");
        listaCircular.insertar("caracter", "char");
        listaCircular.insertar("binario", "boolean");
        listaCircular.insertar("!", ";");
        listaCircular.insertar("programa", "class");
        listaCircular.insertar("#programa", "");
        listaCircular.insertar("imprimirln", "System.out.println");
        listaCircular.insertar("imprimir", "System.out.print");
        listaCircular.insertar("mientras", "while");        
        listaCircular.insertar("#mientras", "");
        listaCircular.insertar("para", "for");
        listaCircular.insertar("#para", "");
        listaCircular.insertar("si", "if");
        listaCircular.insertar("osi", "else if");
        listaCircular.insertar("sino", "else");
        listaCircular.insertar("#si", "");
        listaCircular.insertar("repita", "do");
        listaCircular.insertar("cuando", "while");
        listaCircular.insertar("#repita", "");
        listaCircular.insertar("<", "(");
        listaCircular.insertar(">", ")");
        listaCircular.insertar("$", "");
        listaCircular.insertar("::", "");
        listaCircular.insertar("o", "||");
        listaCircular.insertar("y", "&&");
        listaCircular.insertar("no", "!");
        listaCircular.insertar("=", "==");
        listaCircular.insertar("<>", "!=");
        listaCircular.imprimirListaCircular();
        //**********************************************************************
        
        System.out.println("Palabra mientras: " + listaCircular.esPalabraReservada("mientras"));
        System.out.println(listaCircular.getPalabraReservadaJava("mientras"));
        System.out.println("Palabra repita: " + listaCircular.esPalabraReservada("repita"));
        System.out.println(listaCircular.getPalabraReservadaJava("repita"));
        
        
//        IDE ventana = new IDE();
//        ventana.setVisible(true);
    }

}
