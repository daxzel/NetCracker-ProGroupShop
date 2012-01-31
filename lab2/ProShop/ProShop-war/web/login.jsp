<%-- 
    Document   : login
    Created on : 15.10.2011, 14:12:27
    Author     : Yra
--%>

<%@page import="exceptions.LoginException"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="errorPage.jsp"%>
<%@page import="menu.Menu"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       <%@include file='head.jsp'%>
       <title>Login</title>
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
                    <h1>Вход</h1><br>
                    <form action="<%=request.getContextPath()%>/login" method ="post">
                        <table id="regOrLog">
                            <tr><td>
                                    Ник</td></tr>
                            <tr><td>
                                    <input type="text" name="NIK" value="" size="20" />
                                </td></tr>
                            <tr><td>
                                    Пароль
                                </td></tr>
                            <tr><td>
                                    <input type="password" name="PASSWORD" value="" size="20" />
                                </td></tr>
                            <tr><td>
                                    <input type="submit" value=" Ввод "class="Button" />
                                </td></tr>
                        </table>
                    </form>

                    <%if (request.getAttribute("result") != null) {%>
                    <p align="left">
                        <%=request.getAttribute("result")%><%}%>
                    </p>
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
