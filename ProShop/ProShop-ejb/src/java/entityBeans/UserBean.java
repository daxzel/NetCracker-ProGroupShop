/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
//import java.util.Date;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import OtherBean.Helper;

/**
 *
 * @author Yra
 */
public class UserBean implements EntityBean {

    private EntityContext entityContext;
    private DataSource dataSource;
    private long id_user;
    private String name;
    private String surname;
    private String otchestvo;
    private String nik;
    private String password;
    private Date born;
    private String phone;
    private String email;
    private long id_role;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise beans, Web services)
    // TODO Add business methods
    // TODO Add create methods
    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext ctx) {
        this.entityContext = ctx;
        try {
            javax.naming.Context context = new javax.naming.InitialContext();
            try {
                dataSource = (DataSource) context.lookup("jdbc/InternetShop");
            } catch (Exception e) {
                throw new EJBException("Проблема с подключением к базе");
            }
        } catch (Exception e) {
            throw new EJBException("Проблема с подключением к базе");
        }
    }

    public void unsetEntityContext() throws EJBException, RemoteException {
        this.entityContext = null;
    }

    /**
     * @see javax.ejb.EntityBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() throws RemoveException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("DELETE FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            if (pst.executeUpdate() < 1) {
                throw new RemoveException("Ошибка удаления");
            }
        } catch (SQLException e) {
            throw new EJBException("Ошибка DELETE");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        id_user = Long.parseLong(entityContext.getPrimaryKey().toString());
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE ID_USER = ?");
            pst.setLong(1, id_user);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Не найдена указанная запись");
            }
            id_user = rs.getLong(1);
            name = rs.getString(2);
            surname = rs.getString(3);
            otchestvo = rs.getString(4);
            nik = rs.getString(5);
            password = rs.getString(6);
            born = rs.getDate(7);
            phone = rs.getString(8);
            email = rs.getString(9);
            id_role = rs.getLong(10);
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("UPDATE \"USER\"" + "SET NAME =?, SURNAME =?, OTCHESTVO =?,NIK=?,  PASSWORD =?, BORN=?,PHONE=?,EMAIL=?,ID_ROLE=? WHERE ID_USER=?");

            pst.setString(1, name);
            pst.setString(2, surname);
            pst.setString(3, otchestvo);
            pst.setString(4, nik);
            pst.setString(5, password);
            pst.setDate(6, born);
            pst.setString(7, phone);
            pst.setString(8, email);
            pst.setLong(9, id_role);
            pst.setLong(10, id_user);
            if (pst.executeUpdate() < 1) {
                throw new NoSuchEntityException("Не найдена запись");
            }
        } catch (SQLException e) {
            throw new EJBException("Ошибка UPDATE");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    // </editor-fold>
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long id_user) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE ID_USER = ?");
            //  long l =  (long) id_user;
            id_user.longValue();
            pst.setLong(1, id_user.longValue());
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return id_user;
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.Long ejbFindByNik(String nik) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("SELECT ID_USER FROM \"USER\" WHERE NIK = ?");
            pst.setString(1, nik);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return new Long(rs.getLong(1));
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public Collection ejbFindByRole(java.lang.Long id_role) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"USER\" WHERE ID_ROLE = ?");
            pst.setLong(1, id_role.longValue());
            rs = pst.executeQuery();
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_user = resultSet.getLong(1);
                keys.addElement(new Long(id_user));
            }
            return keys;
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public Collection ejbFindAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"USER\"");
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_user = resultSet.getLong(1);
                keys.addElement(new Long(id_user));
            }
            return keys;
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.Long ejbCreate(String name, String surname, String otchestvo, String nik, String password, java.sql.Date born, String phone, String email, java.lang.Long id_role) throws CreateException {
        try {
            ejbFindByNik(nik);
            throw new DuplicateKeyException("Пользователь с таким ником уже существует");
        } catch (ObjectNotFoundException ex) {
        }
        this.name = name;
        this.surname = surname;
        this.otchestvo = otchestvo;
        this.nik = nik;
        this.password = password;
        this.born = born;
        this.phone = phone;
        this.email = email;
        this.id_role = id_role.longValue();
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"USER\" " + "(NAME,SURNAME,OTCHESTVO,NIK,PASSWORD,BORN,PHONE,EMAIL,ID_ROLE)" + "VALUES(?,?,?,?,?,?,?,?,?) RETURNING ID_USER INTO ?;END;");
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.setString(3, otchestvo);
            pst.setString(4, nik);
            pst.setString(5, password);
            pst.setDate(6, born);
            pst.setString(7, phone);
            pst.setString(8, email);
            pst.setLong(9, id_role.longValue());
          //  pst.registerOutParameter("ID_USER_ID", Types.INTEGER );
            pst.registerOutParameter(10, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            
            id_user = pst.getLong(10);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  finally {

            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
            return new Long(id_user);
        }
    }

    public void ejbPostCreate(String name, String surname, String otchestvo, String nik, String password, java.sql.Date born, String phone, String email, java.lang.Long id_role) throws CreateException {
    }

    public long getId() {
        return id_user;
    }

    public void setId(java.lang.Long nid_user) {
        id_user = nid_user.longValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String nname) {
        name = nname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String nsurname) {
        surname = nsurname;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String notchestvo) {
        otchestvo = notchestvo;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nnik) {
        nik = nnik;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String npassword) {
        password = npassword;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date nborn) {
        born = nborn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String nphone) {
        phone = nphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String nemail) {
        email = nemail;
    }

    public long getRoleId() {
        return id_role;
    }

    public void setRoleId(java.lang.Long nid_role) {
        id_role = nid_role.longValue();
    }
}
