/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import javax.ejb.EJBLocalObject;
import java.sql.Date;
/**
 *
 * @author Yra
 */
public interface UserBeanLocal extends EJBLocalObject {
      public long getId() ;

    public void setId(java.lang.Long nid_user);

    public String getName();

    public void setName(String nname);

    public String getSurname();

    public void setSurname(String nsurname);

    public String getOtchestvo();

    public void setOtchestvo(String notchestvo);

    public String getNik();

    public void setNik(String nnik);

    public Date getBorn();

    public void setBorn(Date nborn);

    public String getPhone();

    public void setPhone(String nphone);

    public String getEmail();

    public void setEmail(String nemail);

    //public String getRoleName();
    public void setRoleId(java.lang.Long nid_role);

    public long getRoleId();

    public String getPassword();

     public void setPassword(String npassword);
}
