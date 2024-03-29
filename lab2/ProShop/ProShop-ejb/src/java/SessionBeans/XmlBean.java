/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import entityBeans.CatalogBeanRemote;
import helpers.EJBHelper;
import entityBeans.CatalogBeanRemoteHome;
import entityBeans.ImageBeanRemoteHome;
import entityBeans.OpinionBeanRemote;
import entityBeans.OpinionBeanRemoteHome;
import entityBeans.OrderBeanRemote;
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
import org.jdom.ProcessingInstruction;
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
    
    public String exportToXMLUsersP(String name, int id_role, boolean flag1, boolean flag2, 
            boolean rolesFlag, boolean opinionsFlag, boolean productsFlag, boolean ordersFlag, boolean catalogsFlag) throws EJBException {
        String result = "<error message = \"Sorry\" />";
        ProcessingInstruction instr = new ProcessingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"/ProShop-war/static/Products.xsl\"");

        Document doc = new Document();
        doc.addContent(instr);
        Element root = new Element("BASE"); 
        doc.setRootElement(root);        
        try {
            userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            if (rolesFlag) {
                roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
            }
            if (opinionsFlag) {
                opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
            }
            if (productsFlag) {
                productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
                orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            }
            if (ordersFlag) {
                orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            }
            if (catalogsFlag) {
                catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
                orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            }
        } catch (NamingException ex) {
            throw new EJBException(ex);
        }
        try {
            if (flag1 & flag2) {
                List users = (List) userHome.findByNameAndRole(name, id_role);
                if (rolesFlag) {
                    List rls = findRoleByUsers(users);
                    for (int i = 0; i < rls.size(); i++) {
                        RoleBeanRemote rl = (RoleBeanRemote) rls.get(i);
                        root.addContent(createRoleNode(rl));
                    }
                }
                for (int i = 0; i < users.size(); i++) {
                    UserBeanRemote usr = (UserBeanRemote) users.get(i);
                    {
                        root.addContent(createUserNode(usr));
                    }
                }
                if (catalogsFlag) {
                    List ctls = findCatalogByUsers(users);
                    for (int i = 0; i < ctls.size(); i++) {
                        CatalogBeanRemote ctl = (CatalogBeanRemote) ctls.get(i);
                        root.addContent(createCatalogNode(ctl));
                    }
                }
                if (productsFlag) {
                    List prds = findProductByUsers(users);
                    for (int i = 0; i < prds.size(); i++) {
                        ProductBeanRemote prd = (ProductBeanRemote) prds.get(i);
                        root.addContent(createProductNode(prd));
                    }
                }
                if (opinionsFlag) {
                    List opns = findOpinionByUsers(users);
                    for (int i = 0; i < opns.size(); i++) {
                        OpinionBeanRemote opn = (OpinionBeanRemote) opns.get(i);
                        root.addContent(createOpinionNode(opn));
                    }
                }
                if (ordersFlag) {
                    List ords = findOrderByUsers(users);
                    for (int i = 0; i < ords.size(); i++) {
                        OrderBeanRemote ord = (OrderBeanRemote) ords.get(i);
                        root.addContent(createOrderNode(ord));
                    }
                }
            }    
            if (flag1 & !flag2) {
                List users = (List) userHome.findByName(name);
                if (rolesFlag) {
                    List rls = findRoleByUsers(users);
                    for (int i = 0; i < rls.size(); i++) {
                        RoleBeanRemote rl = (RoleBeanRemote) rls.get(i);
                        root.addContent(createRoleNode(rl));
                    }
                }
                for (int i = 0; i < users.size(); i++) {
                    UserBeanRemote usr = (UserBeanRemote) users.get(i);
                    {
                        root.addContent(createUserNode(usr));
                    }
                }
                if (catalogsFlag) {
                    List ctls = findCatalogByUsers(users);
                    for (int i = 0; i < ctls.size(); i++) {
                        CatalogBeanRemote ctl = (CatalogBeanRemote) ctls.get(i);
                        root.addContent(createCatalogNode(ctl));
                    }
                }
                if (productsFlag) {
                    List prds = findProductByUsers(users);
                    for (int i = 0; i < prds.size(); i++) {
                        ProductBeanRemote prd = (ProductBeanRemote) prds.get(i);
                        root.addContent(createProductNode(prd));
                    }
                }
                if (opinionsFlag) {
                    List opns = findOpinionByUsers(users);
                    for (int i = 0; i < opns.size(); i++) {
                        OpinionBeanRemote opn = (OpinionBeanRemote) opns.get(i);
                        root.addContent(createOpinionNode(opn));
                    }
                }
                if (ordersFlag) {
                    List ords = findOrderByUsers(users);
                    for (int i = 0; i < ords.size(); i++) {
                        OrderBeanRemote ord = (OrderBeanRemote) ords.get(i);
                        root.addContent(createOrderNode(ord));
                    }
                }
            }
            if (!flag1 & flag2) {
                List users = (List) userHome.findByRole(new Long(id_role));
                if (rolesFlag) {
                    List rls = findRoleByUsers(users);
                    for (int i = 0; i < rls.size(); i++) {
                        RoleBeanRemote rl = (RoleBeanRemote) rls.get(i);
                        root.addContent(createRoleNode(rl));
                    }
                }
                for (int i = 0; i < users.size(); i++) {
                    UserBeanRemote usr = (UserBeanRemote) users.get(i);
                    {
                        root.addContent(createUserNode(usr));
                    }
                }
                if (catalogsFlag) {
                    List ctls = findCatalogByUsers(users);
                    for (int i = 0; i < ctls.size(); i++) {
                        CatalogBeanRemote ctl = (CatalogBeanRemote) ctls.get(i);
                        root.addContent(createCatalogNode(ctl));
                    }
                }
                if (productsFlag) {
                    List prds = findProductByUsers(users);
                    for (int i = 0; i < prds.size(); i++) {
                        ProductBeanRemote prd = (ProductBeanRemote) prds.get(i);
                        root.addContent(createProductNode(prd));
                    }
                }
                if (opinionsFlag) {
                    List opns = findOpinionByUsers(users);
                    for (int i = 0; i < opns.size(); i++) {
                        OpinionBeanRemote opn = (OpinionBeanRemote) opns.get(i);
                        root.addContent(createOpinionNode(opn));
                    }
                }
                if (ordersFlag) {
                    List ords = findOrderByUsers(users);
                    for (int i = 0; i < ords.size(); i++) {
                        OrderBeanRemote ord = (OrderBeanRemote) ords.get(i);
                        root.addContent(createOrderNode(ord));
                    }
                }
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

    public String exportToXMLUser(ArrayList users, boolean needExportRole) throws EJBException {
        String result = "<error message = \"Sorry\" />";
        Document doc = new Document();
        Element root = new Element("BASE");
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

    public String exportToXMLProduct(List products,  boolean allFlag, boolean catalogFlag, boolean orderFlag, boolean commentFlag) throws EJBException {
        String result = "<error message = \"Sorry\" />";

        Document doc = new Document();
        ProcessingInstruction instr = new ProcessingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"/ProShop-war/static/Products.xsl\"");

        Element root = new Element("BASE");
        doc.addContent(instr);
        doc.setRootElement(root);
        Set uids = new TreeSet();
        List opinions = new ArrayList();
        Set catids = new TreeSet();
        List orders = new ArrayList();
        Set rids = new TreeSet();
        UserBeanRemote user = null;
        RoleBeanRemote role = null;
        CatalogBeanRemote catalog = null;
        SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            if (allFlag) {
                userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
                roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
                catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
                opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
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
                    opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
                }
            }

        } catch (NamingException ex) {
            throw new EJBException(ex);
        }
        try {
          
            for (int i = 0; i < products.size(); i++) {
                ProductBeanRemote product = (ProductBeanRemote) products.get(i);
                {
                    if (allFlag || commentFlag) {
                        List opinionsList = opinionHome.findOpinionByProduct(new Long(product.getId()));

                        opinions.addAll(opinionsList);
                        uids.addAll(findUsersByOpinions(opinionsList, uids));
                    }
                    if(allFlag||orderFlag){
                    List ordersList = orderHome.findByProduct(new Long(product.getId()));
                    orders.addAll(ordersList);

                    uids.addAll(findUsersByOrders(ordersList, uids));
                    }
                    if(allFlag||catalogFlag){
                    catids.add(new Long(product.getIdCatalog()));
                    }
                }
            }
            Iterator iter = uids.iterator();
            while (iter.hasNext()) {
                user = userHome.findByPrimaryKey(new Long(iter.next().toString()));
                rids.add(new Long(user.getRoleId()));
                if(rids.size()==3){
                    
                }
            }
            iter = rids.iterator();
            while (iter.hasNext()) {
                role = roleHome.findByPrimaryKey(new Long(iter.next().toString()));
                Element roleNode = createRoleNode(role);
                root.addContent(roleNode);

            }
            iter = uids.iterator();
            while (iter.hasNext()) {
                user = userHome.findByPrimaryKey(new Long(iter.next().toString()));
                Element userNode = createUserNode(user);
                root.addContent(userNode);
                rids.add(new Long(user.getRoleId()));
            }
            iter = catids.iterator();
            while (iter.hasNext()) {
                catalog = catalogHome.findByPrimaryKey(new Long(iter.next().toString()));
                Element catalogNode = createCatalogNode(catalog);
                root.addContent(catalogNode);
            }
            for (int i = 0; i < products.size(); i++) {
                ProductBeanRemote product = (ProductBeanRemote) products.get(i);
                {
                    root.addContent(createProductNode(product));
                }
            }
            iter = opinions.iterator();
            while (iter.hasNext()) {
                Element opinionNode = createOpinionNode((OpinionBeanRemote) iter.next());
                root.addContent(opinionNode);
            }
            iter = orders.iterator();
            while (iter.hasNext()) {
                Element orderNode = createOrderNode((OrderBeanRemote) iter.next());
                root.addContent(orderNode);
            }
        } catch (FinderException ex) {
            throw new EJBException(ex);
        } catch (RemoteException ex) {
            throw new EJBException(ex);
        }
 
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        result = outputter.outputString(doc).toString();
        return result;
    }

    protected Element createUserNode(UserBeanRemote user) throws RemoteException {
        SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
        Element userNode = new Element("USER");
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
        return userNode;
    }

    protected Element createProductNode(ProductBeanRemote product) throws RemoteException {
        Element productNode = new Element("PRODUCT");
        productNode.setAttribute("ID_PRODUCT", (new Long(product.getId())).toString());
        productNode.addContent((new Element("DESCRIPTION")).setText(product.getDescription()));
        productNode.addContent((new Element("ID_CATALOG")).setText("" + product.getIdCatalog()));
        productNode.addContent((new Element("NAME")).setText(product.getName()));
        productNode.addContent((new Element("PRICE")).setText("" + product.getPrice()));
        return productNode;
    }

    protected Element createOpinionNode(OpinionBeanRemote opinion) throws RemoteException {
        Element opinionNode = new Element("OPINION");
        opinionNode.setAttribute("ID_OPINION", (new Long(opinion.getIdOpinion())).toString());
        opinionNode.addContent((new Element("ID_USER")).setText("" + opinion.getIdUser()));
        opinionNode.addContent((new Element("ID_PRODUCT")).setText("" + opinion.getIdProduct()));
        opinionNode.addContent((new Element("TEXT")).setText(opinion.getText()));
        return opinionNode;
    }

    protected Element createOrderNode(OrderBeanRemote order) throws RemoteException {
        Element orderNode = new Element("ORDER");
        orderNode.setAttribute("ID_ORDER", (new Long(order.getId())).toString());
        orderNode.addContent((new Element("ID_USER")).setText("" + order.getIdUser()));
        orderNode.addContent((new Element("ID_PRODUCT")).setText("" + order.getIdProduct()));
        orderNode.addContent((new Element("STATUS")).setText("" + order.getStatus()));
        orderNode.addContent((new Element("KOL_VO")).setText("" + order.getAmount()));
        return orderNode;
    }

    protected Element createCatalogNode(CatalogBeanRemote catalog) throws RemoteException {
        Element catalogNode = new Element("CATALOG");
        catalogNode.setAttribute("ID_CATALOG", (new Long(catalog.getId())).toString());
        catalogNode.addContent((new Element("ID_PARENT")).setText("" + catalog.getParentId()));
        catalogNode.addContent((new Element("NAME")).setText(catalog.getName()));
        return catalogNode;
    }

    protected Element createRoleNode(RoleBeanRemote role) throws RemoteException {
        Element roleNode = new Element("ROLE");        
        roleNode.setAttribute("ID_ROLE", (role.getId()).toString());
        roleNode.addContent((new Element("NAME")).setText(role.getName()));
        return roleNode;
    }

    protected Set findUsersByOpinions(List opinions, Set uids) throws RemoteException, FinderException {
        for (int i = 0; i < opinions.size(); i++) {
            OpinionBeanRemote opinion = (OpinionBeanRemote) opinions.get(i);
            uids.add(new Long(opinion.getIdUser()));
        }
        return uids;
    }

    protected Set findUsersByOrders(List orders, Set uids) throws RemoteException, FinderException {
        for (int i = 0; i < orders.size(); i++) {
            OrderBeanRemote order = (OrderBeanRemote) orders.get(i);
            uids.add(new Long(order.getIdUser()));
        }
        return uids;
    }

    protected List getListRolesBySet(Set set) throws FinderException, RemoteException {
        ArrayList list = new ArrayList();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            list.add(roleHome.findByPrimaryKey((Long) iter.next()));
        }
        return list;
    }
    
    protected List getListOpinionsBySet(Set set) throws FinderException, RemoteException {
        ArrayList list = new ArrayList();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            list.add(opinionHome.findByPrimaryKey((Long) iter.next()));
        }
        return list;
    }
    
    protected List getListOrdersBySet(Set set) throws FinderException, RemoteException {
        ArrayList list = new ArrayList();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            list.add(orderHome.findByPrimaryKey((Long) iter.next()));
        }
        return list;
    }
    
    protected List getListProductsBySet(Set set) throws FinderException, RemoteException {
        ArrayList list = new ArrayList();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            list.add(productHome.findByPrimaryKey((Long) iter.next()));
        }
        return list;
    }
    
    protected List getListCatalogsBySet(Set set) throws FinderException, RemoteException {
        ArrayList list = new ArrayList();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            list.add(catalogHome.findByPrimaryKey((Long) iter.next()));
        }
        return list;
    }

    protected List findRoleByUsers(List users) throws RemoteException, FinderException {
        Set rids = new TreeSet();
        UserBeanRemote user;
        for (int i = 0; i < users.size(); i++) {
            user = (UserBeanRemote) users.get(i);
            rids.add(new Long(user.getRoleId()));
        }
        return getListRolesBySet(rids);
    }
    
    protected List findOpinionByUsers(List users) throws RemoteException, FinderException {
        Set oids = new TreeSet();
        long id_user = 0; 
        List opinions;
        UserBeanRemote user;
        OpinionBeanRemote opinion;
        for (int i = 0; i < users.size(); i++) {
            user = (UserBeanRemote) users.get(i);
            id_user = user.getId();
            opinions = opinionHome.findOpinionByUser(new Long (id_user));
            for (int t = 0; t < opinions.size(); t++) {
                opinion = (OpinionBeanRemote) opinions.get(t);  
                oids.add(new Long(opinion.getIdOpinion()));
            }            
        }        
        return getListOpinionsBySet(oids);
    }
    
    protected List findOrderByUsers(List users) throws RemoteException, FinderException {
        Set oids = new TreeSet();
        long id_user = 0; 
        List orders;
        UserBeanRemote user;
        OrderBeanRemote order;
        for (int i = 0; i < users.size(); i++) {
            user = (UserBeanRemote) users.get(i);
            id_user = user.getId();
            orders = orderHome.findOrderByUser(new Long (id_user));
            for (int t = 0; t < orders.size(); t++) {
                order = (OrderBeanRemote) orders.get(t);  
                oids.add(new Long(order.getId()));
            }            
        }        
        return getListOrdersBySet(oids);
    }
    
    protected List findProductByUsers(List users) throws RemoteException, FinderException {
        Set pids = new TreeSet();
        List orders = findOrderByUsers(users);
        List opinions = findOpinionByUsers(users);
        OrderBeanRemote order;
        OpinionBeanRemote opinion;
        List products;
        for (int i = 0; i < orders.size(); i++) {
            order = (OrderBeanRemote) orders.get(i);             
            //productHome.findByPrimaryKey(order.getIdProduct()).getId();
            pids.add(order.getIdProduct());
        }
        for (int t = 0; t < opinions.size(); t++) {
            opinion = (OpinionBeanRemote) opinions.get(t);             
            //productHome.findByPrimaryKey(order.getIdProduct()).getId();
            pids.add(new Long (opinion.getIdProduct()));
        }
        return getListProductsBySet(pids);
    }
    
     protected List findCatalogByUsers(List users) throws RemoteException, FinderException {
        Set cids = new TreeSet();
        List products = findProductByUsers(users);        
        ProductBeanRemote product;        
        List catalogs;
        for (int i = 0; i < products.size(); i++) {
            product = (ProductBeanRemote) products.get(i);             
            //productHome.findByPrimaryKey(order.getIdProduct()).getId();
            cids.add(new Long (product.getIdCatalog()));
        }        
        return getListCatalogsBySet(cids);
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
