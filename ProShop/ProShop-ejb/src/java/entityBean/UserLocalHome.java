/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBean;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import java.sql.Date;

/**
 *
 * @author Yra
 */
public interface UserLocalHome extends EJBLocalHome {

    public entityBean.UserLocal findByPrimaryKey(java.lang.Long key) throws FinderException,EJBException, ObjectNotFoundException;;

    public java.util.Collection findAll() throws FinderException, EJBException;

    public entityBean.UserLocal findByNik(String nik) throws FinderException, EJBException;

    public java.util.Collection findByRole(java.lang.Long id_role) throws FinderException, EJBException;

  //  public entityBean.UserLocal create(String name, String surname, String otchestvo, String nik, String password, Date born, String phone, String email, long id_role) throws CreateException;
    
}
