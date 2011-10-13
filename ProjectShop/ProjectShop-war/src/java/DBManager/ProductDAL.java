/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBManager;

import javax.naming.*;
import java.sql.PreparedStatement;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class ProductDAL {

    public static ResultSet getAllProducts() throws SQLException,NamingException
    {
        Connection conn = DBManager.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM IMAGE");

        return pst.executeQuery();
    }

    public static void addProduct(String name, String description, int it_catalog, double price) throws SQLException,NamingException
    {
        Connection conn = DBManager.getConnection();


        PreparedStatement pst = conn.prepareStatement("INSERT INTO PRODUCT " + "(NAME,DESCRIPTION,ID_CATALOG,PRICE)" + "VALUES(?,?,?,?)");
        pst.setString(1, name);

        pst.setString(2, description);
        pst.setInt(3, it_catalog);
        pst.setDouble(4, price);
        pst.execute();
    }

}
