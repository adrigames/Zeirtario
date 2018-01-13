/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;
import mediateca.Parametro;
import Singleton.BDSingleton;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
/**
 *
 * @author Adrian
 */
public class proxyQuery {
    private BDSingleton conexion = BDSingleton.getInstancia();
    public proxyQuery() {
    }
    private boolean validarSentencia(String sentencia){
        if(sentencia.contains(";")|| sentencia.contains("(") || sentencia.contains(")")){
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "La sentencia introducida contiene carácteres ilegales.\nConsulte el manual de "
                    + "uso para saber cuales son.","Advertencia",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else{
            return true;
        }
    }
    
    public void insertar(ArrayList<Parametro> oParametros){
        String sentencia = "INSERT INTO (:NOMBRETABLA) VALUES (:CLAVE , :VALORES)";
        String sentenciaConsulta = "";
        String nombreTabla = "";
        Iterator<Parametro> iterador = oParametros.iterator();
        while(iterador.hasNext()){
            if(iterador.next().getNombre() == ":TABLA"){
                nombreTabla = iterador.next().getValor();
            }
        }
        
    }
}
