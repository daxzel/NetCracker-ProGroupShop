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
public interface RoleBeanRemoteHome extends EJBHome {

    entityBeans.RoleBeanRemote findByPrimaryKey(java.lang.Long key)  throws FinderException, RemoteException;

    entityBeans.RoleBeanRemote findByName(String name) throws FinderException, RemoteException;

    java.util.List findAll() throws FinderException, RemoteException;

    RoleBeanRemote create(long id, String name) throws CreateException, RemoteException;

  
}
