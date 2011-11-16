/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import helpers.EJBHelper;
import entityBeans.CatalogBeanRemoteHome;
import entityBeans.ImageBeanRemoteHome;
import entityBeans.OpinionBeanRemote;
import entityBeans.OpinionBeanRemoteHome;
import entityBeans.OrderBeanRemoteHome;
import entityBeans.ProductBeanRemote;
import entityBeans.ProductBeanRemoteHome;
import entityBeans.RoleBeanRemote;
import entityBeans.RoleBeanRemoteHome;
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
import java.util.List;
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
    private RoleBeanRemoteHome roleHome;
    private ProductBeanRemoteHome productHome;
    private ImageBeanRemoteHome imageHome;
    private OrderBeanRemoteHome orderHome;
    private OpinionBeanRemoteHome opinionHome;

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

    public String exportToXMLUser(ArrayList users, boolean needExportRole) throws EJBException {
        String result = "<error message = \"Sorry\" />";
        Document doc = new Document();
        Element root = new Element("EXPORT");
        doc.setRootElement(root);
        Set rids = new TreeSet();
        SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            if (needExportRole) {
                roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
            }
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
                    if (needExportRole) {
                        rids.add(new Long(user.getRoleId()));
                    }
                } catch (FinderException ex) {
                    throw new EJBException(ex);
                } catch (RemoteException ex) {
                    throw new EJBException(ex);
                }
            }
        }
        Iterator iter = rids.iterator();
        RoleBeanRemote role = null;
        while (iter.hasNext()) {
            try {
                role = roleHome.findByPrimaryKey(new Long(Long.parseLong(iter.next().toString())));
                Element roleNode = new Element("ROLE");
                root.addContent(roleNode);
                roleNode.setAttribute("ID_ROLE", (role.getId()).toString());
                roleNode.addContent((new Element("NAME")).setText(role.getName()));
            } catch (FinderException ex) {
                ex.printStackTrace();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        result = outputter.outputString(doc).toString();
        return result;
    }

    public String exportToXMLProduct(double price, boolean flag, boolean allFlag, boolean catalogFlag, boolean orderFlag, boolean commentFlag) throws EJBException {
        String result = "<error message = \"Sorry\" />";
       /* return result;
        Document doc = new Document();
        Element root = new Element("EXPORT");
        doc.setRootElement(root);
        Set uids = new TreeSet();
        Set comids = new TreeSet();
        Set catids = new TreeSet();
        Set oids = new TreeSet();
        Set rids = new TreeSet();
        SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            if (allFlag) {
                userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
                roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
                catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
                opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OrderBeanRemoteHome.class);
            } else {
                if (catalogFlag) {
                    catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                }
                if (orderFlag) {
                    orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
                    userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
                    roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
                }
                if (commentFlag) {
                    if (userHome == null) {
                        userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
                        roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
                    }
                    opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OrderBeanRemoteHome.class);
                }
            }

        } catch (NamingException ex) {
            throw new EJBException(ex);
        }
        List products = productHome.findByPrice(price, flag);
        for (int i = 0; i < products.size(); i++) {
            ProductBeanRemote product = (ProductBeanRemote)products.get(i);
            {

                try {
                    List opinions = opinionHome.findOpinionByProduct(new Long(product.getId()));
                    List orders = orderHome.findByProduct(new Long(product.getId()));
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
                    if (needExportRole) {
                        rids.add(new Long(user.getRoleId()));
                    }
                } catch (FinderException ex) {
                    throw new EJBException(ex);
                } catch (RemoteException ex) {
                    throw new EJBException(ex);
                }
            }
        }
        Iterator iter = rids.iterator();
        RoleBeanRemote role = null;
        while (iter.hasNext()) {
            try {
                role = roleHome.findByPrimaryKey(new Long(Long.parseLong(iter.next().toString())));
                Element roleNode = new Element("ROLE");
                root.addContent(roleNode);
                roleNode.setAttribute("ID_ROLE", (role.getId()).toString());
                roleNode.addContent((new Element("NAME")).setText(role.getName()));
            } catch (FinderException ex) {
                ex.printStackTrace();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        result = outputter.outputString(doc).toString();*/
        return result;
  }

    protected List findUsersByOpinions(List opinions) throws RemoteException, FinderException {
        Set uids = new TreeSet();
        for (int i = 0; i < opinions.size(); i++) {
            OpinionBeanRemote opinion = (OpinionBeanRemote) opinions.get(i);
            uids.add(new Long(opinion.getIdUser()));
        }
        return getListBySet(uids);
    }

    protected List getListBySet(Set set) throws FinderException, RemoteException {
        ArrayList list = new ArrayList();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            list.add(userHome.findByPrimaryKey((Long) iter.next()));
        }
        return list;
    }

    protected List findRoleByUsers(List users) throws RemoteException, FinderException {
        Set rids = new TreeSet();
        for (int i = 0; i < users.size(); i++) {
            UserBeanRemote user = (UserBeanRemote) users.get(i);
            rids.add(new Long(user.getRoleId()));
        }
        return getListBySet(rids);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public String exportAllProducts() throws Exception {
        entityBeans.ProductBeanRemoteHome productHome = (entityBeans.ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", entityBeans.ProductBeanRemoteHome.class);
        java.io.CharArrayWriter arrayWriter = new java.io.CharArrayWriter();
        java.io.PrintWriter writer = new java.io.PrintWriter(arrayWriter);
        helpers.XMLHelper.ProductToXml(productHome.findAll(), writer);
        return arrayWriter.toString();
    }

    public String importOfXML() {
        return null;
    }
}
