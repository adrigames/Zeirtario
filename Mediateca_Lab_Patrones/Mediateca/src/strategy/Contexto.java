/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import mediateca.Articulo;

/**
 *
 * @author Adrian
 */
public class Contexto {
    private Estrategia est;
    private ArrayList<Articulo> lista;

    public Contexto() {
    }

    public Estrategia getEst() {
        return est;
    }

    public void setEst(Estrategia est) {
        this.est = est;
    }

    public ArrayList<Articulo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Articulo> lista) {
        this.lista = lista;
    }
    
    public ArrayList<Articulo> ejecutaEstrategia(){
        est.ordena(lista);
        return lista;
    }
}
