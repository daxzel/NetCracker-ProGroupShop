<%-- 
    Document   : getUsersByRole
    Created on : 11.10.2011, 23:25:21
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*,java.text.SimpleDateFormat, helpers.*,entityBeans.UserBeanRemote;"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="static/main.css">
        <link href="static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="static/default.css" media="all" rel="stylesheet" type="text/css" />
        <title>Поиск пользователей по роле</title>
    </head>
    <body>
        <%
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    UserBeanRemote user;

        %>

        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="/ProShop-war/static/logo.jpg">
                            </td>
                            <td class="team" align="center"><a href="aboutTeam.jsp">Команда</a></td>
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
                        <%=JSPHelper.getMenu(3)%>
                        <%}%>
                    </div>
                </div>
                <div id="content">
                    <h1>Поиск пользователей</h1>
                    <%    if (request.getAttribute("result") == null || request.getAttribute("result") instanceof String) {%>
                    <form action="getUsersByRole">
                        <table id="regOrLog"><tr><td>
                                    Введите роль:</td></tr>
                            <tr><td><select name="ROLE" style="width : 200">
                                        <option value="admin" selected>Админ</option>
                                        <option value="manager" selected>Менеджер</option>
                                        <option value="user">Пользователь</option>
                                    </select></td><td></td></tr>
                            <tr><td><input type="submit" value=" Ввод " class="Button"/></td></tr>
                        </table>
                    </form>
                    <%if (request.getAttribute("result") != null) {%>
                    <%=request.getAttribute("result").toString()%>
                    <%}%>

                    <%            } else {
                            if (request.getAttribute("result") instanceof Collection) {
                                Collection list = (Collection) request.getAttribute("result");
                                SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
                    %>
                    <form action="XML/exportUser">
                        <table align="center"  border="1" width="100%">
                            <tr align="center">
                                <%if (usr.getRoleId() == 1) {%>
                                <td  align="center">User id</td>
                                <%}%>
                                <td  align="center">Name</td>
                                <td align="center">Surname</td>
                                <td align="center">Otchestvo</td>
                                <td align="center">Nik</td>
                                <td align="center">Born</td>
                                <td  align="center">Phone</td>
                                <td  align="center">Email</td>

                            </tr>
                            <%
                                                            Iterator iter = list.iterator();
                                                            while (iter.hasNext()) {
                                                                user = (UserBeanRemote) iter.next();%>
                            <tr align="center">
                                <%if (usr.getRoleId() == 1) {%>
                                <td><%= user.getId()%></td>
                                <%}%>
                                <td><%= user.getName()%></td>
                                <td><%= user.getSurname()%></td>
                                <td><%= user.getOtchestvo()%></td>
                                <td><%= user.getNik()%></td>
                                <td><%=formt.format(user.getBorn())%></td>
                                <%if (user.getPhone() != null) {%>
                                <td><%= user.getPhone()%></td>
                                <%} else {%>
                                <td></td>
                                <%}
                                                                                                if (user.getEmail() != null) {%>
                                <td><%= user.getEmail()%></td>
                                <%} else {%>
                                <td></td>
                                <%}
                                %>
                            </tr>
                            <%
                                                            }%>
                        </table>
                    </form>

                    <%  }
                                }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
