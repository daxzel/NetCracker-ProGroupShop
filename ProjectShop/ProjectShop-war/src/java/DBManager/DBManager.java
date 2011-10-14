package DBManager;
import java.util.List;
import java.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import exceptions.NikNameException;
import DBClasses.User;
import DBClasses.Role;

public class DBManager extends AbstractManager {

    /*public static Connection getConnection() throws SQLException, NamingException {
        DataSource ds = (DataSource) new InitialContext().lookup("jdbc/InternetShop");
        return ds.getConnection();
    }*/

    public static User findUserByNik(String nik) throws SQLException, NamingException {
        Connection conn = getConnection();
        User usr = null;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usr = new User(rs);
            }else{
                throw new SQLException();
            }
        } finally {
            conn.close();
        }
        return usr;
    }
    public static void updateUserbyNik(User usr,String nikOld) throws SQLException, NamingException, NikNameException{
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ? AND ID_USER=?");
        pst.setString(1, usr.getNik());
        pst.setInt(2, usr.getId());
        ResultSet rs = pst.executeQuery();
        //rs.next();
        if (rs.next()) {
            throw new NikNameException();
        }
        String str = "WHERE NIK='"+nikOld+"'";
        pst = conn.prepareStatement("UPDATE \"USER\"" + "SET NAME =?, SURNAME =?, OTCHESTVO =?,NIK=?,  PASSWORD =?, BORN=?,PHONE=?,EMAIL=?,ID_ROLE=?" + str);
        pst.setString(1, usr.getName());
        pst.setString(2, usr.getSurname());
        pst.setString(3, usr.getOtchestvo());
        pst.setString(4, usr.getNik());
        pst.setString(5, usr.getPassword());
        pst.setDate(6, new java.sql.Date(usr.getBorn().getTime()));
        pst.setString(7, usr.getPhone());
        pst.setString(8, usr.getEmail());
        pst.setInt(9,usr.getRoleId());
      //  pst.setString(9, usr.getNik());
        pst.executeUpdate();
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
    /*public static Role findRoleById(int id_role) throws SQLException,NamingException{
         Connection conn = getConnection();
         Role role = null;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"ROLE\" WHERE ID_ROLE = ?");
            pst.setInt(1, id_role);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                role = new Role(rs);
            }else{
                throw new SQLException();
            }
        } finally {
            conn.close();
        }
        return role;
    }*/
    public static Role findRoleByName(String name) throws SQLException,NamingException{
         Connection conn = getConnection();
         Role role = null;
         try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"ROLE\" WHERE NAME = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                role = new Role(rs);
            }else{
                throw new SQLException();
            }
        } finally {
            conn.close();
        }
        return role;
    }
    public static List findUsersByRole(String roleName) throws SQLException, NamingException{
        List<User> list = new ArrayList();
        Connection conn = getConnection();
        Role role = null;
        User usr = null;
        role = findRoleByName(roleName);
         try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE ID_ROLE = ?");
            pst.setInt(1, role.getId_role());
            ResultSet rs = pst.executeQuery();
            int i =0;
            while (rs.next()) {
              usr= new User(rs);
              //list.add(usr);
              list.add(i, usr);
              i=i+1;
            }
        } finally {
            conn.close();
        }
        return list;
    }
    public static int deleteUser(String nik) throws SQLException, NamingException{
        int numDelete=0;
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM \"USER\" WHERE NIK = ?");
        pst.setString(1, nik);
        numDelete = pst.executeUpdate();
        conn.close();
        return numDelete;
    }
    public static List getFullListUsers() throws SQLException, NamingException{
        List<User> list = new ArrayList();
        Connection conn = getConnection();
        User usr = null;
         try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\"");
            ResultSet rs = pst.executeQuery();
            int i =0;
            while (rs.next()) {
              usr= new User(rs);
              //list.add(usr);
              list.add(i, usr);
              i=i+1;
            }
        } finally {
            conn.close();
        }
        return list;
    }
    public static List getFullListRole() throws SQLException, NamingException{
        List<Role> list = new ArrayList();
        Connection conn = getConnection();
        Role role = null;
         try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"ROLE\"");
            ResultSet rs = pst.executeQuery();
            int i =0;
            while (rs.next()) {
              role= new Role(rs);
              //list.add(usr);
              list.add(i, role);
              i=i+1;
            }
        } finally {
            conn.close();
        }
        return list;
    }
}
