/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediateca;
import mediateca.FactoriaArticulos;
import mediateca.Usuario;
import proxy.ProxyQuery;
/**
 *
 * @author Ignacio
 */
public class Mediateca {
    //factoria
    //proxy
    //
    public FactoriaArticulos factoria = null;
    public ProxyQuery proxyDB = null;
    public Usuario usuario = null;
   

    public Mediateca() {
        this.proxyDB = new ProxyQuery();
        this.factoria = new FactoriaArticulos(proxyDB);
    }
    
    public void desconectarUsuario(){
        usuario = null;
    }
    
}
