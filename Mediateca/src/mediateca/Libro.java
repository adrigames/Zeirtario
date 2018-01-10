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
public class Libro extends Articulo{
    private String autor;
    private String genero;
    private int paginas;

    public Libro(int id, String titulo, String autor, String genero, int paginas) {
        super(id, titulo);
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    
}
