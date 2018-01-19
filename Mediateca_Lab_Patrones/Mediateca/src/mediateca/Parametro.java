/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediateca;

/**
 *
 * @author Adrian
 */
public class Parametro {
    private String nombre;
    private String valor;

    public Parametro(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    public Parametro(){
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }
    
    
}
