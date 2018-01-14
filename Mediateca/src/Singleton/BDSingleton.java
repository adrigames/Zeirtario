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
    private static String url = "jdbc:derby://localhost:1527/Mediateca";
    private static Connection conn;
    private  Statement stm;
    
    public BDSingleton() {
    }
    
    public static BDSingleton getInstancia(){
        return instancia;
    }
    
    public boolean abrirConexion(){
        try{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        conn = DriverManager.getConnection(url);
        stm = conn.createStatement();
        return true;
        }catch(Exception e){
            e.printStackTrace();
            
            return false;
        }
    }
    
    public boolean ejecutarSentencia(String sentencia){
        try{
            
            stm.executeUpdate(sentencia);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
    }
    }
    
    public int dameClave(){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ARTICULO");
            ResultSet rs = ps.executeQuery();
            int aux = 0;
            while(rs.next()){
                 aux = rs.getInt("CLAVE");
            }

            return aux;
        } catch (SQLException ex) {
            Logger.getLogger(BDSingleton.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
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
