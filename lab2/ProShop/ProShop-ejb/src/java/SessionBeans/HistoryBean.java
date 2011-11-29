/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import entityBeans.HistoryEntityBeanRemoteHome;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.NamingException;
import moreTools.HistoryMessage;

/**
 *
 * @author Yra
 */
public class HistoryBean implements SessionBean {
    
    private SessionContext context;
    HistoryEntityBeanRemoteHome historyHome1 = null;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;

    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations

    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    
    // </editor-fold>;

    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        try {
            historyHome1 = (HistoryEntityBeanRemoteHome) helpers.EJBHelper.lookupHome("ejb/HistoryEntityBean", HistoryEntityBeanRemoteHome.class);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public void addRecord(HistoryMessage msgobj) throws CreateException, RemoteException {
        try {
            historyHome1.create(msgobj.getUserId().longValue(), msgobj.getNameTable(), msgobj.getMessage(), msgobj.getObjId().longValue());
        } catch (CreateException ex) {
            System.out.println("Ошибка при записи истории");
        }catch (Exception ex) {
            ex.printStackTrace();
            return;

        }
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    
}
