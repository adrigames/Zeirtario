/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import mediateca.*;

/**
 *
 * @author Adrian
 */
public interface Estrategia {
    public void ordena(ArrayList<Articulo> articulos);
}
