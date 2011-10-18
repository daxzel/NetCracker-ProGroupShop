/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Other;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
public class JSPHelper {


    public static String getRequestOrEmpty(HttpServletRequest request,String name)
    {
        if(request.getAttribute(name)!=null)
        {
            return request.getAttribute(name).toString();
        }
        else
        {
            return "";
        }
    }
}
