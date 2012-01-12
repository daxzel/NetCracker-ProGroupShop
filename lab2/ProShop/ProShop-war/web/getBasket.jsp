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

        <title><%String status = request.getParameter("status");
                    if ("false".equals(status)) {
            %>Моя корзина
            <%}
                        if ("true".equals(status)) {%>
            Моя история заказов
            <%}%></title>
    </head>
    <body>
        <%UserBeanRemote usr = JSPHelper.getUser2(session);


        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="/ProShop-war/static/logo.jpg">
                            </td>

                            <td  class="current_user" align="right"><%if (usr == null) {%><a> </a><%} else {%>Текущий пользователь:<a href="<%=request.getContextPath()%>/updateUser.jsp?DO=updateProfil"> <%=usr.getNik()%></a>   Статус: <%=usr.getRoleName()%> <%}%></td>
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
                    <h1><%
                                if ("false".equals(status)) {
                        %>Моя корзина
                        <%}
                                    if ("true".equals(status)) {%>
                        Моя история заказов
                        <%}%>
                    </h1>

                    <%String result2 = (String) request.getAttribute("result2");
                                if (request.getAttribute("result") instanceof List) {
                                    List list = (List) request.getAttribute("result");
                                    if (list.isEmpty()) {%>

                    <%
                                                            if ("false".equals(status)) {

                    %>
                    <div class="emptyBasket">
                        У вас нет продуктов в корзине
                    </div>
                    <%}
                                                                if ("true".equals(status)) {%>
                    <div class="emptyBasket">
                        Ваша история заказов пуста
                    </div>
                    <%}%>
                    <%if (result2 != null) {%>
                    <%=result2%>
                    <%}


                                                        } else {
                                                            OrderBeanRemote ord = (OrderBeanRemote) list.get(0);
                    %>
                    <table align="center"  border="1" width="100%">
                        <tr align="center">

                            <td width="15%" align="center">Название продукта</td>
                            <td width="25%" align="center">Цена</td>
                            <td width="25%" align="center">Количество</td>
                            <td width="20%" align="center">Цена заказа</td>
                            <%if (!ord.getStatus()) {%>

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

                            <td><a href ="deleteOrder?id_order=<%=ord.getId()%>">удалить продукт</a></td>
                            <%}%>
                        </tr>

                        <%}%> </table>
                    <%if (!ord.getStatus()) {%><br><br>
                    <form action="updateOrderStatus">
                        <input type="submit" value=" Оформить заказ " class="Button" />
                    </form>
                    <%}

                                                            if (request.getAttribute("result2") instanceof String) {%>
                    <p>
                        <%=request.getAttribute("result2")%>
                    </p>
                    <%}
                                    }
                                }%>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
