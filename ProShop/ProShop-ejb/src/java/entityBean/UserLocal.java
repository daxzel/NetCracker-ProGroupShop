/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBean;

import java.sql.Date;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Yra
 */
public interface UserLocal extends EJBLocalObject {
     public long getId();

    public void setId(long nid_user);

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

    public void setRoleId(long nid_role);

    public long getRoleId();
}
