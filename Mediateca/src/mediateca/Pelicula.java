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
public class Pelicula extends Articulo{
    private String actores;
    private String sinopsis;
    private int duracion;

    public Pelicula(String actores, String sinopsis, int duracion, int id, String titulo, String autor, String genero, boolean reservado) {
        super(id, titulo, autor, genero, reservado);
        this.actores = actores;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    
}
