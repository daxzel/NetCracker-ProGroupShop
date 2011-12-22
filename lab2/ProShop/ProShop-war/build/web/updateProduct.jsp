<%-- 
    Document   : updateProduct
    Created on : 21.12.2011, 20:55:55
    Author     : Yra
--%>

<%@page import="entityBeans.ProductBeanRemote"%>
<%@page import="exceptions.LoginException"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Обновление продукта</title>
    </head>
    <body>
        <%
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
                    Object result = request.getAttribute("result");
                    if (result == null) {
        %>
        <form action="selectProduct">
            Введите название продукта:
            <input type="text" name="nameProduct" value="" size="60" />
            <input type="submit" value="Input" />
        </form>
        <%                            } else {

                                ProductBeanRemote product = (ProductBeanRemote)session.getAttribute("product");
                                String name = "";
                                String description = "";
                                String price = "";
                                String nameCatalog = "";
                                if (product!=null) {
                                 //   product = (ProductBeanRemote) result;
                                    name = product.getName();
                                    description = product.getDescription();
                                    if (description == null) {
                                        description = "";
                                    }
                                    price = String.valueOf(product.getPrice());
                                    nameCatalog = product.getNameCatalog();
                                   // String g = product.getName();
                                 //   session.setAttribute("product", product);
                                }

        %>
        <form name="updateProductForm" action="updateProduct">
            <table>
                <tr><td>Имя</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="<%= name%>" size="80" /></td><td></td></tr>
                <tr><td>Описание</td><td></td></tr>
                <tr><td><input type="text" name="DESCRIPTION" value="<%= description%>" size="100" /></td><td></td></tr>
                <tr><td>Цена</td><td></td></tr>
                <tr><td><input type="text" name="PRICE" value="<%=price%>" size="20" /></td><td></td></tr>
                <tr><td>Название каталога</td><td></td></tr>
                <tr><td><input type="text" name="NAME_CATALOG" value="<%=nameCatalog%>" size="30" /></td><td></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>

        </form>
        <%if (result instanceof String) {%>
        <%=result%>
        <%}
                    }%>
        <br><p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
