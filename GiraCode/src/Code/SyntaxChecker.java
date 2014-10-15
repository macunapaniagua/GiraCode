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
public class SyntaxChecker {

    private String salidaRevision = "";
    private Pila pilaPalabrasReservadas;
    private ListaSimpleVariables variables;

    public String getSalidaRevision() {
        return salidaRevision;
    }

    /**
     * Metodo constructor que inicializa las variables
     *
     */
    public SyntaxChecker() {
        pilaPalabrasReservadas = new Pila();
        variables = new ListaSimpleVariables();
    }

    /**
     * Metodo utilizado para verificar la sintaxis del codigo
     */
    public void verificarCodigo(String pCodigo) {
        // Separo todo el documento por lineas de codigo (separado por '\n')
        String[] lineas = pCodigo.split("\n");

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
                    if ((lineaAnalizada.charAt(j) == ' ' || lineaAnalizada.charAt(j) == '['
                            || lineaAnalizada.charAt(j) == ':' || lineaAnalizada.charAt(j) == '=' || lineaAnalizada.charAt(j) == '\t')
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
                                case "Texto":
                                    // Intento crear una variable de tipo Texto
                                    if (this.crearVariable(lineaAnalizada, j, (i + 1), "Texto")) {
                                        break;
                                    } else {
                                        // Algo fallo al crear la variable. Sale de la compilacion
                                        return;
                                    }
                                case "entero":
                                    // Intento crear una variable de tipo entero
                                    if (this.crearVariable(lineaAnalizada, j, (i + 1), "entero")) {
                                        break;
                                    } else {
                                        // Algo fallo al crear la variable. Sale de la compilacion
                                        return;
                                    }
                                case "decimal":
                                    // Intento crear una variable de tipo decimal
                                    if (this.crearVariable(lineaAnalizada, j, (i + 1), "decimal")) {
                                        break;
                                    } else {
                                        // Algo fallo al crear la variable. Sale de la compilacion
                                        return;
                                    }
                                case "caracter":
                                    // Intento crear una variable de tipo caracter
                                    if (this.crearVariable(lineaAnalizada, j, (i + 1), "caracter")) {
                                        break;
                                    } else {
                                        // Algo fallo al crear la variable. Sale de la compilacion
                                        return;
                                    }
                                case "binario":
                                    // Intento crear una variable de tipo binario
                                    if (this.crearVariable(lineaAnalizada, j, (i + 1), "binario")) {
                                        break;
                                    } else {
                                        // Algo fallo al crear la variable. Sale de la compilacion
                                        return;
                                    }
                                case "imprimir":
                                    // Intenta imprimir una linea
                                    if (this.verificarImprimir(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        // Algo fallo al imprimir
                                        return;
                                    }
//                                case "imprimirln":
//                                    break;
                                case "si":
                                    // Verifica la condicion
                                    if (this.abrirSi(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        // Algo fallo
                                        return;
                                    }
                                case "osi":
                                    if (comprobarOsi(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                                case "sino":
                                    // No debe contener nada despues de el
                                    if (comprobraSino(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                                case "#si":
                                    if (this.cerrarSi(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                                case "mientras":
                                    if (this.abrirMientras(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                                case "#mientras":
                                    if (this.cerrarMientras(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                                case "para":
                                    if (this.abrirPara(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                                case "#para":
                                    if (this.cerrarPara(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                                case "repita":
                                    if (this.abrirRepita(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                                case "#cuando":
                                    if (this.cerrarRepitaCuando(lineaAnalizada, j, i + 1)) {
                                        break;
                                    } else {
                                        return;
                                    }
                            }
                        } // Verifica si es una variable
                        else if (variables.existeVariable(palabraAnalizada)) {
                            System.out.println("Es variable");
                            // Verifica si la variable que se va a usar es el nombre de la clase
                            if (variables.getVariable(palabraAnalizada).getTipo().equals("clase")) {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + (i + 1)
                                        + ". No se pueden realizar operaciones sobre el nombre de la clase\n";
                                return;
                            } else {
                                // Verifica si la asignacion se ejecuto correctamente
                                if (realizarAsignacion(lineaAnalizada, j, (i + 1), variables.getVariable(palabraAnalizada).getTipo())) {
                                    break;
                                } else {
                                    return;
                                }
                            }
                        } else {
                            // No se reconoce la palabra
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + (i + 1)
                                    + ". No se reconoce la palabra '" + palabraAnalizada + "' como una sentencia válida"
                                    + " o es una variable que no ha sido declarada\n";
                            return;
                        }
                        // Termina de analizar la linea de codigo actual para analizar la siguiente
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
        // Se verifica que la pila de palabras reservadas haya quedado vacia, lo que
        // indica que no quedaron etiquetas abiertas en el programa.
        if (pilaPalabrasReservadas.estaVacia()) {
            salidaRevision += "Compilación correcta\n";
            variables = null;
        } else {
            salidaRevision += "ERROR DE SINTAXIS. Aun no se ha cerrado la "
                    + "etiqueta '" + pilaPalabrasReservadas.getValorEnTop() + "'\n";
        }
    }

    /**
     * Metodo utilizado para verificar la linea de creacion de variables y crear
     * una de estas cuando este correcta la sintaxis
     *
     * @param pLineaCodigo Linea de codigo donde hay una declaracion de vaiables
     * @param pPosicionCaracter posicion donde se encuentra el caracter
     * siguiente de la palabra reservada
     * @param pNumeroLinea numero de la linea analizada
     * @param pTipoVariable El tipo de variable a crear: Texto, entero...
     * @return true si la sintaxis esta correcta o false en caso que haya
     * problemas
     */
    public boolean crearVariable(String pLineaCodigo, int pPosicionCaracter, int pNumeroLinea, String pTipoVariable) {
        // Verifica si despues de Texto, entero... el caracter existente es ' ' o ':' 
        if (pLineaCodigo.charAt(pPosicionCaracter) == '\t' || pLineaCodigo.charAt(pPosicionCaracter) == ' ' || pLineaCodigo.charAt(pPosicionCaracter) == ':') {
            // Verifica si ya se abrio programa o en caso contrario la pila esta vacia
            if (pilaPalabrasReservadas.estaVacia()) {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No es posible crear variables sin antes abrir la etiqueta 'programa\n";
                return false;
            } else {
                // Corta la linea de codigo, despues de el tipo de variable a crear
                pLineaCodigo = pLineaCodigo.substring(pPosicionCaracter);
                // Coloca espacios entre los caracteres especiales para este ejercicio
                pLineaCodigo = pLineaCodigo.replaceAll("::", " :: ");
                //pLineaCodigo = pLineaCodigo.replaceAll("=", " = ");
                pLineaCodigo = pLineaCodigo.replaceAll("!", " ! ");
                pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");
                // Hace un arreglo con todas las palabras de la linea separadas por espacios
                String[] palabrasLinea = pLineaCodigo.split(" ");

                int indiceAnalizado = 0;

                // Verifica que existan mas palabras en la linea para analizar
                if (!(palabrasLinea.length > indiceAnalizado)) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha encontrado el caracter :: despues del tipo de variable a crear\n";
                    return false;
                }

                // Se analiza la existencia de '::' despues del tipo de variable 
                for (; indiceAnalizado < palabrasLinea.length; indiceAnalizado++) {
                    // Realiza hasta que el campo no sea vacio
                    if (!palabrasLinea[indiceAnalizado].equals("")) {
                        // Verifica que la palabra sea unicamente "::"
                        if (!palabrasLinea[indiceAnalizado].equals("::")) {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". No se ha encontrado el caracter :: despues del tipo de variable a crear\n";
                            return false;
                        } else {
                            indiceAnalizado++;
                            break;
                        }
                    }
                }

                // Verifica que existan mas palabras en la linea para analizar
                if (!(palabrasLinea.length > indiceAnalizado)) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha especificado el nombre para la variable que se desea crear\n";
                    return false;
                }

                String nombreVariable = "";
                // Se analiza el nombre de la variable
                for (; indiceAnalizado < palabrasLinea.length; indiceAnalizado++) {
                    // Realiza hasta que el campo no sea vacio
                    if (!palabrasLinea[indiceAnalizado].equals("")) {
                        nombreVariable = palabrasLinea[indiceAnalizado];
                        // Verifica si es ! y no escribio el nombre de la variable
                        if (nombreVariable.equals("!")) {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". No se ha especificado el nombre de la variable\n";
                            return false;
                        } // Verifica si el primer caracter del nombre de la variable, es una letra
                        else if (!Character.isLetter(nombreVariable.charAt(0))) {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". El nombre de una variable debe iniciar con una letra\n";
                            return false;
                        } else {
                            // Se verifica que el nombre de la variable solo tenga numeros y letras
                            for (int i = 1; i < nombreVariable.length(); i++) {
                                if (!Character.isLetter(nombreVariable.charAt(i)) && !Character.isDigit(nombreVariable.charAt(i))) {
                                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                            + ". El nombre de una variable debe contener únicamente números y letras\n";
                                    return false;
                                }
                            }
                            indiceAnalizado++;
                            break;
                        }
                    }
                }

                // Verifica que exista el cierre de linea
                if (!(palabrasLinea.length > indiceAnalizado)) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha encontrado el cierre de línea '!'\n";
                    return false;
                }

                // Se analiza la existencia de '!' despues del nombre de variable 
                for (; indiceAnalizado < palabrasLinea.length; indiceAnalizado++) {
                    // Realiza hasta que el campo no sea vacio
                    if (!palabrasLinea[indiceAnalizado].equals("")) {
                        // Verifica que la palabra sea unicamente "!"
                        if (!palabrasLinea[indiceAnalizado].equals("!")) {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". No se ha encontrado el cierre de línea '!' después del nombre de la variable\n";
                            return false;
                        } else {
                            indiceAnalizado++;
                            break;
                        }
                    }
                }

                // Verifica que no existe otra palabra o caracter luego del '!'
                for (; indiceAnalizado < palabrasLinea.length; indiceAnalizado++) {
                    // Realiza hasta que el campo no sea vacio
                    if (!palabrasLinea[indiceAnalizado].equals("")) {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                + ". No pueden haber caracteres ni palabres después del cierre de línea '!'\n";
                        return false;
                    }
                }

                // Verifica si es una plabra reservada
                if (main.palabrasReservadas.esPalabraReservada(nombreVariable)) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". El nombre que intenta darle a la variable es una palabra reservada\n";
                    return false;
                } // Verifica si ya existe una variable con ese nombre
                else if (variables.existeVariable(nombreVariable)) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". Ya existe una variable o programa con el nombre '" + nombreVariable + "'\n";
                    return false;
                } else {
                    // AL FIN, AQUI SE INSERTA LA VARIABLE EN LA LISTA
                    switch (pTipoVariable) {
                        case "Texto":
                            variables.insertar(nombreVariable, "", pTipoVariable);
                            break;
                        case "entero":
                            variables.insertar(nombreVariable, "0", pTipoVariable);
                            break;
                        case "decimal":
                            variables.insertar(nombreVariable, "0.0", pTipoVariable);
                            break;
                        case "binario":
                            variables.insertar(nombreVariable, "false", pTipoVariable);
                            break;
                        case "caracter":
                            variables.insertar(nombreVariable, "", pTipoVariable);
                            break;
                    }
                    return true;
                }
            }

        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la etiqueta " + pTipoVariable + ", no se puede ubicar el "
                    + "caracter " + pLineaCodigo.charAt(pPosicionCaracter) + ""
                    + ". La sintaxis correcta es: " + pTipoVariable + "::nombreVariable!\n";
            return false;
        }
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
        if (pLineaCodigo.charAt(pPosicionCaracter) == ' ' || pLineaCodigo.charAt(pPosicionCaracter) == '\t') {
            // Verifica si ya existen palabras reservadas en la pila.
            if (!pilaPalabrasReservadas.estaVacia()) {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". La etiqueta "
                        + "programa ya ha sido creada y no es posible abrir dos en un mismo programa.\n";
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
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". El nombre del "
                                        + "programa no puede comenzar con " + pLineaCodigo.charAt(pPosicionCaracter)
                                        + ". El nombre del programa debe iniciar con una letra.\n";
                                return false;
                            } // Verifica que el nombre solo contenga letras y numeros
                            else if (!Character.isLetter(pLineaCodigo.charAt(pPosicionCaracter))
                                    && !Character.isDigit(pLineaCodigo.charAt(pPosicionCaracter))) {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". El nombre del "
                                        + "programa no puede contener el caracter " + pLineaCodigo.charAt(pPosicionCaracter)
                                        + ". El nombre del programa solo puede conterner letras y números.\n";
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
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". "
                            + "No se ha especificado el nombre del programa.\n";
                    return false;
                } // Verifica que el nombre del programa no sea una palabra reservada
                else if (main.palabrasReservadas.esPalabraReservada(nombrePrograma)) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". "
                            + "El nombre del programa no puede ser una palabra reservada\n";
                    return false;
                } else {
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
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". El nombre del "
                                + "programa no puede contener carácteres después de él.\n";
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
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la etiqueta programa, no se puede ubicar el "
                    + "caracter " + pLineaCodigo.charAt(pPosicionCaracter) + ""
                    + ". La sintaxis correcta es: programa nombrePrograma\n";
            return false;
        }
    }

    /**
     * Metodo utilizado para verificar la linea de cierre del programa
     *
     * @param pLineaCodigo Linea de codigo donde se cierra programa
     * @param pPosicionCaracter posicion donde se encuentra el caracter
     * siguiente a la palabra #programa
     * @param pNumeroLinea numero de la linea analizada
     * @return true si la sintaxis esta correcta o false en caso que haya
     * problemas
     */
    public boolean cerrarPrograma(String pLineaCodigo, int pPosicionCaracter, int pNumeroLinea) {
        // Verifica que el caracter ubicado despues de #programa sea ' ' y no ':' o '['
        if (pLineaCodigo.charAt(pPosicionCaracter) == ' ' || pLineaCodigo.charAt(pPosicionCaracter) == '\t') {
            // Verifica si hay palabras reservadas en la pila, sino, no existe 'programa'
            if (pilaPalabrasReservadas.estaVacia()) {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No se ha encontrado la etiqueta de inicio del programa\n";
                return false;
            } // Verifica si en la pila de palabras solo esta "programa" o debe cerrar otras etiquetas
            else if (!pilaPalabrasReservadas.getValorEnTop().equals("programa")) {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". Primero debe cerrar '" + pilaPalabrasReservadas.getValorEnTop() + "' antes"
                        + " de utilizar la etiqueta #programa\n";
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
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". El cierre del "
                            + "programa (#programa), no puede contener carácteres después de él.\n";
                    return false;
                } else {
                    // Hace pop a la lista de palabras reservadas
                    pilaPalabrasReservadas.pop();
                    return true;
                }
            }
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la etiqueta #programa, no se puede ubicar el "
                    + "caracter " + pLineaCodigo.charAt(pPosicionCaracter) + ""
                    + ". La sintaxis correcta es: #programa\n";
            return false;
        }
    }

    /**
     * Metodo utilizado para buscar la posicion del caracter de cierre de linea
     *
     * @param pCodigo Linea de codigo a analizar
     * @param pPosicionInicio Posicion a partir de la cual comienza el codigo a
     * analizar
     * @return el numero de linea donde esta el caracter '!' o -1 si no existe
     */
    public int getCierreLinea(String pCodigo, int pPosicionInicio) {
        int ultimoChar = pCodigo.length() - 1;
        // Busca que exista el cierre de linea
        for (; ultimoChar > pPosicionInicio; ultimoChar--) {
            if (pCodigo.charAt(ultimoChar) != ' ' && pCodigo.charAt(ultimoChar) != '\t') {
                if (pCodigo.charAt(ultimoChar) == '!') {
                    return ultimoChar;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    // LISTO
    public int getCierreCorchete(String pCodigo, int pPosicionInicio) {
        int ultimoChar = pCodigo.length() - 1;
        // Busca que exista el cierre de corchete
        for (; ultimoChar > pPosicionInicio; ultimoChar--) {
            if (pCodigo.charAt(ultimoChar) != ' ' && pCodigo.charAt(ultimoChar) != '\t') {
                if (pCodigo.charAt(ultimoChar) == ']') {
                    return ultimoChar;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    // LISTO
    public String verificarOperacion(String pVar1, String pOperador, String pVar2) {
        // Se va a retornar el tipo del valor si es correcta o null si es incorrecto
        switch (pOperador) {
            case "o":
                if (pVar1.equals("binario") && pVar2.equals("binario")) {
                    return "binario";
                }
                return null;
            case "y":
                if (pVar1.equals("binario") && pVar2.equals("binario")) {
                    return "binario";
                }
                return null;
            case "=":
                if (pVar1.equals(pVar2)) {
                    return "binario";
                }
                return null;
            case "<>":
                if (pVar1.equals(pVar2)) {
                    return "binario";
                }
                return null;
            case "<":
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("entero") && pVar2.equals("entero"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "binario";
                }
                return null;
            case ">":
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("entero") && pVar2.equals("entero"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "binario";
                }
                return null;
            case "<=":
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("entero") && pVar2.equals("entero"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "binario";
                }
                return null;
            case ">=":
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("entero") && pVar2.equals("entero"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "binario";
                }
                return null;
            case "-":
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "decimal";
                }
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                }
                return null;
            case "*":
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "decimal";
                }
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                }
                return null;
            case "/":
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "decimal";
                }
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                }
                return null;
            case "%":
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "decimal";
                }
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                }
                return null;
            case "+":
                if (pVar1.equals("Texto") || pVar2.equals("Texto") || (pVar1.equals("caracter") && pVar2.equals("caracter"))) {
                    return "Texto";
                }
                if ((pVar1.equals("decimal") && pVar2.equals("entero"))
                        || (pVar1.equals("entero") && pVar2.equals("decimal"))
                        || (pVar1.equals("decimal") && pVar2.equals("decimal"))) {
                    return "decimal";
                }
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                }
                return null;
        }
        return null;
    }

    // LISTO
    public String verificarNo(String pValor) {
        if (pValor.equals("binario")) {
            return "binario";
        } else {
            return null;
        }
    }

    // LISTO
    public String resolverEcuacion(String pCodigo, int pNumeroLinea) {
        boolean negativo = false;
        String Var1 = "";
        String Var2 = "";
        String operador = "";
        Pila pilaParentesis = new Pila();

        for (int i = 0; i < pCodigo.length(); i++) {

            if (pCodigo.charAt(i) != ' ' && pCodigo.charAt(i) != '\t') {
                // Si es una letra, es porque es una variable o una palabra reservada
                if (Character.isLetter(pCodigo.charAt(i))) {

                    String palabra = "";
                    // Agrega los caracteres a la palabra siempre y cuando sean letras o numeros
                    do {
                        palabra += pCodigo.charAt(i);
                        i++;
                    } while (i < pCodigo.length() && (Character.isLetter(pCodigo.charAt(i))
                            || Character.isDigit(pCodigo.charAt(i))));

                    // El do while se mueve hasta que no sea letra ni numero, pero el for va a mover otra
                    // vez al siguiente, entonces elimino 1 iteracion para que se coloque en el ultimo numero o letra
                    i--;
                    // Verifica si es una variable
                    if (variables.existeVariable(palabra)) {
                        // Obtiene el tipo de dato de la variable
                        String tipo = variables.getVariable(palabra).getTipo();
                        if (!tipo.equals("clase")) {
                            // Verifica en cual variable se va a ingresar la variable
                            if (Var1.equals("")) {
                                Var1 = tipo;
                            } else {
                                // Verifica si hay operador en medio de la segunda expresion
                                if (!operador.equals("")) {
                                    Var2 = tipo;
                                } else {
                                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                            + ". No se ha encontrado un operador entre las expresiones\n";
                                    return null;
                                }
                            }
                        } else {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                    + ". No se pueden realizar operaciones sobre el nombre del programa\n";
                            return null;
                        }
                    } // Verifica si es una palabra reservada la variable (verdadero, falso, o, y, no)
                    else if (main.palabrasReservadas.esPalabraReservada(palabra)) {
                        // Es una palabra reservada binaria
                        if (palabra.equals("verdad") || palabra.equals("falso")) {
                            if (Var1.equals("")) {
                                Var1 = "binario";
                            } else {
                                // Verifica si hay operador en medio de la segunda expresion
                                if (!operador.equals("")) {
                                    Var2 = "binario";
                                } else {
                                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                            + ". No se ha encontrado un operador entre las expresiones\n";
                                    return null;
                                }
                            }
                        } // Es un operador binario
                        else if (palabra.equals("y") || palabra.equals("o")) {
                            // Verifica que exista Var1, sobre quien se va a operar
                            if (!Var1.equals("")) {
                                if (operador.equals("")) {
                                    operador = palabra;
                                } else {
                                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                            + ". No pueden haber dos operadores seguidos\n";
                                    return null;
                                }
                            } else {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                        + ". El operador binario '" + palabra + "' debe ir en medio de una expresión, no al inicio\n";
                                return null;
                            }
                        } // El operador binario es un no
                        else if (palabra.equals("no")) {
                            boolean hayCorchete = false;
                            // Busco si el caracter que sigue es '['
                            while ((i + 1) < pCodigo.length() && !hayCorchete) {
                                // Verifica si el proximo caracter no es espacio
                                if (pCodigo.charAt(i + 1) != ' ') {
                                    // Verifica que el caracter sea '['
                                    if (pCodigo.charAt(i + 1) == '[') {
                                        hayCorchete = true;
                                    } else {
                                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                                + ". Seguido del operador binario 'no', solo se puede ubicar un '['\n";
                                        return null;
                                    }
                                }
                                i++;
                            }
                            // Hay que hacer el push en caso de que exista corchete
                            if (hayCorchete) {
                                // Hace el push del corchete
                                pilaParentesis.push("[");
                                // Verifica si hay datos en variable y operador para hacer el push de estos
                                if (!Var1.equals("")) {
                                    if (!operador.equals("")) {
                                        pilaParentesis.push(Var1);
                                        pilaParentesis.push(operador);
                                        Var1 = operador = "";
                                    } else {
                                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                                + ". No hay un operador entre la variable y el operador 'no['\n";
                                        return null;
                                    }
                                }
                                // Hago el push del no
                                pilaParentesis.push("no");
                            } else {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                        + ". No se encuentra el caracter '[' seguido del operador binario 'no'\n";
                                return null;
                            }
                        } // La palabra reservada no es operable y no es un operador
                        else {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                    + ". La palabra reservada '" + palabra + "' no es válida en este contexto\n";
                            return null;
                        }
                    } // La palabra no se reconoce en el programa
                    else {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". La variable '" + palabra + "' no ha sido declarada en el programa\n";
                        return null;
                    }
                } else if (Character.isDigit(pCodigo.charAt(i))) {

                    //String cifra = "";
                    // Se establece que se ha tomado el negativo.
                    if (negativo) {
                        negativo = false;
                        //cifra += "-";
                    }

                    boolean hayPunto = false;
                    do {
                        // Verifica si es punto y que la expresion no contenga otro punto mas
                        if (pCodigo.charAt(i) == '.') {
                            if (!hayPunto) {
                                // Verificar aqui que haya otro numero despues del punto
                                if ((i + 1) < pCodigo.length() && Character.isDigit(pCodigo.charAt(i + 1))) {
                                    hayPunto = true;
                                    i++;    // Ya acabo de analizar el char siguiente
                                } else {
                                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                            + ". Una expresión numérica decimal a operar, no contiene digitos despues del punto\n";
                                    return null;
                                }
                            } else {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                        + ". Una expresión numérica decimal a operar, contiene dos puntos\n";
                                return null;
                            }
                        }
                        //cifra += pCodigo.charAt(i);
                        i++;
                    } while (i < pCodigo.length() && (Character.isDigit(pCodigo.charAt(i)) || pCodigo.charAt(i) == '.'));

                    // El do while se mueve hasta que no sea numero, pero el for va a mover otra
                    // vez al siguiente, entonces elimino 1 iteracion para que se coloque en el ultimo numero
                    i--;

                    String tipoDato;
                    // Obtengo el tipo de dato numerico
                    if (hayPunto) {
                        tipoDato = "decimal";
                    } else {
                        tipoDato = "entero";
                    }

                    // Verifica en cual variable se va a ingresar la variable
                    if (Var1.equals("")) {
                        Var1 = tipoDato;
                    } else {
                        // Verifica si hay operador en medio de la segunda expresion
                        if (!operador.equals("")) {
                            Var2 = tipoDato;
                        } else {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                    + ". No se ha encontrado un operador entre las expresiones\n";
                            return null;
                        }
                    }
                } else if (pCodigo.charAt(i) == '@') {
                    boolean hayCierre = false;
                    i++;
                    // Se recorre en busca del cierre del Texto
                    while ((i < pCodigo.length()) && !hayCierre) {
                        // Se encontro el cierre, cambia la var hay cierre a true
                        if (pCodigo.charAt(i) == '@') {
                            hayCierre = true;
                        }
                        i++;
                    }
                    // El while se mueve hasta que sea @, pero el for principal va a mover otra vez al 
                    // siguiente, entonces elimino 1 iteracion para que se coloque en el ultimo numero
                    i--;
                    // Verifica si se encontro el cierre
                    if (hayCierre) {
                        // Verifica en cual variable se va a ingresar la variable
                        if (Var1.equals("")) {
                            Var1 = "Texto";
                        } else {
                            // Verifica si hay operador en medio de la segunda expresion
                            if (!operador.equals("")) {
                                Var2 = "Texto";
                            } else {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                        + ". No se ha encontrado un operador entre las expresiones\n";
                                return null;
                            }
                        }
                    } else {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". No se encuentra el caracter '@' de cierre del Texto\n";
                        return null;
                    }
                } // Verifica si la expresion es un caracter, el cual comienza con &
                else if (pCodigo.charAt(i) == '&') {
                    // Verifica que el cierre del caracter se ubique dos espacios despues
                    if ((i + 2) < pCodigo.length() && pCodigo.charAt(i + 2) == '&') {
                        i = i + 2;  // Ubico el indice en el cierre &
                        // Verifica en cual variable se va a ingresar la variable
                        if (Var1.equals("")) {
                            Var1 = "caracter";
                        } else {
                            // Verifica si hay operador en medio de la segunda expresion
                            if (!operador.equals("")) {
                                Var2 = "caracter";
                            } else {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                        + ". No se ha encontrado un operador entre las expresiones\n";
                                return null;
                            }
                        }
                    } else {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". La expresión contiene errores en la definicion de un 'caracter'\n";
                        return null;
                    }
                } // Verifico si es un parentesis '['
                else if (pCodigo.charAt(i) == '[') {
                    // Hago el push del parentesis a la pila
                    pilaParentesis.push("[");
                    // Verifico si hay datos antes del parentesis para meterlos a la pila tambien
                    if (!Var1.equals("")) {
                        //  Verifico que haya un operador entre el parentesis y la variable
                        if (!operador.equals("")) {
                            // Hago el push a la pila de la variable 1 y el operador
                            pilaParentesis.push(Var1);
                            pilaParentesis.push(operador);
                            // Quito los datos que ya estan en la pila
                            Var1 = operador = "";
                        } else {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                    + ". No hay un operador entre la variable y el parentesis '['\n";
                            return null;
                        }
                    }
                    continue;
                } else if (pCodigo.charAt(i) == ']') {
                    // Verifica que la lista de parentesis no este vacia para hacer el pop de '['
                    if (!pilaParentesis.estaVacia()) {
                        // La unica forma de que var1 sea vacia es xq se acaba de ingresar un '['
                        // Se verifica que no este vacia
                        if (!Var1.equals("")) {
                            // Verifica si antes del ']' existe un operador (seria una ecuacion incompleta)
                            if (!operador.equals("")) {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                        + ". No puede existir un operador justo antes de un corchete ']'\n";
                                return null;
                            } else {
                                // El valor de arriba de la pila es un no. Realiza la expresion
                                if (pilaParentesis.getValorEnTop().equals("no")) {
                                    // Realizo la operacion del No
                                    String resultado = verificarNo(Var1);
                                    // Verifico el resultado de la operacion No
                                    if (resultado == null) {
                                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                                + ". La expresion sobre la que se va a realizar la operacion 'no', no es binaria\n";
                                        return null;
                                    } else {
                                        Var1 = resultado;
                                    }
                                    pilaParentesis.pop();
                                }
                                // Verifica si hay una operacion antes del '[' en la pila
                                if (!pilaParentesis.getValorEnTop().equals("[")) {
                                    // Obtengo el operador
                                    operador = pilaParentesis.getValorEnTop();
                                    pilaParentesis.pop();
                                    // Obtengo la expresion 1 que se va a operar
                                    String var = pilaParentesis.getValorEnTop();
                                    pilaParentesis.pop();
                                    // Realizo la operacion
                                    String resultado = verificarOperacion(var, operador, Var1);
                                    // Verifico el resultado de la operacion (null si esta mala)
                                    if (resultado == null) {
                                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                                + ". Se esta realizando una operacion entre tipos incompatibles\n";
                                        return null;
                                    } else {
                                        Var1 = resultado;
                                        Var2 = operador = "";
                                    }
                                }
                                // Saca el parentesis de la pila '['
                                pilaParentesis.pop();
                            }
                        } else {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                    + ". Hay corchetes sin contenido dentro\n";
                            return null;
                        }
                    } else {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". Hay un corchete de cierre ']' de más\n";
                        return null;
                    }
                    continue;
                } // Verifica si es un operador el caracter que se analiza
                else if (main.listaOperadores.esPalabraReservada(String.valueOf(pCodigo.charAt(i)))) {
                    String ope = pCodigo.charAt(i) + "";
                    // Verifico si es un operador de dos simbolos como >= o <>
                    if ((i + 1) < pCodigo.length() && main.listaOperadores.esPalabraReservada(ope + pCodigo.charAt(i + 1))) {
                        // Agrego el otro operador
                        ope += pCodigo.charAt(i + 1);
                        i++;
                    }
                    // Verifica que exista Var1, sobre quien se va a operar
                    if (!Var1.equals("")) {
                        // Verifica si no hay un operador para acomodar el actual ahi
                        if (operador.equals("")) {
                            operador = ope;
                        } else {
                            // Verifica si es un negativo
                            if (ope.equals("-") && i + 1 < pCodigo.length() && Character.isDigit(pCodigo.charAt(i + 1))) {
                                negativo = true;
                            } else {
                                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                        + ". No pueden haber dos operadores seguidos\n";
                                return null;
                            }
                        }
                    } else {
                        // Verifico si es un negativo
                        if (ope.equals("-") && (i + 1) < pCodigo.length() && Character.isDigit(pCodigo.charAt(i + 1))) {
                            negativo = true;
                        } else {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                    + ". El operador '" + ope + "' debe ir en medio de una expresión, no al inicio\n";
                            return null;
                        }
                    }
                    continue;
                } else if (pCodigo.charAt(i) == '!') {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". La expresión contiene un cierre de línea '!' inválido\n";
                    return null;
                } else {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". El caracter '" + pCodigo.charAt(i) + "' no se reconoce como una expresion válida\n";
                    return null;
                }

                // Hay una operacion que realizar xq ya tengo dos expresiones y operador
                if (!Var2.equals("")) {
                    String resultado = verificarOperacion(Var1, operador, Var2);
                    // Verifico el resultado de la operacion (null si esta mala)
                    if (resultado == null) {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". Operación inválida entre los tipos de datos '" + Var1 + "' y '" + Var2 + "'.\n";
                        return null;
                    } else {
                        Var1 = resultado;
                        Var2 = operador = "";
                    }
                }
            }
        }

        if (pilaParentesis.estaVacia() && !Var1.equals("") && operador.equals("")) {
            return Var1;
        } else if (!pilaParentesis.estaVacia()) {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". La expresión contiene corchetes que no han sido cerrados\n";
            return null;
        } else if (Var1.equals("")) {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". No hay ninguna expresion a evaluar o asignar\n";
            return null;
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Hay un operador al final de la linea. Operador debe estar entre dos expresiones\n";
            return null;
        }
    }

    // LISTO
    public boolean verificarImprimir(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        // Verifica que despues de 'imprimir' existe un espacio
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '\t') {
            // Verifica que se haya creado un programa
            if (!pilaPalabrasReservadas.estaVacia()) {
                pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");
                // Se busca el caracter de fin de linea '!'            
                int posCierre = this.getCierreLinea(pLineaCodigo, pPosicion);
                // Verifica que se haya encontrado el cierre de linea '!'
                if (posCierre == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". No se ha encontrado el cierre de la línea '!'\n";
                    return false;
                }
                // obtiene la parte de texto donde esta la expresion que se va a imprimir
                String expresion = pLineaCodigo.substring(pPosicion, posCierre);
                // envia a revisar si la expresion a imprimir es correcta sintacticamente
                String resultado = this.resolverEcuacion(expresion, pNumeroLinea);
                // envia 
                return resultado != null;
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No es posible utilizar la etiqueta imprimir sin antes crear un 'programa'\n";
                return false;
            }
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Despues de la palabra impirmir, solo puede ir ' ' (espacio)\n";
            return false;
        }
    }

    // LISTO
    public boolean realizarAsignacion(String pLineaCodigo, int pPosicion, int pNumeroLinea, String pTipoDato) {

        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '=' || pLineaCodigo.charAt(pPosicion) == '\t') {

            pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");

            // Se busca el caracter de fin de linea '!'            
            int posCierre = this.getCierreLinea(pLineaCodigo, pPosicion);
            // Verifica que se haya encontrado el cierre de linea '!'
            if (posCierre == -1) {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No se ha encontrado el cierre de la línea '!'\n";
                return false;
            }

            //    Se busca el caracter de asignacion '='            
            boolean igualEncontrado = false;
            // Recorre la linea de codigo en busca del '='
            for (; pPosicion < posCierre; pPosicion++) {
                if (pLineaCodigo.charAt(pPosicion) != ' ') {
                    if (pLineaCodigo.charAt(pPosicion) == '=') {
                        igualEncontrado = true;
                        pPosicion++;
                    }
                    break;
                }
            }
            // Verifica si no hay un '=' y envia el error de sintaxis
            if (!igualEncontrado) {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No se ha encontrado el '=' después del nombre de la variable\n";
                return false;
            }

            // Esta es toda la equivalencia que voy a realizar sobre la variable
            String substring = pLineaCodigo.substring(pPosicion, posCierre);

            // Manda a verificar la asignacion
            String equivalencia = this.resolverEcuacion(substring, pNumeroLinea);

            // Verifica que el dato a asignar sea igual al obtenido
            if (equivalencia == null) {
                return false;
            } else if (equivalencia.equals(pTipoDato)) {
                return true;
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". Asignación incompatible. Está intentando asignar un '"
                        + equivalencia + "' a un '" + pTipoDato + "'\n";
                return false;
            }
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". En la asignación de valores a una variable, no puede ir el"
                    + " caracter '" + pLineaCodigo.charAt(pPosicion) + "' después del nombre de la variable\n";
            return false;
        }
    }

    // LISTO
    public boolean abrirMientras(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        // Verifica que se hay ingresado si despues de la palabra si habia un espacio o '[' o un tab
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '[' || pLineaCodigo.charAt(pPosicion) == '\t') {

            if (!pilaPalabrasReservadas.estaVacia()) {
                // Cambio los tab por espacios
                pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");

                int indiceApertura = this.getAperturaCorchete(pLineaCodigo, pPosicion);
                // Verifica que haya un caracter de apertura
                if (indiceApertura == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". No se ha encontrado el caracter '[' despues del 'mientras'\n";
                    return false;
                }

                // Se busca el caracter de cierre ']'            
                int posCierre = this.getCierreCorchete(pLineaCodigo, pPosicion);
                // Verifica que se haya encontrado el cierre ']'
                if (posCierre == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". El último caracter de la línea, no es el corchete de cierre ']' del ciclo mientras\n";
                    return false;
                }
                // Obtiene la parte de la condicion del mientras (+1 xq es en la posicion siguiente del '[')
                String condicion = pLineaCodigo.substring(indiceApertura + 1, posCierre);
                // Envia a evaluar la condicion
                String evaluacionCondicion = this.resolverEcuacion(condicion, pNumeroLinea);

                if (evaluacionCondicion == null) {
                    return false;
                } else if (!evaluacionCondicion.equals("binario")) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". La expresión a evaluar no es binaria\n";
                    return false;
                } else {
                    pilaPalabrasReservadas.push("mientras");
                    return true;
                }
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No es posible crear un ciclo 'mientras' antes de crear un programa\n";
                return false;
            }

        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Despues de la palabra 'mientras', solo puede ir '[', espacio(s) o tabs\n";
            return false;
        }
    }

    // LISTO
    public boolean cerrarMientras(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '\t') {
            // Verifica que se haya creado un programa
            if (!pilaPalabrasReservadas.estaVacia()) {
                // Verifica que sea "mientras" la etiqueta que este en el top de la pila
                if (pilaPalabrasReservadas.getValorEnTop().equals("mientras")) {
                    // Aumento pPosicion que estaba en un espacio o un tab
                    pPosicion++;
                    // Se verifica que no existan caracteres despues de '#mientras'
                    while (pPosicion < pLineaCodigo.length()) {
                        // Si no es un espacio o un tab lo que hay, es xq existe algo mas despues del #mientras
                        if (pLineaCodigo.charAt(pPosicion) != ' ' && pLineaCodigo.charAt(pPosicion) != '\t') {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". Después de la etiqueta '#mientras' no puede haber ningún otro caracter\n";
                            return false;
                        }
                        pPosicion++;
                    }
                    // Extrae la palabra reservada 'mientras' de la pila
                    pilaPalabrasReservadas.pop();
                    return true;

                } else {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha cerrado la etiqueta '" + pilaPalabrasReservadas.getValorEnTop()
                            + "' . Asegurese de cerrarla antes de utilizar la etiqueta '#mientras'\n";
                    return false;
                }
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No es posible utilizar la etiqueta '#mientras' sin antes crear un 'programa'\n";
                return false;
            }
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la palabra reservada '#mientras' no pueden haber más caracteres\n";
            return false;
        }
    }

    // Retorna la posicion donde esta el caracter '[' o -1 si no es el primero
    // LISTO
    public int getAperturaCorchete(String pLineaCodigo, int pPosicionInicial) {
        while (pPosicionInicial < pLineaCodigo.length()) {
            // Verifica que no sea un espacio
            if (pLineaCodigo.charAt(pPosicionInicial) != ' ' && pLineaCodigo.charAt(pPosicionInicial) != '\t') {
                if (pLineaCodigo.charAt(pPosicionInicial) == '[') {
                    return pPosicionInicial;
                } else {
                    return -1;
                }
            }
            pPosicionInicial++;
        }
        return -1;
    }

    // LISTO
    public boolean abrirSi(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        // Verifica que se hay ingresado si despues de la palabra si habia un espacio o '['
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '[' || pLineaCodigo.charAt(pPosicion) == '\t') {

            if (!pilaPalabrasReservadas.estaVacia()) {
                // Cambio los tab por espacios
                pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");

                int indiceApertura = this.getAperturaCorchete(pLineaCodigo, pPosicion);
                // Verifica que haya un caracter de apertura
                if (indiceApertura == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". No se ha encontrado el caracter '[' despues del 'si'\n";
                    return false;
                }

                // Se busca el caracter de cierre ']'            
                int posCierre = this.getCierreCorchete(pLineaCodigo, pPosicion);
                // Verifica que se haya encontrado el cierre ']'
                if (posCierre == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". El último caracter de la línea, no es el cierre del condicional si ']'\n";
                    return false;
                }
                // Obtiene la parte de la condicion del si (+1 xq es en la posicion siguiente del '[')
                String condicion = pLineaCodigo.substring(indiceApertura + 1, posCierre);
                // Envia a evaluar la condicion
                String evaluacionCondicion = this.resolverEcuacion(condicion, pNumeroLinea);

                if (evaluacionCondicion == null) {
                    return false;
                } else if (!evaluacionCondicion.equals("binario")) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". La expresión a evaluar no es binaria\n";
                    return false;
                } else {
                    pilaPalabrasReservadas.push("si");
                    return true;
                }
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No es posible crear una condición 'si' antes de crear un programa\n";
                return false;
            }

        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Despues de la palabra 'si', solo puede ir '[' o espacio(s)\n";
            return false;
        }
    }

    // LISTO
    public boolean cerrarSi(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '\t') {
            // Verifica que se haya creado un programa
            if (!pilaPalabrasReservadas.estaVacia()) {
                // Verifica que sea "si" la etiqueta que este en el top de la pila
                if (pilaPalabrasReservadas.getValorEnTop().equals("si")) {
                    // Aumento pPosicion que estaba en un espacio
                    pPosicion++;
                    // Se verifica que no existan caracteres despues de '#si'
                    while (pPosicion < pLineaCodigo.length()) {
                        // Si no es un espacio lo que hay, es xq existe algo mas despues del #si
                        if (pLineaCodigo.charAt(pPosicion) != ' ' && pLineaCodigo.charAt(pPosicion) != '\t') {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". Después de la etiqueta '#si' no puede haber ningún otro caracter\n";
                            return false;
                        }
                        pPosicion++;
                    }
                    // Extrae la palabra reservada 'si' de la pila
                    pilaPalabrasReservadas.pop();
                    return true;

                } else {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha cerrado la etiqueta '" + pilaPalabrasReservadas.getValorEnTop()
                            + "' . Asegurese de cerrarla antes de utilizar la etiqueta '#si'\n";
                    return false;
                }
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No es posible utilizar la etiqueta '#si' sin antes crear un 'programa'\n";
                return false;
            }
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la palabra reservada '#si' no pueden haber mas caracteres\n";
            return false;
        }
    }

    // LISTO
    public boolean comprobraSino(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '\t') {
            // Verifica que se haya creado un programa
            if (!pilaPalabrasReservadas.estaVacia()) {
                // Verifica que sea "si" la etiqueta que este en el top de la pila
                if (pilaPalabrasReservadas.getValorEnTop().equals("si")) {
                    // Aumento pPosicion que estaba en un espacio
                    pPosicion++;
                    // Se verifica que no existan caracteres despues de '#si'
                    while (pPosicion < pLineaCodigo.length()) {
                        // Si no es un espacio lo que hay, es xq existe algo mas despues del #si
                        if (pLineaCodigo.charAt(pPosicion) != ' ' && pLineaCodigo.charAt(pPosicion) != '\t') {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". Después de la etiqueta 'sino' no puede haber ningún otro caracter\n";
                            return false;
                        }
                        pPosicion++;
                    }
                    // Retorna true, ya que al final de la linea no habian caracteres
                    return true;

                } else {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No es posible utilizar la palabra reservada 'sino' sin antes haber"
                            + " abierto un 'si'. Asegurese de cerrar '" + pilaPalabrasReservadas.getValorEnTop()
                            + "' en caso que este se encuentre dentro del 'si'\n";
                    return false;
                }
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No es posible utilizar la etiqueta 'sino' sin antes crear un 'programa'\n";
                return false;
            }
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la palabra reservada 'sino' no pueden haber mas caracteres\n";
            return false;
        }
    }

    // LISTO
    public boolean comprobarOsi(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        // Verifica que se hay ingresado si despues de la palabra si habia un espacio o '['
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '[' || pLineaCodigo.charAt(pPosicion) == '\t') {
            // Verifica que ya se haya abierto programa
            if (!pilaPalabrasReservadas.estaVacia()) {

                // Verifica que sea "si" la etiqueta que este en el top de la pila
                if (pilaPalabrasReservadas.getValorEnTop().equals("si")) {
                    // Cambio los tab por espacios
                    pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");

                    int indiceApertura = this.getAperturaCorchete(pLineaCodigo, pPosicion);
                    // Verifica que haya un caracter de apertura
                    if (indiceApertura == -1) {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". No se ha encontrado el caracter '[' despues del 'osi'\n";
                        return false;
                    }

                    // Se busca el caracter de cierre ']'            
                    int posCierre = this.getCierreCorchete(pLineaCodigo, pPosicion);
                    // Verifica que se haya encontrado el cierre ']'
                    if (posCierre == -1) {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". El último caracter de la línea, no es el cierre del condicional osi ']'\n";
                        return false;
                    }
                    // Obtiene la parte de la condicion del si (+1 xq es en la posicion siguiente del '[')
                    String condicion = pLineaCodigo.substring(indiceApertura + 1, posCierre);
                    // Envia a evaluar la condicion
                    String evaluacionCondicion = this.resolverEcuacion(condicion, pNumeroLinea);

                    if (evaluacionCondicion == null) {
                        return false;
                    } else if (!evaluacionCondicion.equals("binario")) {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". La expresión a evaluar no es binaria\n";
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No es posible utilizar la palabra reservada 'osi' sin antes haber"
                            + " abierto un 'si'. Asegurese de cerrar '" + pilaPalabrasReservadas.getValorEnTop()
                            + "' en caso que este se encuentre dentro del 'si'\n";
                    return false;
                }
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No es posible crear una condición 'osi' antes de crear un 'programa'\n";
                return false;
            }

        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la palabra 'osi', solo puede ir '[', espacio(s) o tab\n";
            return false;
        }
    }

    // LISTO
    public boolean abrirRepita(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '\t') {
            // Verifica que se haya creado un programa
            if (!pilaPalabrasReservadas.estaVacia()) {
                // Aumento pPosicion que estaba en un espacio
                pPosicion++;
                // Se verifica que no existan caracteres despues de 'repita'
                while (pPosicion < pLineaCodigo.length()) {
                    // Si no es un espacio lo que hay, es xq existe algo mas despues del repita
                    if (pLineaCodigo.charAt(pPosicion) != ' ' && pLineaCodigo.charAt(pPosicion) != '\t') {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                + ". Después de la etiqueta 'repita' no puede haber ningún otro caracter\n";
                        return false;
                    }
                    pPosicion++;
                }
                // Ingresa la palabra 'repita' a la pila
                pilaPalabrasReservadas.push("repita");
                return true;
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No es posible utilizar la etiqueta 'repita' sin antes crear un 'programa'\n";
                return false;
            }
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la palabra reservada 'repita' no pueden haber mas caracteres\n";
            return false;
        }
    }

    // LISTO
    public boolean cerrarRepitaCuando(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        // Verifica que se hay ingresado si despues de la palabra #cuando habia un espacio o '[' o tab
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '[' || pLineaCodigo.charAt(pPosicion) == '\t') {
            // Verifica que ya se haya abierto programa
            if (!pilaPalabrasReservadas.estaVacia()) {
                // Verifica que sea "repita" la etiqueta que este en el top de la pila
                if (pilaPalabrasReservadas.getValorEnTop().equals("repita")) {
                    // Cambio los tab por espacios
                    pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");

                    // Busca el corchete de apertura de la condicion
                    int indiceApertura = this.getAperturaCorchete(pLineaCodigo, pPosicion);
                    // Verifica que haya un caracter de apertura
                    if (indiceApertura == -1) {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". No se ha encontrado el caracter '[' despues del '#cuando'\n";
                        return false;
                    }

                    // Se busca el caracter de cierre ']'            
                    int posCierre = this.getCierreCorchete(pLineaCodigo, pPosicion);
                    // Verifica que se haya encontrado el cierre ']'
                    if (posCierre == -1) {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". El último caracter de la línea, no es el cierre de la condicion #cuando ']'\n";
                        return false;
                    }

                    // Obtiene la parte de la condicion del cuando (+1 xq es en la posicion siguiente del '[')
                    String condicion = pLineaCodigo.substring(indiceApertura + 1, posCierre);
                    // Envia a evaluar la condicion
                    String evaluacionCondicion = this.resolverEcuacion(condicion, pNumeroLinea);
                    if (evaluacionCondicion == null) {
                        return false;
                    } else if (!evaluacionCondicion.equals("binario")) {
                        salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                                + ". La expresión a evaluar no es binaria\n";
                        return false;
                    } else {
                        // Remueve el 'repita' de la pila de operadores
                        pilaPalabrasReservadas.pop();
                        return true;
                    }
                } else {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No es posible utilizar la palabra reservada '#cuando' sin antes haber"
                            + " abierto un 'repita'. Asegurese de cerrar '" + pilaPalabrasReservadas.getValorEnTop()
                            + "' en caso que este se encuentre dentro del 'repita'\n";
                    return false;
                }
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No es posible cerrar un ciclo con '#cuando' sin antes crear un 'repita'\n";
                return false;
            }

        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la palabra '#cuando', solo puede ir '[', espacio(s) o tab\n";
            return false;
        }
    }

    // LISTO
    public boolean abrirPara(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        // Verifica que se hay ingresado si despues de la palabra si habia un espacio o '[' o un tab
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '[' || pLineaCodigo.charAt(pPosicion) == '\t') {
            if (!pilaPalabrasReservadas.estaVacia()) {
                // Cambio los tab por espacios
                pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");

                int indiceApertura = this.getAperturaCorchete(pLineaCodigo, pPosicion);
                // Verifica que haya un caracter de apertura
                if (indiceApertura == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". No se ha encontrado el caracter '[' despues del 'para'\n";
                    return false;
                }

                // Se busca el caracter de cierre ']'            
                int posCierre = this.getCierreCorchete(pLineaCodigo, pPosicion);
                // Verifica que se haya encontrado el cierre ']'
                if (posCierre == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". No se ha cerrado el corchete ']' en la expresion del ciclo 'para'\n";
                    return false;
                }

                // Busca el separador de la condicion y la asignacion posterior del ciclo '$'
                int posSeparador = pLineaCodigo.indexOf('$');
                // Verifica que dentro de la condicion, se encuentre el caracter '$'
                if (posSeparador == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". El caracter $ que separa la condicion y la asignacion posterior del ciclo 'para',"
                            + " no se ha encontrado\n";
                    return false;
                }

                // Obtiene la parte de la condicion del para (+1 xq es en la posicion siguiente del '[')
                String condicion = pLineaCodigo.substring(indiceApertura + 1, posSeparador);
                // Envia a evaluar la condicion
                String evaluacionCondicion = this.resolverEcuacion(condicion, pNumeroLinea);

                if (evaluacionCondicion == null) {
                    return false;
                } else if (!evaluacionCondicion.equals("binario")) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". La expresión a evaluar no es binaria\n";
                    return false;
                }

                // Obtengo la asignacion que se va a hacer depues de cada iteracion
                String asignacionPosterior = pLineaCodigo.substring(posSeparador + 1, posCierre);
                // Obtiene la posicion donde se encuentra el igual en la asignacion
                int posIgual = asignacionPosterior.indexOf('=');
                // Verifica que dentro de la asignacion, se encuentre el caracter '='
                if (posIgual == -1) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". No se ha encontrado una asignacion posterior '=' en el ciclo' para'\n";
                    return false;
                }

                String variable = "";
                // Busca el nombre de la variable que se encuentra antes del '='
                asigna:
                for (int firstLetter = 0; firstLetter < posIgual; firstLetter++) {
                    if (asignacionPosterior.charAt(firstLetter) != ' ') {
                        // Busca la ultima letra del nombre de la variable
                        for (int lastLetter = posIgual - 1; lastLetter > firstLetter; lastLetter--) {
                            if (asignacionPosterior.charAt(lastLetter) != ' ') {
                                variable = asignacionPosterior.substring(firstLetter, lastLetter + 1);
                                break asigna;
                            }
                        }
                        variable = String.valueOf(asignacionPosterior.charAt(firstLetter));
                        break;
                    }
                }

                // Verifica que se le haya asignado un nombre a la variable
                if (variable.equals("")) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". No se ha especificado una variable antes del signo igual\n";
                    return false;
                }

                // Verifica si la variable de la asignacion posterior a la iteracion no se ha declarado aun
                if (!variables.existeVariable(variable)) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". La variable '" + variable + "' en la asignacion del 'para' no ha sido declarada\n";
                    return false;
                }

                String asignacion = asignacionPosterior.substring(posIgual + 1);
                // Envia a evaluar la expresion que coincida con el mismo tipo de dato de la variable
                evaluacionCondicion = this.resolverEcuacion(asignacion, pNumeroLinea);

                // Verifica que la igualdad coincida con el tipo de dato de la variable
                if (evaluacionCondicion == null) {
                    return false;
                } else if (!evaluacionCondicion.equals(variables.getVariable(variable).getTipo())) {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                            + ". El tipo de dato que desea asignar en el 'para' despues de cada ciclo, no"
                            + " coincide con el tipo de dato de la variable\n";
                    return false;
                } else {
                    pilaPalabrasReservadas.push("para");
                    return true;
                }

            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No es posible crear un ciclo 'para' sin antes haber creado un 'programa'\n";
                return false;
            }

        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Despues de la palabra 'para', solo puede ir '[', espacio(s) o tabs\n";
            return false;
        }
    }

    // LISTO
    public boolean cerrarPara(String pLineaCodigo, int pPosicion, int pNumeroLinea) {
        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '\t') {
            // Verifica que se haya creado un programa
            if (!pilaPalabrasReservadas.estaVacia()) {
                // Verifica que sea "para" la etiqueta que este en el top de la pila
                if (pilaPalabrasReservadas.getValorEnTop().equals("para")) {
                    // Aumento pPosicion que estaba en un espacio
                    pPosicion++;
                    // Se verifica que no existan caracteres despues de '#para'
                    while (pPosicion < pLineaCodigo.length()) {
                        // Si no es un espacio lo que hay, es xq existe algo mas despues del #para
                        if (pLineaCodigo.charAt(pPosicion) != ' ' && pLineaCodigo.charAt(pPosicion) != '\t') {
                            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". Después de la etiqueta '#para' no puede haber ningún otro caracter\n";
                            return false;
                        }
                        pPosicion++;
                    }
                    // Extrae la palabra reservada 'para' de la pila
                    pilaPalabrasReservadas.pop();
                    return true;

                } else {
                    salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha cerrado la etiqueta '" + pilaPalabrasReservadas.getValorEnTop()
                            + "' . Asegurese de cerrarla antes de utilizar la etiqueta '#para'\n";
                    return false;
                }
            } else {
                salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No es posible utilizar la etiqueta '#para' sin antes crear un ciclo 'para'\n";
                return false;
            }
        } else {
            salidaRevision += "ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la palabra reservada '#para' no pueden haber mas caracteres\n";
            return false;
        }
    }
}
