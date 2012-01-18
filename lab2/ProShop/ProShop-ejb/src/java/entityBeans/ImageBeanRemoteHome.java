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
import java.awt.*;

/**
 *
 * @author Admin
 */
public interface ImageBeanRemoteHome extends EJBHome {
    
    entityBeans.ImageBeanRemote findByPrimaryKey(java.lang.Long key)  throws FinderException, RemoteException;

    public entityBeans.ImageBeanRemote findByName(java.lang.String name) throws FinderException, RemoteException;

    public java.util.List findImageByProduct(java.lang.Long id_pr) throws FinderException, RemoteException;

    java.util.List findAll() throws FinderException, RemoteException;

    entityBeans.ImageBeanRemote create(long i_id_product,String i_name,  int i_width, int i_height) throws CreateException, RemoteException;

    entityBeans.ImageBeanRemote create(long id, long i_id_product,String i_name, int i_width, int i_height) throws CreateException, RemoteException;


}
