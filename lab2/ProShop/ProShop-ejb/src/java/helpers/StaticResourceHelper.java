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

    public static java.net.URL getBASEXSD(HttpServletRequest request) throws Exception
    {
        return new java.net.URL ("http://"+request.getRemoteHost()+":32722"+request.getContextPath()+"/static/BASE.xsd");
    }
}
