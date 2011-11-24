/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author Yra
 */
public interface HistoryBeanLocalHome extends EJBLocalHome {
    
    SessionBeans.HistoryBeanLocal create()  throws CreateException;

}
