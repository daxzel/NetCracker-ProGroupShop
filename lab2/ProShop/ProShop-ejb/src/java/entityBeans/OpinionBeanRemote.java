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
public interface OpinionBeanRemote extends EJBObject {

    public long getIdOpinion() throws RemoteException;

    public void setIdOpinion(java.lang.Long id_op) throws RemoteException;

    public long getIdProduct() throws RemoteException;

    public void setIdProduct(java.lang.Long id_pr) throws RemoteException;

    public long getIdUser() throws RemoteException;

    public void setIdUser(java.lang.Long id_usr) throws RemoteException;

    public String getText() throws RemoteException;

    public void setText(String text) throws RemoteException;

    public String getUserName() throws RemoteException;
}
