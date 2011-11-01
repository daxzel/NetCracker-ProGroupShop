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
    public long getIdUser() throws RemoteException;

    public void setNameUser() throws NamingException, FinderException, RemoteException;

    public void setIdProduct(java.lang.Long id_product) throws RemoteException;

    public java.lang.Long getIdProduct() throws RemoteException;

    public String getNameProduct() throws RemoteException;

    public int getAmount() throws RemoteException;

    public boolean getStatus() throws RemoteException;

    public void setStatus(boolean status) throws RemoteException;

    public double getPriceProduct() throws RemoteException;

    public double getPrice() throws RemoteException;

    public String getNameUser() throws FinderException, RemoteException;
}
