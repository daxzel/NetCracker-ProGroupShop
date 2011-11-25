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
public interface HistoryEntityBeanLocalHome extends EJBLocalHome {

    entityBeans.HistoryEntityBeanLocal findByPrimaryKey(java.lang.Long key) throws FinderException;
    
}
