/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import Singleton.BDSingleton;
import java.awt.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import mediateca.Comic;
import mediateca.Disco;
import mediateca.Libro;
import mediateca.Pelicula;
import mediateca.Usuario;
import proxy.ProxyQuery;
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
       Libro a = new Libro(1,"SE TE ",2,"sE","sEEE","sEEEEE",false);
       ProxyQuery m = new ProxyQuery();
       m.insertarLibro(a);
        
    }

    static class Articulo {

        public Articulo() {
        }
    }
    
}
