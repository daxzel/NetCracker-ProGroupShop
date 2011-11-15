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
public interface OrderBeanRemoteHome extends EJBHome {

    entityBeans.OrderBeanRemote findByPrimaryKey(java.lang.Long id_order) throws FinderException, RemoteException;

   // public java.util.List findAll() throws FinderException, RemoteException;

   // public java.util.List findByUser(java.lang.Long id_user) throws FinderException, RemoteException;

   // public entityBeans.OrderBeanRemote findByNikAndId(String nik, java.lang.Long id_user) throws FinderException, RemoteException;

    //public entityBeans.OrderBeanRemote findByNikAndPassword(String nik, String password) throws FinderException, RemoteException;

    public java.util.List findByUserAndStatus(java.lang.Long id_user, boolean status) throws FinderException, RemoteException;

    public entityBeans.OrderBeanRemote create(java.lang.Long id_user, java.lang.Long id_product, java.lang.Boolean status, java.lang.Integer amount) throws CreateException, RemoteException;

    public entityBeans.OrderBeanRemote create(java.lang.Long id, java.lang.Long id_user, java.lang.Long id_product, java.lang.Boolean status, java.lang.Integer amount) throws CreateException, RemoteException;
}
