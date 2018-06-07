/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Utilidades.Plano;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author itanortegaortega
 */
public class GestorBd {
    private static Connection conexion;
    private static Statement statement;
    private static ResultSet resulset;
    
    private static String ruta = Plano.getRuta();
    
    private static String servidor = Plano.leerAVector(ruta).get(0).toString();
    private static String puerto = Plano.leerAVector(ruta).get(1).toString();
    private static String bd = Plano.leerAVector(ruta).get(2).toString();
    private static String usuario = Plano.leerAVector(ruta).get(3).toString();
    private static String pass = Plano.leerAVector(ruta).get(4).toString();

    public static void conectar(){    
        System.out.println("Base de datos a usar: " + bd);
        if(!estaConectado()){
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }

            try {
                conexion = DriverManager.getConnection("jdbc:postgresql://" + servidor + ":" + puerto + "/" + bd, usuario, pass);

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public static void desconectar(){
        if(conexion!=null){
            try {
                conexion.close();
            } catch (SQLException e) {
                
            }
            finally{
                conexion=null;
            }
        }
    }
    
    public static boolean estaConectado(){
        return (conexion!=null);
    }
    
    public static ResultSet procesarConsulta (String sentencia, String origen){
        try {
            statement = conexion.createStatement();
            resulset = statement.executeQuery(sentencia);
        } catch (SQLException e) {
            System.out.println("procesarConsulta." + origen + e);
        }
         
        return resulset;
    }
    
    public static boolean ejecutarSentenciaSQL(String sentencia, String origen){ 
        boolean ban = false;
        try {
            statement.executeUpdate(sentencia);
            ban = true;
        } catch (SQLException e) {
            System.out.println("ejecutarSentenciaSQL" + origen + e);
        }
        return ban;
    }
    
    public static long ejecutarSentenciaSQLGetId(String sentencia, String origen){ 
        long res = 0;
        int idGenerado;
        try {
            PreparedStatement statement2 = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = statement2.executeUpdate();

            ResultSet generatedKeys = statement2.getGeneratedKeys();
            if (generatedKeys.next()) {
                res = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("ejecutarSentenciaSQL" + origen + e);
        }
        return res;
    }

    
    public static boolean ejecutarTransaccion(ArrayList<String> sentencias, String origen){
        boolean ban = false;
        try {
            conexion.setAutoCommit(false);
            for(int i=0;i<sentencias.size();i++){
                statement.addBatch((String)  sentencias.get(i));
            }
            statement.executeBatch();
            conexion.commit();
            ban = true;
        } catch (SQLException e) {
            System.out.println("ejecutarTransaccion" + origen + e);
            ban=false;
            try {
                conexion.rollback();
            } catch (SQLException ex) { }
        }
        try {
            conexion.setAutoCommit(true);
        }catch (SQLException ex) {}
        return ban;
    }
    
    public static Connection getConexion() {
        return conexion;
    }
}
