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
                    if ((lineaAnalizada.charAt(j) == ' ' || lineaAnalizada.charAt(j) == '['
                            || lineaAnalizada.charAt(j) == ':' || lineaAnalizada.charAt(j) == '=')
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
                                    break;
                                case "imprimirln":
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
                        }
                        // Verifica si es una variable
                        else if (variables.existeVariable(palabraAnalizada)) {
                            System.out.println("Es variable");
                            // Verifica si la variable que se va a usar es el nombre de la clase
                            if (variables.getVariable(palabraAnalizada).getTipo().equals("clase")) {
                                System.out.println("ERROR DE SINTAXIS en la linea " + (i + 1)
                                        + ". No se pueden realizar operaciones sobre el nombre de la clase");
                                return;
                            } else {
                                // Verifica si la asignacion se ejecuto correctamente
                                if (realizarAsignacion(lineaAnalizada, j, (i + 1))) {
                                    break;
                                } else {
                                    return;
                                }
                            }
                        } else {
                            // No se reconoce la palabra
                            System.out.println("ERROR DE SINTAXIS en la linea " +(i + 1)
                                    + ". No se reconoce la palabra '" + palabraAnalizada + "' como una sentencia válida");
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
            syntaxisCorrecta = true;
            variables = null;
        } else {
            System.out.println("ERROR DE SINTAXIS. Aun no se ha cerrado la "
                    + "etiqueta '" + pilaPalabrasReservadas.getValorEnTop() + '\'');
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
        if (pLineaCodigo.charAt(pPosicionCaracter) == ' ' || pLineaCodigo.charAt(pPosicionCaracter) == ':') {
            // Verifica si ya se abrio programa o en caso contrario la pila esta vacia
            if (pilaPalabrasReservadas.estaVacia()) {
                System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                        + ". No es posible crear variables sin antes abrir la etiqueta programa");
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
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha encontrado el caracter :: despues del tipo de variable a crear");
                    return false;
                }

                // Se analiza la existencia de '::' despues del tipo de variable 
                for (; indiceAnalizado < palabrasLinea.length; indiceAnalizado++) {
                    // Realiza hasta que el campo no sea vacio
                    if (!palabrasLinea[indiceAnalizado].equals("")) {
                        // Verifica que la palabra sea unicamente "::"
                        if (!palabrasLinea[indiceAnalizado].equals("::")) {
                            System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". No se ha encontrado el caracter :: despues del tipo de variable a crear");
                            return false;
                        } else {
                            indiceAnalizado++;
                            break;
                        }
                    }
                }

                // Verifica que existan mas palabras en la linea para analizar
                if (!(palabrasLinea.length > indiceAnalizado)) {
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha especificado el nombre para la variable que se desea crear");
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
                            System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". No se ha especificado el nombre de la variable");
                            return false;
                        } // Verifica si el primer caracter del nombre de la variable, es una letra
                        else if (!Character.isLetter(nombreVariable.charAt(0))) {
                            System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". El nombre de una variable debe iniciar con una letra");
                            return false;
                        } else {
                            // Se verifica que el nombre de la variable solo tenga numeros y letras
                            for (int i = 1; i < nombreVariable.length(); i++) {
                                if (!Character.isLetter(nombreVariable.charAt(i)) && !Character.isDigit(nombreVariable.charAt(i))) {
                                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                            + ". El nombre de una variable debe contener únicamente números y letras");
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
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". No se ha encontrado el cierre de línea '!'");
                    return false;
                }

                // Se analiza la existencia de '!' despues del nombre de variable 
                for (; indiceAnalizado < palabrasLinea.length; indiceAnalizado++) {
                    // Realiza hasta que el campo no sea vacio
                    if (!palabrasLinea[indiceAnalizado].equals("")) {
                        // Verifica que la palabra sea unicamente "!"
                        if (!palabrasLinea[indiceAnalizado].equals("!")) {
                            System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                    + ". No se ha encontrado el cierre de línea '!' después del nombre de la variable");
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
                        System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                                + ". No pueden haber caracteres ni palabres después del cierre de línea '!'");
                        return false;
                    }
                }

                // Verifica si es una plabra reservada
                if (main.palabrasReservadas.esPalabraReservada(nombreVariable)) {
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". El nombre que intenta darle a la variable es una palabra reservada");
                    return false;
                } // Verifica si ya existe una variable con ese nombre
                else if (variables.existeVariable(nombreVariable)) {
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea
                            + ". Ya existe una variable con el nombre " + nombreVariable);
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
            System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". Después de la etiqueta " + pTipoVariable + ", no se puede ubicar el "
                    + "caracter " + pLineaCodigo.charAt(pPosicionCaracter) + ""
                    + ". La sintaxis correcta es: " + pTipoVariable + "::nombreVariable{=valor}!");
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
                } // Verifica que el nombre del programa no sea una palabra reservada
                else if (main.palabrasReservadas.esPalabraReservada(nombrePrograma)) {
                    System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ". "
                            + "El nombre del programa no puede ser una palabra reservada");
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
                    // Hace pop a la lista de palabras reservadas
                    pilaPalabrasReservadas.pop();
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

    public boolean realizarAsignacion(String pLineaCodigo, int pPosicion, int pNumeroLinea) {

        if (pLineaCodigo.charAt(pPosicion) == ' ' || pLineaCodigo.charAt(pPosicion) == '=') {

            pLineaCodigo = pLineaCodigo.replaceAll("\t", " ");
            int ultimoChar = pLineaCodigo.length() - 1;
            
            //////////    Se busca el caracter de fin de linea '!'  ////////////
            
            boolean cierreEncontrado = false;            
            // Busca que exista el cierre de linea
            for (; ultimoChar > pPosicion; ultimoChar--) {
                if(pLineaCodigo.charAt(ultimoChar) != ' '){
                    if(pLineaCodigo.charAt(ultimoChar) == '!'){
                        //ultimoChar--;
                        cierreEncontrado = true;
                    }
                    break;
                }                
            }
            // Verifica que se haya encontrado el cierre de linea '!'
            if(!cierreEncontrado){
                System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No se ha encontrado el cierre de la línea '!'");
                return false;
            }
            
            ///////////    Se busca el caracter de asignacion '='  /////////////
            
            boolean igualEncontrado = false;
            // Recorre la linea de codigo en busca del '='
            for (; pPosicion < ultimoChar; pPosicion++) {
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
                System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                        + ". No se ha encontrado el '=' después del nombre de la variable");
                return false;
            }

            // Esta es toda la equivalencia que voy a realizar sobre la variable
            String equivalencia = pLineaCodigo.substring(pPosicion, ultimoChar);
            
            
//            
//
//            pLineaCodigo = pLineaCodigo.substring(pPosicion);
//            pLineaCodigo = pLineaCodigo.replaceAll("!", " ! ");
//            pLineaCodigo = pLineaCodigo.replaceAll("[", " [ ");
//            pLineaCodigo = pLineaCodigo.replaceAll("]", " ] ");
//            pLineaCodigo = pLineaCodigo.replaceAll("+", " + ");
//            pLineaCodigo = pLineaCodigo.replaceAll("-", " - ");
//            pLineaCodigo = pLineaCodigo.replaceAll("*", " * ");
//            pLineaCodigo = pLineaCodigo.replaceAll("/", " / ");
//            pLineaCodigo = pLineaCodigo.replaceAll("%", " % ");
//
//            pLineaCodigo = pLineaCodigo.replaceAll("!", " ! ");
//            pLineaCodigo = pLineaCodigo.replaceAll("!", " ! ");
//            pLineaCodigo = pLineaCodigo.replaceAll("!", " ! ");
//            pLineaCodigo = pLineaCodigo.replaceAll("!", " ! ");
            return true;
        } else {
            System.out.println("ERROR DE SINTAXIS en la linea " + pNumeroLinea + ""
                    + ". En la asignación de valores a una variable, no puede ir el"
                    + " caracter '" + pLineaCodigo.charAt(pPosicion) + "' después del nombre de la variable");
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
