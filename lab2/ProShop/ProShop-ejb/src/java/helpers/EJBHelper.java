/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javax.naming.NamingException;
import javax.sql.*;
import java.sql.*;
import entityBeans.*;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.*;
import moreTools.SerializbleImage;

/**
 *
 * @author Yra
 */
public class EJBHelper {

    private static DataSource dataSource = null;

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
//        public static CatalogBeanRemote Catalog(String parent,
//                String name ) throws NamingException, CreateException, RemoteException
//        {
//            return EJBHelper.getCatlogRemoteHome().create(parent, name);
//        }

//        public static CatalogBeanRemote Catalog(long id, String parent,
//                String name ) throws NamingException, CreateException, RemoteException
//        {
//            return EJBHelper.getCatlogRemoteHome().create(id, parent, name);
//        }

        public static CatalogBeanRemote Catalog(long id, long parentId,
                String name ) throws NamingException, CreateException, RemoteException
        {
            return EJBHelper.getCatlogRemoteHome().create(id, parentId, name);
        }

//        public static ImageBeanRemote Image(long idProduct, String name, SerializbleImage image,
//                int width, int heaight) throws NamingException,CreateException,RemoteException
//        {
//            return EJBHelper.getImageRemoteHome().create(idProduct, name, image, width, heaight);
//        }

         public static ImageBeanRemote Image(long id, long idProduct, String name, SerializbleImage image,
                int width, int heaight) throws NamingException,CreateException,RemoteException
        {
            return EJBHelper.getImageRemoteHome().create(id,idProduct, name, image, width, heaight);
        }

//        public static OpinionBeanRemote  Opinion(java.lang.Long idProduct,
//                java.lang.Long idUser, String text) throws NamingException,CreateException,RemoteException
//        {
//            return EJBHelper.getOpinionRemoteHome().create(idProduct, idUser, text);
//        }

        public static OpinionBeanRemote  Opinion(long id,java.lang.Long idProduct,
                java.lang.Long idUser, String text) throws NamingException,CreateException,RemoteException
        {
            return EJBHelper.getOpinionRemoteHome().create(new java.lang.Long(id),idProduct, idUser, text);
        }

//        public static OrderBeanRemote  Order(Long idUser, Long idProduct,
//                Boolean status, Integer amount) throws NamingException,CreateException,RemoteException
//        {
//            return EJBHelper.getOrderRemoteHome().create(idUser, idProduct ,status, amount);
//        }

        public static OrderBeanRemote  Order(long id, Long idUser, Long idProduct,
                Boolean status, Integer amount) throws NamingException,CreateException,RemoteException
        {
            return EJBHelper.getOrderRemoteHome().create(new java.lang.Long(id),idUser, idProduct ,status, amount);
        }

         public static RoleBeanRemote  Role(long id, String name) throws NamingException,CreateException,RemoteException
        {
            return EJBHelper.getRoleRemoteHome().create(id,name);
        }

//        public static ProductBeanRemote  Product(String description, String nameCatalog, String name,
//                double price) throws NamingException,CreateException,RemoteException,FinderException
//        {
//            return EJBHelper.getProductRemoteHome().create(description, nameCatalog, name, price);
//        }
//
//        public static ProductBeanRemote  Product(long  id, String description, String nameCatalog, String name,
//                double price) throws NamingException,CreateException,RemoteException,FinderException
//        {
//            return EJBHelper.getProductRemoteHome().create(new java.lang.Long(id),description, nameCatalog, name, price);
//        }

        public static ProductBeanRemote  Product(long  id, String description, long  idCatalog, String name,
                double price) throws NamingException,CreateException,RemoteException,FinderException
        {
            return EJBHelper.getProductRemoteHome().create(new java.lang.Long(id),description, idCatalog, name, price);
        }

//        public static UserBeanRemote  User(String name,String surName,String otchestvo,
//                String nik, String password,Date born, String phone,String email, Long idRole ) throws NamingException,CreateException,RemoteException
//        {
//            return EJBHelper.getUserRemoteHome().create(name, surName, otchestvo, nik,
//                    password, born, phone, email, idRole);
//        }

        public static UserBeanRemote  User(long id, String name,String surName,String otchestvo,
                String nik, String password,Date born, String phone,String email, Long idRole ) throws NamingException,CreateException,RemoteException
        {
            return EJBHelper.getUserRemoteHome().create(new java.lang.Long(id),name, surName, otchestvo, nik,
                    password, born, phone, email, idRole);
        }
    }

}
