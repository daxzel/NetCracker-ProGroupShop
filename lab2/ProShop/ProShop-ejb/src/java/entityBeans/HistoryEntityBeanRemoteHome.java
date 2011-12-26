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
public interface HistoryEntityBeanRemoteHome extends EJBHome {

    entityBeans.HistoryEntityBeanRemote findByPrimaryKey(java.lang.Long key) throws FinderException, RemoteException;

    entityBeans.HistoryEntityBeanRemote findByIdObjAndNameTable(java.lang.Long id, String nameTable) throws FinderException, RemoteException;

    public java.util.Collection findByIdObjAndNameTableP(java.lang.Long id, String nameTable) throws FinderException, RemoteException;
    
    public entityBeans.HistoryEntityBean create(long id_user, String name_table, String status, long id_obj) throws CreateException, RemoteException;

    public entityBeans.HistoryEntityBean create(String name_table, String status, long id_obj) throws CreateException, RemoteException;

    public entityBeans.HistoryEntityBean create(long id_user, String name_table, String status) throws CreateException, RemoteException;
}
