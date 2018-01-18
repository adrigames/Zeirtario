/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediateca;

import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
public class Fachada {
    private static Fachada fachada;
    public static Mediateca mediateca = null;
    
    private Fachada() {
        mediateca = new Mediateca();
    }
    
    public static Fachada getInstancia() {
        if(fachada == null)
        {
            fachada = new Fachada();
        }
        return fachada;
    }
    
    public static boolean getAdminUser(){
        if(mediateca == null)
        {
            return false;
        }else{
            return mediateca.usuarioConectado.isAdmin();
        }
    }
    
    public static void desconectarUsuario(){
        mediateca.desconectarUsuario();
    }
    
    public static boolean logearse(String dni, String contrasena){
        boolean respuesta = false;
        //metodo logearse del proxy que devuelve false si ha habido error, true si se ha logeado
        //Usuario user = mediateca.proxyDB.
        respuesta = mediateca.logIn(dni , contrasena);
        return respuesta;
    }
    
    public static boolean crearUsuario(String nombre, String apellido, int edad, String dni, String sexo, String email, String contrasena){
        boolean respuesta;
        //metodo del proxy que de de alta un usuario y devuleva true si se ha intoducido correctamente
        Usuario u = new Usuario();//prototype method
        mediateca.configurarUsuario(u, nombre, apellido, dni, sexo, edad, email, false, contrasena); //ponemos el id a 0 pero la base de datos lo ignorara, y admin falso por defecto
        respuesta = mediateca.proxyDB.insertarUsuario(u);
        return respuesta;
    }
    
    public static ArrayList<String> usuariosNoAdmin(){
        
        return mediateca.usuariosNoAdmin();
        
    }
    
    public static boolean insertarElemento(String tipo, String titulo, String autor, String genero, int enteroAux, String sinopsis, String cadenaAux){
        return mediateca.insertarArticulo(tipo, titulo, autor, genero, false, enteroAux, sinopsis, cadenaAux); //empiezan sin reserva
    }
    
    public static boolean insertarComic(String titulo, String autor, String ilustrador, String genero, int paginas, String sinopsis){
        return mediateca.insertarComic(titulo, autor, ilustrador, genero, paginas, sinopsis);
    }
    //public static boolean insertarLibro
    //public static boolean insertarUsuario
}
