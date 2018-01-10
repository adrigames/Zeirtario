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
public class Disco extends Articulo {
    private String autor;
    private int duracion;
    private int numeroCanciones;

    public Disco(int id, String titulo, String autor, int duracion, int numeroCanciones) {
        super(id, titulo);
        this.autor = autor;
        this.duracion = duracion;
        this.numeroCanciones = numeroCanciones;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

}
