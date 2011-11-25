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
public interface HistoryEntityBeanRemote extends EJBObject {

    public String getStatus() throws RemoteException;

    public long getRecordId() throws RemoteException;

    public long getObjId() throws RemoteException;

    public long getUserId() throws RemoteException;

    public String getTimestampSaved() throws RemoteException;
}
