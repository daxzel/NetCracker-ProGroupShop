/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

/**
 *
 * @author Yra
 */
public interface OpinionBeanRemoteHome extends EJBHome {

    public entityBeans.OpinionBeanRemote findByPrimaryKey(java.lang.Long id_opinion) throws FinderException, RemoteException;

    public java.util.List findOpinionByProduct(java.lang.Long id_pr) throws FinderException, RemoteException;

    public java.util.List findOpinionByProductName(java.lang.Long id_prod) throws FinderException, RemoteException;
    
    public java.util.List findOpinionByUser(java.lang.Long id_user) throws FinderException, RemoteException;

    public entityBeans.OpinionBeanRemote create(java.lang.Long id_pr, java.lang.Long id_usr, String text) throws CreateException, RemoteException;

    public entityBeans.OpinionBeanRemote create(java.lang.Long id, java.lang.Long id_pr, java.lang.Long id_usr, String text) throws CreateException, RemoteException;


}
