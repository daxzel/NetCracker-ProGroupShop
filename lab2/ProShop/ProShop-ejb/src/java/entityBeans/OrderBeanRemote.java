/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import javax.naming.NamingException;

/**
 *
 * @author Yra
 */
public interface OrderBeanRemote extends EJBObject {

    public void setId(java.lang.Long id_order) throws RemoteException;

    public long getId() throws RemoteException;

    //public void setIdUser(java.lang.Long id_order) throws RemoteException;
    long getIdUser() throws RemoteException;

    void setNameUser() throws NamingException, FinderException, RemoteException;

    void setIdProduct(java.lang.Long id_product) throws RemoteException;

    java.lang.Long getIdProduct() throws RemoteException;

    String getNameProduct() throws RemoteException;

    int getAmount() throws RemoteException;

    boolean getStatus() throws RemoteException;

    void setStatus(boolean status) throws RemoteException;

    double getPriceProduct() throws RemoteException;

    double getPrice() throws RemoteException;

    String getNameUser() throws FinderException, RemoteException;

    void setAll(long id_user, long id_product,boolean status, int amount) throws RemoteException;;
}
