/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageBean;

import SessionBeans.HistoryBeanRemote;
import SessionBeans.HistoryBeanRemoteHome;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import moreTools.HistoryMessage;

/**
 *
 * @author Yra
 */
public class NewMessageBean implements MessageDrivenBean, MessageListener {

    private MessageDrivenContext context;
    HistoryBeanRemoteHome historyHome = null;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click on the + sign on the left to edit the code.">
    /**
     * @see javax.ejb.MessageDrivenBean#setMessageDrivenContext(javax.ejb.MessageDrivenContext)
     */
    public void setMessageDrivenContext(MessageDrivenContext aContext) {
        context = aContext;
    }

    /**
     * See section 15.4.4 of the EJB 2.0 specification
     * See section 15.7.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        try {
        

          //  historyHome = (HistoryBeanRemoteHome) ic.lookup("ejb/HistoryBean") ;
          historyHome = (HistoryBeanRemoteHome) helpers.EJBHelper.lookupHome("ejb/HistoryBean", HistoryBeanRemoteHome.class);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see javax.ejb.MessageDrivenBean#ejbRemove()
     */
    public void ejbRemove() {
        // TODO release any resource acquired in ejbCreate.
        // The code here should handle the possibility of not getting invoked
        // See section 15.7.3 of the EJB 2.1 specification
    }

    // </editor-fold>
    public void onMessage(Message aMessage) {
        try {
            HistoryMessage msgobj = null;
            try {
                ObjectMessage m = (ObjectMessage) aMessage;
                msgobj = (HistoryMessage) m.getObject();
            } catch (JMSException ex) {
                throw new EJBException(ex);
            }
            HistoryBeanRemote historyBean = null;
            try {
                historyBean = historyHome.create();
            } catch (CreateException ex) {
                throw new EJBException(ex);
            }
            historyBean.addRecord(msgobj);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;

        }
    }
}
