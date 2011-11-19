/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import java.sql.*;
import java.util.*;
import java.awt.Image;

/**
 *
 * @author Admin
 */
public interface ImageBeanRemote extends EJBObject {

    java.lang.Long getPk() throws RemoteException;

    long getId_img() throws RemoteException;

    void setId_img(long id) throws RemoteException;

    long getId_product() throws RemoteException;

    void setId_product(java.lang.Long id) throws RemoteException;

    byte[] getImage() throws RemoteException;

    void setImage(byte[] image) throws RemoteException;

    long getWidth() throws RemoteException;

    void setWidth(int i_width) throws RemoteException;

    long getHeaight() throws RemoteException;

    void setHeaight(int i_heaight) throws RemoteException;

    moreTools.SerializbleImage getImageI() throws RemoteException;

    void setImageI(moreTools.SerializbleImage image) throws RemoteException;

    void setAll(long i_id_product,String i_name, moreTools.SerializbleImage image,  int i_width, int i_heaight) throws RemoteException;

}
