<%-- 
    Document   : delProduct
    Created on : Dec 22, 2011, 7:22:11 PM
    Author     : пк
--%>

<%@page import="helpers.JSPHelper"%>
<%@page import="exceptions.LoginException"%>
<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Удаление продукта</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() >= 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
        <form action="delProduct">
            Введите название продукта или его ПК:
            <input type="text" name="VALUE" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%if (request.getAttribute("result") != null) {%>
        <%=request.getAttribute("result").toString()%>
        <%}%>

        <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
