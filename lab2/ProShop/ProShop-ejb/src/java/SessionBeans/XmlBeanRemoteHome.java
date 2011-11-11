/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 *
 * @author Yra
 */
public interface XmlBeanRemoteHome extends EJBHome {

    SessionBeans.XmlBeanRemote create()  throws CreateException, RemoteException;
    
}
