<%-- 
    Document   : updateProduct
    Created on : 21.12.2011, 20:55:55
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
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
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%          String type = request.getParameter("DO");
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
                    Object result = request.getAttribute("result");

        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="/ProShop-war/static/logo.jpg">
                            </td>
                            <td class="team" align="center"><a href="aboutTeam.jsp">Команда</a></td>
                            <td class="user_nav" align="right"><%if (usr == null) {%><a href="login.jsp">Вход</a>   <a href="registration.jsp">Регистрация</a><%} else {%><a href="logout">Выход</a><%}%></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="cols">
                <div id="menu">
                    <div class="catalog">
                        <%=Menu.getMenu()%>
                    </div>
                    <div class="user_menu">
                        <%if (usr != null) {%>
                        <%=JSPHelper.getMenu(usr.getRoleId())%>
                        <%} else {%>
                        <%=JSPHelper.getMenu(4)%>
                        <%}%>
                    </div>
                </div>
                <div id="content">
                    <h1>Редактирование продукта</h1>
                    <% if ("select".equals(type)) {%>
                    <form action="selectProduct">
                        <table id="regOrLog">
                            <tr><td> Введите название продукта</td></tr>
                            <tr><td>  <input type="text" name="nameProduct" value="" size="60" /></td></tr>
                            <tr><td> <input type="submit" value=" Ввод " class="Button"/></td></tr>
                        </table>
                    </form>
                    <%                            } if("update".equals(type)){

                         ProductBeanRemote product = (ProductBeanRemote) session.getAttribute("product");
                         String name = "";
                         String description = "";
                         String price = "";
                         String nameCatalog = "";
                         if (product != null) {
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
                        <table id="regOrLog">
                            <tr><td>Имя</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="<%= name%>" size="80" /></td><td></td></tr>
                            <tr><td>Описание</td><td></td></tr>
                            <tr><td><input type="text" name="DESCRIPTION" value="<%= description%>" size="100" /></td><td></td></tr>
                            <tr><td>Цена</td><td></td></tr>
                            <tr><td><input type="text" name="PRICE" value="<%=price%>" size="20" /></td><td></td></tr>
                            <tr><td>Название каталога</td><td></td></tr>
                            <tr><td><input type="text" name="NAME_CATALOG" value="<%=nameCatalog%>" size="30" /></td><td></td></tr>
                            <tr><td><input type="submit" value=" Ввод " class="Button"/></td><td></td></tr>
                        </table>

                    </form>
                    <%}if (result instanceof String) {%>
                    <%=result%>
                    <%}
                                %>
                </div>
            </div>
        </div>
    </body>
</html>
