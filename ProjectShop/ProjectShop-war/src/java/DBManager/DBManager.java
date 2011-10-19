package DBManager;

import DBClasses.Opinion;
import DBClasses.Product;
import java.util.List;
import java.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import exceptions.*;
import DBClasses.*;
import DBClasses.Role;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DBManager extends AbstractManager {

    public static User findUserByNik(String nik) throws SQLException, NamingException {
        Connection conn = getConnection();
        User usr = null;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usr = new User(rs);
            } else {
                throw new SQLException();
            }
        } finally {
            conn.close();
        }
        return usr;
    }

    public static void updateUserbyNik(User usr, String nikOld) throws SQLException, NamingException, NikNameException {
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ? AND ID_USER<>?");
        pst.setString(1, usr.getNik());
        pst.setInt(2, usr.getId());
        ResultSet rs = pst.executeQuery();
        //rs.next();
        if (rs.next()) {
            throw new NikNameException();
        }
        String str = "WHERE NIK='" + nikOld + "'";
        pst = conn.prepareStatement("UPDATE \"USER\"" + "SET NAME =?, SURNAME =?, OTCHESTVO =?,NIK=?,  PASSWORD =?, BORN=?,PHONE=?,EMAIL=?,ID_ROLE=?" + str);
        pst.setString(1, usr.getName());
        pst.setString(2, usr.getSurname());
        pst.setString(3, usr.getOtchestvo());
        pst.setString(4, usr.getNik());
        pst.setString(5, usr.getPassword());
        pst.setDate(6, new java.sql.Date(usr.getBorn().getTime()));
        pst.setString(7, usr.getPhone());
        pst.setString(8, usr.getEmail());
        pst.setInt(9, usr.getRoleId());
        //  pst.setString(9, usr.getNik());
        pst.executeUpdate();
        conn.commit();
    }

    public static void addUser(String name, String surname, String otchestvo, String nik, String password, java.util.Date born, String phone, String email, int id_role) throws SQLException, NamingException, NikNameException {
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

    public static Role findRoleByName(String name) throws SQLException, NamingException {
        Connection conn = getConnection();
        Role role = null;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"ROLE\" WHERE NAME = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                role = new Role(rs);
            } else {
                throw new SQLException();
            }
        } finally {
            conn.close();
        }
        return role;
    }

    public static List findUsersByRole(String roleName) throws SQLException, NamingException {
        List<User> list = new ArrayList();
        Connection conn = getConnection();
        Role role = null;
        User usr = null;
        role = findRoleByName(roleName);
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE ID_ROLE = ?");
            pst.setInt(1, role.getId());
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                usr = new User(rs);
                //list.add(usr);
                list.add(i, usr);
                i = i + 1;
            }
        } finally {
            conn.close();
        }
        return list;
    }

    public static int deleteUser(String nik) throws SQLException, NamingException {
        int numDelete = 0;
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM \"USER\" WHERE NIK = ?");
        pst.setString(1, nik);
        numDelete = pst.executeUpdate();
        conn.close();
        return numDelete;
    }

    public static void addComment(int id_pr, int id_usr, String text) throws SQLException, NamingException, FileNotFoundException, IOException {
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

    public static void delComment(int id_op) throws SQLException, NamingException, FileNotFoundException, IOException {
        Connection conn = getConnection();

        String sql = "DELETE FROM OPINION WHERE ID_OPINION = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id_op);
        stmt.executeUpdate();
        conn.commit();
        conn.close();
    }
    
    public static List findOpinionByProduct(int id_pr) throws SQLException, NamingException{
        List<Opinion> list = new ArrayList();
        Connection conn = getConnection();
        //Product product = null;
        Opinion opn = null;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM OPINION WHERE ID_PRODUCT = ?");
            pst.setInt(1, id_pr);
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                opn = new Opinion(rs);
                list.add(i, opn);
                i = i + 1;
            }
        } finally {
            conn.close();
        }
        return list;
    }
    public static void addProduct(Product prd) throws SQLException,NamingException
    {
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("INSERT INTO PRODUCT " + "(DESCRIPTION,ID_CATALOG,NAME,PRICE)" + "VALUES(?,?,?,?)");
        pst.setString(1,prd.getDescription());
        pst.setInt(2, prd.getIdCatalog());
        pst.setString(3,prd.getName());
        pst.setDouble(4, prd.getPrice());
        pst.execute();
    }
    public static void addProduct(String name, String description, int it_catalog, double price) throws SQLException,NamingException
    {
       Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("INSERT INTO PRODUCT " + "(DESCRIPTION,ID_CATALOG,NAME,PRICE)" + "VALUES(?,?,?,?)");
        pst.setString(1,description);
        pst.setInt(2,it_catalog);
        pst.setString(3,name);
        pst.setDouble(4, price);
        pst.execute();
    }
     public static void addCatalog(String idParent, String name) throws SQLException, NamingException, CatalogException {
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE NAME = ?");
        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            throw new CatalogException();
        }
        if (idParent.equals("")) {
            pst = conn.prepareStatement("INSERT INTO \"CATALOG\" " + "(NAME)" + "VALUES(?)");
            pst.setString(1, name);
            pst.execute();
        } else {
            pst = conn.prepareStatement("INSERT INTO \"CATALOG\" " + "(ID_PARENT,NAME)" + "VALUES(?,?)");
            pst.setInt(1, Integer.parseInt(idParent));
            pst.setString(2, name);
            pst.execute();
        }
        conn.commit();
        conn.close();
    }

    public static int delCatalog(String name) throws SQLException, NamingException {
        int value = 0;
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM \"CATALOG\" WHERE NAME = ?");
        pst.setString(1, name);
        value = pst.executeUpdate();
        conn.close();
        return value;
    }
    public static List findChildCatalog(String name) throws SQLException, NamingException {
        List<Catalog> list = new ArrayList();
        Connection conn = getConnection();
        Catalog ctg = null;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE NAME = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ctg = new Catalog(rs);
            }
            pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE ID_PARENT = ?");
            pst.setInt(1, ctg.getId());
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                ctg = new Catalog(rs);
                list.add(i, ctg);
                i = i + 1;
            }
        } finally {
            conn.close();
        }
        return list;
    }

     public static List findCatalogBuPid(int id_parent) throws SQLException, NamingException, CatalogException{
        List<Catalog> list = new ArrayList();
        Connection conn = getConnection();
        //Product product = null;
        Catalog cat = null;
        try {
            PreparedStatement pst  = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE ID_PARENT = ?");
            pst.setInt(1, id_parent);
            ResultSet rs = pst.executeQuery();
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                cat = new Catalog(rs);
                list.add(i, cat);
                i = i + 1;
            }if(i==0){
                throw new CatalogException();
            }
        }finally {
            conn.close();
        }
        return list;
    }
      public static List findProductByCatalog(int id_cat) throws SQLException, NamingException{
        List<Product> list = new ArrayList();
        Connection conn = getConnection();
        //Product product = null;
        Product prd = null;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM PRODUCT WHERE ID_CATALOG = ?");
            pst.setInt(1, id_cat);
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                prd = new Product(rs);
                list.add(i, prd);
                i = i + 1;
            }
        } finally {
            conn.close();
        }
        return list;
      }
}
