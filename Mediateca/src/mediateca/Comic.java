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
public class Comic extends Articulo{
    
    private String ilustrador;
    private int paginas;
    private String sinopsis;

    //ojo que van primero los atributos propios de la clase y luego los de articulo
    public Comic(String ilustrador, int paginas, String sinopsis, int id, String titulo, String autor, String genero, boolean reservado) {
        super(id, titulo, autor, genero, reservado);
        this.ilustrador = ilustrador;
        this.paginas = paginas;
        this.sinopsis = sinopsis;
    }

    public String getIlustrador() {
        return ilustrador;
    }

    public void setIlustrador(String ilustrador) {
        this.ilustrador = ilustrador;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

}
