/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import moreTools.HistoryMessage;

/**
 *
 * @author Yra
 */
public interface HistoryBeanRemote extends EJBObject {
  void addRecord(HistoryMessage msgobj) throws RemoteException;
}
