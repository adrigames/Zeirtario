/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;
import Singleton.BDSingleton;
/**
 *
 * @author Adrian
 */
public class proxyQuery {
    private static BDSingleton conexion;
    public proxyQuery() {
    }
    private static boolean insertar(String sentencia){
        if(sentencia.contains(";")){
            return false;
        }
        else{
            return conexion.insertarOBorrar(sentencia);
        }
    }
}
