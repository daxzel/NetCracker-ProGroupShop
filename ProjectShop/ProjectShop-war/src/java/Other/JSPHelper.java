/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import DBClasses.*;
import exceptions.*;




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

    public static EnumRole Role(HttpSession sess)
    {
        Object user = sess.getAttribute("user");

        if (user==null)
        {
            return EnumRole.guest;
        }
        else
        {
            UserInterface usr = (UserInterface) sess.getAttribute("user");
            if(usr.getRoleId()==1)
            {
                return EnumRole.admin;
            }
            else
            {
                return EnumRole.user;
            }
        }

    }
    public static UserInterface getUser(HttpSession sess) throws LoginException
    {
        Object user = sess.getAttribute("user");

        if (user==null)
        {
            throw new LoginException();
        }
        else
        {
            UserInterface usr = (UserInterface) sess.getAttribute("user");
            if(usr.getRoleId()==1)
            {
                    return usr;
            }
            else
            {
                return usr;
            }
        }

    }
}
