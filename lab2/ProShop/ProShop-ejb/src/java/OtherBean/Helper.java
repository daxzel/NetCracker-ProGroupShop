/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OtherBean;

import java.sql.*;
import javax.naming.NamingException;
import javax.sql.*;

/**
 *
 * @author Yra
 */
public class Helper {

    private static DataSource dataSource = null;

    public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs) throws SQLException {
        if (rs != null) {
            pst.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement pst) throws SQLException {
        if (pst != null) {
            pst.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static Connection getConnection() throws SQLException, NamingException {
       // if (dataSource == null) {
              javax.naming.Context context = new javax.naming.InitialContext();
              DataSource dataSource = (DataSource) context.lookup("jdbc/InternetShop");
       // }
        return dataSource.getConnection();

    }

    public static Object lookupHome(String name, java.lang.Class clas) throws NamingException {
        return javax.rmi.PortableRemoteObject.narrow(lookupName(name), clas);
    }

    public static Object lookupName(String name) throws NamingException {
        javax.naming.Context namingContext = new javax.naming.InitialContext();
        return namingContext.lookup(name);
    }
    /*public static void main(String args){

    }*/
}
