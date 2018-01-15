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
   

    public Mediateca() {
        this.proxyDB = new ProxyQuery();
        this.factoria = new FactoriaArticulos(proxyDB);
    }
    
    public void desconectarUsuario(){
        usuarioConectado = null;
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
    
    public ArrayList<String> usuariosNoAdmin()
    {
        ArrayList<Usuario> listaUsuarios = proxyDB.obtenerUsuariosNoAdmin();
        ArrayList<String> listaNombres = new ArrayList();
        Usuario uAux;
        if(listaUsuarios != null){
            Iterator i = listaUsuarios.iterator();
             while (i.hasNext()) {
                uAux = (Usuario) i.next();
                
                boolean funciona = listaNombres.add(uAux.getDni() + "," + uAux.getNombre() + "," + uAux.getApellido());
                //listaUsuarios.iterator();
            }
        }
        return listaNombres;
    }
    
    public boolean insertarComic(String titulo, String autor, String ilustrador, String genero, int paginas, String sinopsis){
        Comic comic = factoria.createComic(ilustrador, paginas, sinopsis, titulo, autor, genero, false); //false por no reservado
        return proxyDB.insertarComic(comic);
    }
}
