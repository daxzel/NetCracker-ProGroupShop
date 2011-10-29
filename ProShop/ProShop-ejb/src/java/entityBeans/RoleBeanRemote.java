/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
/**
 *
 * @author Yra
 */
public interface RoleBeanRemote extends EJBObject {

    public Long getId() throws RemoteException;

    public String getName() throws RemoteException;
}
