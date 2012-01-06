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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />
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
                    <h1>Вход</h1><br>
                    <form action="<%=request.getContextPath()%>/login">
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
