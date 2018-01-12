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
public class Fachada {
    private static Fachada fachada = null;
    public Mediateca mediateca = null;
    
    private Fachada() {
        mediateca = new Mediateca();
    }
    
    public Fachada getInstancia() {
        if(fachada == null)
        {
            fachada = new Fachada();
        }
        return fachada;
    }
}
