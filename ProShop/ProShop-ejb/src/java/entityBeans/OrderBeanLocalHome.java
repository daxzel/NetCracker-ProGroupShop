/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Yra
 */
public interface OrderBeanLocalHome extends EJBLocalHome {

    entityBeans.OrderBeanLocal findByPrimaryKey(java.lang.Long key) throws FinderException;
    
}
