<%-- 
    Document   : deleteUser
    Created on : 12.10.2011, 13:15:57
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "entityBeans.UserBeanRemote, helpers.*"%>
<%@page errorPage="errorPage.jsp"%>
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
        <title>Удаление пользователя</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() >= 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="/ProShop-war/static/logo.jpg">
                            </td>
                           
                             <td  class="current_user" align="right"><%if (usr == null) {%><a> </a><%} else {%>Текущий пользователь:<a href="<%=request.getContextPath()%>/updateUser.jsp?DO=updateProfil"> <%=usr.getNik()%></a><a> Статус: <%=usr.getRoleName()%> </a><%}%></td>
                            <td class="user_nav" align="right"><%if (usr == null) {%><a href="login.jsp">Вход</a>   <a href="registration.jsp">Регистрация</a><%} else {%><a href="logout">Выход</a><%}%></td>
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
                    <h1>Удаление пользователя</h1>
                    <form action="deleteUser">
                        <table id="regOrLog">
                            <tr><td>Введите ник:</td></tr>
                            <tr><td><input type="text" name="NIK" value="" size="20" /></td></tr>
                            <tr><td><input type="submit" value=" Ввод " class="Button" /></td></tr>
                        </table>
                    </form>

                    <%if (request.getAttribute("result") != null) {%>
                    <%=request.getAttribute("result").toString()%>
                    <%}%>

                </div>
            </div>
                    <div class="team" align="center">
               <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
        </div>
        </div>
    </body>
</html>
