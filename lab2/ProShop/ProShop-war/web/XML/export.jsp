<%-- 
    Document   : export
    Created on : 14.11.2011, 20:26:55
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="exceptions.LoginException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/errorPage.jsp"%>

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

        <title>Экспорт сущностей в XML</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="<%=request.getContextPath()%>/static/logo.jpg">
                            </td>
                            <td class="team" align="center"><a href="<%=request.getContextPath()%>/aboutTeam.jsp">Команда</a></td>
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
                        <%=JSPHelper.getMenu(3)%>
                        <%}%>
                    </div>
                </div>
                <div id="content">
                    <h1>Экспорт сущностей в XML</h1>
                    <p align="center"><a href="exportProduct.jsp?poiskType=byPrice">Экспортировать продукты используя поиск по цене </a></p>
                    <p align="center"><a href="exportProduct.jsp?poiskType=byName">Экспортировать продукты используя поиск по имени </a></p>
                    <p align="center"><a href="exportUser.jsp">Экспорт пользователей</a></p>
                </div>
            </div>
        </div>
        <%%>
    </body>
</html>
