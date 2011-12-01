/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.sql.*;
import java.sql.*;
import entityBeans.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import javax.jms.Queue;
import javax.ejb.CreateException;
import javax.ejb.*;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import moreTools.SerializbleImage;

/**
 *
 * @author Yra
 */
public class EJBHelper {

    private static DataSource dataSource = null;

    private static QueueSender queue_sender = null;
    private static QueueSession queue_session = null;
    private static boolean canSendMessage = true;

        public static void setMessageSending(boolean allowed) {
        canSendMessage = allowed;
    }
public static void sendMessage(Object msgContent, int prior) throws EJBException, JMSException{
        if (canSendMessage) {
            if (queue_sender == null) {
                try {
                    // Create initial context and look up QueueConnectionFactory and Queue.
                    InitialContext ctx = new InitialContext();
                    QueueConnectionFactory qcf = (QueueConnectionFactory) ctx.lookup("jms/historyQueueFactory");
                    Queue queue = (Queue) ctx.lookup("jms/historyQueue");
                    // Create connection, session, and sender.
                    QueueConnection queue_conn = qcf.createQueueConnection();
                    queue_session = queue_conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                    if (prior ==1){
                    queue_sender.setPriority(9);
                    queue_sender = queue_session.createSender(queue);
                    } else
                     if (prior ==2){
                    queue_sender.setPriority(4);
                    queue_sender = queue_session.createSender(queue);
                    } else
                     if (prior ==3){
                    queue_sender.setPriority(1);
                    queue_sender = queue_session.createSender(queue);
                    }

                } catch (JMSException ex) {
                    throw new EJBException(ex);
                } catch (NamingException ex) {
                    throw new EJBException(ex);
                }
            }

            // Create the message and send.
            ObjectMessage message;
            try {
                message = queue_session.createObjectMessage();
                message.setObject((Serializable) msgContent);
                queue_sender.send(message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs) throws SQLException {
        if (rs != null) {
            pst.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement pst) throws SQLException {
        if (pst != null) {
            pst.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static Connection getConnection() throws SQLException, NamingException {
       // if (dataSource == null) {
              javax.naming.Context context = new javax.naming.InitialContext();
              DataSource dataSource = (DataSource) context.lookup("jdbc/InternetShop");
       // }
        return dataSource.getConnection();

    }

    public static Object lookupHome(String name, java.lang.Class clas) throws NamingException {
        return javax.rmi.PortableRemoteObject.narrow(lookupName(name), clas);
    }

    public static Object lookupName(String name) throws NamingException {
        javax.naming.Context namingContext = new javax.naming.InitialContext();
        return namingContext.lookup(name);
    }

    public static UserBeanRemoteHome  getUserRemoteHome() throws NamingException
    {
        return (UserBeanRemoteHome) lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
    }

    public static RoleBeanRemoteHome  getRoleRemoteHome() throws NamingException
    {
        return (RoleBeanRemoteHome) lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
    }

    public static CatalogBeanRemoteHome  getCatlogRemoteHome() throws NamingException
    {
        return (CatalogBeanRemoteHome) lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
    }

    public static ImageBeanRemoteHome  getImageRemoteHome() throws NamingException
    {
        return (ImageBeanRemoteHome) lookupHome("ejb/ImageBean", ImageBeanRemoteHome.class);
    }

    public static OpinionBeanRemoteHome  getOpinionRemoteHome() throws NamingException
    {
        return (OpinionBeanRemoteHome) lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
    }

    public static OrderBeanRemoteHome  getOrderRemoteHome() throws NamingException
    {
        return (OrderBeanRemoteHome) lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
    }

    public static ProductBeanRemoteHome  getProductRemoteHome() throws NamingException
    {
        return (ProductBeanRemoteHome) lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
    }

    public static class AddOrUpdate
    {
        public static void Catalog(long id, long parentId,
                String name ) throws NamingException, CreateException, RemoteException
        {
            CatalogBeanRemote catalog = null;
            try
            {
                catalog = EJBHelper.getCatlogRemoteHome().findByPrimaryKey(new java.lang.Long(id));
            }
            catch(FinderException ex)
            {
            }

            if (catalog!=null)
            {
                catalog.setAll(parentId, name);
            }
            else
            {
                EJBHelper.getCatlogRemoteHome().create(id, parentId, name);
            }
        }

         public static void Image(long id, long idProduct, String name, SerializbleImage imageS,
                int width, int heaight) throws NamingException,CreateException,RemoteException
         {
            ImageBeanRemote image = null;
            try
            {
                image = EJBHelper.getImageRemoteHome().findByPrimaryKey(new java.lang.Long(id));
            }
            catch(FinderException ex)
            {
            }

            if (image!=null)
            {
                image.setAll(idProduct, name, imageS, width, heaight);
            }
            else
            {
                EJBHelper.getImageRemoteHome().create(id, idProduct, name, imageS, width, heaight);
            }
         }

        public static void  Opinion(long id,long idProduct,
                long idUser, String text) throws NamingException,CreateException,RemoteException
        {
            OpinionBeanRemote opinion = null;
            try
            {
                opinion = EJBHelper.getOpinionRemoteHome().findByPrimaryKey(new java.lang.Long(id));
            }
            catch(FinderException ex)
            {
            }

            if (opinion!=null)
            {
                opinion.setAll(idProduct, idUser, text);
            }
            else
            {
                EJBHelper.getOpinionRemoteHome().create(new java.lang.Long(id), new java.lang.Long(idProduct),
                        new java.lang.Long(idUser), text);
            }
        }

        public static void  Order(long id, long idUser, long idProduct,
                Boolean status, int amount) throws NamingException,CreateException,RemoteException
        {
            OrderBeanRemote order = null;
            try
            {
                order = EJBHelper.getOrderRemoteHome().findByPrimaryKey(new java.lang.Long(id));
            }
            catch(FinderException ex)
            {
            }

            if (order!=null)
            {
                order.setAll(idUser, idProduct, true, amount);
            }
            else
            {
                EJBHelper.getOrderRemoteHome().create(new java.lang.Long(id),new java.lang.Long(idUser),
                        new java.lang.Long(idProduct) ,status,new java.lang.Integer(amount));
            }
        }

         public static void  Role(long id, String name) throws NamingException,CreateException,RemoteException
        {
            RoleBeanRemote role = null;
            try
            {
                role = EJBHelper.getRoleRemoteHome().findByPrimaryKey(new java.lang.Long(id));
            }
            catch(FinderException ex)
            {
            }

            if (role!=null)
            {
                role.setAll(name);
            }
            else
            {
                EJBHelper.getRoleRemoteHome().create(id, name);
            }
        }

        public static void  Product(long  id, String description, long  idCatalog, String name,
                double price) throws NamingException,CreateException,RemoteException,FinderException
        {
            ProductBeanRemote product = null;
            try
            {
                product = EJBHelper.getProductRemoteHome().findByPrimaryKey(new java.lang.Long(id));
            }
            catch(FinderException ex)
            {
            }

            if (product!=null)
            {
                product.setAll(description, idCatalog, name, price);
            }
            else
            {
                EJBHelper.getProductRemoteHome().create(new java.lang.Long(id),description, idCatalog, name, price);
            }

            
        }

        public static void  User(long id, String name,String surName,String otchestvo,
                String nik, String password,Date born, String phone,String email, Long idRole ) throws NamingException,CreateException,RemoteException
        {
            UserBeanRemote user = null;
            try
            {
                user = EJBHelper.getUserRemoteHome().findByPrimaryKey(new java.lang.Long(id));
            }
            catch(FinderException ex)
            {
            }

            if (user!=null)
            {
                user.setAll(name, surName, otchestvo, nik, password, born, phone, email, idRole.longValue());
            }
            else
            {
                EJBHelper.getUserRemoteHome().create(new java.lang.Long(id), name, surName, otchestvo, nik, password, born, phone, email, idRole);
            }
        }
    }

}
