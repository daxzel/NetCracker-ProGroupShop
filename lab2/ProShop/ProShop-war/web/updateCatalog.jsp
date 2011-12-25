<%-- 
    Document   : updateCatalog
    Created on : 25.12.2011, 17:25:21
    Author     : Yra
--%>

<%@page import="entityBeans.CatalogBeanRemote"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>обновление каталога</title>
    </head>
    <body>
        <%
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
                    String type = request.getParameter("DO");
                    Object result = request.getAttribute("result");
                    if ("select".equals(type) || (type == null)) {

        %>
        <form action="selectCatalog">
            Введите название каталога:
            <input type="text" name="nameCatalog" value="" size="60" />
            <input type="submit" value="Input" />
        </form>
        <%                      } else {
                                CatalogBeanRemote catalog = (CatalogBeanRemote) session.getAttribute("catalog");
                                String name = "";
                                String parentName = "";
                                String price = "";
                                String nameCatalog = "";
                                if (catalog != null) {
                                    //   product = (ProductBeanRemote) result;
                                    name = catalog.getName();
                                    parentName = catalog.getParentName();
                                    if (parentName == null) {
                                        parentName = "";
                                    }

                                    // String g = product.getName();
                                    //   session.setAttribute("product", product);
                                }

        %>
        <form name="updateCatalogForm" action="updateCatalog">
            <table>
                <tr><td>Имя</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="<%= name%>" size="80" /></td><td></td></tr>
                <tr><td>Имя родительского каталога </td><td></td></tr>
                <tr><td><input type="text" name="PARENT" value="<%= parentName%>" size="100" /></td><td></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>

        </form>
        <%
                    }%>
        <%if (result instanceof String) {%>
        <%=result%>
        <%}%>
        <br><p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
