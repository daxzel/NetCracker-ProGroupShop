/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Yra
 */
public interface CatalogBeanLocal extends EJBLocalObject {
    public java.lang.String getName() ;

    public long getId();
}
