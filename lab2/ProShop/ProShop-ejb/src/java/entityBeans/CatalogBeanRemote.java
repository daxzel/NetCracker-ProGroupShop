/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 *
 * @author Yra
 */
public interface CatalogBeanRemote extends EJBObject {

    public java.lang.String getName() throws RemoteException;

    public long getId() throws RemoteException;

    public long getParentId() throws RemoteException;

    public void setName(java.lang.String name) throws RemoteException;

    void setId(long id_catalog) throws RemoteException;

    void setParentId(long id_parent) throws RemoteException;

    void setAll(long parent_id, String name) throws RemoteException;
}
