/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Adrian
 */
public class BDSingleton {
    
    private static BDSingleton instancia = new BDSingleton();
    private String url = "jdbc:derby://localhost:1527/Mediateca";
    private Connection conn;
    private Statement stm;
    
    public BDSingleton() {
    }
    
    public static BDSingleton getInstancia(){
        return instancia;
    }
    
    public boolean abrirConexion(){
        try{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        conn = DriverManager.getConnection(url);
        return true;
        }catch(Exception e){
            e.printStackTrace();
            
            return false;
        }
    }
    
    public boolean ejecutarSentencia(String sentencia){
        try{
            stm = conn.createStatement();
            stm.execute(sentencia);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
    }
    }
    public ResultSet seleccionar(String sentencia){
        try{
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sentencia);
            return rs;
            
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean cerrarConexion(){
        try {
            stm.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
}
