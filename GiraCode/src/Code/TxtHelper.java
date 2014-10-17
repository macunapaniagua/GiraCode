/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Mario A
 */
public class TxtHelper {

    /**
     * Metodo utilizado para leer un archivo txt
     *
     * @param pArchivo Archivo que se va a leer
     * @return el texto leido
     */
    public String cargarTXT(File pArchivo) {
        String codigo = "";
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //File archivo = new File(pRuta);
            FileReader fileReader = new FileReader(pArchivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Lectura del fichero
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                codigo += linea + "\n";
            }
            // Cierra
            fileReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codigo;
    }

    /**
     * Metodo utilizado para escribir un archivo y guardarlo en el disco
     *
     * @param pArchivo archivo donde se va a guardar la informacion
     * @param pCodigo Codigo que se va a escribir
     * @return true si se escibio o false si ocurrio un error
     */
    public boolean guardarTxt(File pArchivo, String pCodigo) {
        boolean escrito = false;
        try {
            FileWriter fileWriter = new FileWriter(pArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            // Escribe el archivo
            printWriter.write(pCodigo);
            escrito = true;
            // Cierra
            printWriter.close();
            bufferedWriter.close();
        } catch (Exception e) {
        }
        return escrito;
    }

}
