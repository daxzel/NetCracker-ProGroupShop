/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBean;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
/**
 *
 * @author Yra
 */
public interface UserHome  extends EJBHome {

    entityBean.User findByPrimaryKey(java.lang.Long id_user)  throws FinderException, RemoteException;

    entityBean.User create(java.lang.Long key)  throws CreateException, RemoteException;

}
