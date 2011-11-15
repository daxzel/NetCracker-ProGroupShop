/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;
import javax.servlet.http.*;

/**
 *
 * @author Admin
 */
public class StaticResourceHelper {

    public static String getStaticDirectory(HttpServletRequest request)
    {
        return request.getContextPath();
    }

    public static String getProdudctsXSD()
    {
        return "\\static\\Products.xsd";
    }

    public static String getProdudctsXSLT()
    {
        return "\\static\\Products.xslt";
    }
        public static String getRolesXSLT()
    {
        return "\\static\\Roles.xslt";
    }
            public static String getUsersXSLT()
    {
        return "\\static\\Users.xslt";
    }
                public static String getCatalogsXSLT()
    {
        return "\\static\\Catalogs.xslt";
    }
                    public static String getOpinionsXSLT()
    {
        return "\\static\\Opinions.xslt";
    }
               public static String getImagesXSLT()
    {
        return "\\static\\Images.xslt";
    }
                    public static String getOrdersXSLT()
    {
        return "\\static\\Orders.xslt";
    }

     public static String getDBXSD()
    {
        return "\\static\\DB.xsd";
    }
}
