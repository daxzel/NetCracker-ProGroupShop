<%-- 
    Document   : getBasket
    Created on : 23.10.2011, 17:19:19
    Author     : Yra
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="menu.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "entityBeans.UserBeanRemote,entityBeans.OrderBeanRemote, helpers.*,java.util.*"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       <%@include file='head.jsp'%>
       <title>
           <%String status = request.getParameter("status");
                    if ("false".equals(status)) {
            %>Моя корзина
            <%}
                        if ("true".equals(status)) {%>
            Моя история заказов
            <%}%>
       </title>
    </head>
    <body>
        <%UserBeanRemote usr = JSPHelper.getUser2(session);


        %>
        <div id="container">
            <%@include file='header.jspf'%>
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
                <%@include file='searchBlock.jspf'%>
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
                    <form action="changeAmount">
                        <table align="center"  frame="border" rules="all" width="100%" bordercolor="#c0c0c0">
                            <tr align="center">

                                <td width="30%" align="center">Название продукта</td>
                                <td width="10%" align="center">Цена, руб</td>
                                <td width="20%" align="center">Колличество</td>
                                <td width="20%" align="center">Цена продуктов, руб</td>
                                <%if (!ord.getStatus()) {%>
                                <td width="10%" align="center">Изменить количество продуктов</td>
                                <td width="15%" align="center">Удалить продукт</td>
                                <%}%>
                            </tr>
                            <%
                                                                    double priceProduct;
                                                                    int amount;
                                                                    
                                                                    double sum = 0;
                                                                    for (int i = 0; i <= (list.size() - 1); i++) {
                                                                        ord = (OrderBeanRemote) list.get(i);
                                                                        priceProduct = ord.getPriceProduct();
                                                                        BigDecimal priceProd = new BigDecimal(priceProduct);
                                                                        amount = ord.getAmount();
                                                                        sum = sum + (priceProduct) * (amount);
                                                                        BigDecimal pPa = new BigDecimal((priceProduct) * (amount));

                            %>

                            <tr align="center">
                                <td><a href ="product?ID=<%=ord.getIdProduct().longValue()%>"><%= ord.getNameProduct()%></a></td>
                                <td><%= priceProd.toBigInteger()%></td>

                                <td> <%if (!ord.getStatus()) {%><input type="text" value="<%=  amount%>" name="amount" size="15"><%}else{%><%=amount%><%}%></td>

                                <td><%= pPa.toBigInteger()%></td>
                                <%if (!ord.getStatus()) {%>
                                <td width="10%"> <input type="submit" value="Изменить" class="Button" width="60px"/></td>
                                <td width="10%"><a href ="deleteOrder?id_order=<%=ord.getId()%>">удалить продукт</a></td>
                                <%}%>

                            </tr>
                            <input type="hidden" value="<%=  ord.getId()%>" name="ID_ORDER">
                            <%}%> </table>
                    </form>
                    <br>
                    <br>
                    <% BigDecimal summ = new BigDecimal(sum);



%>
                    <p align="right"><font size="4"> Итого:</font> <%= summ.toBigInteger()%> руб.</p>
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
