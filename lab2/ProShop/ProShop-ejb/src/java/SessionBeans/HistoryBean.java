/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import entityBeans.HistoryEntityBeanRemote;
import entityBeans.HistoryEntityBeanRemoteHome;
import entityBeans.UserBeanRemote;
import entityBeans.UserBeanRemoteHome;
import helpers.EJBHelper;
import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.NamingException;
import moreTools.HistoryMessage;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.ProcessingInstruction;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Yra
 */
public class HistoryBean implements SessionBean {

    private SessionContext context;
    HistoryEntityBeanRemoteHome historyHome1 = null;
    UserBeanRemoteHome userHome1 = null;

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
    
    public String exportToXML(String table, long id) throws EJBException {
        String result = "<error message = \"Sorry\" />";
        ProcessingInstruction instr = new ProcessingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"/ProShop-war/static/Histories.xsl\"");
        Document doc = new Document();        
        doc.addContent(instr);
        Element root = new Element("HISTORIES"); 
        doc.setRootElement(root);  
        try {
            historyHome1 = (HistoryEntityBeanRemoteHome) EJBHelper.lookupHome("ejb/HistoryEntityBean", HistoryEntityBeanRemoteHome.class);    
            userHome1 = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);            
        } catch (NamingException ex) {
            throw new EJBException(ex);
        }
        try {
            List hstrs = (List) historyHome1.findByIdObjAndNameTableP(new Long(id), table);             
            for (int i = 0; i < hstrs.size(); i++) {
                HistoryEntityBeanRemote hstr = (HistoryEntityBeanRemote) hstrs.get(i);
                UserBeanRemote usr = (UserBeanRemote) userHome1.findByPrimaryKey(new Long(hstr.getUserId()));
                root.addContent(createHistoryNode(hstr, usr));
            }
        } catch (FinderException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        result = outputter.outputString(doc).toString();
        return result;
    }

    public void addRecord(HistoryMessage msgobj) throws CreateException, RemoteException {
        try {
            if (msgobj.getObjId() != null && msgobj.getUserId() != null) {
                historyHome1.create(msgobj.getUserId().longValue(), msgobj.getNameTable(), msgobj.getMessage(), msgobj.getObjId().longValue());
            }
            if(msgobj.getUserId()==null){
                historyHome1.create(msgobj.getNameTable(), msgobj.getMessage(), msgobj.getObjId().longValue());
            }
            if(msgobj.getObjId()==null){
                historyHome1.create(msgobj.getUserId().longValue(),msgobj.getNameTable(), msgobj.getMessage());
            }
        } catch (CreateException ex) {
            System.out.println("Ошибка при записи истории");
        } catch (Exception ex) {
            ex.printStackTrace();
            return;

        }
    }
    
    protected Element createHistoryNode(HistoryEntityBeanRemote history, UserBeanRemote user) throws RemoteException {
        Element historyNode = new Element("HISTORY");
        historyNode.setAttribute("ID_HISTORY", (new Long(history.getRecordId())).toString());
        historyNode.addContent((new Element("NIK_USER")).setText("" + user.getNik())); //history.getUserId()));
        historyNode.addContent((new Element("NAME_TABLE")).setText("" + history.getNameTable()));
        historyNode.addContent((new Element("STATUS")).setText(history.getStatus()));
        historyNode.addContent((new Element("DATE_UPDATE")).setText(history.getTimestampSaved()));
        historyNode.addContent((new Element("ID_OBJECT")).setText(new Long(history.getObjId()).toString()));
        return historyNode;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
}
