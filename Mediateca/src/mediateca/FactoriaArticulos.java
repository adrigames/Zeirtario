/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediateca;
import mediateca.Comic;
import mediateca.Libro;
import mediateca.Pelicula;
import mediateca.Disco;
import proxy.ProxyQuery;

/**
 *
 * @author Ignacio
 */
public class FactoriaArticulos {
    
    //incluirle un objeto de tipo proxy DB
    private ProxyQuery proxyDB;
    public Usuario usuarioConectado = null;
    
    public FactoriaArticulos(ProxyQuery proxyDB) {
        this.proxyDB = proxyDB;
    }
    
    Libro createLibro(int paginas, String sinopsis, String titulo, String autor, String genero, boolean reservado)
    {
        //ver si existe ese libro (por titulo)
        //si no existe, se introduce, sino no
        int id = 0; //obtener id de la base de datos
        Libro libroConstruido = new Libro( paginas, sinopsis, id, titulo, autor, genero, reservado);
        //meter en la base de datos y hacer void
        return libroConstruido;
    }
    
    Comic createComic(String ilustrador, int paginas, String sinopsis, String titulo, String autor, String genero, boolean reservado)
    {
         //ver si existe ese comic (por titulo)
        //si no existe, se introduce, sino no
        int id = 0; //obtener id de la base de datos
        Comic comicConstruido = new Comic(ilustrador, paginas, sinopsis, id, titulo, autor, genero, reservado);
        //meter en la base de datos y hacer void
        //boolean respuesta = proxyDB.insertarComic(comicConstruido);
        return comicConstruido;
    }
    /*Comic createComic(String ilustrador, int paginas, String sinopsis, String titulo, String autor, String genero, boolean reservado)
    {
         //ver si existe ese comic (por titulo)
        //si no existe, se introduce, sino no
        int id = 0; //obtener id de la base de datos
        Comic comicConstruido = new Comic(ilustrador, paginas, sinopsis, id, titulo, autor, genero, reservado);
        //meter en la base de datos y hacer void
        return comicConstruido;
    }*/
    
    Pelicula createPelicula(String actores, String sinopsis, int duracion, String titulo, String autor, String genero, boolean reservado)
    {
         //ver si existe ese pelicula (por titulo)
        //si no existe, se introduce, sino no
        int id = 0; //obtener id de la base de datos
        Pelicula peliculaConstruida = new Pelicula( actores, sinopsis, duracion, id, titulo, autor, genero, reservado);
        //meter en la base de datos y hacer void
        return peliculaConstruida;
    }
    
    Disco createDisco(int duracion, String titulo, String autor, String genero, boolean reservado)
    {
         //ver si existe ese disco (por titulo)
        //si no existe, se introduce, sino no
        int id = 0; //obtener id de la base de datos
        Disco discoConstruido = new Disco( duracion, id, titulo, autor, genero, reservado);
        //meter en la base de datos y hacer void
        return discoConstruido;
    }
    
}
