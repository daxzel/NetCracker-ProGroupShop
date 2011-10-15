/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;
import java.util.Date;

/**
 *
 * @author Yra
 */
public interface UserInterface extends DBClassInterface {

public String getName();

public String getSurname();

public String getOtchestvo();

public String getNik();

public Date getBorn();

public String getPhone();

public String getEmail();

public String getRoleName();

public int getRoleId();

public boolean getLogin();

}
