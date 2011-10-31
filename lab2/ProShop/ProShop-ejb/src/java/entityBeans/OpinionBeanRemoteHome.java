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

    entityBeans.OpinionBeanRemote findByPrimaryKey(java.lang.Long key) throws FinderException, RemoteException;

    public java.util.List findOpinionByProduct(int id_pr) throws FinderException, RemoteException;

    public java.util.List findOpinionByProductName(String name_pr) throws FinderException, RemoteException;

    public entityBeans.UserBeanRemote create(java.lang.Long id_pr, java.lang.Long id_usr, String text) throws CreateException, RemoteException;
}
