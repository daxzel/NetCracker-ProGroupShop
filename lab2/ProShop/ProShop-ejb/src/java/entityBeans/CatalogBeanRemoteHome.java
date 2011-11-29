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

    public entityBeans.CatalogBeanRemote findByPrimaryKey(java.lang.Long key) throws FinderException, RemoteException;

    public java.util.List findAll() throws FinderException, RemoteException;

    public entityBeans.CatalogBeanRemote findByName(java.lang.String name) throws FinderException, RemoteException;

    public java.util.List findCatalogByPid(java.lang.Long id_parent) throws FinderException, RemoteException;

    public entityBeans.CatalogBeanRemote create( String parent_name, String name) throws CreateException, RemoteException;

    public entityBeans.CatalogBeanRemote create( long id,String parent_name, String name) throws CreateException, RemoteException;

    public entityBeans.CatalogBeanRemote create(long id, long parent_id, String name) throws CreateException, RemoteException;

     public void setParamMessage(long userId, long objId) throws FinderException, RemoteException;

    public void setParamMessage(long userId ) throws FinderException, RemoteException;

}
