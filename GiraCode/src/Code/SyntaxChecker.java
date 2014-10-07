/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.util.ArrayList;

/**
 *
 * @author Mario A
 */
public class SyntaxChecker {

    private String codigo;
    private boolean syntaxisCorrecta;
    private Pila pilaPalabrasReservadas;
    private ListaCircular pilaDeErrores;
    private ListaSimpleVariables variables;

    /**
     * Metodo constructor que inicializa las variables
     *
     * @param pCodigo codigo del programa
     */
    public SyntaxChecker(String pCodigo) {
        this.codigo = pCodigo;
        syntaxisCorrecta = false;
        pilaDeErrores = new ListaCircular();
        pilaPalabrasReservadas = new Pila();
        variables = new ListaSimpleVariables();
    }

    /**
     * Metodo utilizado para indicar si la sintaxis del codigo es correcta o si
     * contiene errores
     *
     * @return true si la sintaxis es correcta o false en caso contrario
     */
    public boolean isSyntaxisCorrecta() {
        return syntaxisCorrecta;
    }

    /**
     * Metodo utilizado para verificar la sintaxis del codigo
     */
    public void verificarCodigo() {
        // Separo todo el documento por lineas de codigo (separado por '\n')
        String[] lineas = codigo.split("\n");

        // Analizo las lineas de codigo de una en una
        for (int i = 0; i < lineas.length; i++) {

            // Se analiza el código únicamente si la línea no es vacía
            if (!lineas[i].isEmpty()) {

                String palabraAnalizada = "";
                // Agrego un espacio al final para no tener problemas con el ultimo char en el for
                String lineaAnalizada = lineas[i] + ' ';

                // Ciclo recorre la linea de codigo en busca de la primer
                // palabra, para verificar cual instruccion realizar
                for (int j = 0; j < lineaAnalizada.length(); j++) {

                    // Verifica si el caracter analizado es un espacio ubicado 
                    // despues de una palabra, si es un '[' o ':'
                    if ((lineaAnalizada.charAt(j) == ' ' || lineaAnalizada.charAt(j) == '[' || lineaAnalizada.charAt(j) == ':')
                            && !palabraAnalizada.equals("")) {

                        if (main.palabrasReservadas.esPalabraReservada(palabraAnalizada)) {
                            // La palabra analizada es una palabra reservada
                            System.out.println(palabraAnalizada + " es palabra reservada");
                            switch (palabraAnalizada) {
                                case "programa":
                                    // Intento agregar la etiqueta de apertura del programa
                                    if (this.abrirPrograma(lineaAnalizada, j, (i + 1))) {
                                        break;
                                    } else {
                                        // Algo fallo al crear la etiqueta programa. Sale de la compilacion
                                        return;
                                    }
                                case "#programa":
                                    // Intento agregar la etiqueta de cierre del programa
                                    if (this.cerrarPrograma(lineaAnalizada, j, (i + 1))) {
                                        break;
                                    } else {
                                        // Algo fallo al cerrar la etiqueta #programa. Sale de la compilacion
                                        return;
                                    }
                                case "imprimir":
                                    break;
                                case "imprimirln":
                                    break;
                                case "Texto":
                                    break;
                                case "entero":
                                    break;
                                case "decimal":
                                    break;
                                case "caracter":
                                    break;
                                case "binario":
                                    break;
                                case "si":
                                    break;
                                case "osi":
                                    break;
                                case "sino":
                                    break;
                                case "#si":
                                    break;
                                case "mientras":
                                    break;
                                case "#mientras":
                                    break;
                                case "para":
                                    break;
                                case "#para":
                                    break;
                                case "repita":
                                    break;
                                case "#cuando":
                                    break;
                            }
                            // continua con la proxima linea de codigo (proxima iteracion del for)
                            // continue;
                        } else if (variables.existeVariable(palabraAnalizada)) {
                            // La palabra reservada es una variable, por lo tanto debe de ser una asignacion de valor
                        } else {
                            // No se reconoce la palabra
                            System.out.println("No se reconoce la palabra " + palabraAnalizada
                                    + " ubicada en la linea de codigo " + (i + 1));
                            //return;
                        }
                        // Termina de analizar la linea de codigo actual
                        break;

                    } else {
                        // Ignora los espacios en blanco seguidos y los tab
                        if (lineaAnalizada.charAt(j) != ' ' && lineaAnalizada.charAt(j) != '\t') {
                            palabraAnalizada += lineaAnalizada.charAt(j);
                        }
                    }
                }
            }
        }
    }

    public boolean crearVariable() {
        return true;
    }

    /**
     * Metodo utilizado para verificar la primera linea del programa
     *
     * @param pLineaCodigo Linea de codigo donde se abre programa
     * @param pPosicionCaracter posicion donde se encuentra el caracter
     * siguiente de la palabra programa
     * @param pNumeroLinea numero de la linea analizada
     * @return true si la sintaxis esta correcta o false en caso que haya
     * problemas
     */
    public boolean abrirPrograma(String pLineaCodigo, int pPosicionCaracter, int pNumeroLinea) {
        // Verifica si el caracter seguido de la palabra reservada es ' ', '[' o ':'
        if (pLineaCodigo.charAt(pPosicionCaracter) == ' ') {
            // Verifica si ya existen palabras reservadas en la pila.
            if (!pilaPalabrasReservadas.estaVacia()) {
                System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". La etiqueta "
                        + "programa ya ha sido creada y no es posible abrir dos en un mismo programa.");
                return false;
            } else {
                pPosicionCaracter++;
                String nombrePrograma = "";

                // Se itera sobre la linea de codigo, para obtener el nombre del programa
                for (; pPosicionCaracter < pLineaCodigo.length(); pPosicionCaracter++) {

                    if (!nombrePrograma.equals("") && pLineaCodigo.charAt(pPosicionCaracter) == ' ') {
                        // Ya se obtuvo el nombre del programa
                        pPosicionCaracter++;
                        break;
                    } else {
                        // Verifica el caracter que esta ingresando para ponerlo en el nombre, e ignora si es un espacio o un tab.
                        if (pLineaCodigo.charAt(pPosicionCaracter) != ' ' && pLineaCodigo.charAt(pPosicionCaracter) != '\t') {
                            // Verifica que el primer caracter del nombre sea una letra
                            if (nombrePrograma.equals("") && !Character.isLetter(pLineaCodigo.charAt(pPosicionCaracter))) {
                                System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". El nombre del "
                                        + "programa no puede comenzar con " + pLineaCodigo.charAt(pPosicionCaracter)
                                        + ". El nombre del programa debe iniciar con una letra.");
                                return false;
                            } // Verifica que el nombre solo contenga letras y numeros
                            else if (!Character.isLetter(pLineaCodigo.charAt(pPosicionCaracter))
                                    && !Character.isDigit(pLineaCodigo.charAt(pPosicionCaracter))) {
                                System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". El nombre del "
                                        + "programa no puede contener el caracter " + pLineaCodigo.charAt(pPosicionCaracter)
                                        + ". El nombre del programa solo puede conterner letras y números.");
                                return false;

                            } else {
                                // Agrega el caracter actual al nombre del programa
                                nombrePrograma += pLineaCodigo.charAt(pPosicionCaracter);
                            }
                        }
                    }
                }
                // Verifica que el nombre del programa no haya quedado en blanco
                if (nombrePrograma.equals("")) {
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". "
                            + "No se ha especificado el nombre del programa.");
                    return false;
                }
                // Verifica que el nombre del programa no sea una palabra reservada
                else if(main.palabrasReservadas.esPalabraReservada(nombrePrograma)){
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". "
                            + "El nombre del programa no puede ser una palabra reservada");
                    return false;
                }else {
                    // Aqui verifica que no haya otro caracter despues del nombre del programa
                    boolean otraLetra = false;
                    for (; pPosicionCaracter < pLineaCodigo.length(); pPosicionCaracter++) {
                        if (pLineaCodigo.charAt(pPosicionCaracter) != ' ' && pLineaCodigo.charAt(pPosicionCaracter) != '\t') {
                            otraLetra = true;
                            break;
                        }
                    }
                    // Verifica si el codigo posee algun caracter despues del nombre del programa
                    // y muestra el error o finaliza la insercion del programa de manera exitosa
                    if (otraLetra) {
                        System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". El nombre del "
                                + "programa no puede contener carácteres después de él.");
                        return false;
                    } else {
                        // Escritura exitosa del nombre del programa.
                        variables.insertar(nombrePrograma, "", "clase");
                        pilaPalabrasReservadas.push("programa");
                        return true;
                    }
                }
            }
        } else {
            System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la etiqueta programa, no se puede ubicar el "
                    + "caracter " + pLineaCodigo.charAt(pPosicionCaracter) + ""
                    + ". La sintaxis correcta es: programa nombrePrograma");
            return false;
        }
    }

    public boolean cerrarPrograma(String pLineaCodigo, int pPosicionCaracter, int pNumeroLinea) {
        // Verifica que el caracter ubicado despues de #programa sea ' ' y no ':' o '['
        if (pLineaCodigo.charAt(pPosicionCaracter) == ' ') {
            // Verifica si hay palabras reservadas en la pila, sino, no existe 'programa'
            if (pilaPalabrasReservadas.estaVacia()) {
                System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No se ha encontrado la etiqueta de inicio del programa");
                return false;
            } // Verifica si en la pila de palabras solo esta "programa" o debe cerrar otras etiquetas
            else if (!pilaPalabrasReservadas.getValorEnTop().equals("programa")) {
                System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". Primero debe cerrar " + pilaPalabrasReservadas.getValorEnTop() + " antes"
                        + "de utilizar la etiqueta #programa");
                return false;
            } else {
                // Verifica que no haya nada mas despues de #programa
                pPosicionCaracter++;
                boolean otraLetra = false;
                for (; pPosicionCaracter < pLineaCodigo.length(); pPosicionCaracter++) {
                    if (pLineaCodigo.charAt(pPosicionCaracter) != ' ' && pLineaCodigo.charAt(pPosicionCaracter) != '\t') {
                        otraLetra = true;
                        break;
                    }
                }
                // Verifica si el codigo posee algun caracter despues de #programa y muestra el error
                //  o finaliza la revision del programa de manera exitosa
                if (otraLetra) {
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". El cierre del "
                            + "programa (#programa), no puede contener carácteres después de él.");
                    return false;
                } else {
                    // Escritura exitosa del nombre del programa. Hace pop a la lista de palabras reservadas
                    pilaPalabrasReservadas.pop();
                    syntaxisCorrecta = true;
                    variables = null;
                    return true;
                }
            }
        } else {
            System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la etiqueta #programa, no se puede ubicar el "
                    + "caracter " + pLineaCodigo.charAt(pPosicionCaracter) + ""
                    + ". La sintaxis correcta es: #programa");
            return false;
        }
    }

    public boolean verificarImprimir() {
        return true;
    }

    public boolean abrirMientras() {
        return true;
    }

    public boolean cerrarMientras() {
        return true;
    }

    public boolean abrirPara() {
        return true;
    }

    public boolean cerrarPara() {
        return true;
    }

    public boolean abrirSi() {
        return true;
    }

    public boolean cerrarSi() {
        return true;
    }

    public boolean abrirRepita() {
        return true;
    }

    public boolean cerrarRepitaCuando() {
        return true;
    }

}
