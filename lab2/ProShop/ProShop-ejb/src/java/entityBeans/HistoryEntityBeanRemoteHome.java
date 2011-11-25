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
public interface HistoryEntityBeanRemoteHome extends EJBHome {

    entityBeans.HistoryEntityBeanRemote findByPrimaryKey(java.lang.Long key) throws FinderException, RemoteException;

    entityBeans.HistoryEntityBeanRemote findByIdObjAndNameTable(java.lang.Long id, String nameTable) throws FinderException, RemoteException;
}
