<%-- 
    Document   : deleteUser
    Created on : 12.10.2011, 13:15:57
    Author     : Yra
--%>

<%@page import="exceptions.LoginException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "entityBeans.UserBeanRemote, helpers.*"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Удаление пользователя</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() >= 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
        <form action="deleteUser">
            Введите ник:
            <input type="text" name="NIK" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%if (request.getAttribute("result") != null) {%>
        <%=request.getAttribute("result").toString()%>
        <%}%>

        <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
