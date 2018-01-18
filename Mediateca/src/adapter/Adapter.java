/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.util.ArrayList;
import mediateca.*;
import proxy.ProxyQuery;

/**
 *
 * @author Adrian
 */
public class Adapter {
    ProxyQuery proxy = new ProxyQuery();
    public Adapter() {
    }

    private String parseaParametros(ArrayList<Parametro> p){
        String s = "";
        for (int i = 0; i < p.size(); i++){
            Parametro oPar = p.get(i);
            if(oPar.getNombre().equals("CLAVE")){
                if(s.equals("")){
                    s = "CLAVE = " + oPar.getValor();
                }else{
                    s+= " AND CLAVE = "  + oPar.getValor();
                }
            }
            if(oPar.getNombre().equals("TITULO")){
                if(s.equals("")){
                    s = "TITULO = '" + oPar.getValor()+"'";
                }else{
                    s+= " AND TITULO = '"  + oPar.getValor()+"'";
                }
            }
            if(oPar.getNombre().equals("AUTOR")){
                if(s.equals("")){
                    s = "AUTOR = '" + oPar.getValor()+"'";
                }else{
                    s+= " AND AUTOR = '"  + oPar.getValor()+"'";
                }
            }
            if(oPar.getNombre().equals("GENERO")){
                if(s.equals("")){
                    s = "GENERO = '" + oPar.getValor()+"'";
                }else{
                    s+= " AND GENERO = '"  + oPar.getValor()+"'";
                }
            }
            if(oPar.getNombre().equals("TIPO")){
                if(s.equals("")){
                    s = "TIPO = '" + oPar.getValor()+"'";
                }else{
                    s+= " AND TIPO = '"  + oPar.getValor()+"'";
                }
            }
            if(oPar.getNombre().equals("RESERVADO")){
                if(s.equals("")){
                    s = "RESERVADO = " + oPar.getValor();
                }else{
                    s+= " AND RESERVADO = "  + oPar.getValor();
                }
            }
            if(oPar.getNombre().equals("DURACION")){
                if(s.equals("")){
                    s = "DURACION = " + oPar.getValor();
                }else{
                    s+= " AND DURACION = "  + oPar.getValor();
                }
            }
            if(oPar.getNombre().equals("PAGINAS")){
                if(s.equals("")){
                    s = "PAGINAS = " + oPar.getValor();
                }else{
                    s+= " AND PAGINAS = "  + oPar.getValor();
                }
            }
            if(oPar.getNombre().equals("ILUSTRADOR")){
                if(s.equals("")){
                    s = "ILUSTRADOR = '" + oPar.getValor()+"'";
                }else{
                    s+= " AND ILUSTRADOR = '"  + oPar.getValor()+"'";
                }
            }  
        }
        return s;
    }
    
    public ArrayList<Disco> seleccionarDisco(ArrayList<Parametro> p){
        String sent = parseaParametros(p);
        return proxy.seleccionarDisco(sent);
    }
}


