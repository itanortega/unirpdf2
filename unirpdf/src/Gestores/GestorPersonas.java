/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Pojos.Dato;
import Pojos.Registro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author itanortegaortega
 */
public class GestorPersonas{    

    public ArrayList<Registro> faltanPorGenerarPortada(boolean imprimir) {
        ArrayList<Registro> registros = new ArrayList<>();
        String metodo = "faltanPorGenerarPortada";
        Registro r;
        int id = 0;
        ResultSet rs = null;
        SQL sql = new SQL()
                .select("id,numero_identificacion,nombre,orden")
                .from("hv_personas")  
                .where("creado", "=", false);

        rs=GestorBd.procesarConsulta(sql.obtenerSql(imprimir), metodo); 
        try {
            if(rs!=null){
                while (rs.next()) {
                    r = new Registro();
                    r.setCampo0(rs.getLong("id"));
                    r.setCampo1(rs.getString("numero_identificacion"));
                    r.setCampo2(rs.getString("nombre"));
                    r.setCampo3(rs.getString("orden"));
                    registros.add(r);
                }
            }
        } catch (SQLException e) {
            System.out.println(metodo + e);
        }
        return registros;
    }
    
    public ArrayList<Registro> faltanPorFusionar(boolean imprimir) {
        ArrayList<Registro> registros = new ArrayList<>();
        String metodo = "faltanPorFusionar";
        Registro r;
        int id = 0;
        ResultSet rs = null;
        SQL sql = new SQL()
                .select("id,numero_identificacion,nombre,orden")
                .from("hv_personas")  
                .where("fusionado", "=", false);

        rs=GestorBd.procesarConsulta(sql.obtenerSql(imprimir), metodo); 
        try {
            if(rs!=null){
                while (rs.next()) {
                    r = new Registro();
                    r.setCampo0(rs.getLong("id"));
                    r.setCampo1(rs.getString("numero_identificacion"));
                    r.setCampo2(rs.getString("nombre"));
                    r.setCampo3(rs.getString("orden"));
                    registros.add(r);
                }
            }
        } catch (SQLException e) {
            System.out.println(metodo + e);
        }
        return registros;
    }
    
    public boolean actualizarEstado(String cedula, String campo) {
        String metodo = "actualizarEstado";
        boolean res = false;
        String sql = "UPDATE hv_personas SET " + campo + "=true WHERE numero_identificacion = '" + cedula + "' ";
        
        if(GestorBd.ejecutarSentenciaSQL(sql, metodo)){
            res = true;
        }

        return res; 
    }

}
