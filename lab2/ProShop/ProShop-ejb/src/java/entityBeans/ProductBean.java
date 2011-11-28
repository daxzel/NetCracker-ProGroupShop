/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import helpers.EJBHelper;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.sql.DataSource;
import moreTools.HistoryMessage;

/**
 *
 * @author Yra
 */
public class ProductBean implements EntityBean {

    private OpinionBeanRemoteHome opinionHome;
    private CatalogBeanRemoteHome catalogHome;
    private EntityContext entityContext;
    private Connection conn;
    private long id_product;
    private String description;
    private long id_catalog;
    private String name;
    private double price;
    private long userId;
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
            opinionHome = (OpinionBeanRemoteHome) helpers.EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
            catalogHome = (CatalogBeanRemoteHome) helpers.EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            //   javax.naming.Context context = new javax.naming.InitialContext();
            try {
                //     conn = Helper.getConnection();
            } catch (Exception e) {
                throw new EJBException("Проблема с подключением к базе");
            }
        } catch (Exception e) {
            throw new EJBException("Проблема с подключением к базе");
        }
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
    public void unsetEntityContext() {
        this.entityContext = null;
    }

    public void ejbRemove() throws RemoveException, EJBException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("DELETE FROM \"PRODUCT\" WHERE ID_PRODUCT = ?");
            pst.setLong(1, id_product);

             EJBHelper.sendMessage(new HistoryMessage(userId,"PRODUCT","Удален продукт",objId));

            if (pst.executeUpdate() < 1) {
                throw new RemoveException("Ошибка удаления");
            }
            // conn.commit();

          } catch (JMSException ex){
            throw new EJBException("Ошибка");
        } catch (NamingException ex) {
            throw new EJBException("Ошибка при удалении");
        } catch (SQLException ex) {
            throw new EJBException("Ошибка при удалении");
        } finally {
            try {
//                 conn.commit();
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
        id_product = Long.parseLong(entityContext.getPrimaryKey().toString());
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"PRODUCT\" WHERE ID_PRODUCT = ?");
            pst.setLong(1, id_product);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Не найдена указанная запись");
            }
            description = rs.getString(1);
            id_product = rs.getLong(2);
            id_catalog = rs.getLong(3);
            name = rs.getString(4);
            price = rs.getDouble(5);


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
    public void ejbStore() throws EJBException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("UPDATE \"PRODUCT\"" + "SET DESCRIPTION = ?,ID_CATALOG =?,NAME=?,PRICE=?   WHERE ID_PRODUCT=?");
            pst.setString(1, description);
            pst.setLong(2, id_catalog);
            pst.setString(3, name);
            pst.setDouble(4, price);
            pst.setLong(5, id_product);

            EJBHelper.sendMessage(new HistoryMessage(userId,"PRODUCT","Изменен продукт",objId));



            if (pst.executeUpdate() < 1) {
                throw new NoSuchEntityException("Не найдена запись");
            }
        } catch (JMSException ex){
            throw new EJBException("Ошибка");
        } catch (NamingException ex) {
            throw new EJBException("Ошибка UPDATE");
        } catch (SQLException e) {
            throw new EJBException("Ошибка UPDATE");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.Long ejbCreate(java.lang.String description, java.lang.String name_catalog, java.lang.String name, double price) throws CreateException, FinderException, EJBException, JMSException {
        this.description = description;
        this.name = name;
        this.price = price;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) helpers.EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            CatalogBeanRemote ctg = catalogHome.findByName(name_catalog);
            this.id_catalog = ctg.getId();
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"PRODUCT\" " + "(DESCRIPTION,ID_CATALOG,NAME,PRICE)" + "VALUES(?,?,?,?) RETURNING ID_PRODUCT INTO ?;END;");
            pst.setString(1, this.description);
            pst.setLong(2, this.id_catalog);
            pst.setString(3, this.name);
            pst.setDouble(4, this.price);
            pst.registerOutParameter(5, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            this.id_product = pst.getLong(5);

             EJBHelper.sendMessage(new HistoryMessage(userId,"PRODUCT","Добавлен продукт",objId));

            return new Long(this.id_product);
        } catch (RemoteException ex) {
            throw new EJBException("Произошла ошибка добавления");
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

     public java.lang.Long ejbCreate(java.lang.Long id, java.lang.String description,
             java.lang.String name_catalog, java.lang.String name, double price) throws CreateException, FinderException {
        //try {
        //  ejbFindByName(name);
        //throw new DuplicateKeyException("Продукт с таким именем уже существует");
        //} catch (ObjectNotFoundException ex) {
        //}
        this.id_product = id.longValue();
        this.description = description;
        this.name = name;
        this.price = price;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) helpers.EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            CatalogBeanRemote ctg = catalogHome.findByName(name_catalog);
            this.id_catalog = ctg.getId();
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("INSERT INTO \"PRODUCT\" " + "(ID_PRODUCT,DESCRIPTION,ID_CATALOG,NAME,PRICE)" + "VALUES(?,?,?,?,?)");
            pst.setLong(1, this.id_product);
            pst.setString(2, this.description);
            pst.setLong(3, this.id_catalog);
            pst.setString(4, this.name);
            pst.setDouble(5, this.price);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            return new Long(this.id_product);
        } catch (RemoteException ex) {
            throw new EJBException("Произошла ошибка добавления");
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

     public java.lang.Long ejbCreate(java.lang.Long id, java.lang.String description,
             long catalog_id, java.lang.String name, double price) throws CreateException, FinderException {
        //try {
        //  ejbFindByName(name);
        //throw new DuplicateKeyException("Продукт с таким именем уже существует");
        //} catch (ObjectNotFoundException ex) {
        //}
        this.id_product = id.longValue();
        this.description = description;
        
         Connection conn = null;
         CallableStatement pst = null;
         ResultSet rs = null;
        
         try {
            conn = EJBHelper.getConnection();
            this.id_catalog = catalog_id;
            this.name = name;
            this.price = price;

       

            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("INSERT INTO \"PRODUCT\" " + "(ID_PRODUCT,DESCRIPTION,ID_CATALOG,NAME,PRICE)" + "VALUES(?,?,?,?,?)");
            pst.setLong(1, this.id_product);
            pst.setString(2, this.description);
            pst.setLong(3, this.id_catalog);
            pst.setString(4, this.name);
            pst.setDouble(5, this.price);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            return new Long(this.id_product);
        }  catch (NamingException ex) {
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


    public void ejbPostCreate(java.lang.String description, java.lang.String name_catalog, java.lang.String name, double price) throws CreateException {
    }

    public void ejbPostCreate(java.lang.Long id, java.lang.String description, java.lang.String name_catalog, java.lang.String name, double price) throws CreateException {
    }

    public void ejbPostCreate(java.lang.Long id, java.lang.String description, long catalog_id, java.lang.String name, double price) throws CreateException {
    }

    // </editor-fold>
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long id_product) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"PRODUCT\" WHERE ID_PRODUCT = ?");
            pst.setLong(1, id_product.longValue());
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return id_product;
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

    public Collection ejbFindByCatalog(java.lang.Long id_catalog) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_PRODUCT FROM \"PRODUCT\" WHERE ID_CATALOG = ?");
            pst.setLong(1, id_catalog.longValue());
            // rs = pst.executeQuery();
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_product = resultSet.getLong(1);
                keys.addElement(new Long(id_product));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EJBException("Ошибка SELECT");
            // e.printStackTrace();
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public Collection ejbFindByPrice(double price, boolean flag) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            if (flag) {
                pst = conn.prepareStatement("SELECT ID_PRODUCT FROM \"PRODUCT\" WHERE PRICE >= ?");
            } else {
                 pst = conn.prepareStatement("SELECT ID_PRODUCT FROM \"PRODUCT\" WHERE PRICE <= ?");
            }
            pst.setDouble(1, price);
            // rs = pst.executeQuery();
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_product = resultSet.getLong(1);
                keys.addElement(new Long(id_product));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EJBException("Ошибка SELECT");
            // e.printStackTrace();
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public Collection ejbFindAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_PRODUCT FROM \"PRODUCT\"");
            //   pst.setLong(1, id_catalog.longValue());
            // rs = pst.executeQuery();
            rs = pst.executeQuery();
            Vector keys = new Vector();
            while (rs.next()) {
                long id_product = rs.getLong(1);
                keys.addElement(new Long(id_product));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EJBException("Ошибка SELECT");
            // e.printStackTrace();
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst, rs);
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
            pst = conn.prepareStatement("SELECT * FROM \"PRODUCT\" WHERE NAME = ?");
            pst.setString(1, name);
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            Long id = new Long(resultSet.getLong(2) );
            return id;
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

    public void setParamMessage(long userId ){
      this.userId = userId;
      //this.objId = objId;
    }

    public long getIdCatalog() {
        return id_catalog;
    }

    public void setIdCatalog(java.lang.Long nid) {
        id_catalog = nid.longValue();
    }

    public String getNameCatalog() throws FinderException, RemoteException {
        CatalogBeanRemote ctg = catalogHome.findByPrimaryKey(new Long(this.id_catalog));
        return ctg.getName();
    }

    public long getId() {
        return id_product;
    }

    public void setId(java.lang.Long nid) {
        id_product = nid.longValue();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String nDescription) {
        description = nDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String nname) {
        name = nname;
    }

    public List getOpinionList() throws NamingException, FinderException, RemoteException {

        List list = opinionHome.findOpinionByProduct(new Long(this.id_product));
        return list;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(java.lang.Double nprice) {
        price = nprice.doubleValue();
    }

    public void setAll(java.lang.String description, long id_catalog, java.lang.String name, double price) throws FinderException,SQLException,NamingException,RemoteException
    {
        this.description = description;
        this.id_catalog = id_catalog;
        this.name = name;
        this.price = price;
    }
    
}
