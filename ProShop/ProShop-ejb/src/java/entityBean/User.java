/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBean;
import java.rmi.RemoteException;
import javax.ejb.EJBObject;
/*
 * @author Yra
 */
public interface User  extends EJBObject {

    java.lang.Long getIdUser() throws RemoteException;

}
