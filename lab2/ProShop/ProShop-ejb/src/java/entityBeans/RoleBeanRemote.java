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

    Long getId() throws RemoteException;

    String getName() throws RemoteException;

    void setAll(String name) throws RemoteException;

    void sendMessage(Long id_user,  String nameTables, String message, Long id_obj, int prior) throws RemoteException;

    
}
