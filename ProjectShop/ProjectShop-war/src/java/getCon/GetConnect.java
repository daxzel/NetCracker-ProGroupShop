package getCon;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yra
 */
import java.sql.*;
import oracle.jdbc.pool.*;
import javax.naming.*;
import java.util.*;

import javax.sql.DataSource;
import com.sun.jndi.cosnaming.*;
public class GetConnect  {
    static private String user= "USER";
    static private String password = "0000";
    static private String drivType = "thin";
    static private String serverName = "localhost";
    static private String serviceName = "XE";
    static private int port = 1521;
    /*public static void main(String args[])throws SQLException, NamingException {
        Properties prop = new Properties( );
     //   prop.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.cosnaming.CNCtxFactory");
    //    prop.put(Context.PROVIDER_URL, "http://localhost:34989");
        DataSource ds = (DataSource) new InitialContext().lookup("jdbc/InternetShop");
        
        Connection conn = ds.getConnection( );
//...
        conn.close( );

    }*/
    public static DataSource getDataSource() throws SQLException, NamingException{
      DataSource ds = (DataSource) new InitialContext().lookup("jdbc/InternetShop");
      return ds;
    }
}
