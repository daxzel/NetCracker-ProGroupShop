/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.rmi.RemoteException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import exceptions.*;
import entityBeans.UserBeanRemote;

/**
 *
 * @author Admin
 */
public class JSPHelper {

    public static String getRequestOrEmpty(HttpServletRequest request, String name) {
        if (request.getAttribute(name) != null) {
            return request.getAttribute(name).toString();
        } else {
            return "";
        }
    }

    public static UserBeanRemote getUser2(HttpSession sess) throws LoginException {
        Object user = sess.getAttribute("user");

        if (user == null) {
            throw new LoginException();
        } else {
            try {
                UserBeanRemote usr = (UserBeanRemote) sess.getAttribute("user");
                if (usr.getRoleId() == 1) {
                    return usr;
                } else {
                    return usr;
                }
            } catch (RemoteException ex) {
                throw new LoginException("Произошла ошибка повторите попытку");
            }
        }

    }
}
