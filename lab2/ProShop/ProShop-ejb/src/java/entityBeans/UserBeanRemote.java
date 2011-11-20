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

    long getId() throws RemoteException;

    void setId(java.lang.Long nid_user) throws RemoteException;

    String getName() throws RemoteException;

    void setName(String nname) throws RemoteException;

    String getSurname() throws RemoteException;

    void setSurname(String nsurname) throws RemoteException;

    String getOtchestvo() throws RemoteException;

    void setOtchestvo(String notchestvo) throws RemoteException;

    String getNik() throws RemoteException;

    void setNik(String nnik) throws RemoteException;

    Date getBorn() throws RemoteException;

    void setBorn(Date nborn) throws RemoteException;

    String getPhone() throws RemoteException;

    void setPhone(String nphone) throws RemoteException;

    String getEmail() throws RemoteException;

    void setEmail(String nemail) throws RemoteException;

    //public String getRoleName();
    void setRoleId(java.lang.Long nid_role) throws RemoteException;

    long getRoleId() throws RemoteException;

    String getPassword() throws RemoteException;

    void setPassword(String npassword) throws RemoteException;

    void setAll(String name, String surname, String otchestvo, String nik, String password,
            java.sql.Date born, String phone, String email, long id_role) throws RemoteException;

}
