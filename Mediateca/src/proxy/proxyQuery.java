/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;
import mediateca.Parametro;
import Singleton.BDSingleton;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import mediateca.*;
/**
 *
 * @author Adrian
 */
public class ProxyQuery {
    private BDSingleton conexion = BDSingleton.getInstancia();
    public ProxyQuery() {
    }
    public boolean validarSentencia(String sentencia){
        if(sentencia.contains(";")|| sentencia.contains("(") || sentencia.contains(")")){
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "La sentencia introducida contiene carácteres ilegales.\nConsulte el manual de "
                    + "uso para saber cuales son.","Advertencia",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else{
            return true;
        }
    }
    
    public boolean insertarUsuario(Usuario u){
        String sentencia = "INSERT INTO USUARIO (NOMBRE, APELLIDO, SEXO, EDAD, PASS, ADMIN, EMAIL, DNI) VALUES";
        String aux;
        if(u.isAdmin())
            aux = "TRUE";
        else
            aux = "FALSE";
        
        String sentencia_valores = "'"+
                u.getNombre()+"','"
                +u.getApellido()+"','"
                +u.getSexo()+"',"
                +u.getEdad()+",'"
                +u.getPass()+"',"
                +aux+",'"
                +u.getEmail()+"','"
                +u.getDni()+"'";
        if(validarSentencia(sentencia_valores)){
            sentencia_valores= " (" +sentencia_valores+")";
            sentencia = sentencia + sentencia_valores;
            if(conexion.abrirConexion()){
                if(conexion.ejecutarSentencia(sentencia)){
                    if(conexion.cerrarConexion());
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }
    
    public boolean insertarAudio(Disco a){
        String sentencia = "INSERT INTO ARTICULO(TITULO, AUTOR, GENERO, TIPO, RESERVADO) VALUES";
        String strArt = "'" +a.getTitulo()+ "','"+ a.getAutor()+"','"+ a.getGenero()+"','AUDIO', FALSE";
        if(!conexion.abrirConexion())
            return false;
        if(validarSentencia(strArt)){
            sentencia = sentencia + "(" +strArt +")";
            if(conexion.ejecutarSentencia(sentencia)){
                int aux = conexion.dameClave();
                sentencia = "INSERT INTO AUDIO(CLAVE, DURACION) VALUES ";
                strArt = "" + aux + "," + a.getDuracion();
                if(validarSentencia(strArt)){
                    sentencia = sentencia + "(" + strArt + ")";
                    if(conexion.ejecutarSentencia(sentencia) && conexion.cerrarConexion()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean insertarCine(Pelicula a){
        String sentencia = "INSERT INTO ARTICULO(TITULO, AUTOR, GENERO, TIPO, RESERVADO) VALUES";
        String strArt = "'" +a.getTitulo()+ "','"+ a.getAutor()+"','"+ a.getGenero()+"','CINE', FALSE";
        if(!conexion.abrirConexion())
            return false;
        if(validarSentencia(strArt)){
            sentencia = sentencia + "(" +strArt +")";
            if(conexion.ejecutarSentencia(sentencia)){
                int aux = conexion.dameClave();
                sentencia = "INSERT INTO CINE(CLAVE, DURACION, ACTORES, SINOPSIS) VALUES ";
                strArt = "" + aux + "," + a.getDuracion() + ",'"+a.getActores()+"','" +a.getSinopsis()+"'";
                if(validarSentencia(strArt)){
                    sentencia = sentencia + "(" + strArt + ")";
                    if(conexion.ejecutarSentencia(sentencia) && conexion.cerrarConexion()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean insertarComic(Comic a){
        String sentencia = "INSERT INTO ARTICULO(TITULO, AUTOR, GENERO, TIPO, RESERVADO) VALUES";
        String strArt = "'" +a.getTitulo()+ "','"+ a.getAutor()+"','"+ a.getGenero()+"','COMIC', FALSE";
        if(!conexion.abrirConexion())
            return false;
        if(validarSentencia(strArt)){
            sentencia = sentencia + "(" +strArt +")";
            if(conexion.ejecutarSentencia(sentencia)){
                int aux = conexion.dameClave();
                sentencia = "INSERT INTO COMIC(CLAVE, PAGINAS, ILUSTRADOR, SINOPSIS) VALUES ";
                strArt = "" + aux + "," + a.getPaginas() + ",'"+a.getIlustrador()+"','" +a.getSinopsis()+"'";
                if(validarSentencia(strArt)){
                    sentencia = sentencia + "(" + strArt + ")";
                    if(conexion.ejecutarSentencia(sentencia) && conexion.cerrarConexion()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean insertarLibro(Libro a){
        String sentencia = "INSERT INTO ARTICULO(TITULO, AUTOR, GENERO, TIPO, RESERVADO) VALUES";
        String strArt = "'" +a.getTitulo()+ "','"+ a.getAutor()+"','"+ a.getGenero()+"','LIBRO', FALSE";
        if(!conexion.abrirConexion())
            return false;
        if(validarSentencia(strArt)){
            sentencia = sentencia + "(" +strArt +")";
            if(conexion.ejecutarSentencia(sentencia)){
                int aux = conexion.dameClave();
                sentencia = "INSERT INTO LIBRO(CLAVE, PAGINAS, SINOPSIS) VALUES ";
                strArt = "" + aux + "," + a.getPaginas() +",'" +a.getSinopsis()+"'";
                if(validarSentencia(strArt)){
                    sentencia = sentencia + "(" + strArt + ")";
                    if(conexion.ejecutarSentencia(sentencia) && conexion.cerrarConexion()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public ArrayList<Articulo> seleccionarArticulo(ArrayList<Parametro> aParam){
        String sent = "SELECT * FROM ARTICULO";
        String cons = "";
        
        for(int i = 0; i < aParam.size(); i++){
            String n = aParam.get(i).getNombre();
            String v = aParam.get(i).getValor();
            if(n=="CLAVE"){
                if(cons==""){
                    cons = " WHERE ARTICULO.CLAVE = " +v;
                }else{
                    cons = cons + " AND ARTICULO.CLAVE = '"+v+"'";
                }
            }
            if(n=="TITULO"){
                if(cons==""){
                    cons = " WHERE ARTICULO.TITULO = '" +v+"'";
                }else{
                    cons = cons + " AND ARTICULO.TITULO '= "+v+"'";
                }
            }
            if(n=="AUTOR"){
                if(cons==""){
                    cons = " WHERE ARTICULO.AUTOR = '" +v+"'";
                }else{
                    cons = cons + " AND ARTICULO.AUTOR = '"+v+"'";
                }
            }
            if(n=="GENERO"){
                if(cons==""){
                    cons = " WHERE ARTICULO.GENERO = '" +v+"'";
                }else{
                    cons = cons + " AND ARTICULO.GENERO = '"+v+"'";
                }
            }
            if(n=="TIPO"){
                if(cons==""){
                    cons = " WHERE ARTICULO.TIPO = '" +v+"'";
                }else{
                    cons = cons + " AND ARTICULO.TIPO = '"+v+"'";
                }
            }
            if(n=="RESERVADO"){
                if(cons==""){
                    cons = " WHERE ARTICULO.RESERVADO = " +v;
                }else{
                    cons = cons + " AND ARTICULO.RESERVADO = "+v;
                }
            }
        }
        if(validarSentencia(cons)){
            if(!conexion.abrirConexion())
                return null;
            ResultSet rs = conexion.seleccionar(sent + cons);
            ArrayList<Articulo> resultado = new ArrayList<Articulo>();
            try {
                while(rs.next()){
                    resultado.add(dameArticulo(rs));
                }
                conexion.cerrarConexion();
                return resultado;
            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
            return null;
        return null;
    }
    
    public Articulo dameArticulo(ResultSet rs) throws SQLException{
        Articulo res = new Articulo(
        rs.getInt("CLAVE"),
             rs.getString("TITULO"),
             rs.getString("AUTOR"),
             rs.getString("GENERO"),
             true
        );
        return res;
    }
}
