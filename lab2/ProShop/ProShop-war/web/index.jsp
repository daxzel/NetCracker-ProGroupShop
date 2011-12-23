<%-- 
    Document   : index
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="menu.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="errorPage.jsp"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource,entityBeans.UserBeanRemoteHome,entityBeans.UserBeanRemote, helpers.*,exceptions.LoginException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="static/main.css">
        <link href="static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="static/default.css" media="all" rel="stylesheet" type="text/css" />
        <title>Интернет-магазин</title>
    </head>
    <body>
        <%
                    //    PrintWriter pw = response.getWriter();
                    UserBeanRemote usr = null;
                    try {
                        usr = JSPHelper.getUser2(session);
                    } catch (LoginException ex) {
        %>


        <%} finally {%>

        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="/ProShop-war/static/logo.jpg">
                            </td>
                            <td class="team" align="center"><a href="aboutTeam.jsp">Команда</a></td>
                            <td class="user_nav" align="right"><%if (usr == null) {%><a href="login.jsp">Вход</a>   <a href="login.jsp">Регистрация</a><%} else {%><a href="logout">Выход</a><%}%></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="menu">
                <div class="catalog">
                    <%=Menu.getMenu()%>
                </div>
                <div class="user_menu">
                <%if(usr!=null){%>
                <%=JSPHelper.getMenu(usr.getRoleId()) %>
                    <%}else{%>
                    <%=JSPHelper.getMenu(3) %>
                    <%}%>
                </div>
            </div>
            <div class="content">
                ТУТ БутеТ ЧТО ТО<br><br><br><br><br><br><br>sdsdsdsdsdsd<br><br><br>sdsdsdds<br><br>sdsddsdssdsd
            </div>
        </div>
        <%}
        %>
    </body>
</html>
