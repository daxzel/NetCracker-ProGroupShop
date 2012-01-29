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
import java.util.*;


/**
 *
 * @author Admin
 */
public class AdderAndUpdater
{
    private List catalogs;
    private List images;
    private List opinions;
    private List orders;
    private List roles;
    private List products;
    private List users;

    public List getCatalogs()
    {
        return catalogs;
    }

    public List getImages()
    {
        return images;
    }

    public List getOpinions()
    {
        return opinions;
    }

    public List getOrders()
    {
        return orders;
    }

    public List getRoles()
    {
        return roles;
    }

    public List getUsers()
    {
        return users;
    }

    public List getProducts()
    {
        return products;
    }

    public AdderAndUpdater()
    {
        catalogs = new LinkedList();
        images = new LinkedList();
        opinions = new LinkedList();
        orders = new LinkedList();
        roles = new LinkedList();
        products = new LinkedList();
        users = new LinkedList();
    }

    public void Catalog(long id, long parentId,
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
            catalogs.add(catalog);
            //catalog.setAll(parentId, name);
        }
        else
        {
            EJBHelper.getCatlogRemoteHome().create(id, parentId, name);
        }
    }

     public void Image(long id, long idProduct, String name,
            int width, int height) throws NamingException,CreateException,RemoteException
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
            images.add(image);
            //image.setAll(idProduct, name, width, height);
        }
        else
        {
            EJBHelper.getImageRemoteHome().create(id, idProduct, name, width, height);
        }
     }

    public void  Opinion(long id,long idProduct,
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
            opinions.add(opinion);
            //opinion.setAll(idProduct, idUser, text);
        }
        else
        {
            EJBHelper.getOpinionRemoteHome().create(new java.lang.Long(id), new java.lang.Long(idProduct),
                    new java.lang.Long(idUser), text);
        }
    }

    public void  Order(long id, long idUser, long idProduct,
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
            orders.add(order);
            //order.setAll(idUser, idProduct, true, amount);
        }
        else
        {
            EJBHelper.getOrderRemoteHome().create(new java.lang.Long(id),new java.lang.Long(idUser),
                    new java.lang.Long(idProduct) ,status,new java.lang.Integer(amount));
        }
    }

    public void  Role(long id, String name) throws NamingException,CreateException,RemoteException
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
            roles.add(role);
            //role.setAll(name);
        }
        else
        {
            EJBHelper.getRoleRemoteHome().create(id, name);
        }
    }

    public void  Product(long  id, String description, long  idCatalog, String name,
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
            products.add(product);
           // product.setAll(description, idCatalog, name, price);
        }
        else
        {
            EJBHelper.getProductRemoteHome().create(new java.lang.Long(id),description, idCatalog, name, price);
        }


    }

    public void  User(long id, String name,String surName,String otchestvo,
            String nik, String password, java.sql.Date born, String phone,String email, Long idRole ) throws NamingException,CreateException,RemoteException
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
            users.add(user);
            //user.setAll(name, surName, otchestvo, nik, password, born, phone, email, idRole.longValue());
        }
        else
        {
            EJBHelper.getUserRemoteHome().create(new java.lang.Long(id), name, surName, otchestvo, nik, password, born, phone, email, idRole);
        }
    }
}
