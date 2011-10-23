<%-- 
    Document   : getBasket
    Created on : 23.10.2011, 17:19:19
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "DBClasses.ProductInterface, DBClasses.UserInterface, DBClasses.OrderInterface,Other.*,java.util.*"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>basket/orders</title>
    </head>
    <body>
        <%UserInterface user = JSPHelper.getUser(session);
                    if (request.getAttribute("result") instanceof List) {
                        List<OrderInterface> list = (List<OrderInterface>) request.getAttribute("result");
                        if (list.size() == 0) {%>
        <p align="center">У вас нет заказов<br>
        <p align="center"><a href="getFull_catalog">Каталог</a></p>
            <%} else {

            %>
        <table align="center"  border="1" width="80%">
            <tr align="center">

                <td width="15%" align="center">Название продукта</td>
                <td width="25%" align="center">Цена</td>
                <td width="25%" align="center">Количество</td>
                <td width="20%" align="center">Цена заказа</td>
                <%if (list.size() != 0 && !list.get(0).getStatus()) {%>
                <td width="20%" align="center">Оформить заказ</td>
                 <td width="20%" align="center">Удалить заказ</td>
                <%}%>
            </tr>
            <% for (int i = 0; i <= (list.size() - 1); i++) {%>
            <tr align="center">
                <td><a href ="product?NAME=<%=list.get(i).getNameProduct()%>"><%= list.get(i).getNameProduct()%></a></td>
                <td><%= list.get(i).getPriceProduct()%></td>
                <td><%= list.get(i).getKol()%></td>
                <td><%= list.get(i).getPrice()%></td>
                <%if (!list.get(i).getStatus()) {%>
                <td><a href ="updateStatusOrder?id_order=<%=list.get(i).getId()%>">оформить заказ</a></td>
                <td><a href ="deleteOrder?id_order=<%=list.get(i).getId()%>">удалить заказ</a></td>
                <%}%>
            </tr>
</table>
            <%}

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
