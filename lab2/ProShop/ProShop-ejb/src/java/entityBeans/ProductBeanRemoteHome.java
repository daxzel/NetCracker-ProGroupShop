/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

/**
 *
 * @author Yra
 */
public interface ProductBeanRemoteHome extends EJBHome {

    entityBeans.ProductBeanRemote findByPrimaryKey(java.lang.Long key) throws FinderException, RemoteException;

    public entityBeans.ProductBeanRemote findByName(java.lang.String name) throws FinderException, RemoteException;

    public java.util.List findByCatalog(java.lang.Long id_catalog) throws FinderException, RemoteException;

    public java.util.List findByPrice(double price, boolean flag) throws FinderException, RemoteException;

    public entityBeans.ProductBeanRemote create(java.lang.String description, java.lang.String name_catalog, java.lang.String name, double price) throws CreateException, RemoteException,FinderException;

    public entityBeans.ProductBeanRemote create(java.lang.Long id,java.lang.String description, java.lang.String name_catalog, java.lang.String name, double price) throws CreateException, RemoteException,FinderException;

    public entityBeans.ProductBeanRemote create(java.lang.Long id,java.lang.String description, long catalog_id, java.lang.String name, double price) throws CreateException, RemoteException,FinderException;

    public java.util.List findAll() throws FinderException, RemoteException;

    public java.util.List findBySubstrOfName(java.lang.String substrName) throws FinderException, RemoteException;


}
