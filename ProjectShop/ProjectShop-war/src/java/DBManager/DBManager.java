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
import java.sql.PreparedStatement;
import exceptions.NikNameException;
import DBClasses.User;

public class DBManager {

    private static Connection _conn;

    public static Connection getConnection() throws SQLException, NamingException {
        if (_conn !=null)
        {
            return _conn;
        }
        else
        {
            DataSource ds = (DataSource) new InitialContext().lookup("jdbc/InternetShop");
            _conn =  ds.getConnection();
            return _conn;
        }
    }

    public DBManager()
    {
    }

    public static User findUserByNik(String nik) throws SQLException, NamingException {
        Connection conn = getConnection();
        User usr = null;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usr = new User(rs);
            }
        } finally {
            conn.close();
        }
        return usr;
    }
    public static void updateUserbyNik(User usr) throws SQLException, NamingException{
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("UPDATE \"USER\" SET NAME =?, SURNAME =?, OTCHESTVO =?, NIK =?, PASSWORD =?, BORN=?,PHONE=?,EMAIL=?,ID_ROLE");
        pst.setString(1, usr.getName());
        pst.setString(2, usr.getSurname());
        pst.setString(3, usr.getOtchestvo());
        pst.setString(4, usr.getNik());
        pst.setString(5, usr.getPassword());
        pst.setDate(6, new java.sql.Date(usr.getBorn().getTime()));
        pst.setString(7, usr.getPhone());
        pst.setString(8, usr.getEmail());
        pst.setInt(9,usr.getId_role());
        pst.execute();
        conn.commit();
    }
    public static void addUser(String name, String surname, String otchestvo, String nik, String password, Date born, String phone, String email, int id_role) throws SQLException, NamingException, NikNameException {
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ?");
        pst.setString(1, nik);
        ResultSet rs = pst.executeQuery();
        //rs.next();
        if (rs.next()) {
            throw new NikNameException();
        }
        pst = conn.prepareStatement("INSERT INTO \"USER\" " + "(NAME,SURNAME,OTCHESTVO,NIK,PASSWORD,BORN,PHONE,EMAIL,ID_ROLE)" + "VALUES(?,?,?,?,?,?,?,?,?)");
        pst.setString(1, name);
        pst.setString(2, surname);
        pst.setString(3, otchestvo);
        pst.setString(4, nik);
        pst.setString(5, password);
        pst.setDate(6, new java.sql.Date(born.getTime()));
        pst.setString(7, phone);
        pst.setString(8, email);
        pst.setInt(9, id_role);
        pst.execute();
        conn.commit();
        conn.close();
    }

}
