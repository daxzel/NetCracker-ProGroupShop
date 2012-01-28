/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import entityBeans.CatalogBeanRemote;
import entityBeans.CatalogBeanRemoteHome;
import helpers.EJBHelper;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.FinderException;
import javax.naming.NamingException;
import moreTools.CatalogNode;

/**
 *
 * @author Yra
 */
public abstract class Menu {

    public static String MenuHtml;

    public static String getMenu() throws FinderException, RemoteException, NamingException {
        if (Menu.MenuHtml == null) {
          
                String htmlOut = "";
                CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                List list = catalogHome.findAll();
                HashMap map = new HashMap();

                for (int j = 0; j < list.size(); j++) {
                    CatalogNode catalog = new CatalogNode((CatalogBeanRemote) list.get(j));
                    catalog.accept(map);
                }
                CatalogNode ctg = (CatalogNode) map.get(new Long(1));
                String html = "<li id=\"initialCatalog\">" + ctg.ctg.getName() + "</li>" + "\r\n";
                for (int j = 0; j < ctg.children.size(); j++) {
                    CatalogNode ctg1 = (CatalogNode) ctg.children.get(j);
                    //     html = html+"<li><a href=\""+ "getProductsByCatalog?ID="+ctg1.ctg.getId() +"\">"+ctg1.ctg.getName()+"</a>"+"\r\n";
                    html = html + ctg1.getHtml();
                    //   html=html+"</li>"+"\r\n";
                }
                map = null;
                ctg = null;


                htmlOut = "<ul id=\"nav\"  class=\"dropdown dropdown-vertical\">\r\n"
                        + html
                        + "</ul>\r\n";
                Menu.MenuHtml = htmlOut;
        
        }
        return MenuHtml;
    }

    public static void destroyMenu() {
        MenuHtml = null;
    }
}
