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
    <%
                    if (exception instanceof LoginException) {
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    } else {%>

    <head>
        <%@include file='head.jsp'%>
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
                    <%
                                                        if (exception != null) {
                    %>
                    <div class="warning"><p align="center"> <%=exception.getMessage()%></p></div>
                    <%} else {
                                                                                if (request.getAttribute("exception") != null) {
                                                                                    Exception ex = (Exception) request.getAttribute("exception");%>
                    <div class="warning"><p align="center">  <%=ex.getMessage()%></p></div>
                    <%} else {%>
                   <div class="warning"><p align="center"> произошла ошибка, пожалуйста обратитесь к администратору.</p></div>
                    <%                    }
                                                }
                                            }




                    %>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
    <%}%>
</html>
