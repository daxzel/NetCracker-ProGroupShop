/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBManager;

import java.util.List;
import java.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import exceptions.NikNameException;
import DBClasses.*;

/**
 *
 * @author Yra
 */
public class AbstractManager {
    public static Connection getConnection() throws SQLException, NamingException {
        DataSource ds = (DataSource) new InitialContext().lookup("jdbc/InternetShop");
        return ds.getConnection();
    }

    public static Object getById(String table, int id) throws SQLException, NamingException {

        Connection conn = getConnection();
        Object obj = null;
        try {
            PreparedStatement pst = null;
            if (table.equals("ROLE")) {
                pst = conn.prepareStatement("SELECT * FROM \"ROLE\" WHERE ID_ROLE = ?");
            }
            if (table.equals("USER")) {
                pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE ID_USER = ?");
            }
            if (table.equals("PRODUCT")) {
                pst = conn.prepareStatement("SELECT * FROM PRODUCT WHERE ID_PRODUCT = ?");
            }
            if (table.equals("OPINION")) {
                pst = conn.prepareStatement("SELECT * FROM OPINION WHERE ID_OPINION = ?");
            }
             if (table.equals("CATALOG")) {
                pst = conn.prepareStatement("SELECT * FROM CATALOG WHERE ID_CATALOG = ?");
            }
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (table.equals("ROLE")) {
                    obj = new Role(rs);
                }
                if (table.equals("USER")) {
                    obj = new User(rs);
                }
                if (table.equals("PRODUCT")) {
                    obj = new Product(rs);
                }
                if (table.equals("OPINION")) {
                    obj = new Opinion(rs);
                }
                if (table.equals("CATALOG")) {
                    obj = new Catalog(rs);
                }

            } else {
                throw new SQLException();
            }
        } finally {
            conn.close();
        }
        return obj;
    }
public static List getFullList(String table) throws SQLException, NamingException {
        List list = new ArrayList();
        Connection conn = getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \""+table+"\"");
            ResultSet rs = pst.executeQuery();
            int i =0;
            while (rs.next()) {
                if(table.equals("USER")){
                list.add(i,new User(rs));
                }
                if(table.equals("ROLE")){
                list.add(i,new Role(rs));
                }
                if(table.equals("PRODUCT")){
                list.add(i,new Product(rs));
                }
                if(table.equals("CATALOG")){
                list.add(i,new Catalog(rs));
                }
                if(table.equals("OPINION")){
                list.add(i,new Catalog(rs));
                }
            i=i+1;
            }
        } finally {
            conn.close();
        }
        return list;
}

}
