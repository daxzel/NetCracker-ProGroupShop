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

    long getIdCatalog() throws RemoteException;

    void setIdCatalog(java.lang.Long nid) throws RemoteException;

    void setNameCatalog(String nid) throws RemoteException;

    long getId() throws RemoteException;

    void setId(java.lang.Long nid) throws RemoteException;

    String getDescription() throws RemoteException;

    void setDescription(String nDescription) throws RemoteException;

    String getName() throws RemoteException;

    void setName(String nname) throws RemoteException;

    double getPrice() throws RemoteException;

    void setPrice(java.lang.Double nprice) throws RemoteException;

    java.util.List getOpinionList()  throws RemoteException;

    java.util.List getImageList()  throws RemoteException;

    void setAll(java.lang.String description, long id_catalog,
            java.lang.String name, double price) throws RemoteException;

    String getNameCatalog() throws RemoteException;

    void sendMessage(Long id_user,  String nameTables, String message, Long id_obj, int prior) throws RemoteException;
    
}
