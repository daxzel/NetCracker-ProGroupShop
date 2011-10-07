package DBManager;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yra
 */
import java.sql.*;

import javax.naming.*;
import java.util.*;
import java.util.Date;
import javax.sql.DataSource;
import com.sun.jndi.cosnaming.*;
import java.sql.PreparedStatement;
import exceptions.NikNameException;
public class DBManager  {
 
     public static Connection getConnection() throws SQLException, NamingException{
      DataSource ds = (DataSource) new InitialContext().lookup("jdbc/InternetShop");
      return ds.getConnection();}

    public static void addUser(String name, String surname,String otchestvo,String nik, String password, Date born, String phone, String email, int id_role) throws SQLException, NamingException, NikNameException {
      Connection conn= getConnection();
      PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ?");
      pst.setString(1, nik);
      ResultSet rs = pst.executeQuery();
      boolean b = rs.next();
      if(b){
          throw new NikNameException();}
      pst = conn.prepareStatement("INSERT INTO \"USER\" "+"(NAME,SURNAME,OTCHESTVO,NIK,PASSWORD,BORN,PHONE,EMAIL,ID_ROLE)"+"VALUES(?,?,?,?,?,?,?,?,?)");
      pst.setString(1, name);
      pst.setString(2, surname);
      pst.setString(3, otchestvo);
      pst.setString(4, nik);
      pst.setString(5, password);
      pst.setDate(6,new java.sql.Date(born.getTime()));
      pst.setString(7, phone);
      pst.setString(8, email);
      pst.setInt(9, id_role);
      pst.execute();
      conn.commit();
      conn.close();
      
  }
}
