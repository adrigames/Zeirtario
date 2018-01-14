/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediateca;

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
            return mediateca.usuario.isAdmin();
        }
    }
    
    public static void desconectarUsuario(){
        mediateca.desconectarUsuario();
    }
    
    public static boolean logearse(String usuario, String contraseña){
        boolean respuesta = false;
        //metodo logearse del proxy que devuelve false si ha habido error, true si se ha logeado
        return respuesta;
    }
    
    public static boolean crearUsuario(String nombre, String apellido, int edad, String dni, String sexo, String email, String contrasena){
        boolean respuesta = false;
        //metodo del proxy que de de alta un usuario y devuleva true si se ha intoducido correctamente
        return respuesta;
    }
    
}
