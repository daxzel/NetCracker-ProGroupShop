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

     public static String getDBXSD()
    {
        return "\\static\\DB.xsd";
    }
}
