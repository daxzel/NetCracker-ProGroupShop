/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

/**
 *
 * @author Yra
 */
public interface ProductBeanRemote extends EJBObject {

    public long getIdCatalog() throws RemoteException;

    public void setIdCatalog(java.lang.Long nid) throws RemoteException;

    public String getNameCatalog() throws RemoteException;

    public void setNameCatalog(String nid) throws RemoteException;

    public long getId() throws RemoteException;

    public void setId(java.lang.Long nid) throws RemoteException;

    public String getDescription() throws RemoteException;

    public void setDescription(String nDescription) throws RemoteException;

    public String getName() throws RemoteException;

    public void setName(String nname) throws RemoteException;

    public double getPrice() throws RemoteException;

    public void setPrice(java.lang.Double nprice) throws RemoteException;

    public java.util.List getOpinions() throws RemoteException;
    
}
