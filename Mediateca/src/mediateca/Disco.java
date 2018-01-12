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
    
    private int duracion;

    public Disco(int duracion, int id, String titulo, String autor, String genero, boolean reservado) {
        super(id, titulo, autor, genero, reservado);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}
