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
public interface CatalogBeanLocalHome extends EJBLocalHome {

    entityBeans.CatalogBeanLocal findByPrimaryKey(java.lang.Long key) throws FinderException;

    public  entityBeans.CatalogBeanLocal findByName(java.lang.String name) throws FinderException;
    
}
