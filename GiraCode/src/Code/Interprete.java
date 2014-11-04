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
public class Interprete {

    ListaSimpleVariables variables;
    ListaCircular errores;
    int numeroDeLinea;

    /**
     * Metodo utilizado para retornar la lista que almacena el flujo de
     * ejecucion
     *
     * @return lista con el flujo de ejecucion
     */
    public ListaCircular getErrores() {
        return errores;
    }

    /**
     * Metodo constructor
     */
    public Interprete() {
        numeroDeLinea = 0;
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

    /**
     * Metodo utilizado para ejecutar un codigo GiraCODE
     *
     * @param pGiraCODE codigo a ejecutar
     * @return true si la ejecucion fue correcta o false si hubo un error
     */
    public boolean ejecutarCodigo(String pGiraCODE) {
        // Separa el codigo por lineas (\n)
        String[] codeLines = pGiraCODE.split("\n");
        // Analiza cada linea
        for (int i = 0; i < codeLines.length; i++) {
            numeroDeLinea++;
            // Elimina todos los espacios antes y despues de la linea de codigo
            String codeLine = codeLines[i].trim();
            // Verifica que la linea tenga texto
            if (!codeLine.isEmpty()) {
                // Variables que almacenan la posicion de inicio y la sig palabra
                int posNextWord;
                String nextWord;
                String primeraPalabra = getFirstWord(codeLine);
                // Busca la primera palabra de la linea de codigo y compara cual es
                switch (primeraPalabra) {
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
                        if (this.imprimir(nextWord, false)) {
                            break;
                        } else {
                            // Algo fallo al imprimir
                            return false;
                        }
                    case "imprimirln":
                        // Obtiene la parte del codigo a imprimir y quita espacio con trim()
                        nextWord = codeLine.substring(("imprimirln").length(), codeLine.length() - 1).trim();
                        if (this.imprimir(nextWord, true)) {
                            break;
                        } else {
                            // Algo fallo al imprimir
                            return false;
                        }
                    case "para":
                        // Obtiene el codigo de la condicion y la asignacion posterior
                        String condicion = codeLine.substring(codeLine.indexOf("[") + 1, codeLine.length() - 1).trim();
                        int numLineasPara = 0;
                        int paraAbiertos = 1;
                        String codigoPara = "";

                        // Obtiene todo el bloque  para
                        while (paraAbiertos > 0) {
                            // Obtiene la primera palabra de la linea de codigo 
                            codeLine = codeLines[++i].trim();
                            primeraPalabra = getFirstWord(codeLine);

                            if (primeraPalabra.equals("para")) {
                                paraAbiertos++;
                                codigoPara += codeLine + "\n";
                            } else if (primeraPalabra.equals("#para") && (paraAbiertos - 1) == 0) {
                                paraAbiertos--;
                            } else {
                                if (primeraPalabra.equals("#para")) {
                                    paraAbiertos--;
                                }
                                codigoPara += codeLine + "\n";
                            }
                            numLineasPara++;
                        }
                        if (cicloPara(condicion, codigoPara)) {
                            numeroDeLinea += numLineasPara;
                            break;
                        }
                        return false;
                    case "si":
                        int numLineaAbreSi = numeroDeLinea;
                        int numLineaBloqueSi;
                        ArrayList<String> bloqueIf = new ArrayList<>();
                        bloqueIf.add(codeLine);
                        int ifAbiertos = 1;
                        // Obtiene todo el bloque de codigo del condicional SI
                        do {
                            codeLine = codeLines[++i].trim();
                            primeraPalabra = getFirstWord(codeLine);
                            if (primeraPalabra.equals("si")) {
                                ifAbiertos++;
                            } else if (primeraPalabra.equals("#si")) {
                                ifAbiertos--;
                            } else if (codeLine.equals("")) {
                                codeLine = " ";
                            }
                            bloqueIf.add(codeLine);
                        } while (ifAbiertos >= 1);
                        // Obtiene el numero de lineas del bloque si(-1 xq el si ya fue contado por numeroDeLinea)
                        numLineaBloqueSi = bloqueIf.size() - 1;
                        // Manda a realizar el bloque si y verifica el resultado devuelto
                        if (condicionIf(bloqueIf)) {
                            numeroDeLinea = (numLineaAbreSi + numLineaBloqueSi);
                            break;
                        } else {
                            return false;
                        }
                    case "mientras":
                        // Obtiene el codigo de la condicion y la asignacion posterior
                        String condicionMientras = codeLine.substring(codeLine.indexOf("[") + 1, codeLine.length() - 1).trim();
                        int mientrasAbiertos = 1;
                        String codigoMientras = "";
                        int numLineasMientras = 0;
                        // Obtiene todo el bloque  mientras
                        while (mientrasAbiertos > 0) {
                            // Obtiene la primera palabra de la linea de codigo 
                            codeLine = codeLines[++i].trim();
                            primeraPalabra = getFirstWord(codeLine);
                            // Verifica si hay otro while dentro y aumenta o disminuye cantidades
                            if (primeraPalabra.equals("mientras")) {
                                mientrasAbiertos++;
                                codigoMientras += codeLine + "\n";
                            } else if (primeraPalabra.equals("#mientras") && (mientrasAbiertos - 1) == 0) {
                                mientrasAbiertos--;
                            } else {
                                if (primeraPalabra.equals("#mientras")) {
                                    mientrasAbiertos--;
                                }
                                codigoMientras += codeLine + "\n";
                            }
                            numLineasMientras++;
                        }
                        if (cicloMientras(condicionMientras, codigoMientras)) {
                            // Asigna el numero de linea del final del ciclo
                            numeroDeLinea += numLineasMientras;
                            break;
                        }
                        return false;
                    case "repita":
                        int repitaAbiertos = 1;
                        String codigoRepita = "";
                        // Obtiene todo el bloque  repita cuando
                        while (repitaAbiertos > 0) {
                            // Obtiene la primera palabra de la linea de codigo 
                            codeLine = codeLines[++i].trim();
                            primeraPalabra = getFirstWord(codeLine);
                            // Verifica si hay otro 'repita' dentro y aumenta o disminuye cantidades
                            if (primeraPalabra.equals("repita")) {
                                repitaAbiertos++;
                                codigoRepita += codeLine + "\n";
                            } else if (primeraPalabra.equals("#cuando") && (repitaAbiertos - 1) == 0) {
                                repitaAbiertos--;
                            } else {
                                if (primeraPalabra.equals("#cuando")) {
                                    repitaAbiertos--;
                                } else if (codeLine.equals("")) {
                                    codeLine = " ";
                                }
                                codigoRepita += codeLine + "\n";
                            }
                        }
                        // Obtiene el codigo de la condicion
                        String condicionRepita = codeLine.substring(codeLine.indexOf("[") + 1, codeLine.length() - 1).trim();
                        if (cicloRepitaCuando(condicionRepita, codigoRepita)) {
                            numeroDeLinea++;
                            break;
                        }
                        return false;
                    // Es una variable
                    default:
                        posNextWord = codeLine.indexOf("=") + ("=").length();
                        nextWord = codeLine.substring(posNextWord, codeLine.length() - 1).trim();

                        if (this.realizarAsignacion(nextWord, primeraPalabra)) {
                            break;
                        } else {
                            // Algo fallo (division por 0)
                            return false;
                        }
                }
            }
        }
        return true;
    }

    /**
     * Metodo utilizado para realizar el condicional Si-Osi-Sino
     *
     * @param pBloqueIf Bloque de codigo si
     * @return true si se ejecuto correctamente o false en caso contrario
     */
    private boolean condicionIf(ArrayList<String> pBloqueIf) {
        int indiceBloque = 0;
        // Obtiene la linea de codigo del condicional if y seguidamente su condicion y aumenta una unidad el indiceBloque
        String codeLine = pBloqueIf.get(indiceBloque);
        String condicion = codeLine.substring(codeLine.indexOf("[") + 1, codeLine.length() - 1);
        String condicionEvaluada = resolverEcuacion(condicion);

        if (condicionEvaluada == null) {
            return false;
        } else if (condicionEvaluada.equals("verdad")) {
            int siAbiertos = 1;
            String firstWord;
            String codigoSi = "";
            // Recorre todo el bloque hasta que se encuentre el cierre del condicional if
            while (true) {
                // Obtiene la linea a analizar y la primera palabra de esta
                codeLine = pBloqueIf.get(++indiceBloque);
                firstWord = getFirstWord(codeLine);
                // Verifica la primera palabra para ver si continua el bloque del si o acaba
                if (firstWord.equals("si")) {
                    siAbiertos++;
                } else if (siAbiertos == 1 && (firstWord.equals("sino")
                        || firstWord.equals("osi") || firstWord.equals("#si"))) {
                    break;
                } else if (firstWord.equals("#si")) {
                    siAbiertos--;
                }
                codigoSi += codeLine + "\n";
            }
            return ejecutarCodigo(codigoSi);
            // resolver casos "osi" y "sino";
        } else {
            // Cuenta cuandas lineas hay para luego verificar cuantas no se analizaron
            int totalLineasIf = pBloqueIf.size();
            // Elimina el "si" del codigo
            pBloqueIf.remove(indiceBloque);
            String firstWord;
            int siAbiertos = 1;
            // Elimina todas las lineas innecesarias del si/osi
            while (true) {
                firstWord = getFirstWord(pBloqueIf.get(indiceBloque));
                // Verifica la primera palabra
                if (firstWord.equals("si")) {
                    siAbiertos++;
                } else if (siAbiertos == 1 && (firstWord.equals("sino")
                        || firstWord.equals("osi") || firstWord.equals("#si"))) {
                    break;
                } else if (firstWord.equals("#si")) {
                    siAbiertos--;
                }
                pBloqueIf.remove(indiceBloque);
            }
            // Establece la linea por la que va ahora despues de que no se cumpliera la condicion del Si
            numeroDeLinea += (totalLineasIf - pBloqueIf.size());

            if (firstWord.equals("sino")) {
                // Cambia el sino por sino[verdad] para que se ejecute si o si
                pBloqueIf.set(indiceBloque, pBloqueIf.get(indiceBloque) + "[verdad]");
                return condicionIf(pBloqueIf);
            } else if (firstWord.equals("osi")) {
                return condicionIf(pBloqueIf);
            } else {
                // Es el ultimo #si -> el ciclo se ejuto de manera exitosa
                return true;
            }
        }
    }

    /**
     * Metodo utilizado para ejecutar el ciclo Repita-Cuando
     *
     * @param pCondicion condicion del ciclo
     * @param pCodigo codigo que se va a ejecutar varias veces
     * @return true si se ejecuto satisfactoriamente el ciclo o false en caso
     * contrario
     */
    private boolean cicloRepitaCuando(String pCondicion, String pCodigo) {
        int numFirstLine = numeroDeLinea;
        String respuestaCondicion;
        // Aqui se ejecuta el codigo repetitivo
        do {
            // Vuelvo a poner el numero de linea en el inicio de ciclo repita
            numeroDeLinea = numFirstLine;
            // Manda a ejecutar el codigo del ciclo
            if (ejecutarCodigo(pCodigo) == false) {
                return false;
            }
            // Revisa y asigna el valor de la condicion
            respuestaCondicion = resolverEcuacion(pCondicion);
            if (respuestaCondicion == null) {
                return false;
            }
        } while (respuestaCondicion.equals("verdad"));
        return true;
    }

    /**
     * Metodo utilizado para realizar el ciclo mientras
     *
     * @param pCondicion condicion de parada del ciclo mientras
     * @param pCodigo codigo a ejecutarse dentro del ciclo mientras
     * @return true si se logra ejecutar todo el ciclo o false en caso contrario
     */
    private boolean cicloMientras(String pCondicion, String pCodigo) {
        int numFirstLine = numeroDeLinea;
        // Se envia a evaluar la condicion
        String respuestaCondicion = resolverEcuacion(pCondicion);
        if (respuestaCondicion == null) {
            return false;
        }
        // Aqui se ejecuta el codigo repetitivo
        while (respuestaCondicion.equals("verdad")) {
            // Manda a ejecutar el codigo del ciclo
            if (ejecutarCodigo(pCodigo) == false) {
                return false;
            }

            // Vuelvo a poner el numero de linea en el inicio de ciclo mientras
            numeroDeLinea = numFirstLine;
            // Vuelve a mandar la condicion para verificar el nuevo valor en caso que dependa de una variable
            respuestaCondicion = resolverEcuacion(pCondicion);
            if (respuestaCondicion == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo utilizado para ejecutar un ciclo for
     *
     * @param pCondicion condicion del ciclo y postAsignacion
     * @param pCodigo codigo a ejecutar en el ciclo
     * @return true si el resultado del ciclo fue exitoso o false en caso
     * contrario
     */
    private boolean cicloPara(String pCondicion, String pCodigo) {
        int numFirstLine = numeroDeLinea;

        // Obtiene la condicion y la variable a la que se va a asignar el valor posterior
        int pos$ = getPosicionCharEnPara(pCondicion, 0, '$');
        String condicion = pCondicion.substring(0, pos$).trim();
        String asignacion = pCondicion.substring(pos$ + 1);
        int posIgual = getPosicionCharEnPara(asignacion, pos$ + 1, '=');
        String varAsignacion = pCondicion.substring(pos$ + 1, posIgual).trim();
        asignacion = pCondicion.substring(posIgual + 1).trim();

        // Se evalua la condicion en busca de posibles errores (null)
        String respuestaCondicion = resolverEcuacion(condicion);
        if (respuestaCondicion == null) {
            return false;
        }
        // Aqui se ejecuta el codigo repetitivo
        while (respuestaCondicion.equals("verdad")) {
            // Manda a ejecutar el codigo del ciclo
            if (ejecutarCodigo(pCodigo) == false) {
                return false;
            }
            // Vuelvo a poner el numero de linea en el inicio de ciclo mientras
            numeroDeLinea = numFirstLine;
            // Realiza la asignacion y verifica que se haya realizado exitosamente
            if (realizarAsignacion(asignacion, varAsignacion) == false) {
                return false;
            }
            // Se vuelve a verificar la condicion en caso de que cambie con respecto a una var
            respuestaCondicion = resolverEcuacion(condicion);
            if (respuestaCondicion == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo utilizado para enviar a imprimir una expresion
     *
     * @param pLineaCodigo linea de codigo que contiene la expresion a imprimir
     * @param pNumeroLinea numero de linea, el cual se indica en caso de error
     * @return true si se imprime correctamente o false si ocurrio un error
     */
    private boolean imprimir(String pLineaCodigo, boolean pConEnter) {
        String resultado = this.resolverEcuacion(pLineaCodigo);
        if (resultado == null) {
            return false;
        } else {
            if (pConEnter) {
                errores.insertar(resultado + "\n");
            } else {
                errores.insertar(resultado);
            }
            return true;
        }
    }

    /**
     * Metodo utilizado para asignarle un valor a una variable
     *
     * @param pLineaCodigo Codigo con la expresion que se le va a asignar a la
     * variable
     * @param pNumeroLinea Numero de linea donde se realiza la asignacion
     * @param pVariable Nombre de la variable a la que se le va a asignar un
     * valor
     * @return true si la asignacion se lleva a cabo con exito o false en caso
     * contrario
     */
    private boolean realizarAsignacion(String pLineaCodigo, String pVariable) {
        // Manda a realizar las posibles operaciones para obtener el dato unico a asignar
        String resultado = this.resolverEcuacion(pLineaCodigo);
        if (resultado == null) {
            return false;
        } else {
            variables.getVariable(pVariable).setValor(resultado);
////            errores.insertar("Se ha asignado a la variable '" + pVariable + "' el valor: " + resultado);
            return true;
        }
    }

    /**
     * Metodo utilizado para resolver una ecuacion, ya sea una igualdad o un
     * condicion
     *
     * @param pExpresion Linea de codigo con la expresion a resolver
     * @param pNumeroLinea Numero de la linea de codigo actual
     * @return retorna el valor resultante o null en caso que la ecuacion tenga
     * division por cero
     */
    private String resolverEcuacion(String pExpresion) {

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
                        var1 = realizarOperacion(var, tipoVar, operador, var1, tipoVar1);
                        // Verifica si hubo un error en la operacion
                        if (var1 == null) {
                            return null;
                        }
                        tipoVar1 = getTipoResultante(tipoVar, operador, tipoVar1);
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
                    var1 = realizarOperacion(var1, tipoVar1, operador, var2, tipoVar2);
                    // Verifica si hubo un error en la operacion
                    if (var1 == null) {
                        return null;
                    }
                    tipoVar1 = getTipoResultante(tipoVar1, operador, tipoVar2);
                    var2 = tipoVar2 = operador = "";
                }
            }
        }
        return var1;
    }

    /**
     * Metodo utilizado para realizar la operacion binaria de negacion
     *
     * @param pValor valor binario "falso" o "verdad"
     * @return la negacion de la expresion binaria
     */
    private String operacionNo(String pValor) {
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
    private String realizarOperacion(String pVar1, String pTipo1, String pOperador, String pVar2, String pTipo2) {
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
                // Verifica si la comparacion es entre entero y decimal
                if ((pTipo1.equals("decimal") && pTipo2.equals("entero"))
                        || (pTipo1.equals("entero") && pTipo2.equals("decimal"))) {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    if (numero1.equals(numero2)) {
                        return "verdad";
                    } else {
                        return "falso";
                    }
                } // La comparacion es entre iguales 
                else {
                    if (pVar1.equals(pVar2)) {
                        return "verdad";
                    } else {
                        return "falso";
                    }
                }
            case "<>":
                // Verifica si la comparacion es entre entero y decimal
                if ((pTipo1.equals("decimal") && pTipo2.equals("entero"))
                        || (pTipo1.equals("entero") && pTipo2.equals("decimal"))) {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    if (!numero1.equals(numero2)) {
                        return "verdad";
                    } else {
                        return "falso";
                    }
                } // La comparacion es entre iguales 
                else {
                    if (!pVar1.equals(pVar2)) {
                        return "verdad";
                    } else {
                        return "falso";
                    }
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
                        errores.insertar("\nError en la linea " + numeroDeLinea + " al intentar dividir por 0");
                        return null;
                    } else {
                        return String.valueOf(num1 / num2);
                    }
                } else {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    if (numero2 == 0) {
                        errores.insertar("\nError en la linea " + numeroDeLinea + " al intentar dividir por 0");
                        return null;
                    } else {
                        return String.valueOf(numero1 / numero2);
                    }
                }
            case "%":
                if (pTipo1.equals("entero") && pTipo2.equals("entero")) {
                    int num1 = Integer.parseInt(pVar1);
                    int num2 = Integer.parseInt(pVar2);
                    if (num2 == 0) {
                        errores.insertar("\nError en la linea " + numeroDeLinea + " al realizar mod (%) por 0");
                        return null;
                    } else {
                        return String.valueOf(num1 % num2);
                    }
                } else {
                    numero1 = Double.parseDouble(pVar1);
                    numero2 = Double.parseDouble(pVar2);
                    if (numero2 == 0) {
                        errores.insertar("\nError en la linea " + numeroDeLinea + " al realizar mod (%) por 0");
                        return null;
                    } else {
                        return String.valueOf(numero1 % numero2);
                    }
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
     * @param pTipoVar1 Tipo de variable 1
     * @param pOperador Operador
     * @param pTipoVar2 Tipo de variable 2
     * @return el tipo de dato que se obtiene al resolver la ecuacion
     */
    private String getTipoResultante(String pTipoVar1, String pOperador, String pTipoVar2) {
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
                if ((pTipoVar1.equals("entero") && pTipoVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
            case "*":
                if ((pTipoVar1.equals("entero") && pTipoVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
            case "/":
                if ((pTipoVar1.equals("entero") && pTipoVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
            case "%":
                if ((pTipoVar1.equals("entero") && pTipoVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
            case "+":
                if (pTipoVar1.equals("Texto") || pTipoVar2.equals("Texto") || (pTipoVar1.equals("caracter") && pTipoVar2.equals("caracter"))) {
                    return "Texto";
                } else if ((pTipoVar1.equals("entero") && pTipoVar2.equals("entero"))) {
                    return "entero";
                } else {
                    return "decimal";
                }
        }
        return null;
    }

    /**
     * Metodo utilizado para encontrar un caracter dado en la linea de codigo.
     * Es util para encontrar el '$' o el '=' de la expresion del ciclo 'para'
     *
     * @param pExpresion Expresion del ciclo 'para'
     * @param pPosicion Posicion actual del caracter de la linea
     * @param pChar Caracter que se va a buscar
     * @return la posicion en la que se encuentra
     *
     */
    private int getPosicionCharEnPara(String pExpresion, int pPosicion, char pChar) {
        boolean esTexto = false;
        boolean esChar = false;
        // Ciclo que busca la aparicion del caracter de separacion del 'para' '$',
        // Ademas verifica que no este presente entre Texto o entre un Char
        for (int i = 0; i < pExpresion.length(); i++) {
            if (pExpresion.charAt(i) == pChar && !esTexto && !esChar) {
                return i + pPosicion;
            } else if (pExpresion.charAt(i) == '@') {
                esTexto = !esTexto;
            } else if (pExpresion.charAt(i) == '&') {
                esChar = !esChar;
            }
        }
        return -1;
    }
}
