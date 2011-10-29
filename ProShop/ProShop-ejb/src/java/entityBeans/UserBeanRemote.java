/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import javax.ejb.EJBObject;
import java.sql.Date;
import java.rmi.RemoteException;

/**
 *
 * @author Yra
 */
public interface UserBeanRemote extends EJBObject {

    public long getId() throws RemoteException;

    public void setId(java.lang.Long nid_user) throws RemoteException;

    public String getName() throws RemoteException;

    public void setName(String nname) throws RemoteException;

    public String getSurname() throws RemoteException;

    public void setSurname(String nsurname) throws RemoteException;

    public String getOtchestvo() throws RemoteException;

    public void setOtchestvo(String notchestvo) throws RemoteException;

    public String getNik() throws RemoteException;

    public void setNik(String nnik) throws RemoteException;

    public Date getBorn() throws RemoteException;

    public void setBorn(Date nborn) throws RemoteException;

    public String getPhone() throws RemoteException;

    public void setPhone(String nphone) throws RemoteException;

    public String getEmail() throws RemoteException;

    public void setEmail(String nemail) throws RemoteException;

    //public String getRoleName();
    public void setRoleId(java.lang.Long nid_role) throws RemoteException;

    public long getRoleId() throws RemoteException;

    public String getPassword() throws RemoteException;

     public void setPassword(String npassword) throws RemoteException;

}
