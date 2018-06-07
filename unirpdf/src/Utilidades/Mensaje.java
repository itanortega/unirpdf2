/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import javax.swing.JOptionPane;

/**
 *
 * @author itanortegaortega
 */
public class Mensaje {
    public static void Info(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void Error(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void Warning(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    public static boolean Confirmacion(String mensaje){
        if(JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION)==0)
            return true;
        return false;
    }
    
    public static void mensajesCrud(String identificador) {
        String res = "";
        switch (identificador) {
            case "guardar.ko": res = "No fué posible guardar el registro, por favor intente más tarde."; break;
            case "actualizar.ko": res = "No fué posible actualizar el registro, por favor intente más tarde."; break;
            case "eliminar.ko": res = "No fué posible eliminar el registro, por favor intente más tarde."; break;
        }
        Error(res);
    }
}
