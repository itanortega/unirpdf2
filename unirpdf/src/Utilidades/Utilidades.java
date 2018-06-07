/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Gestores.GestorBd;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.in;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author itanortegaortega
 */
public class Utilidades {
    
    public static Font fuente13 = new java.awt.Font("Leelawadee UI Semilight", 0, 13);
    public static Font fuente14 = new java.awt.Font("Leelawadee UI Semilight", 0, 14);

    public static String getMD5(String input) {
        String hashtext = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashtext = number.toString(16);

            while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
            }
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        
        return hashtext;
    }

    public static String formatoMoneda(long valor) {
        String cadena = String.valueOf(valor);
        String fm = "";
        int digitos = 0;
        for(int i=cadena.length(); i>0; i--){
            if(digitos == 2){
                if(i==1) { 
                    fm = cadena.substring(i-1, i) + fm;
                }else {
                    fm = "." + cadena.substring(i-1, i) + fm;
                }                
                digitos = 0;
            }else {
                fm = cadena.substring(i-1, i) + fm;
                digitos ++;
            }
        }
        fm = "$ " + fm;
        return fm;
    }
    
    public static String convertirTextoSql(Object obj){
        if(obj.getClass().equals(String.class)){
            return "'" + obj.toString() + "'";
        }else{
            return obj.toString();
        }
    }

    public static boolean verificarPermisos(ArrayList<String> permisos, String permiso) {
        boolean res = false;
        for(int i=0; i<permisos.size(); i++){
            if(permisos.get(i).toString().equals(permiso)){
                res = true;
            }
        }
        return res;
    }
    
    public static boolean validarFecha(String fecha, String comparacion, Date fechaV) {
        int dias = 0;
        if(fecha.length() != 10){
            return false; 
        }
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        
        dias = compararFechas(dateToString(fechaV), fecha);
        
        if(comparacion.equals("<") && dias<=0){
            return false;
        }else if(comparacion.equals(">") && dias>=0){
            return false;
        }else if(comparacion.equals("<=") && dias<0){
            return false;
        }else if(comparacion.equals(">=") && dias>0){
            return false;
        }
        
        return true;
    }
    
    public static String dateToString(Date date) {
        SimpleDateFormat FECHA=new SimpleDateFormat("yyyy-MM-dd");
        return date==null ? "" : FECHA.format(date);
    }

    
    public static Date obtenerFecha(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"), new Locale("es", "Colombia"));
        return calendar.getTime();
     }

    
    public static int compararFechas(String fecha1,String fecha2){
        return fechaToDateAMD(fecha1).compareTo(fechaToDateAMD(fecha2));
    }

    public static Date fechaToDateAMD(String fecha){
        int anio=Integer.parseInt(fecha.substring(0,4));
        int mes=Integer.parseInt(fecha.substring(5,7));
        int dia=Integer.parseInt(fecha.substring(8,10));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"), new Locale("es", "Colombia"));
        calendar.set(anio,mes-1,dia,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    private static String obtenerRutaProyecto() {
        File miDir = new File (".");
        String dir = "";
        try {
            dir =  miDir.getCanonicalPath();
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return dir;
    }
    
    public static boolean crearPdf(String cedula){
        String rutasalida= obtenerRutaProyecto() + "/Rep/Portadas/portada_"+cedula+".pdf";
        Map parameters = new HashMap();
        parameters.put("identificacion", cedula);
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(obtenerRutaProyecto() + "/Rep/Reportes/HojaVidaPares.jasper", parameters, GestorBd.getConexion());
            JasperExportManager.exportReportToPdfFile(jasperPrint, rutasalida);
        } catch (JRException ex) {
            ex.printStackTrace();
            return false;
        }        
        return true;
    }
    
    public static boolean unirPdf(String cedula, String nombre) throws FileNotFoundException, DocumentException, IOException{

        Document document = new Document();
        
        FileInputStream archivo1 = new FileInputStream(new File(obtenerRutaProyecto() + "/Rep/Portadas/portada_"+cedula+".pdf"));
        FileInputStream archivo2 = new FileInputStream(new File(obtenerRutaProyecto() + "/Rep/Soportes/"+cedula+".pdf"));        
        FileOutputStream outputStream = new FileOutputStream(new File(obtenerRutaProyecto() + "/Rep/Resultados/" + nombre + ".pdf"));
        
        
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        
        PdfReader reader1 = new PdfReader(archivo1);
        for (int i = 1; i <= reader1.getNumberOfPages(); i++) {
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(reader1, i);
            cb.addTemplate(page, 0, 0);
        }
        
        PdfReader reader2 = new PdfReader(archivo2);
        for (int i = 1; i <= reader2.getNumberOfPages(); i++) {
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(reader2, i);
            cb.addTemplate(page, 0, 0);
        }
        
        outputStream.flush();
        document.close();
        outputStream.close();
        
        return true;
    }

}
