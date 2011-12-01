/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import helpers.EJBHelper;
import java.sql.*;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.sql.*;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import moreTools.HistoryMessage;
import java.sql.Date;

/**
 *
 * @author Yra
 */
public class CatalogBean implements EntityBean {

    private EntityContext entityContext;
    private Connection conn;
    private long id_catalog;
    private long id_parent;
    private String name;
    private long objId;
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
            //  javax.naming.Context context = new javax.naming.InitialContext();
            try {
                //    conn = Helper.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
                throw new EJBException("Проблема с подключением к базе");
            }
        } catch (Exception e) {
            throw new EJBException("Проблема с подключением к базе");
        }
    }

    public void unsetEntityContext() {
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
    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void ejbRemove() throws RemoveException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("DELETE FROM \"CATALOG\" WHERE ID_CATALOG = ?");
            pst.setLong(1, id_catalog);
            if (pst.executeUpdate() < 1) {
                throw new RemoveException("Ошибка удаления");
            }
        } catch (NamingException ex) {
            throw new EJBException("Ошибка при удалении");
        } catch (SQLException ex) {
            throw new EJBException("Ошибка при удалении");
            // ex.printStackTrace();
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        id_catalog = Long.parseLong(entityContext.getPrimaryKey().toString());
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE ID_CATALOG = ?");
            pst.setLong(1, id_catalog);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Не найдена указанная запись");
            }
            id_catalog = rs.getLong(1);
            id_parent = rs.getLong(2);
            name = rs.getString(3);

        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
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
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("UPDATE \"CATALOG\"" + "SET ID_PARENT=?, NAME =? WHERE ID_CATALOG=?");

            pst.setLong(1, id_parent);
            pst.setString(2, name);
            pst.setLong(3, id_catalog);
            if (pst.executeUpdate() < 1) {
                throw new NoSuchEntityException("Не найдена запись");
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
            throw new EJBException("Ошибка UPDATE");
        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
            } else {
                e.printStackTrace();
                throw new EJBException("Ошибка UPDATE");
            }
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    // </editor-fold>
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long id_catalog) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"CATALOG\" WHERE ID_CATALOG = ?");
            //id_catalog.longValue();
            pst.setLong(1, id_catalog.longValue());
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return id_catalog;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.Long ejbFindByName(java.lang.String name) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_CATALOG FROM \"CATALOG\" WHERE NAME = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return new Long(rs.getLong(1));
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.Long ejbCreate(String parent_name, String name) throws CreateException, JMSException {
        try {
            ejbFindByName(name);
            throw new DuplicateKeyException("Каталог с таким названием уже существует");
        } catch (ObjectNotFoundException ex) {
        }
        try {
            this.id_parent = ejbFindByName(parent_name).longValue();
        } catch (ObjectNotFoundException ex) {
            throw new CreateException("Родительский каталог не найден");
        }
        this.name = name;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"CATALOG\" " + "(ID_PARENT,NAME)" + "VALUES(?,?) RETURNING ID_CATALOG INTO ?;END;");
            pst.setLong(1, id_parent);
            pst.setString(2, name);
            pst.registerOutParameter(3, Types.INTEGER);
            rs = pst.executeQuery();
            id_catalog = pst.getLong(3);
           


          //  EJBHelper.sendMessage(new HistoryMessage(userId, "CATALOG", "Добавлена каталог", objId));

            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }


            return new Long(id_catalog);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }
    }

    public java.lang.Long ejbCreate(long userId, long id, String parent_name, String name) throws CreateException {
        try {
            ejbFindByName(name);
            throw new DuplicateKeyException("Каталог с таким названием уже существует");
        } catch (ObjectNotFoundException ex) {
        }
        try {
            this.id_parent = ejbFindByName(parent_name).longValue();
        } catch (ObjectNotFoundException ex) {
            throw new CreateException("Родительский каталог не найден");
        }
        this.id_catalog = id;
        this.name = name;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("INSERT INTO \"CATALOG\" " + "(ID_CATALOG,ID_PARENT,NAME)" + "VALUES(?,?,?)");
            pst.setLong(1, id);
            pst.setLong(2, id_parent);
            pst.setString(3, name);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            return new Long(id_catalog);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (SQLException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }
    }

    public java.lang.Long ejbCreate(long id, long parent_id, String name) throws CreateException {
        try {
            ejbFindByName(name);
            throw new DuplicateKeyException("Каталог с таким названием уже существует");
        } catch (ObjectNotFoundException ex) {
        }
        this.id_parent = parent_id;
        this.id_catalog = id;
        this.name = name;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("INSERT INTO \"CATALOG\" " + "(ID_CATALOG,ID_PARENT,NAME)" + "VALUES(?,?,?)");
            pst.setLong(1, id);
            pst.setLong(2, id_parent);
            pst.setString(3, name);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            return new Long(id_catalog);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (SQLException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }
    }

    public void ejbPostCreate(String parent_name, String name) throws CreateException {
    }

    public void ejbPostCreate(long userId, long id, String parent_name, String name) throws CreateException {
    }

    public void ejbPostCreate(long id, long parent_id, String name) throws CreateException {
    }

    public Collection ejbFindCatalogByPid(java.lang.Long id_catalog) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_CATALOG FROM \"CATALOG\" WHERE ID_PARENT = ?");
            pst.setLong(1, id_catalog.longValue());
            resultSet = pst.executeQuery();
            //  ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_catalogs = resultSet.getLong(1);
                keys.addElement(new Long(id_catalogs));
            }

            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst, resultSet);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public Collection ejbFindAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_CATALOG FROM CATALOG START WITH ID_PARENT is NULL CONNECT BY PRIOR ID_CATALOG=ID_PARENT ORDER SIBLINGS BY NAME");
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_catalog = resultSet.getLong(1);
                keys.addElement(new Long(id_catalog));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.String getName() {
        return name;
    }

    public long getId() {
        return id_catalog;
    }

    public long getParentId() {
        return id_parent;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public void setId(long id_catalog) {
        this.id_catalog = id_catalog;
    }

    public void setParentId(long id_parent) {
        this.id_parent = id_parent;
    }

    public void setAll(long parent_id, String name) {
        this.id_parent = parent_id;
        this.name = name;
    }

    public void sendMessage(Long id_user,  String nameTables, String message, Long id_obj, int  prior) {
        try {
            EJBHelper.sendMessage(new HistoryMessage(id_user, nameTables, message, id_obj), prior);
        } catch (EJBException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}
