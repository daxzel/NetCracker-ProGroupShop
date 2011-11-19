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

    long getIdOpinion() throws RemoteException;

    void setIdOpinion(java.lang.Long id_op) throws RemoteException;

    long getIdProduct() throws RemoteException;

    void setIdProduct(java.lang.Long id_pr) throws RemoteException;

    long getIdUser() throws RemoteException;

    void setIdUser(java.lang.Long id_usr) throws RemoteException;

    String getText() throws RemoteException;

    void setText(String text) throws RemoteException;

    String getUserName() throws RemoteException;

    void setAll( long id_prod, long id_user, String txt) throws RemoteException;
}
