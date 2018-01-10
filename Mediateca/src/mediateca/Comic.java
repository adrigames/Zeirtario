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
    private String guionista;
    private String dibujante;
    private int paginas;

    public Comic(int id, String titulo, String guionista, String dibujante, int paginas) {
        super(id, titulo);
        this.guionista = guionista;
        this.dibujante = dibujante;
        this.paginas = paginas;
    }

    public String getGuionista() {
        return guionista;
    }

    public void setGuionista(String guionista) {
        this.guionista = guionista;
    }

    public String getDibujante() {
        return dibujante;
    }

    public void setDibujante(String dibujante) {
        this.dibujante = dibujante;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    
}
