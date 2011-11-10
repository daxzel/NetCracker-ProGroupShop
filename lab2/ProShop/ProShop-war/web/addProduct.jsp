
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : product
    Created on : 06.10.2011, 20:35:21
    Author     : �����
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource, Other.JSPHelper,entityBeans.UserBeanRemote;" %>
<%@page errorPage="errorPage.jsp"%>
<%@page import="exceptions.LoginException"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add product</title>
    </head>
    <body>
        <%
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
                    String name = JSPHelper.getRequestOrEmpty(request, "NAME");
                    String description = JSPHelper.getRequestOrEmpty(request, "DESCRIPTION");
                    String price = JSPHelper.getRequestOrEmpty(request, "PRICE");
                    String name_catalog = JSPHelper.getRequestOrEmpty(request, "NAME_CATALOG");
        %>

        <form name="addProductForm" action="addProduct">
            <table>
                <tr><td>Имя</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="<%=name%>" size="20" /></td><td></td></tr>
                <tr><td>Описание</td><td></td></tr>
                <tr><td><input type="text" name="DESCRIPTION" value="<%=description%>" size="100" /></td><td></td></tr>
                <tr><td>Цена</td><td></td></tr>
                <tr><td><input type="text" name="PRICE" value="<%=price%>" size="20" /></td><td></td></tr>
                <tr><td>Название каталога</td><td></td></tr>
                <tr><td><input type="text" name="NAME_CATALOG" value="<%=name_catalog%>" size="10" /></td><td></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>

        </form>
        <%if (request.getAttribute("result") != null) {
        %><%=request.getAttribute("result")%><%
                    }
        %>
        <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
