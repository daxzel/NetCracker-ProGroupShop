/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import OtherBean.Helper;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Yra
 */
public class OrderBean implements EntityBean {

    private UserBeanRemoteHome userHome;
    private ProductBeanRemoteHome productHome;
    private EntityContext entityContext;
    // private Connection conn;
    private long id_order;
    private long id_user;
    private long id_product;
    private boolean status;
    private int amount;
    private String name_product;
    private String name_user;
    private double price_product;

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
            userHome = (UserBeanRemoteHome) OtherBean.Helper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            productHome = (ProductBeanRemoteHome) OtherBean.Helper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            //   javax.naming.Context context = new javax.naming.InitialContext();
            try {
                //  dataSource = (DataSource) context.lookup("jdbc/InternetShop");
                // conn= Helper.getConnection();
            } catch (Exception e) {
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
    public void ejbRemove() throws RemoveException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareStatement("DELETE FROM \"ORDER\" WHERE ID_ORDER = ?");
            pst.setLong(1, id_order);
            if (pst.executeUpdate() < 1) {
                throw new RemoveException("Ошибка удаления");
            }

        } catch (NamingException ex) {
            throw new EJBException("Ошибка при удалении");
        } catch (SQLException ex) {
            throw new EJBException("Ошибка при удалении");
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

        id_order = Long.parseLong(entityContext.getPrimaryKey().toString());
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"ORDER\" WHERE ID_ORDER = ?");
            pst.setLong(1, id_order);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Не найдена указанная запись");
            }
            id_order = rs.getLong(1);
            id_user = rs.getLong(2);


            id_product = rs.getLong(3);
            //
            status = rs.getBoolean(4);
            amount = rs.getInt(5);


        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
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
            conn = Helper.getConnection();
            pst = conn.prepareStatement("UPDATE \"ORDER\"" + "SET STATUS = ? WHERE ID_ORDER=?");
            pst.setBoolean(1, status);
            pst.setLong(2, id_order);
            if (pst.executeUpdate() < 1) {
                throw new NoSuchEntityException("Не найдена запись");
            }
        } catch (NamingException ex) {
            throw new EJBException("Ошибка UPDATE");
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
    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long id_order) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"ORDER\" WHERE ID_ORDER = ?");
            pst.setLong(1, id_order.longValue());
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return id_order;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
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

    public Collection ejbFindByUserAndStatus(java.lang.Long id_user, boolean status) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"ORDER\" WHERE ID_USER = ? AND STATUS = ?");
            pst.setLong(1, id_user.longValue());
            pst.setBoolean(2, status);
            rs = pst.executeQuery();
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_order = resultSet.getLong(1);
                keys.addElement(new Long(id_order));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.Long ejbCreate(java.lang.Long id_user, java.lang.Long id_product, java.lang.Boolean status, java.lang.Integer amount) throws CreateException {

        this.id_user = id_user.longValue();
        this.id_product = id_product.longValue();
        this.status = status.booleanValue();
        this.amount = amount.intValue();
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"ORDER\" " + "(ID_USER,ID_PRODUCT,STATUS,KOL_VO)" + "VALUES(?,?,?,?) RETURNING ID_ORDER INTO ?;END;");
            pst.setLong(1, this.id_user);
            pst.setLong(2, this.id_product);
            pst.setBoolean(3, this.status);
            pst.setInt(4, this.amount);
            pst.registerOutParameter(5, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            this.id_order = pst.getLong(5);
            return new Long(this.id_order);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (SQLException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                Helper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }
    }

    public void ejbPostCreate(java.lang.Long id_user, java.lang.Long id_product, java.lang.Boolean status, java.lang.Integer amount) throws CreateException {
    }

    public void setId(java.lang.Long id_order) {
        this.id_order = id_order.longValue();
    }

    public long getId() {
        return id_order;
    }

    //public void setIdUser(java.lang.Long id_order) throws RemoteException;
    public long getIdUser() {
        return id_user;
    }

    /*public void setNameUser() throws NamingException, FinderException, RemoteException {
    UserBeanRemoteHome userHome = (UserBeanRemoteHome) OtherBean.Helper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
    UserBeanRemote user = userHome.findByPrimaryKey(new Long(this.id_user));
    this.name_user = user.getNik();
    }*/

    /*public void setNameAndPriceProduct() throws NamingException, FinderException, RemoteException {
    ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) OtherBean.Helper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
    ProductBeanRemote product = productHome.findByPrimaryKey(new Long(this.id_product));
    this.name_product = product.getName();
    this.price_product = product.getPrice();
    }*/
    public String getNameUser() throws FinderException, RemoteException {
        UserBeanRemote usr = userHome.findByPrimaryKey(new Long(id_user));
        return usr.getNik();
    }

    public String getNameProduct() throws FinderException, RemoteException {
        ProductBeanRemote prd = productHome.findByPrimaryKey(new Long(id_product));
        return prd.getName();
    }

    public double getPriceProduct() throws FinderException, RemoteException {
        ProductBeanRemote prd = productHome.findByPrimaryKey(new Long(id_product));
        return prd.getPrice();
    }

    public void setIdProduct(java.lang.Long id_product) {
        this.id_product = id_product.longValue();
    }

    public java.lang.Long getIdProduct() {
        return new Long(id_product);
    }

    public int getAmount() {
        return amount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPrice() {
        return price_product * amount;
    }
}
