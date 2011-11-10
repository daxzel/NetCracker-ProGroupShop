<%-- 
    Document   : del_image
    Created on : 09.10.2011, 16:02:48
    Author     : ололо
--%>

<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="exceptions.LoginException"%>
<%@page import="Other.JSPHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
  <form action="DelImageServlet">

            <table border="0">

                <tbody>
                    <tr>
                        <td>ПК изображения</td>
                        <td><input type="text" name="ID_IMG" value=""/> </td>
                    </tr>

                </tbody>
            </table>

            <input type="submit" value="OK" />
        </form>
         <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
