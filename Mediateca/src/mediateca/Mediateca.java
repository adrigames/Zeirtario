/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediateca;
import java.util.ArrayList;
import java.util.Iterator;
import mediateca.FactoriaArticulos;
import mediateca.Usuario;
import proxy.ProxyQuery;
/**
 *
 * @author Ignacio
 */
public class Mediateca {
    //factoria
    //proxy
    //
    public FactoriaArticulos factoria = null;
    public ProxyQuery proxyDB = null;
    public Usuario usuarioConectado = null;
    public String tipoSiguienteArticulo = null;

    public Mediateca() {
        this.proxyDB = new ProxyQuery();
        this.factoria = new FactoriaArticulos();
    }
    
    public void desconectarUsuario(){
        usuarioConectado = null;
    }
    
    public void configurarUsuario(Usuario u, String nombre, String apellido, String dni, String sexo, int edad, String email, boolean admin,String pass) {
        u.setApellido(apellido);
        u.setDni(dni);
        u.setEdad(edad);
        u.setEmail(email);
        u.setNombre(nombre);
        u.setPass(pass);
        u.setSexo(sexo);
    }
    
    public boolean logIn(String dni, String contrasena){
        boolean respuesta = false;
        Usuario u = proxyDB.cargaUsuario(dni) ;//= buscarUsuarioProxy(nombre
        //coger user de la base de datos
        if(u == null)
        {
            //usuario no existe
            respuesta = false;
            this.usuarioConectado = null;
        }else{
            if(u.getPass().equalsIgnoreCase(contrasena)){
                //usuario logeado correctamente
                respuesta = true;
                this.usuarioConectado = u;
            }else{
                //contrasena erronea
                respuesta = false;
                this.usuarioConectado = null;
            }
        }
        return respuesta;
    }
    
    public ArrayList<Usuario> usuariosNoAdmin()
    {
        ArrayList<Usuario> listaUsuarios = proxyDB.obtenerUsuariosNoAdmin();
        
        return listaUsuarios;
    }
    
    public boolean insertarComic(String titulo, String autor, String ilustrador, String genero, int paginas, String sinopsis){
        Comic comic = factoria.createComic(ilustrador, paginas, sinopsis, titulo, autor, genero, false); //false por no reservado
        return proxyDB.insertarComic(comic);
    }
    
    public boolean insertarArticulo(String tipo, String titulo, String autor, String genero, boolean reservado, int enteroAux, String sinopsis, String cadenaAux)
    {
        Articulo articulo = factoria.createArticulo(tipo, titulo, autor, genero, reservado, enteroAux, sinopsis, cadenaAux);
        boolean respuesta = false;
        if(tipo.equalsIgnoreCase("libro")){
            respuesta = proxyDB.insertarLibro((Libro) articulo);
        }
        if(tipo.equalsIgnoreCase("comic")){
            respuesta = proxyDB.insertarComic((Comic) articulo);
        }
        if(tipo.equalsIgnoreCase("disco")){
            respuesta = proxyDB.insertarAudio((Disco) articulo);
        }
        if(tipo.equalsIgnoreCase("pelicula")){
            respuesta = proxyDB.insertarCine((Pelicula) articulo);
        }
        return respuesta;
    }
    
}
