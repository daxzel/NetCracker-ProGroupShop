/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.sql.*;

/**
 *
 * @author Admin
 */
public interface ImageBeanRemoteHome extends EJBHome {
    
    entityBeans.ImageBeanRemote findByPrimaryKey(java.lang.Long key)  throws FinderException, RemoteException;

    public java.util.List findAll() throws FinderException, RemoteException;



    entityBeans.ImageBeanRemote create(long i_id_product,String i_name,byte[] picture, int i_width, int i_heaight) throws CreateException, RemoteException;

}
