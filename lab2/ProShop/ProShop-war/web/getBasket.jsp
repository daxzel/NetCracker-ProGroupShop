<%-- 
    Document   : getBasket
    Created on : 23.10.2011, 17:19:19
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "entityBeans.UserBeanRemote,entityBeans.OrderBeanRemote, helpers.*,java.util.*"%>
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

        <title>Корзина/заказы</title>
    </head>
    <body>
        <%UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (request.getAttribute("result") instanceof List) {
                        List list = (List) request.getAttribute("result");


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
                        <%=JSPHelper.getMenu(3)%>
                        <%}%>
                    </div>
                </div>
                <div id="content">
                    <h1></h1>
                    <%   if (list.isEmpty()) {%>
                    <p align="center">У вас нет заказов<br>
                    <p align="center"><a href="getFull_catalog">Каталог</a></p>
                    <%} else {
                                                OrderBeanRemote ord = (OrderBeanRemote) list.get(0);
                    %>
                    <table align="center"  border="1" width="100%">
                        <tr align="center">

                            <td width="15%" align="center">Название продукта</td>
                            <td width="25%" align="center">Цена</td>
                            <td width="25%" align="center">Количество</td>
                            <td width="20%" align="center">Цена заказа</td>
                            <%if (!ord.getStatus()) {%>
                            <td width="20%" align="center">Оформить заказ</td>
                            <td width="20%" align="center">Удалить заказ</td>
                            <%}%>
                        </tr>
                        <%
                                                    double priceProduct;
                                                    int amount;
                                                    for (int i = 0; i <= (list.size() - 1); i++) {
                                                        ord = (OrderBeanRemote) list.get(i);
                                                        priceProduct = ord.getPriceProduct();
                                                        amount = ord.getAmount();
                        %>
                        <tr align="center">
                            <td><a href ="product?ID=<%=ord.getIdProduct().longValue()%>"><%= ord.getNameProduct()%></a></td>
                            <td><%= priceProduct%></td>
                            <td><%=  amount%></td>
                            <td><%= (priceProduct) * (amount)%></td>
                            <%if (!ord.getStatus()) {%>
                            <td><a href ="updateStatusOrder?id_order=<%=ord.getId()%>">оформить заказ</a></td>
                            <td><a href ="deleteOrder?id_order=<%=ord.getId()%>">удалить заказ</a></td>
                            <%}%>
                        </tr>

                        <%}%> </table><%

                                                    if (request.getAttribute("result2") instanceof String) {%>
                    <p align="center">
                        <%=request.getAttribute("result2")%>
                    </p>
                    <%}
                                    }
                                }%>
                </div>
            </div>
        </div>
    </body>
</html>
