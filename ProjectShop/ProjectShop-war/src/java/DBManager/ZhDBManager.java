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
import java.io.*;
import java.util.*;
import java.util.Date;
import javax.sql.DataSource;
import com.sun.jndi.cosnaming.*;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import oracle.sql.BLOB;
public class ZhDBManager  {

     public static Connection getConnection() throws SQLException, NamingException{
      DataSource ds = (DataSource) new InitialContext().lookup("jdbc/InternetShop");
      return ds.getConnection();}
/*
     public static void getTablComment() throws SQLException, NamingException{
        Connection conn= getConnection();
        String sql = "SELECT * FROM OPINION";

        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.getResultSet();

       


     }
*/
    public static void addImage(int id_pr, String name, String path,String width,String height) throws SQLException, NamingException, FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
      Connection conn= getConnection();

/*
    String driver = "org.apache.derby.jdbc.ClientDriver";
    Class.forName(driver).newInstance();
    Properties props = new Properties();
    props.put("user", "USER");
    props.put("password", "0000");
    System.out.println("OK");
    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample",props);
*/

      String sql = "INSERT INTO IMAGE (ID_PRODUCT, NAME, IMAGE, WIDTH, HEIGHT )"+ "VALUES (?, ?, ?, ?, ?)";
      
      
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, id_pr);
      stmt.setString(2, name);
      stmt.setString(4, width);
      stmt.setString(5, height);
      
      
     // sql = "SELECT IMAGE FROM IMAGE WHERE ID_IMG = ?";
      //stmt = conn.prepareStatement(sql);
      //stmt.getInt(1,  )

      Blob blob = conn.createBlob();
 ///     ImageIcon li = new ImageIcon ()

 File image = new File (path);     
     // FileInputStream fis = new FileInputStream(image);

      ObjectOutputStream oos;
        oos = new ObjectOutputStream(blob.setBinaryStream(1));
        oos.writeObject(image);
        oos.close();
        stmt.setBlob(3, blob);
        //stmt.execute();
        blob.free();
       // stmt.close();
      
     //byte[] b = new byte[(int)image.length()];
      //fis.read(b);
      //stmt.setBytes(3, b);
     // stmt.setBinaryStream(3, , (int)image.length());
      stmt.executeUpdate();
      conn.commit();
      //fis.close();
      conn.close();

  }
        public static void delImage(int id_img) throws SQLException, NamingException, FileNotFoundException, IOException{
      Connection conn= getConnection();

      String sql = "DELETE FROM IMAGE WHERE ID_IMG = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, id_img);
      stmt.executeUpdate();

      conn.commit();
      conn.close();



  }

        public static void addComment (int id_pr,int id_usr, String text) throws SQLException, NamingException, FileNotFoundException, IOException {
    Connection conn = getConnection();

    String sql = "INSERT INTO OPINION (ID_PRODUCT, ID_USER, TEXT) VALUES (?, ? , ?)";
    PreparedStatement stmt = conn.prepareStatement(sql);

    stmt.setInt(1, id_pr);
    stmt.setInt(2, id_usr);
    stmt.setString(3, text);
    stmt.executeUpdate();

    conn.commit();
    conn.close();


        }

        public static void delComment(int id_op) throws SQLException, NamingException, FileNotFoundException, IOException{
      Connection conn= getConnection();

      String sql = "DELETE FROM OPINION WHERE ID_OPINION = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, id_op);
      stmt.executeUpdate();

      conn.commit();
      conn.close();



  }
       /* public static void superSelect (String tables, String atr){
            Connection conn= getConnection();

      String sql = "DELETE FROM OPINION WHERE ID_OPINION = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, id_op);
      stmt.executeUpdate();

      conn.commit();
      conn.close();
        }*/
        }
