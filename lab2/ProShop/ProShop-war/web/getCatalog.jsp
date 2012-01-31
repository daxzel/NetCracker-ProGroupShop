<%-- 
    Document   : getFull_catalog
    Created on : 16.10.2011, 17:41:55
    Author     : Pushok
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entityBeans.ImageBeanRemote"%>
<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="entityBeans.CatalogBeanRemote,entityBeans.ProductBeanRemote"%>
<%@page import="java.util.List"%>
<%@page errorPage="/errorPage.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file='head.jsp'%>
        <title>Интернет-магазин</title>
    </head>
    <body>
        <%
                    long r = 5;

                    //    PrintWriter pw = response.getWriter();
                    UserBeanRemote usr = null;
                    session.setAttribute("homepage", "getCatalog.jsp");
                    try {
                        usr = JSPHelper.getUser2(session);
                        r = usr.getRoleId();
                    } catch (LoginException ex) {
                    } finally {%>
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
                    <h1>Просмотр каталога</h1>
                    <%
                                            Object obj1 = request.getAttribute("result");
                                            Object obj2 = session.getAttribute("list");
                                            ImageBeanRemote img;
                                            if (((obj1 != null)) || (obj2 != null)) {
                                                List list1 = null;
                                                String result = "";

                                                if (obj1 instanceof List) {
                                                    list1 = (List) obj1;
                                                    session.setAttribute("list", list1);
                                                } else {
                                                    if (obj2 instanceof List) {
                                                        list1 = (List) obj2;
                                                    } else {
                                                        throw new Exception("Произошла ошибка при загрузке каталога, пожалуйста сообщите администрации сайта.");
                                                    }
                                                }

                                                if (!list1.isEmpty()) {
                                                    if (list1.get(0) instanceof CatalogBeanRemote) {
                                                        CatalogBeanRemote ctg = (CatalogBeanRemote) list1.get(0);
                    %>
                    <table align="center" width="100%">
                        <% for (int i = 0; i <= (list1.size() - 1); i++) {
                                                                                    ctg = (CatalogBeanRemote) list1.get(i);%>
                        <tr align="center">
                            <td><p align="center"><a href ="catalog?pid=<%= ctg.getId()%>"><%=  ctg.getName()%></a></p></td>
                        </tr>

                        <%}%>
                    </table>

                    <%}
                                                                        if (list1.get(0) instanceof ProductBeanRemote) {
                                                                            ProductBeanRemote prd = (ProductBeanRemote) list1.get(0);
                                                                            session.setAttribute("catalog", String.valueOf(prd.getIdCatalog()));
                    %>
                    <table align="center" rules="rows" frame="void" bordercolor="#c0c0c0" >

                        <% for (int i = 0; i <= (list1.size() - 1); i++) {
                                                                                                        prd = (ProductBeanRemote) list1.get(i);
                                                                                                        List list2 = prd.getImageList();
                                                                                                        if (list2.size() > 0) {
                                                                                                            img = (ImageBeanRemote) list2.get(0);

                        %>
                        <tr align="justify">
                            <td align="center" width="20%" >
                                <a href ="product?ID=<%=prd.getId()%>"</a> <img width="55%" align="center" alt="Картинка"   src="<%=request.getContextPath()%>/image?ID=<%= img.getId_img()%>"
                            </td>
                            <td width="65%">
                                <a href ="product?ID=<%=prd.getId()%>"><font size="5"><%= prd.getName()%></font></a><br><br>
                                <% if (prd.getDescription() != null) {%>
                                <%= prd.getDescription()%><br><br>
                                <%}%>
                                <% BigDecimal priceProd = new BigDecimal(prd.getPrice()); %>
                                <p align="right"> <font size="5"><%= priceProd.toBigInteger()%></font> руб.</p>
                            </td>
                            <% if (r <= 3) {%>
                            <td  width="15%" align="center"><a href ="order?VOL=1&ID_PRODUCT=<%=prd.getId()%>"</a><img align="center" alt="В корзину"  width="60%" src="<%=request.getContextPath()%>/static/cart.jpg"></td>
                                <% }%>
                        </tr>
                        <%} else {%>

                        <tr align="justify">
                            <td align="center" width="20%" >
                                <a href ="product?ID=<%=prd.getId()%>"</a> <img width="55%" align="center" alt="Картинка"   src="<%=request.getContextPath()%>/Image/noimg.jpg"
                            </td>
                            <td width="65%">
                                <a href ="product?ID=<%=prd.getId()%>"><font size="5"><%= prd.getName()%></font></a><br><br>
                                <% if (prd.getDescription() != null) {%>
                                <%= prd.getDescription()%><br><br>
                                <%}%>
                                <% BigDecimal priceProd = new BigDecimal(prd.getPrice()); %>
                                <p align="right"> <font size="5"><%= priceProd.toBigInteger()%></font> руб.</p>
                            </td>
                            <% if (r <= 3) {%>
                            <td  width="15%" align="center"><a href ="order?VOL=1&ID_PRODUCT=<%=prd.getId()%>"</a> <img align="center" alt="В корзину"  width="60%" src="<%=request.getContextPath()%>/static/cart.jpg"></td>
                                <% }%>
                        </tr>
                        <% }
                                                                                              }%>
                    </table>

                    <br><%
                                                                        }
                                                                        if (obj1 instanceof String) {%>
                    <%=obj1.toString()%>
                    <%}
                                                                        } else {%><div class="warning"><p align="center">Каталог не содержит никакой информации</p></div><%}
                                                                                                                    } else {%><div class="warning"><p align="center">Произошла ошибка</p></div><%                                                 }%>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
        <%}%>
    </body>
</html>
