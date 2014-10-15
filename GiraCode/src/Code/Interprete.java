/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import javax.swing.text.Document;

/**
 *
 * @author Mario A
 */
public class Interprete {

    ListaSimpleVariables variables;
    ListaCircular errores;
    String salida = "";

    public ListaCircular getErrores() {
        return errores;
    }
    
    public String getSalida() {
        return salida;
    }

    /**
     * Metodo constructor
     *
     * @param pSalida Document del texto de salida
     */
    public Interprete() {
        errores = new ListaCircular();
        variables = new ListaSimpleVariables();
    }

    /**
     * Metodo que recorre la linea de codigo en busca de la primera palabra
     *
     * @param pLineaCodigo Linea de codigo
     * @return String con la primera palabra encontrada
     */
    private String getFirstWord(String pLineaCodigo) {
        String firstWord = "";
        char letraActual;
        // Recorre la linea hasta que aparezca el primer caracter separador de palabras
        for (int i = 0; i < pLineaCodigo.length(); i++) {
            letraActual = pLineaCodigo.charAt(i);
            if (letraActual != ' ' && letraActual != '[' && letraActual != '\t' && letraActual != ':' && letraActual != '=') {
                firstWord += letraActual;
            } else {
                break;
            }
        }
        return firstWord;
    }

    public void ejecutarCodigo(String pGiraCODE) {
        // Separa el codigo por lineas (\n)
        String[] codeLines = pGiraCODE.split("\n");
        // Analiza cada linea
        for (int i = 0; i < codeLines.length; i++) {
            // Elimina todos los espacios antes y despues de la linea de codigo
            String codeLine = codeLines[i].trim();
            // Verifica que la linea tenga texto
            if (!codeLine.isEmpty()) {
                // Variables que almacenan la posicion de inicio y la sig palabra
                int posNextWord;
                String nextWord;
                // Busca la primera palabra de la linea de codigo y compara cual es
                switch (getFirstWord(codeLine)) {
                    case "programa":
                        // Busco el nombre del programa y lo agrego en la lista de variabes
                        String nombrePrograma = codeLine.substring(("programa").length()).trim();
                        variables.insertar(nombrePrograma, "clase");
                        break;
                    case "#programa":
                        // Elimina las variables
                        variables = null;
                        break;
                    case "Texto":
                        posNextWord = codeLine.indexOf("::") + ("::").length();
                        nextWord = codeLine.substring(posNextWord, codeLine.length() - 1).trim();
                        // Crea una variable de tipo Texto
                        variables.insertar(nextWord, "", "Texto");
                        break;
                    case "entero":
                        posNextWord = codeLine.indexOf("::") + ("::").length();
                        nextWord = codeLine.substring(posNextWord, codeLine.length() - 1).trim();
                        // Crea una variable de tipo entero
                        variables.insertar(nextWord, "0", "entero");
                        break;
                    case "decimal":
                        posNextWord = codeLine.indexOf("::") + ("::").length();
                        nextWord = codeLine.substring(posNextWord, codeLine.length() - 1).trim();
                        // Crea una variable de tipo decimal
                        variables.insertar(nextWord, "0.0", "decimal");
                        break;
                    case "caracter":
                        posNextWord = codeLine.indexOf("::") + ("::").length();
                        nextWord = codeLine.substring(posNextWord, codeLine.length() - 1).trim();
                        // Crea una variable de tipo caracter
                        variables.insertar(nextWord, "_", "caracter");
                        break;
                    case "binario":
                        posNextWord = codeLine.indexOf("::") + ("::").length();
                        nextWord = codeLine.substring(posNextWord, codeLine.length() - 1).trim();
                        // Crea una variable de tipo binario
                        variables.insertar(nextWord, "falso", "binario");
                        break;
                    case "imprimir":
                        // Obtiene la parte del codigo a imprimir y quita espacio con trim()
                        nextWord = codeLine.substring(("imprimir").length(), codeLine.length() - 1).trim();
                        if (this.imprimir(nextWord, i + 1)) {
                            break;
                        } else {
                            // Algo fallo al imprimir
                            return;
                        }
////////                                case "imprimirln":
////////                                    break;
//////                    case "si":
//////                        // Verifica la condicion
//////                        if (this.abrirSi(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            // Algo fallo
//////                            return;
//////                        }
//////                    case "osi":
//////                        if (comprobarOsi(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }
//////                    case "sino":
//////                        // No debe contener nada despues de el
//////                        if (comprobraSino(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }
//////                    case "#si":
//////                        if (this.cerrarSi(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }
//////                    case "mientras":
//////                        if (this.abrirMientras(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }
//////                    case "#mientras":
//////                        if (this.cerrarMientras(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }
//////                    case "para":
//////                        if (this.abrirPara(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }
//////                    case "#para":
//////                        if (this.cerrarPara(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }
//////                    case "repita":
//////                        if (this.abrirRepita(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }
//////                    case "#cuando":
//////                        if (this.cerrarRepitaCuando(lineaAnalizada, j, i + 1)) {
//////                            break;
//////                        } else {
//////                            return;
//////                        }

                }

////                for (int j = 0; j < lineaAnalizada.length(); j++) {
////
////                    // Verifica si el caracter analizado es un espacio ubicado 
////                    // despues de una palabra, si es un '[' o ':'
////                    if ((lineaAnalizada.charAt(j) == ' ' || lineaAnalizada.charAt(j) == '['
////                            || lineaAnalizada.charAt(j) == ':' || lineaAnalizada.charAt(j) == '=' || lineaAnalizada.charAt(j) == '\t')
////                            && !palabraAnalizada.equals("")) {
////
////                        if (main.palabrasReservadas.esPalabraReservada(palabraAnalizada)) {
////                            // La palabra analizada es una palabra reservada
////                            System.out.println(palabraAnalizada + " es palabra reservada");
////                            switch (palabraAnalizada) {
////
////                            }
////                        } // Verifica si es una variable
////                        else if (variables.existeVariable(palabraAnalizada)) {
////                            System.out.println("Es variable");
////                            // Verifica si la variable que se va a usar es el nombre de la clase
////                            if (variables.getVariable(palabraAnalizada).getTipo().equals("clase")) {
////                                System.out.println("ERROR DE SINTAXIS en la linea " + (i + 1)
////                                        + ". No se pueden realizar operaciones sobre el nombre de la clase");
////                                return;
////                            } else {
////                                // Verifica si la asignacion se ejecuto correctamente
////                                if (realizarAsignacion(lineaAnalizada, j, (i + 1), variables.getVariable(palabraAnalizada).getTipo())) {
////                                    break;
////                                } else {
////                                    return;
////                                }
////                            }
////                        } else {
////                            // No se reconoce la palabra
////                            System.out.println("ERROR DE SINTAXIS en la linea " + (i + 1)
////                                    + ". No se reconoce la palabra '" + palabraAnalizada + "' como una sentencia válida"
////                                    + " o es una variable que no ha sido declarada");
////                            return;
////                        }
////                        // Termina de analizar la linea de codigo actual para analizar la siguiente
////                        break;
////
////                    } else {
////                        // Ignora los espacios en blanco seguidos y los tab
////                        if (lineaAnalizada.charAt(j) != ' ' && lineaAnalizada.charAt(j) != '\t') {
////                            palabraAnalizada += lineaAnalizada.charAt(j);
////                        }
////                    }
////                }
            }
        }
        variables = null;
    }

    public boolean imprimir(String pLineaCodigo, int pNumeroLinea) {
        String resultado = this.resolverEcuacion(pLineaCodigo, pNumeroLinea);
        if (resultado == null) {
            return false;
        } else {
            salida += resultado + "\n";
            return true;
        }
    }

    /**
     * Metodo utilizado para resolver una ecuacion, ya sea una igualdad o un
     * condicion
     *
     * @param pExpresion Linea de codigo con la expresion a resolver
     * @param pNumeroLinea Numero de la linea de codigo actual
     * @return
     */
    public String resolverEcuacion(String pExpresion, int pNumeroLinea) {

        String var1 = "";
        String var2 = "";
        String tipoVar1 = "";
        String tipoVar2 = "";
        String operador = "";
        boolean esNegativo = false;
        Pila pilaParentesis = new Pila();

        for (int i = 0; i < pExpresion.length(); i++) {
            if (pExpresion.charAt(i) != ' ' && pExpresion.charAt(i) != '\t') {
                // Es una variable o una palabra reservada
                if (Character.isLetter(pExpresion.charAt(i))) {
                    String palabra = String.valueOf(pExpresion.charAt(i));
                    // Agrega los caracteres a la palabra siempre y cuando sean letras o numeros
                    while ((i + 1) < pExpresion.length()
                            && (Character.isLetter(pExpresion.charAt(i + 1))
                            || Character.isDigit(pExpresion.charAt(i + 1)))) {
                        palabra += pExpresion.charAt(++i);
                    }

                    // Verifica si la palabra es: "verdad", "falso", "o", "y", "no" o "una variable" y almacena los datos
                    if (palabra.equals("verdad") || palabra.equals("falso")) {
                        if (var1.equals("")) {
                            var1 = palabra;
                            tipoVar1 = "binario";
                        } else {
                            var2 = palabra;
                            tipoVar2 = "binario";
                        }
                    } else if (palabra.equals("y") || palabra.equals("o")) {
                        operador = palabra;
                    } else if (palabra.equals("no")) {
                        // Muevo el indice hasta el caracter '['
                        while ((++i) < pExpresion.length() && pExpresion.charAt(i) != '[') {
                        }
                        // Hace el push del corchete
                        pilaParentesis.push("[");
                        // Verifica si hay datos en var1 para hecer el push de este y el operador
                        if (!var1.equals("")) {
                            pilaParentesis.push(var1);
                            pilaParentesis.push(tipoVar1);
                            pilaParentesis.push(operador);
                            var1 = tipoVar1 = operador = "";
                        }
                        // Hago el push del no
                        pilaParentesis.push("no");
                    } // Es una variable
                    else {
                        // Obtiene el Nodo con la informacion de la variable
                        NodoVariable dato = variables.getVariable(palabra);
                        // Verifica en cual variable se va a almacenar
                        if (var1.equals("")) {
                            var1 = dato.getValor();
                            tipoVar1 = dato.getTipo();
                        } else {
                            var2 = dato.getValor();
                            tipoVar2 = dato.getTipo();
                        }
                    }
                } // El dato analizado es un numero 
                else if (Character.isDigit(pExpresion.charAt(i))) {
                    String cifra = "";
                    // Se verifica si es negativo, lo agrega a la cifra y vuelve falso la variable
                    if (esNegativo) {
                        cifra += "-";
                        esNegativo = false;
                    }
                    cifra += String.valueOf(pExpresion.charAt(i));
                    // Agrega todos los numeros que hayan despues del actual o el punto si es decimal
                    while ((i + 1) < pExpresion.length()
                            && (Character.isDigit(pExpresion.charAt(i + 1)) || pExpresion.charAt(i + 1) == '.')) {
                        cifra += pExpresion.charAt(++i);
                    }
                    // Obtengo el tipo de dato numerico que es
                    String tipoDato;
                    if (cifra.contains(".")) {
                        tipoDato = "decimal";
                    } else {
                        tipoDato = "entero";
                    }
                    // Verifica en cual variable se va a ingresar la variable
                    if (var1.equals("")) {
                        var1 = cifra;
                        tipoVar1 = tipoDato;
                    } else {
                        var2 = cifra;
                        tipoVar2 = tipoDato;
                    }
                } // Es una variable tipo texto 
                else if (pExpresion.charAt(i) == '@') {
                    String cadena = "";
                    // Se recorre en busca del cierre del Texto
                    while ((++i < pExpresion.length()) && pExpresion.charAt(i) != '@') {
                        cadena += pExpresion.charAt(i);
                    }
                    if (var1.equals("")) {
                        var1 = cadena;
                        tipoVar1 = "Texto";
                    } else {
                        var2 = cadena;
                        tipoVar2 = "Texto";
                    }
                } // Es variable tipo caracter
                else if (pExpresion.charAt(i) == '&') {
                    if (var1.equals("")) {
                        var1 = String.valueOf(pExpresion.charAt(i + 1));
                        tipoVar1 = "caracter";
                    } else {
                        var2 = String.valueOf(pExpresion.charAt(i + 1));
                        tipoVar2 = "caracter";
                    }
                    i = i + 2;
                } // Es un parentesis '['
                else if (pExpresion.charAt(i) == '[') {
                    // Hago el push del parentesis a la pila
                    pilaParentesis.push("[");
                    // Verifico si hay datos antes del parentesis para meterlos a la pila tambien
                    if (!var1.equals("")) {
                        // Hago el push a la pila
                        pilaParentesis.push(var1);
                        pilaParentesis.push(tipoVar1);
                        pilaParentesis.push(operador);
                        // Quito los datos que ya estan en la pila
                        var1 = tipoVar1 = operador = "";
                    }
                    continue;
                } else if (pExpresion.charAt(i) == ']') {
                    // El valor de arriba de la pila es un no.
                    if (pilaParentesis.getValorEnTop().equals("no")) {
                        // Realiza la operacion del No y lo saca de la pila
                        var1 = operacionNo(var1);
                        pilaParentesis.pop();
                    }
                    // Verifica si hay una operacion antes del '[' en la pila
                    if (!pilaParentesis.getValorEnTop().equals("[")) {
                        // Obtiene el valor del operador y lo saca de la pila
                        operador = pilaParentesis.getValorEnTop();
                        pilaParentesis.pop();
                        // Obtengo el tipo de dato de la variable en la pila
                        String tipoVar = pilaParentesis.getValorEnTop();
                        pilaParentesis.pop();
                        // Obtengo la expresion 1 que se va a operar
                        String var = pilaParentesis.getValorEnTop();
                        pilaParentesis.pop();

                        // Realizo la operacion
                        var1 = realizarOperacion(var, tipoVar, operador, var1, tipoVar1, pNumeroLinea);
                        // Verifica si hubo un error en la operacion
                        if (var1 == null) {
                            return null;
                        }
//////////                        System.out.println(var1 +" "+ operador +" "+ var2 + " = " + var1);
//////////                        System.out.print(tipoVar1 + operador + tipoVar2 + " = ");
                        tipoVar1 = getTipoResultante(tipoVar, operador, tipoVar1);
//////////                        System.out.println(tipoVar1);
                        operador = "";
                    }
                    // Saca el parentesis de la pila '['
                    pilaParentesis.pop();
                    continue;
                } // Es un operador
                else if (main.listaOperadores.esPalabraReservada(String.valueOf(pExpresion.charAt(i)))) {
                    String ope = pExpresion.charAt(i) + "";
                    // Verifico si es un operador de dos simbolos como >= o <>
                    if ((i + 1) < pExpresion.length() && main.listaOperadores.esPalabraReservada(ope + pExpresion.charAt(i + 1))) {
                        // Agrego el otro operador
                        ope += pExpresion.charAt(++i);
                    }
                    // Verifica que exista Var1
                    if (!var1.equals("")) {
                        // Verifica si no hay un operador
                        if (operador.equals("")) {
                            operador = ope;
                        } else {
                            // Es un negativo
                            esNegativo = true;
                        }
                    } else {
                        // Es un negativo
                        esNegativo = true;
                    }
                    continue;
                }

                // Hay una operacion que realizar xq ya tengo dos expresiones y operador
                if (!var2.equals("")) {
                    // Realizo la operacion
                    var1 = realizarOperacion(var1, tipoVar1, operador, var2, tipoVar2, pNumeroLinea);
                    // Verifica si hubo un error en la operacion
                    if (var1 == null) {
                        return null;
                    }
////////                    System.out.println(var1 + operador + var2 + " = " + var1);
////////                    System.out.print(tipoVar1 + operador + tipoVar2 + " = ");
                    tipoVar1 = getTipoResultante(tipoVar1, operador, tipoVar2);
////////                    System.out.println(tipoVar1);
                    var2 = tipoVar2 = operador = "";
                }
            }
        }

        errores.insertar(var1);
        return var1;
    }

    /**
     * Metodo utilizado para realizar la operacion binaria de negacion
     *
     * @param pValor valor binario "falso" o "verdad"
     * @return la negacion de la expresion binaria
     */
    public String operacionNo(String pValor) {
        if (pValor.equals("verdad")) {
            return "falso";
        } else {
            return "verdad";
        }
    }

    /**
     * Metodo utilizado para resolver una operacion o una condicion
     *
     * @param pVar1 Variable uno
     * @param pTipo1 Tipo de dato de la primera variable
     * @param pOperador Operador entre variables
     * @param pVar2 Variable dos
     * @param pTipo2 Tipo de dato de la variable dos
     * @param pLinea Numero de linea de codigo analizada
     * @return El resultado de la operacion o null si es division por 0
     */
    public String realizarOperacion(String pVar1, String pTipo1, String pOperador, String pVar2, String pTipo2, int pLinea) {
        Double numero1;
        Double numero2;
        switch (pOperador) {
            case "o":
                if (pVar1.equals("verdad") || pVar2.equals("verdad")) {
                    return "verdad";
                } else {
                    return "falso";
                }
            case "y":
                if (pVar1.equals("verdad") && pVar2.equals("verdad")) {
                    return "verdad";
                } else {
                    return "falso";
                }
            case "=":
                if (pVar1.equals(pVar2)) {
                    return "verdad";
                } else {
                    return "falso";
                }
            case "<>":
                if (!pVar1.equals(pVar2)) {
                    return "verdad";
                } else {
                    return "falso";
                }
            case "<":
                numero1 = Double.parseDouble(pVar1);
                numero2 = Double.parseDouble(pVar2);
                if (numero1 < numero2) {
                    return "verdad";
                } else {
                    return "falso";
                }
            case ">":
                numero1 = Double.parseDouble(pVar1);
                numero2 = Double.parseDouble(pVar2);
                if (numero1 > numero2) {
                    return "verdad";
                } else {
                    return "falso";
                }
            case "<=":
                numero1 = Double.parseDouble(pVar1);
                numero2 = Double.parseDouble(pVar2);
                if (numero1 <= numero2) {
                    return "verdad";
                } else {
                    return "falso";
                }
            case ">=":
                numero1 = Double.parseDouble(pVar1);
                numero2 = Double.parseDouble(pVar2);
                if (numero1 >= numero2) {
                    return "verdad";
                } else {
                    return "falso";
                }
            case "-":
                if (pTipo1.equals("entero") && pTipo2.equals("entero")) {
                    int num1 = Integer.parseInt(pVar1);
                    int num2 = Integer.parseInt(pVar2);
                    return String.valueOf(num1 - num2);
                } else {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    return String.valueOf(numero1 - numero2);
                }

            case "*":
                if (pTipo1.equals("entero") && pTipo2.equals("entero")) {
                    int num1 = Integer.parseInt(pVar1);
                    int num2 = Integer.parseInt(pVar2);
                    return String.valueOf(num1 * num2);
                } else {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    return String.valueOf(numero1 * numero2);
                }
            case "/":
                if (pTipo1.equals("entero") && pTipo2.equals("entero")) {
                    int num1 = Integer.parseInt(pVar1);
                    int num2 = Integer.parseInt(pVar2);
                    if (num2 == 0) {
                        errores.insertar("Error en la linea " + pLinea + " al intentar dividir por 0");
                        return null;
                    } else {
                        return String.valueOf(num1 / num2);
                    }
                } else {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    if (numero2 == 0) {
                        errores.insertar("Error en la linea " + pLinea + " al intentar dividir por 0");
                        return null;
                    } else {
                        return String.valueOf(numero1 / numero2);
                    }
                }
            case "%":
                if (pTipo1.equals("entero") && pTipo2.equals("entero")) {
                    int num1 = Integer.parseInt(pVar1);
                    int num2 = Integer.parseInt(pVar2);
                    return String.valueOf(num1 % num2);
                } else {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    return String.valueOf(numero1 % numero2);
                }
            case "+":
                if (pTipo1.equals("Texto") || pTipo2.equals("Texto")
                        || (pTipo1.equals("caracter") && pTipo2.equals("caracter"))) {
                    return pVar1 + pVar2;
                } else if (pTipo1.equals("entero") && pTipo2.equals("entero")) {
                    int num1 = Integer.parseInt(pVar1);
                    int num2 = Integer.parseInt(pVar2);
                    return String.valueOf(num1 + num2);
                } else {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    return String.valueOf(numero1 + numero2);
                }
        }
        return null;
    }

    /**
     * Metodo utilizado para obtener el tipo de dato que devuelve una operacion
     *
     * @param pVar1 Tipo de variable 1
     * @param pOperador Operador
     * @param pVar2 Tipo de variable 2
     * @return el tipo de dato que se obtiene al resolver la ecuacion
     */
    public String getTipoResultante(String pVar1, String pOperador, String pVar2) {
        // Se va a retornar el tipo del valor si es correcta o null si es incorrecto
        switch (pOperador) {
            case "o":
                return "binario";
            case "y":
                return "binario";
            case "=":
                return "binario";
            case "<>":
                return "binario";
            case "<":
                return "binario";
            case ">":
                return "binario";
            case "<=":
                return "binario";
            case ">=":
                return "binario";
            case "-":
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
            case "*":
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
            case "/":
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
            case "%":
                if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
            case "+":
                if (pVar1.equals("Texto") || pVar2.equals("Texto") || (pVar1.equals("caracter") && pVar2.equals("caracter"))) {
                    return "Texto";
                } else if ((pVar1.equals("entero") && pVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
        }
        return null;
    }

}
