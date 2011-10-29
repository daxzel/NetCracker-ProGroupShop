/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
/**
 *
 * @author Yra
 */
public interface UserBeanRemoteHome extends EJBHome {

    public entityBeans.UserBeanRemote findByPrimaryKey(java.lang.Long id_user)  throws FinderException, RemoteException;

    public java.util.Collection findAll() throws FinderException, RemoteException;

    public entityBeans.UserBeanRemote findByNik(String nik) throws FinderException, RemoteException;

    public java.util.Collection findByRole(java.lang.Long id_role) throws FinderException, RemoteException;

    public entityBeans.UserBeanRemote create(String name, String surname, String otchestvo, String nik, String password, java.sql.Date born, String phone, String email, java.lang.Long id_role) throws CreateException, RemoteException;
    
}
