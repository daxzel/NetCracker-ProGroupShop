/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

/**
 *
 * @author Yra
 */
public interface ProductBeanRemoteHome extends EJBHome {

    entityBeans.ProductBeanRemote findByPrimaryKey(java.lang.Long key)  throws FinderException, RemoteException;
    
}
