/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import helpers.EJBHelper;
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
public class OpinionBean implements EntityBean {

    private EntityContext entityContext;
    private long id_opinion;
    private long id_product;
    private long id_user;
    private String text;
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
            // javax.naming.Context context = new javax.naming.InitialContext();
            try {
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
        entityContext = null;
    }

    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {

        id_opinion = Long.parseLong(entityContext.getPrimaryKey().toString());
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"OPINION\" WHERE ID_OPINION = ?");
            pst.setLong(1, id_opinion);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Не найдена указанная запись");
            }
            //  id_opinion = rs.getLong(1);
            id_product = rs.getLong(2);
            id_user = rs.getLong(3);
            text = rs.getString(4);
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
            pst = conn.prepareStatement("UPDATE \"OPINION\"" + "SET TEXT=? WHERE ID_OPINION=?");
            pst.setString(1, text);
            pst.setLong(2, id_opinion);

   //         EJBHelper.sendMessage(new HistoryMessage(userId,"OPINION","Изменен комментарий",objId));

            if (pst.executeUpdate() < 1) {
                throw new NoSuchEntityException("Не найдена запись");
            }

     //   } catch (JMSException ex){
     //       throw new EJBException("Ошибка jms");
        } catch (NamingException ex) {
            throw new EJBException("Ошибка UPDATE");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EJBException("Ошибка UPDATE");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public void ejbRemove() throws RemoveException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("DELETE FROM \"OPINION\" WHERE ID_OPINION = ?");
            pst.setLong(1, id_opinion);
         //   EJBHelper.sendMessage(new HistoryMessage(userId,"OPINION","Удален комментарий",objId));
            if (pst.executeUpdate() < 1) {
                throw new RemoveException("Ошибка удаления");
            }
            // conn.commit();
             //   } catch (JMSException ex){
         } catch (NamingException ex) {
            throw new RemoveException("Ошибка удаления");
        } catch (SQLException ex) {
            throw new RemoveException("Ошибка удаления");
        } finally {
            try {
//                 conn.commit();
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
    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long id_op) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_OPINION FROM \"OPINION\" WHERE ID_OPINION = ?");
            //id_op.longValue();
            pst.setLong(1, id_op.longValue());
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return id_op;
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

    public Collection ejbFindOpinionByProduct(java.lang.Long id_pr) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_OPINION FROM \"OPINION\" WHERE ID_PRODUCT = ?");
            pst.setLong(1, id_pr.longValue());
            rs = pst.executeQuery();
            Vector keys = new Vector();
            while (rs.next()) {
                long id_opinion = rs.getLong(1);
                keys.addElement(new Long(id_opinion));
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
    
    public Collection ejbFindOpinionByUser(java.lang.Long id_user) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_OPINION FROM \"OPINION\" WHERE ID_USER = ?");
            pst.setLong(1, id_user.longValue());
            rs = pst.executeQuery();
            Vector keys = new Vector();
            while (rs.next()) {
                long id_opinion = rs.getLong(1);
                keys.addElement(new Long(id_opinion));
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

    public java.lang.Long ejbCreate(java.lang.Long id_prod, java.lang.Long id_user, String txt) throws CreateException {
        this.id_product = id_prod.longValue();
        this.id_user = id_user.longValue();
        this.text = txt;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"OPINION\" " + "(ID_PRODUCT, ID_USER, TEXT)" + "VALUES(?, ?, ?) RETURNING ID_OPINION INTO ?;END;");
            pst.setLong(1, id_prod.longValue());
            pst.setLong(2, id_user.longValue());
            pst.setString(3, txt);
            pst.registerOutParameter(4, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            id_opinion = pst.getLong(4);

           // EJBHelper.sendMessage(new HistoryMessage(userId,"OPINION","Удален комментарий",pst.getLong(4)));

            return new Long(id_opinion);
            //  } catch (JMSException ex){
           // throw new EJBException("Ошибка jms");
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

    public java.lang.Long ejbCreate(java.lang.Long id, java.lang.Long id_prod, java.lang.Long id_user, String txt) throws CreateException {
        this.id_opinion = id.longValue();
        this.id_product = id_prod.longValue();
        this.id_user = id_user.longValue();
        this.text = txt;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("INSERT INTO \"OPINION\" " + "(ID_OPINION,ID_PRODUCT, ID_USER, TEXT)" + "VALUES(?,?,?,?)");
            pst.setLong(1, id_prod.longValue());
            pst.setLong(2, id_user.longValue());
            pst.setString(3, txt);
            pst.registerOutParameter(4, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            id_opinion = pst.getLong(4);
            return new Long(id_opinion);
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

    public void ejbPostCreate(java.lang.Long id_prod, java.lang.Long id_user, String txt) throws CreateException {
    }

    public void ejbPostCreate(java.lang.Long id, java.lang.Long id_prod, java.lang.Long id_user, String txt) throws CreateException {
    }

    public long getIdOpinion() {
        return id_opinion;
    }

    public void setIdOpinion(java.lang.Long id_opn) {
        id_opinion = id_opn.longValue();
    }

    public long getIdProduct() {
        return id_product;
    }

    public void setIdProduct(java.lang.Long id_prod) {
        id_product = id_prod.longValue();
    }

    public long getIdUser() {
        return id_user;
    }

    public void setIdUser(java.lang.Long id_user) {
        this.id_user = id_user.longValue();
    }

    public String getText() {
        return text;
    }

    public void setText(String txt) {
        text = txt;
    }

    public String getUserName() {
        UserBeanRemote user = null;
        String name = "";
        try {
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) helpers.EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            user = userHome.findByPrimaryKey(new Long(this.id_user));
            name = user.getNik();
        } catch (FinderException ex) {
            //ex.printStackTrace();
        } catch (RemoteException ex) {
            // ex.printStackTrace();
        } catch (NamingException ex) {
            //ex.printStackTrace();
        }
        return name;
    }

    public void setAll( long id_prod, long id_user, String txt)
    {
        this.id_product = id_prod;
        this.id_user = id_user;
        this.text = txt;
    }

     public void setParamMessage(long userId, long objId ){
      this.userId = userId;
      this.objId = objId;
    }

        public void setParamMessage(long userId ){
      this.userId = userId;
    }

 public void sendMessage(Long id_user,  String nameTables, String message, Long id_obj, int prior) {
        try {
            EJBHelper.sendMessage(new HistoryMessage(id_user, nameTables, message, id_obj), prior);
        } catch (EJBException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}
