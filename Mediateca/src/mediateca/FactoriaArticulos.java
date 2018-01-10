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

/**
 *
 * @author Ignacio
 */
public class FactoriaArticulos {
    
    //incluirle un objeto de tipo proxy DB
    
    Libro createLibro(String titulo, String autor, String genero, int paginas)
    {
        int id = 0; //obtener id de la base de datos
        Libro libroConstruido = new Libro( id, titulo, autor, genero, paginas);
        //meter en la base de datos y hacer void
        return libroConstruido;
    }
    
    Comic createComic(String titulo, String guionista, String dibujante, int paginas)
    {
        int id = 0; //obtener id de la base de datos
        Comic comicConstruido = new Comic(id, titulo, guionista, dibujante, paginas);
        //meter en la base de datos y hacer void
        return comicConstruido;
    }
    
    Pelicula createPelicula(String titulo, String director, int duracion)
    {
        int id = 0; //obtener id de la base de datos
        Pelicula peliculaConstruida = new Pelicula(id, titulo, director, duracion);
        //meter en la base de datos y hacer void
        return peliculaConstruida;
    }
    
    Disco createDisco(String titulo, String autor, int duracion, int numeroCanciones)
    {
        int id = 0; //obtener id de la base de datos
        Disco discoConstruido = new Disco(id, titulo, autor, duracion, numeroCanciones);
        //meter en la base de datos y hacer void
        return discoConstruido;
    }
    
}
