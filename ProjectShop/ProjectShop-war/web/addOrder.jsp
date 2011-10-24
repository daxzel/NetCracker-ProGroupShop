<%-- 
    Document   : addOrder
    Created on : 22.10.2011, 22:22:47
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "DBClasses.ProductInterface, DBClasses.UserInterface, DBClasses.OrderInterface,Other.*;"%>
<%@page errorPage="errorPage.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body><% 
                UserInterface user = JSPHelper.getUser(session);
                   
                    ProductInterface prd = null;
                    String kol_vo="";
                    if (session.getAttribute("product") != null) {
                        prd = (ProductInterface) session.getAttribute("product");
                        session.setAttribute("ID_PRODUCT",prd.getId());
                        if(request.getAttribute("kol_vo")!=null){
                            kol_vo=request.getAttribute("kol_vo").toString();
                        }
                        //request.setAttribute("NIK", user.getNik());
                    }

        %>
        <form name="myForm" action="order">
            <table>
                <tr><td>Название продукта</td></tr>
                <tr><td><a href ="product?NAME=<%=prd.getName() %>"><%= prd.getName()%></a></td>/tr>
                <tr><td>Цена</td><td></td></tr>
                <tr><td><%=prd.getPrice() %></td></tr>
                <tr><td>Количество</td></tr>
                <tr><td><input type="text" name="KOL" value="<%=kol_vo%>" size="25" /></td></tr>
                <tr><td><select name="STATUS" style="width : 200">
                        <option value="false" selected>Добавить в корзину</option>
                        <option value="true">Оформить заказ</option>
                </select></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>
        </form>
        <% if(request.getAttribute("result")!=null){%>
        <%=request.getAttribute("result") %>
        <%}%>
          <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
