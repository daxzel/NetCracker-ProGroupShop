<%-- 
    Document   : getBasket
    Created on : 23.10.2011, 17:19:19
    Author     : Yra
--%>

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

        <title>Корзина/заказы</title>
    </head>
    <body>
        <%UserBeanRemote user = JSPHelper.getUser2(session);
                    if (request.getAttribute("result") instanceof List) {
                        List list = (List) request.getAttribute("result");


                        if (list.isEmpty()) {%>
        <p align="center">У вас нет заказов<br>
        <p align="center"><a href="getFull_catalog">Каталог</a></p>
        <%} else {
                                    OrderBeanRemote ord = (OrderBeanRemote) list.get(0);
        %>
        <table align="center"  border="1" width="80%">
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
                <td><%= priceProduct %></td>
                <td><%=  amount%></td>
                <td><%= (priceProduct)*(amount)%></td>
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


        <p align="center"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
