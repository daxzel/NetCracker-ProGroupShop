/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import OtherBean.Helper;
import entityBeans.CatalogBeanRemoteHome;
import entityBeans.UserBeanRemote;
import entityBeans.UserBeanRemoteHome;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Yra
 */
public class XmlBean implements SessionBean {

    private SessionContext context;
    private UserBeanRemoteHome userHome;
    private CatalogBeanRemoteHome catalogHome;

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
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
    }

    public String exportToXML(ArrayList users) throws EJBException {
        String result = "<error message = \"Sorry\" />";
        Document doc = new Document();
        Element root = new Element("EXPORT");
        doc.setRootElement(root);
        SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            userHome = (UserBeanRemoteHome) Helper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
        } catch (NamingException ex) {
            throw new EJBException(ex);
        }

        for (int i = 0; i < users.size(); i++) {
            UserBeanRemote user = null;
            {
                try {
                    user = userHome.findByPrimaryKey((Long) users.get(i));
                    Element userNode = new Element("USER");
                    root.addContent(userNode);

                    userNode.setAttribute("ID_USER", (new Long(user.getId())).toString());
                    userNode.addContent((new Element("NAME")).setText(user.getName()));
                    userNode.addContent((new Element("SURNAME")).setText(user.getSurname()));
                    userNode.addContent((new Element("OTCHESTVO")).setText(user.getOtchestvo()));
                    userNode.addContent((new Element("NIK")).setText(user.getNik()));
                    userNode.addContent((new Element("PASSWORD")).setText(user.getPassword()));
                    userNode.addContent((new Element("BORN")).setText(formt.format(user.getBorn())));
                    userNode.addContent((new Element("PHONE")).setText(user.getPhone()));
                    userNode.addContent((new Element("EMAIL")).setText(user.getEmail()));
                    userNode.addContent((new Element("ID_ROLE")).setText((new Long(user.getRoleId())).toString()));

                } catch (FinderException ex) {
                    throw new EJBException(ex);
                } catch (RemoteException ex) {
                    throw new EJBException(ex);
                }
            }
        }
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        result = outputter.outputString(doc).toString();
        return result;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
}
