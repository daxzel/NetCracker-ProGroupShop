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
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />
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
                    <h1>Поиск пользователей</h1>
                    <%    if (request.getAttribute("result") == null || request.getAttribute("result") instanceof String) {%>
                    <form action="getUsersByRole">
                        <table id="regOrLog"><tr><td>
                                    Введите роль:</td></tr>
                            <tr><td><select name="ROLE" style="width : 200">
                                        <option value="admin" selected>Админ</option>
                                        <option value="manager" selected>Менеджер</option>
                                        <option value="user">Пользователь</option>
                                        <option value="block">Заблокированные</option>
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

                    <form action="blockUsers">
                        <table align="center"  border="1" width="100%">
                            <tr align="center">
                                <%if (usr.getRoleId() == 1) {%>
                                <td  align="center" width="5%">User id</td>
                                <%}%>
                                <td  align="center" width="10%">Name</td>
                                <td align="center" width="10%">Surname</td>
                                <td align="center" width="20%">Otchestvo</td>
                                <td align="center" width="10%">Nik</td>
                                <td align="center" width="15%">Born</td>
                                <td  align="center" width="10%">Phone</td>
                                <td  align="center" width="15%">Email</td>
                                <%if ((usr.getRoleId() == 1)) {%>

                                <td  align="center" width="5%">Заблокировать пользователя</td>
                                <%}%>
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
                                                                                                if ((usr.getRoleId() == 1)) {
                                %>
                                <td  align="center"><input type="checkbox" name="id_user" value=<%=user.getId()%> /></td>
                                    <%}%>

                            </tr>
                            <%      }%>
                        </table>
                        <br>
                        <%if ((usr.getRoleId() == 1)) {%>
                        <input type="submit" value=" Заблокировать выбранных пользователей " class="Button"/>
                        <%}%>
                    </form>

                    <%  }



                            if (request.getAttribute("result2") instanceof String) {%>
                    <p>
                        <%=request.getAttribute("result2")%>
                    </p>
                    <%}
                                }%>
                </div>
            </div>
                <div class="team" align="center">
               <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
        </div>
        </div>
    </body>
</html>
