/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.awt.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
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
        List<String> crunchifyList = new ArrayList<String>();
       crunchifyList.add("eBay");
		crunchifyList.add("Paypal");
		crunchifyList.add("Google");
		crunchifyList.add("Yahoo");
 
		// iterate via "for loop"
		System.out.println("==> For Loop Example.");
		for (int i = 0; i < crunchifyList.size(); i++) {
			System.out.println(crunchifyList.get(i));
		}
 
		// iterate via "New way to loop"
		System.out.println("\n==> Advance For Loop Example..");
		for (String temp : crunchifyList) {
			System.out.println(temp);
		}
 
		// iterate via "iterator loop"
		System.out.println("\n==> Iterator Example...");
		Iterator<String> crunchifyIterator = crunchifyList.iterator();
		while (crunchifyIterator.hasNext()) {
			System.out.println(crunchifyIterator.next());
		}
    }

    static class Articulo {

        public Articulo() {
        }
    }
    
}
