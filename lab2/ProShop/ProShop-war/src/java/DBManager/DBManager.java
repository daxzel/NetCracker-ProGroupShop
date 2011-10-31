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
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            rs = pst.executeQuery();
            if (rs.next()) {
                usr = new User(rs);
            } else {
                throw new SQLException();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return usr;
    }

    public static boolean IsThereUser(String nik) throws SQLException, NamingException {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            rs = pst.executeQuery();
            return rs.next();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static void updateUserbyNik(User usr, String nikOld) throws SQLException, NamingException, NikNameException {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ? AND ID_USER<>?");
            pst.setString(1, usr.getNik());
            pst.setInt(2, usr.getId());
            rs = pst.executeQuery();
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

            pst.executeUpdate();
            conn.commit();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static void addUser(String name, String surname, String otchestvo, String nik, String password, java.util.Date born, String phone, String email, int id_role) throws SQLException, NamingException, NikNameException {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            rs = pst.executeQuery();
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
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public static Role findRoleByName(String name) throws SQLException, NamingException {
        Connection conn = getConnection();
        Role role = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM \"ROLE\" WHERE NAME = ?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                role = new Role(rs);
            } else {
                throw new SQLException();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return role;
    }

    public static List findUsersByRole(String roleName) throws SQLException, NamingException {
        List list = new ArrayList();
        Role role = null;
        User usr = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        role = findRoleByName(roleName);
        try {
            conn = getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE ID_ROLE = ?");
            pst.setInt(1, role.getId());
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                usr = new User(rs);
                //list.add(usr);
                list.add(i, usr);
                i = i + 1;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public static int deleteUser(String nik) throws SQLException, NamingException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        int numDelete = 0;
        try {

            conn = getConnection();
            pst = conn.prepareStatement("DELETE FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            numDelete = pst.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return numDelete;
    }

    public static void addComment(int id_pr, int id_usr, String text) throws SQLException, NamingException, FileNotFoundException, IOException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO OPINION (ID_PRODUCT, ID_USER, TEXT) VALUES (?, ? , ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_pr);
            stmt.setInt(2, id_usr);
            stmt.setString(3, text);
            stmt.executeUpdate();
            conn.commit();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        conn.close();
    }

    public static void delComment(int id_op) throws SQLException, NamingException, FileNotFoundException, IOException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM OPINION WHERE ID_OPINION = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_op);
            stmt.executeUpdate();
            conn.commit();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        conn.close();
    }

    public static List findOpinionByProduct(int id_pr) throws SQLException, NamingException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        List list = new ArrayList();
        try {
            conn = getConnection();

            Opinion opn = null;

            pst = conn.prepareStatement("SELECT * FROM OPINION WHERE ID_PRODUCT = ?");
            pst.setInt(1, id_pr);
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                opn = new Opinion(rs);
                list.add(i, opn);
                i = i + 1;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public static Product findOpinionByProductName(String name_pr) throws SQLException, NamingException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        Product prd = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement("SELECT * FROM PRODUCT WHERE NAME=?");
            pst.setString(1, name_pr);
            rs = pst.executeQuery();
            rs.next();
            prd = new Product(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDouble(5));
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return prd;
    }

    public static void addProduct(Product prd) throws SQLException, NamingException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement("INSERT INTO PRODUCT " + "(DESCRIPTION,ID_CATALOG,NAME,PRICE)" + "VALUES(?,?,?,?)");
            pst.setString(1, prd.getDescription());
            pst.setInt(2, prd.getIdCatalog());
            pst.setString(3, prd.getName());
            pst.setDouble(4, prd.getPrice());
            pst.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static void addProduct(String name, String description, String name_catalog, double price) throws SQLException, NamingException, CatalogException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Catalog ctg = findCatalogByName(name_catalog);
            pst = conn.prepareStatement("INSERT INTO PRODUCT " + "(DESCRIPTION,ID_CATALOG,NAME,PRICE)" + "VALUES(?,?,?,?)");
            pst.setString(1, description);
            pst.setInt(2, ctg.getId());
            pst.setString(3, name);
            pst.setDouble(4, price);
            pst.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static void addCatalog(String nameParent, String name) throws SQLException, NamingException, CatalogException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE NAME = ?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            Catalog ctg = null;
            if (rs.next()) {
                throw new CatalogException("Каталог с таким именем уже существует");
            }
            pst.setString(1, nameParent);
            rs = pst.executeQuery();
            if (rs.next()) {
                 ctg = new Catalog(rs.getInt(1),rs.getInt(2),rs.getString(3));
            }else{
                throw new CatalogException("Название родительского каталога введено не правильно");
            }

            if (nameParent.equals("")) {
                pst = conn.prepareStatement("INSERT INTO \"CATALOG\" " + "(NAME)" + "VALUES(?)");
                pst.setString(1, name);
                pst.execute();
            } else {
                pst = conn.prepareStatement("INSERT INTO \"CATALOG\" " + "(ID_PARENT,NAME)" + "VALUES(?,?)");
                pst.setInt(1, ctg.getId());
                pst.setString(2, name);
                pst.execute();
            }
            conn.commit();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public static void addOrder(String id_product, String nik, String kol_vo, String status) throws SQLException, NamingException, CatalogException, NikNameException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            int id_user = 0;
            pst = conn.prepareStatement("SELECT ID_USER FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            rs = pst.executeQuery();
            if (rs.next()) {
                id_user = rs.getInt(1);
            } else {
                throw new NikNameException();
            }
            int id_prd = Integer.parseInt(id_product);
            int kolichestvo = Integer.parseInt(kol_vo);
            boolean stat = Boolean.parseBoolean(status);
            pst = conn.prepareStatement("INSERT INTO \"ORDER\" " + "(ID_USER,ID_PRODUCT,STATUS,KOL_VO)" + "VALUES(?,?,?,?)");
            pst.setInt(1, id_user);
            pst.setInt(2, id_prd);
            pst.setBoolean(3, stat);
            pst.setInt(4, kolichestvo);
            pst.execute();
            conn.commit();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public static int delCatalog(String name) throws SQLException, NamingException, CatalogException {
        int value = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement("DELETE FROM \"CATALOG\" WHERE NAME = ?");
            pst.setString(1, name);
            value = pst.executeUpdate();
            if(value==0){
                throw new CatalogException("Каталог не удален");
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return value;
    }

    public static List findChildCatalog(String name) throws SQLException, NamingException {
        List list = new ArrayList();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Catalog ctg = null;
            pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE NAME = ?");
            pst.setString(1, name);
            rs = pst.executeQuery();
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
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public static List findCatalogBuPid(int id_parent) throws SQLException, NamingException, CatalogException {
        List list = new ArrayList();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Catalog cat = null;
            pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE ID_PARENT = ?");
            pst.setInt(1, id_parent);
            rs = pst.executeQuery();
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                cat = new Catalog(rs);
                list.add(i, cat);
                i = i + 1;
            }
            if (i == 0) {
                throw new CatalogException("Каталог не содержит подкаталогов");
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public static List findProductByCatalog(int id_cat) throws SQLException, NamingException {
        List list = new ArrayList();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            //Product product = null;
            Product prd = null;

            pst = conn.prepareStatement("SELECT * FROM PRODUCT WHERE ID_CATALOG = ?");
            pst.setInt(1, id_cat);
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                prd = new Product(rs);
                list.add(i, prd);
                i = i + 1;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public static Order getByIdOrder(int id) throws SQLException, NamingException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        Order obj = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement("SELECT * FROM ORDER WHERE ID_ORDER = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                obj = new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getInt(5));
            } else {
                throw new SQLException();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return obj;
    }

    public static Product getByIdProduct(int id) throws SQLException, NamingException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        Product obj = null;
        try {
            conn = getConnection();
            pst = null;
            pst = conn.prepareStatement("SELECT * FROM \"PRODUCT\" WHERE ID_PRODUCT = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                obj = new Product(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDouble(5));
            } else {
                throw new SQLException();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return obj;
    }

    public static User getByIdUser(int id) throws SQLException, NamingException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        User obj = null;
        try {
            conn = getConnection();
            pst = null;
            pst = conn.prepareStatement("SELECT * FROM USER WHERE ID_USER = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                obj = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getInt(10));
            } else {
                throw new SQLException();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return obj;
    }

    public static List findOrderByUser(int id_user, String status) throws SQLException, NamingException {
        List list = new ArrayList();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        Order ord = null;
        try {
            conn = getConnection();
            boolean stat = Boolean.parseBoolean(status);
            pst = conn.prepareStatement("SELECT * FROM \"ORDER\" WHERE ID_USER = ? AND STATUS =?");
            pst.setInt(1, id_user);
            pst.setBoolean(2, stat);
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                ord = new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getInt(5));
                list.add(i, ord);
                i = i + 1;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public static String updateOrderStatus(String id_order) throws SQLException, NamingException {
        String result = "Заказ не оформлен";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement("UPDATE \"ORDER\" SET STATUS =1  WHERE ID_ORDER = ? ");
            int id_ord = Integer.parseInt(id_order);
            boolean stat = true;
            pst.setInt(1, id_ord);
            int numUpdate = pst.executeUpdate();
            int i = 0;
            if (numUpdate == 1) {
                result = "Заказ оформлен";
            } else {
                result = "Произошла ошибка при оформлении заказа";
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }

    public static String deleteOrder(String id_order) throws SQLException, NamingException {
        int numDelete = 0;
        String result = "Заказ не удален из корзины";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            int id_ord = Integer.parseInt(id_order);
            conn = getConnection();
            pst = conn.prepareStatement("DELETE FROM \"ORDER\" WHERE ID_ORDER = ?");
            pst.setInt(1, id_ord);
            numDelete = pst.executeUpdate();
            if (numDelete == 1) {
                result = "Заказ удален из корзины";
            } else {
                result = "Произошла ошибка при удалении заказа";
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }

    public static Catalog findCatalogByName(String name) throws SQLException, NamingException, CatalogException {
        Connection conn = getConnection();
        Catalog ctg = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE NAME = ?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                ctg = new Catalog(rs.getInt(1), rs.getInt(2), rs.getString(3));
            } else {
                throw new CatalogException("Каталог с таким именем не найден");
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return ctg;
    }
}
