/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;

/**
 *
 * @author Yra
 */
public interface CatalogBeanRemoteHome extends EJBHome {

    entityBeans.CatalogBeanRemote findByPrimaryKey(java.lang.Long key) throws FinderException, RemoteException;

    public java.util.List findAll() throws FinderException, RemoteException;

    public entityBeans.CatalogBeanRemote findByName(java.lang.String name) throws FinderException, RemoteException;

    public java.util.List findCatalogByPid(java.lang.Long id_parent) throws FinderException, RemoteException;

    public entityBeans.CatalogBeanRemote create(java.lang.Long id_parent, java.lang.String name) throws CreateException, RemoteException;
}
