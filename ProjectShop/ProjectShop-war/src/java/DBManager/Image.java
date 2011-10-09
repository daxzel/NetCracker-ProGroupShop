package DBManager;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yra
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

import javax.naming.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.Date;
import javax.sql.DataSource;
import com.sun.jndi.cosnaming.*;
import java.sql.PreparedStatement;
import exceptions.NikNameException;
public class Image  {

     public static Connection getConnection() throws SQLException, NamingException{
      DataSource ds = (DataSource) new InitialContext().lookup("jdbc/InternetShop");
      return ds.getConnection();}

    public static void addImage(int id_pr, String name, String path,String width,String height) throws SQLException, NamingException, FileNotFoundException, IOException{
      Connection conn= getConnection();
      
      String sql = "INSERT INTO image (ID_PRODUCT, NAME, IMAGE, WIDTH, HEIGHT ) VALUES (?, ?, ?, ?, ?)";
      
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, id_pr);
      stmt.setString(2, name);

      File image = new File ("path");
      FileInputStream fis = new FileInputStream(image);
      
      stmt.setBinaryStream(3, fis, (int)image.length());
      stmt.setString(4, width);
      stmt.setString(5, height);
      stmt.execute();

      conn.commit();
      fis.close();
      conn.close();

  }
        public static void delImage(int id_img) throws SQLException, NamingException, FileNotFoundException, IOException{
      Connection conn= getConnection();

      String sql = "DELETE FROM IMAGE WHERE ID_IMG = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, id_img);

      conn.commit();
      conn.close();



  }
}