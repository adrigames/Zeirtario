/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Adrian
 */
public class BDSingleton {
    
    private static BDSingleton instancia = new BDSingleton();
    String url = "jdbc:derby://localhost:1527/Mediateca";
    Connection conn = null;
    Statement stm = null;
    
    public BDSingleton() {
    }
    
    public static BDSingleton getInstancia(){
        return instancia;
    }
    
    public boolean insertarOBorrar(String sentencia){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(url);
            stm = conn.createStatement();
            stm.execute(sentencia);
            stm.close();
            conn.close();
            stm = null;
            conn = null;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            
            return false;
    }
    }
    public ResultSet seleccionar(String sentencia){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(url);
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sentencia);
            stm.close();
            conn.close();
            stm = null;
            conn = null;
            return rs;
            
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
