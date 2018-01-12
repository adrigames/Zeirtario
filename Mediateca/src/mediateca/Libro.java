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
    
    private int paginas;
    private String sinopsis;

    public Libro(int paginas, String sinopsis, int id, String titulo, String autor, String genero, boolean reservado) {
        super(id, titulo, autor, genero, reservado);
        this.paginas = paginas;
        this.sinopsis = sinopsis;
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
