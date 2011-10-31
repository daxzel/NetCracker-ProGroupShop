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
public interface OrderBeanRemote extends EJBObject {

    public void setId(java.lang.Long id_order) throws RemoteException;

    public long getId() throws RemoteException;

    //public void setIdUser(java.lang.Long id_order) throws RemoteException;

    public long getIdUser() throws RemoteException;

    public void setNameUser(String nameUser) throws RemoteException;

    public String getNameUser() throws RemoteException;

    public void setIdProduct(java.lang.Long id_product) throws RemoteException;

    public java.lang.Long getIdProduct() throws RemoteException;

    public String getNameProduct() throws RemoteException;

    public int getAmount() throws RemoteException;

    public boolean getStatus() throws RemoteException;

    public void setStatus(boolean status) throws RemoteException;

    public double getPriceProduct() throws RemoteException;

    public double getPrice() throws RemoteException;
}
