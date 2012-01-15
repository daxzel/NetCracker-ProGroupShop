<%-- 
    Document   : index
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page import="entityBeans.ProductBeanRemote"%>
<%@page import="java.util.List"%>
<%@page import="entityBeans.RoleBeanRemote"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="menu.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="errorPage.jsp"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource,entityBeans.UserBeanRemoteHome,entityBeans.UserBeanRemote, helpers.*,exceptions.LoginException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />
        <title>Интернет-магазин</title>
    </head>
    <body>
        <%
                    //    PrintWriter pw = response.getWriter();

                    UserBeanRemote usr = null;

                    try {
                        usr = JSPHelper.getUser2(session);
                        long r = usr.getRoleId();

                    } catch (LoginException ex) {
                    } finally {%>

        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>


                        <tr>
                            <td class="logo" align="left">
                                <img src="<%=request.getContextPath()%>/static/logo.jpg"
                            </td>
                            <td  class="current_user" align="right"><%if (usr == null) {%><a> </a><%} else {%>Текущий пользователь:<a href="<%=request.getContextPath()%>/updateUser.jsp?DO=updateProfil"> <%=usr.getNik()%></a>   Статус: <%=usr.getRoleName()%> <%}%></td>
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
                <div id="find">
                    <form action="<%=request.getContextPath()%>/find">
                        <input type="text" name="sabstrName" value="" size="100" class="in"/>
                        <input type="submit" value=" Найти " class="Button1" />
                    </form>
                </div>
                <div id="content">
                    <%
                                            List products = (List) request.getAttribute("products");
                                            String result = (String) request.getAttribute("result");
                                            if (products == null) {
                                                if (result != null) {%>
                    <%=result%>
                    <%} else {%>
                    Когда руки дойдут тут появятся новости<br><br><br>
                    <br><br><br> Когда руки дойдут тут появятся новости<br><br><br><br><br>
                    <%}
                                                                } else {
                                                                    ProductBeanRemote prd;//= (ProductBeanRemote) products.get(0);
                                                                    if (products.isEmpty()) {                                              //session.setAttribute("catalog", String.valueOf(prd.getIdCatalog()));
                    %>
                    По вашему запросу не найдено продуктов.
                    <%} else {%>
                    <table align="center"  width="70%">
                        <tr>
                            <td width="40%" align="center">Название</td><td width="15%">Цена</td><td width="15%"></td>
                        </tr>
                        <% for (int i = 0; i <= (products.size() - 1); i++) {
                                                                                                    prd = (ProductBeanRemote) products.get(i);%>
                        <tr>
                            <td><a href ="product?ID=<%=prd.getId()%>"><%= prd.getName()%></a></td><td><%= prd.getPrice()%></td><td><a href ="order?VOL=1&ID_PRODUCT=<%=prd.getId()%>" class="Button">В корзину</a></td>
                        </tr>
                        <%}%>

                    </table>
                    <%}
                                            }%>
                </div>
            </div>

            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>

        <%}
        %>

    </body>
</html>
