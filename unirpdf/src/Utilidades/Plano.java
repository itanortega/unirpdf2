/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author itanortegaortega
 */
public class Plano {
    public static ArrayList<String> leerAVector(String ruta) {
      ArrayList<String> vector = new ArrayList<String>();
      String res="";
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            vector.add(linea);
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        return vector;
    }

    public static String getRuta() {
        File win = new File("c:/windows/servidor.dat");
        File unix = new File("/Users/Shared/servidor.dat");
        
        if(win.exists()){
            return "c:/windows/servidor.dat";
        }else {
            return "/Users/Shared/servidor.dat";
        }        
    }
    
    public static boolean existeArchivo(String ruta){
        File fichero = new File(ruta);
        if (fichero.exists())
            return true;
        else
            return false;
    }
}
