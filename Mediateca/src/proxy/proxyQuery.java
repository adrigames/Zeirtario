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
    
    
    
    //METODOS PUBLICOS
    
    //Region insertar
    
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
    
    public boolean reservarArticulo(int id_articulo, int id_usuario){
        
        ArrayList<Usuario> obs = observandoArticulo(id_articulo);
        
        for(int i = 0; i<obs.size(); i++){
        notificarUsuario("alquiler", obs.get(i).getId(), id_articulo);
        }
        
        String sent = "INSERT INTO TABLE RESERVAS(ID_ARTICULO, ID_USUARIO) VALUES ("+id_articulo+","+id_usuario+")";
        if(conexion.abrirConexion()&&conexion.ejecutarSentencia(sent) && conexion.cerrarConexion()){
            return true;
        }
        return false;
    }
    
    public boolean observarArticulo(int id_articulo, int id_usuario){
        String sent = "INSERT INTO TABLE OBSERVACIONES(ID_ARTICULO, ID_USUARIO) VALUES ("+id_articulo+","+id_usuario+")";
        if(conexion.abrirConexion()&&conexion.ejecutarSentencia(sent) && conexion.cerrarConexion()){
            return true;
        }
        return false;
    }
    
    // REGION DELETES
    
    public boolean bajaUsuario(int id_usuario){
        String s = "DELETE FROM USUARIO WHERE CLAVE = " + id_usuario;
        if(conexion.abrirConexion()&&conexion.ejecutarSentencia(s) && conexion.cerrarConexion()){
            return true;
        }
        return false;
    }
    
    public boolean bajaArticulo(int id_articulo){
        ArrayList<Usuario> obs = observandoArticulo(id_articulo);
        
        for(int i = 0; i<obs.size(); i++){
        notificarUsuario("alquiler", obs.get(i).getId(), id_articulo);
        }
        
        String s = "DELETE FROM ARTICULO WHERE CLAVE = " +id_articulo;
        if(conexion.abrirConexion()&&conexion.ejecutarSentencia(s) && conexion.cerrarConexion()){
            return true;
        }
        return false;
    }
    
    public boolean devolverArticulo(int id_articulo){
        ArrayList<Usuario> obs = observandoArticulo(id_articulo);
        
        for(int i = 0; i<obs.size(); i++){
        notificarUsuario("alquiler", obs.get(i).getId(), id_articulo);
        }
        
        String s = "DELETE FROM RESERVAS WHERE CLAVE = " +id_articulo;
        if(conexion.abrirConexion()&&conexion.ejecutarSentencia(s) && conexion.cerrarConexion()){
            return true;
        }
        return false;
    }
    
    //REGION UPDATE
    
    public boolean hacerAdmin(String dni){
        boolean respuesta = false;
        //modificar ese usuario para que sea admin
        return respuesta;
    }
    
    //Region seleccionar
    
    public ArrayList<Disco> seleccionarDisco(String parametros){
        String consulta = "SELECT * FROM ARTICULO INNER JOIN AUDIO ON ARTICULO.CLAVE = AUDIO.CLAVE ";
        if(parametros!=""){
            consulta += "AND ";
        }
        consulta += parametros;
        if(validarSentencia(parametros)){
            if(!conexion.abrirConexion())
                return null;
            ResultSet rs = conexion.seleccionar(consulta);
            ArrayList<Disco> resultado = new ArrayList<Disco>();
            try {
                while(rs.next()){
                    resultado.add(dameDisco(rs));
                }
                conexion.cerrarConexion();
                return resultado;
            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public ArrayList<Libro> seleccionarLibro(String parametros){
        String consulta = "SELECT * FROM ARTICULO INNER JOIN LIBRO ON ARTICULO.CLAVE = LIBRO.CLAVE ";
        if(parametros!=""){
            consulta += "AND ";
        }
        consulta += parametros;
        if(validarSentencia(parametros)){
            if(!conexion.abrirConexion())
                return null;
            ResultSet rs = conexion.seleccionar(consulta);
            ArrayList<Libro> resultado = new ArrayList<Libro>();
            try {
                while(rs.next()){
                    resultado.add(dameLibro(rs));
                }
                conexion.cerrarConexion();
                return resultado;
            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public ArrayList<Comic> seleccionarComic(String parametros){
        String consulta = "SELECT * FROM ARTICULO INNER JOIN COMIC ON ARTICULO.CLAVE = COMIC.CLAVE ";
        if(parametros!=""){
            consulta += "AND ";
        }
        consulta += parametros;
        if(validarSentencia(parametros)){
            if(!conexion.abrirConexion())
                return null;
            ResultSet rs = conexion.seleccionar(consulta);
            ArrayList<Comic> resultado = new ArrayList<Comic>();
            try {
                while(rs.next()){
                    resultado.add(dameComic(rs));
                }
                conexion.cerrarConexion();
                return resultado;
            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public ArrayList<Pelicula> seleccionarPelicula(String parametros){
        String consulta = "SELECT * FROM ARTICULO INNER JOIN CINE ON ARTICULO.CLAVE = CINE.CLAVE ";
        if(parametros!=""){
            consulta += "AND ";
        }
        consulta += parametros;
        if(validarSentencia(parametros)){
            if(!conexion.abrirConexion())
                return null;
            ResultSet rs = conexion.seleccionar(consulta);
            ArrayList<Pelicula> resultado = new ArrayList<Pelicula>();
            try {
                while(rs.next()){
                    resultado.add(damePelicula(rs));
                }
                conexion.cerrarConexion();
                return resultado;
            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public ArrayList<Articulo> seleccionarArticulo(String cons){
        String sent = "SELECT * FROM ARTICULO ";
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
    
    public Articulo verReserva(int id_usuario){
        String sent = "SELECT * FROM RESERVAS WHERE ID_USUARIO = "+id_usuario;
        
            if(!conexion.abrirConexion())
                return null;
            ResultSet rs = conexion.seleccionar(sent);
            try {
                if(rs.next()){
                     return dameArticulo(rs);
                }
                conexion.cerrarConexion();

            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;
    }
    
    public boolean estaReservado(int id_articulo){
        String sent = "SELECT * FROM RESERVAS WHERE ID_ARTICULO = " +id_articulo;
        if(!conexion.abrirConexion())
                return false;
            ResultSet rs = conexion.seleccionar(sent);
            try {
                if(rs.next()){
                     return true;
                }
                conexion.cerrarConexion();

            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
    }
    
    public ArrayList<String> verAvisos(int id_usuario){
        String sent = "SELECT * FROM AVISOS WHERE ID_USUARIO = "+id_usuario;
        
            if(!conexion.abrirConexion())
                return null;
            ResultSet rs = conexion.seleccionar(sent);
            try {
                ArrayList<String> res = new ArrayList<String>();                
                while(rs.next()){
                     res.add(dameAviso(rs));
                }
                conexion.cerrarConexion();
                return res;
            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
    }
    
    public Usuario cargaUsuario (String dni){
        if(validarSentencia(dni)){
            String sent = "SELECT * FROM USUARIO WHERE DNI = '" + dni + "'";
            if(conexion.abrirConexion()){
                try {
                    ResultSet rs = conexion.seleccionar(sent);
                    Usuario res = null;
                    if(rs.next())
                        res = dameUsuario(rs);
                    conexion.cerrarConexion();
                    return res;
                } catch (SQLException ex) {
                    Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    public ArrayList<Usuario> obtenerUsuariosNoAdmin(){
        //devolverá una lista con los usuarios no admin
        String sent = "SELECT * FROM USUARIO WHERE ADMIN = FALSE";
        if(conexion.abrirConexion()){
            try {
                ArrayList<Usuario> u= new ArrayList<Usuario>();
                ResultSet rs = conexion.seleccionar(sent);
                while(rs.next()){
                    Usuario aux = dameUsuario(rs);
                    u.add(aux);
                }
                return u;
            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public ArrayList<Usuario> observandoArticulo(int id_articulo){
        String s = "SELECT * FROM OBSERVACIONES WHERE ID_ARTICULO = " +id_articulo;
        if(conexion.abrirConexion()){
            try {
                ArrayList<Usuario> u= new ArrayList<Usuario>();
                ResultSet rs = conexion.seleccionar(s);
                while(rs.next()){
                    Usuario aux = dameUsuario(rs);
                    u.add(aux);
                }
                return u;
            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public Articulo cargarArticulo(int id){
        String sent = "SELECT * FROM ARTICULOS WHERE CLAVE = "+id;
        
            if(!conexion.abrirConexion())
                return null;
            ResultSet rs = conexion.seleccionar(sent);
            try {
                if(rs.next()){
                     return dameArticulo(rs);
                }
                conexion.cerrarConexion();

            } catch (SQLException ex) {
                Logger.getLogger(ProxyQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
    //METODOS PRIVADOS
    
    private String dameAviso(ResultSet rs) throws SQLException{
        return rs.getString("AVISO");
    }    
    
    private Articulo dameArticulo(ResultSet rs) throws SQLException{
        Articulo res = new Articulo(
        rs.getInt("CLAVE"),
             rs.getString("TITULO"),
             rs.getString("AUTOR"),
             rs.getString("GENERO"),
             rs.getBoolean("RESERVADO")
        );
        return res;
    }
    
    private Usuario dameUsuario(ResultSet rs) throws SQLException{
        Usuario u = new Usuario();
        u.setId(rs.getInt("CLAVE"));
        u.setNombre(rs.getString("NOMBRE"));
        u.setApellido(rs.getString("APELLIDO"));
        u.setDni(rs.getString("DNI"));
        u.setSexo(rs.getString("SEXO"));
        u.setEdad(rs.getInt("EDAD"));
        u.setAdmin(rs.getBoolean("ADMIN"));
        u.setPass(rs.getString("PASS"));
        return u;
    }
     
    private Disco dameDisco(ResultSet rs) throws SQLException{
        FactoriaArticulos f = new FactoriaArticulos();
        Disco d = (Disco) f.createArticulo("disco",
                rs.getString("TITULO"),
                rs.getString("AUTOR"),
                rs.getString("GENERO"),
                rs.getBoolean("RESERVADO"),
                rs.getInt("DURACION"),
                "","");
        d.setId(rs.getInt("CLAVE"));
        return d;
    }
    
    private Libro dameLibro(ResultSet rs) throws SQLException{
        FactoriaArticulos f = new FactoriaArticulos();
        Libro d = (Libro) f.createArticulo("libro",
                rs.getString("TITULO"),
                rs.getString("AUTOR"),
                rs.getString("GENERO"),
                rs.getBoolean("RESERVADO"),
                rs.getInt("PAGINAS"),
                rs.getString("SINOPSIS"),"");
        d.setId(rs.getInt("CLAVE"));
        return d;
    }
    
    private Comic dameComic(ResultSet rs) throws SQLException{
        FactoriaArticulos f = new FactoriaArticulos();
        Comic d = (Comic) f.createArticulo("comic",
                rs.getString("TITULO"),
                rs.getString("AUTOR"),
                rs.getString("GENERO"),
                rs.getBoolean("RESERVADO"),
                rs.getInt("PAGINAS"),
                rs.getString("SINOPSIS"),
                rs.getString("ILUSTRADOR"));
        d.setId(rs.getInt("CLAVE"));
        return d;
    }
    
    private Pelicula damePelicula(ResultSet rs) throws SQLException{
        FactoriaArticulos f = new FactoriaArticulos();
        Pelicula d = (Pelicula) f.createArticulo("pelicula",
                rs.getString("TITULO"),
                rs.getString("AUTOR"),
                rs.getString("GENERO"),
                rs.getBoolean("RESERVADO"),
                rs.getInt("DURACION"),
                rs.getString("SINOPSIS"),
                rs.getString("ACTORES"));
        d.setId(rs.getInt("CLAVE"));
        return d;
    }
    
    private boolean notificarUsuario(String notificacion, int id_usuario, int id_articulo){
        String s = "INSERT INTO AVISOS(ID_USUARIO, AVISOS) VALUES (" + id_usuario +",";
        Articulo a = cargarArticulo(id_articulo);
        if (notificacion.equals("baja")){
            s+="' SE HA DADO DE BAJA EL ARTICULO " + a.getTitulo() + "')";
        }else if (notificacion.equals("alquiler")){
            s+="' SE HA ALQUILADO EL ARTICULO " + a.getTitulo() + "')";
        }else if (notificacion.equals("devolucion")){
            s+="' SE HA DEVUELTO EL ARTICULO " + a.getTitulo() + "')";
        }
        if(conexion.abrirConexion()&&conexion.ejecutarSentencia(s) && conexion.cerrarConexion()){
            return true;
        }
        return false;
    }
    
    private boolean validarSentencia(String sentencia){
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
    
    
    
}
