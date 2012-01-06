<%-- 
    Document   : getFull_catalog
    Created on : 16.10.2011, 17:41:55
    Author     : Pushok
--%>

<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="entityBeans.CatalogBeanRemote,entityBeans.ProductBeanRemote"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />

        <title>Каталог</title>
    </head>
    <body>
        <%

                    //    PrintWriter pw = response.getWriter();
                    UserBeanRemote usr = null;
                    try {
                        usr = JSPHelper.getUser2(session);
                    } catch (LoginException ex) {
                    } finally {%>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="<%=request.getContextPath()%>/static/logo.jpg">
                            </td>
                            
                             <td  class="current_user" align="right"><%if (usr == null) {%><a> </a><%} else {%>Текущий пользователь:<a href="<%=request.getContextPath()%>/updateUser.jsp?DO=updateProfil"> <%=usr.getNik()%></a><a> Статус: <%=usr.getRoleName()%> </a><%}%></td>
                            <td class="user_nav" align="right"><%if (usr == null) {%><a href="<%=request.getContextPath()%>/login.jsp">Вход</a>   <a href="<%=request.getContextPath()%>/registration.jsp">Регистрация</a><%} else {%><a href="<%=request.getContextPath()%>/logout">Выход</a><%}%></td>
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
                    <% if (request.getAttribute("result") != null && request.getAttribute("result") instanceof List) {

                                                List list1 = (List) request.getAttribute("result");
                                                if (!list1.isEmpty()) {
                                                    if (list1.get(0) instanceof CatalogBeanRemote) {
                                                        CatalogBeanRemote ctg = (CatalogBeanRemote) list1.get(0);
                    %>
                    <table align="center"  border="1" width="80%">
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
                    %>
                    <table align="center"  border="1" width="100%">
                        <tr align="center">
                            <td width="70%" align="center">Название</td><td width="15%" align="center">Цена</td><td width="15%" align="center">В корзину</td>
                        </tr>
                        <% for (int i = 0; i <= (list1.size() - 1); i++) {
                                                                                            prd = (ProductBeanRemote) list1.get(i);%>
                        <tr align="center">
                            <td><a href ="product?ID=<%=prd.getId()%>"><%= prd.getName()%></a></td><td><%= prd.getPrice()%></td><td>Пока не работает</td>
                        </tr>
                        <%}%>

                    </table>

                    <br><%
                                                                                }%>
                        <%
                                                                            } else {%><br><p align="center">Каталог не содержит никакой информации<br></p><%}
                                                                             } else {%><br><p align="center">произошла ошибка<br></p><%                                                 }%>
                </div>
            </div>
                <div class="team" align="center">
               <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
        </div>
        </div>
        <%}%>
    </body>
</html>
