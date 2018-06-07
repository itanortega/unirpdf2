/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Pojos.Dato;
import Utilidades.Utilidades;
import java.util.ArrayList;

/**
 *
 * @author itanortegaortega
 */
public class SQL {
    ArrayList<String> lineas = new ArrayList<>();
    int tabsBloque = 0;
    String tabsStringBloque = "";
    int tabs = 1;
    String tabsString = "\n";
    
    public String obtenerSql(boolean imprimir){
        String res = "";
        for(int i=0; i<lineas.size(); i++){
            res += lineas.get(i).toString();
        }
        if(imprimir){
            System.out.println(res);
        }
        return res;
    }
    
    public void tabStringBloque(){        
        tabsStringBloque = "";
        for(int i=0; i<tabsBloque; i++){
            tabsStringBloque +="\t";
        }
        tabStringLinea();
    }
    
    public void tabStringLinea(){        
        tabsString = "";
        for(int i=0; i<tabs; i++){
            tabsString +="\t";
        }
        tabsString += tabsStringBloque;
    }
    
    public void reestructurarTabs(){
        tabStringBloque();
        tabStringLinea();
    }
    
    public SQL tabBloque(){
        tabsBloque ++;
        tabStringBloque();
        return this;
    }
    
    public SQL backTabBloque(){
        tabsBloque --;
        tabStringBloque();
        return this;
    }
    
    public SQL tabLinea(){
        tabs ++;
        tabStringLinea();
        return this;
    }
    
    public SQL backTabLinea(){
        tabs --;
        tabStringLinea();
        return this;
    }
    
    public SQL select(String campos) {
        reestructurarTabs();
        String[] camposSeparados = campos.split(",");
        lineas.add(tabsStringBloque + "SELECT");
        for(int i=0; i<camposSeparados.length; i++){           
            if(i==0){
                lineas.add("\n" + tabsString + camposSeparados[i].trim());
            }else{                
                lineas.add(",\n" + tabsString + camposSeparados[i].trim());
            }
        }
        return this;
    }
    
    public SQL selectCount(String campo, String alias) {
        reestructurarTabs();
        lineas.add(tabsStringBloque + "SELECT \n" + tabsString + "COUNT(" + campo + ") " + alias);
        return this;
    }
    
    public SQL selectDistinct(String campo){
        reestructurarTabs();
        lineas.add(tabsStringBloque + "SELECT \n" +  tabsString + "DISTINCT ON (" + campo.trim() + ")");
        return this;
    }
    
    public SQL selectAdd(String campo) {
        lineas.add(",\n" + tabsStringBloque + tabsString + campo.trim());
        return this;
    }
    
    public SQL select1(String campo) {
        lineas.add("\n" + tabsStringBloque +  tabsString + campo.trim());
        return this;
    }

    public SQL from(String tabla) {
        reestructurarTabs();
        lineas.add("\n" + tabsStringBloque + "FROM \n" + tabsString + tabla + "\n");
        return this;
    }
    
    public SQL where(String campo, String operador, Object valor) {
        reestructurarTabs();
        lineas.add(tabsStringBloque + "WHERE \n"  + tabsString + campo + " " + operador + " " + Utilidades.convertirTextoSql(valor) + "\n");
        return this;
    }
    
    public SQL whereAnd(String campo, String operador, Object valor) {
        reestructurarTabs();
        lineas.add(tabsString + "AND " + campo + " " + operador + " " + Utilidades.convertirTextoSql(valor) + "\n");
        return this;
    }

    public SQL innerJoin(String innerJoin) {
        tabLinea();
        lineas.add(tabsString + "INNER JOIN " + innerJoin + "\n");
        return this;
    }
    
    public SQL on(String on1, String on2) {     
        lineas.add(tabsString + "ON " + on1 + " = " + on2 + "\n");       
        backTabLinea();   
        return this;
    }

    public SQL startFoo(int foo) {
        reestructurarTabs();
        lineas.add("\n" + tabsStringBloque + "FROM \n" + tabsString + "(\n");
        tabBloque();
        tabBloque();
        return this;
    }

    public SQL endFoo(int foo) {
        backTabBloque();
        backTabBloque();
        lineas.add("\n" + tabsString + ") AS foo" + String.valueOf(foo));
        return this;
    }
    
    public SQL pesonalizado(String texto) {
        lineas.add(texto);
        return this;
    }
    
    public SQL order(String campos) {
        reestructurarTabs();
        String[] camposSeparados = campos.split(",");
        lineas.add(tabsStringBloque + "ORDER BY");
        for(int i=0; i<camposSeparados.length; i++){           
            if(i==0){
                lineas.add("\n" + tabsString + camposSeparados[i].trim());
            }else{                
                lineas.add(",\n" + tabsString + camposSeparados[i].trim());
            }
        }
        lineas.add("\n");
        return this;
    }
    
    public SQL delete(String tabla, Object id){
        lineas.add("DELETE FROM " + tabla + " WHERE id = " + Utilidades.convertirTextoSql(id));
        return this;
    }
    
    public SQL insertar(String tabla, ArrayList<Dato> datos){
        String campos="";
        String valores="";
        for(int i=0; i<datos.size(); i++){
            if(i==0){
                campos += datos.get(i).getClave();                
                valores += datos.get(i).getValor();
            }else{
                campos += ", " + datos.get(i).getClave();               
                valores += ", " + datos.get(i).getValor();
            }
        }
        
        lineas.add("INSERT INTO " + tabla + "(" + campos + ") VALUES\n(" + valores + ")");
        return this;
    }
    
    public SQL actualizar(String tabla, ArrayList<Dato> datos, Object id){
        reestructurarTabs();
        lineas.add("UPDATE " + tabla + " SET");
        for(int i=0; i<datos.size(); i++){
            if(i==0){
                lineas.add("\n" + tabsString + datos.get(i).getClave() + " = " + datos.get(i).getValor());
            }else{
                lineas.add(",\n" + tabsString + datos.get(i).getClave() + " = " + datos.get(i).getValor());
            }
        }
        lineas.add("\nWHERE id = " + Utilidades.convertirTextoSql(id));
        return this;
    }
   
}
 