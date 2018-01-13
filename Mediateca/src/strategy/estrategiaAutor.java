/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import mediateca.Articulo;

/**
 *
 * @author Adrian
 */
public class estrategiaAutor implements Estrategia {
    @Override
    public void ordena(ArrayList<Articulo> articulos) {
        
        Comparator ArticulosTitulo = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Articulo a1 = (Articulo) o1;
                Articulo a2 = (Articulo) o2;

                return a1.getAutor().compareTo(a2.getAutor());
            }
        };

        //Ordenamos los objetos del array por el atributo nombre
        Collections.sort(articulos, ArticulosTitulo);
    }
}
