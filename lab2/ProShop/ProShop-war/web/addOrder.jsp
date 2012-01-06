<%-- 
    Document   : addOrder
    Created on : 22.10.2011, 22:22:47
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "entityBeans.UserBeanRemote,entityBeans.ProductBeanRemote,entityBeans.OrderBeanRemote, helpers.*;"%>
<%@page errorPage="errorPage.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />

        <title>Order</title>
    </head>
    <body><%
                UserBeanRemote usr = JSPHelper.getUser2(session);

                ProductBeanRemote prd = null;
                String kol_vo = "";
                if (session.getAttribute("product") != null) {
                    prd = (ProductBeanRemote) session.getAttribute("product");
                    session.setAttribute("ID_PRODUCT", new Long(prd.getId()));
                    if (request.getAttribute("kol_vo") != null) {
                        kol_vo = request.getAttribute("kol_vo").toString();
                    }
                    //request.setAttribute("NIK", user.getNik());
                }
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
                    <form name="myForm" action="order">
                        <table>
                            <tr><td>Название продукта:</td>
                            <td><a href ="product?NAME=<%=prd.getName()%>"><%= prd.getName()%></a></td>
                            <tr><td>Цена:</td>
                            <td><%=prd.getPrice()%></td></tr>
                            <tr><td>Количество:</td><td><input type="text" name="KOL" value="<%=kol_vo%>" size="25" /></td></tr>
                           
                            <tr><td><input type="submit" value=" Ввод " class="Button"/></td><td></td></tr>
                        </table>
                    </form>
                    <% if (request.getAttribute("result") != null) {%>
                    <%=request.getAttribute("result")%>
                    <%}%>
                </div>
            </div>
        </div>
    </body>
</html>
