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
    private final int ARTICULO = 0;
    private final int DISCO = 1;
    private final int CINE = 2;
    private final int COMIC = 3;
    private final int LIBRO = 4;
    public int tipo = ARTICULO;
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
            if(oPar.getNombre().equals("ACTORES")){
                if(s.equals("")){
                    s = "ACTORES LIKE '" + oPar.getValor()+"'";
                }else{
                    s+= " AND ACTORES LIKE '%"  + oPar.getValor()+"%'";
                }
            }
            if(oPar.getNombre().equals("TIPO")){
                String aux = oPar.getValor();
                if(aux.equals("AUDIO")){
                    tipo = DISCO;
                }
                if(aux.equals("COMIC")){
                    tipo = COMIC;
                }
                if(aux.equals("CINE")){
                    tipo = CINE;
                }
                if(aux.equals("LIBRO")){
                    tipo = LIBRO;
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
                String comp = "";
                for (int j = 0; j<p.size();j++){
                    if (p.get(j).getNombre().equals("COMPARADOR")){
                        comp = p.get(j).getValor();
                    }
                }
                if(s.equals("")){
                    s = "DURACION "+ comp +" " + oPar.getValor();
                }else{
                    s+= " AND DURACION "+ comp +" "  + oPar.getValor();
                }
            }
            if(oPar.getNombre().equals("PAGINAS")){
                String comp = "";
                for (int j = 0; j<p.size();j++){
                    if (p.get(j).getNombre().equals("COMPARADOR")){
                        comp = p.get(j).getValor();
                    }
                }
                if(s.equals("")){
                    s = "PAGINAS = "+ comp +" " + oPar.getValor();
                }else{
                    s+= " AND PAGINAS = "+ comp +" " + oPar.getValor();
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
    
    public ArrayList<Articulo> seleccionarArticulo(ArrayList<Parametro> p){
        String sent = parseaParametros(p);
        switch(tipo){
            case ARTICULO: return proxy.seleccionarArticulo(sent);
            case DISCO: return proxy.seleccionarDisco(sent);
            case CINE: return proxy.seleccionarPelicula(sent);
            case COMIC: return proxy.seleccionarComic(sent);
            case LIBRO: return proxy.seleccionarLibro(sent);
        }
        return null;
    }
    
    
}


