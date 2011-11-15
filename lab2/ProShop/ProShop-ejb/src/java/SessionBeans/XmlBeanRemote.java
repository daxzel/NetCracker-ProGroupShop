/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.ejb.EJBObject;

/**
 *
 * @author Yra
 */
public interface XmlBeanRemote extends EJBObject {

    public String exportToXMLUser(ArrayList users, boolean needExportAll) throws RemoteException;

    public String exportAllProducts() throws RemoteException,Exception;

    public String importOfXML() throws RemoteException;
}
