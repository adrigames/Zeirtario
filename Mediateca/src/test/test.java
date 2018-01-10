/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.sql.*;
/**
 *
 * @author Adrian
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String url = "jdbc:derby://localhost:1527/Mediateca";
        Connection conn = null;
        Statement stm =null;
        System.out.println("URL: "+url);
        try{
            
            System.out.println("Creando conexion");
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(url);
            stm = conn.createStatement();
            stm.execute("Insert into ARTICULO values (2,'NOMBRE','AUTOOR','MISTERIO','LIBRO',FALSE)");
            stm.close();
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
    }
    
}
