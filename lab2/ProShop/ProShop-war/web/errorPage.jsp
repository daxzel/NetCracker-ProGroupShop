<%-- 
    Document   : errorPage
    Created on : 22.10.2011, 23:32:15
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="helpers.JSPHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@ page import = "exceptions.*,javax.servlet.RequestDispatcher;"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%
                    if (exception instanceof LoginException) {
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    } else {%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />

        <title>Error page</title>
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
                    <%
                                                        if (exception != null) {
                    %>
                    <%=exception.getMessage()%>
                    <%}
                                                        if (request.getAttribute("exception") != null) {
                                                            Exception ex = (Exception) request.getAttribute("exception");%>
                    <%=ex.getMessage()%>
                    <%}
                                    }
                                }

                    %>
                </div>
            </div>
        </div>
    </body>
</html>
