/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Admin
 */
public interface ImageBeanLocalHome extends EJBLocalHome {

    entityBeans.ImageBeanLocal findByPrimaryKey(java.lang.Long key)  throws FinderException;

}
