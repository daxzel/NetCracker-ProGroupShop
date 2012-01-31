nhfvdfq euyfkb njtcnm
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
        <%@include file='head.jsp'%>
       <title>Интернет-магазин</title>
    </head>
    <body><%
                UserBeanRemote usr = JSPHelper.getUser2(session);
                session.setAttribute("homepage", "addOrder.jsp");
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
                    <h1>Добавление продукта в корзину</h1>
                    <%if(prd!=null){%>
                    <form name="myForm" action="order">
                        <table>
                            <tr><td>Название продукта:</td>
                            <td><a href ="product?ID=<%=prd.getId()%>"><%= prd.getName()%></a></td>
                            <tr><td>Цена:</td>
                            <td><%=prd.getPrice()%></td></tr>
                            <tr><td>Количество:</td><td><input type="text" name="VOL" value="<%=kol_vo%>" size="25" /></td></tr>
                           
                            <tr><td><input type="submit" value=" Ввод " class="Button"/></td><td></td></tr>
                        </table>
                    </form>
                    <%}else{%>
                   <div class="warning"><p align="center">Продукт не выбран.</p></div>
                    <%}if (request.getAttribute("result") != null) {%>
                    <%=request.getAttribute("result")%>
                    <%}%>
                </div>
            </div>
                 <div class="team" align="center">
               <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
        </div>
        </div>
    </body>
</html>
