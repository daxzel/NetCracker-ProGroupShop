<%-- 
    Document   : registration
    Created on : 05.10.2011, 23:56:50
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="exceptions.*, helpers.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       <%@include file='head.jsp'%>
       <title>Регистрация пользователя</title>
    </head>
    <body>
        <%

                    String name = JSPHelper.getRequestOrEmpty(request, "NAME");
                    String surname = JSPHelper.getRequestOrEmpty(request, "SURNAME");
                    String otchestvo = JSPHelper.getRequestOrEmpty(request, "OTCHESTVO");
                    String nik = JSPHelper.getRequestOrEmpty(request, "NIK");
                    String password = JSPHelper.getRequestOrEmpty(request, "PASSWORD");
                    String password2 = JSPHelper.getRequestOrEmpty(request, "PASSWORD2");
                    String born = JSPHelper.getRequestOrEmpty(request, "BORN");
                    String phone = JSPHelper.getRequestOrEmpty(request, "PHONE");
                    String email = JSPHelper.getRequestOrEmpty(request, "EMAIL");


        %>
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
                <div id="content">
                    <H1>Регистрация</H1>

                    <form name="myForm" action="registration" method="post">
                        <table id="regOrLog">
                            <tr><td>Имя</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="<%=name%>" size="25" title=" обязательно для заполнения "/></td><td></td></tr>
                            <tr><td>Фамилия</td><td></td></tr>
                            <tr><td><input type="text" name="SURNAME" value="<%=surname%>" size="25" title=" обязательно для заполнения "/></td><td></td></tr>
                            <tr><td>Отчество</td><td></td></tr>
                            <tr><td><input type="text" name="OTCHESTVO" value="<%=otchestvo%>" size="25" title=" обязательно для заполнения "/></td><td></td></tr>
                            <tr><td>Ник</td><td></td></tr>
                            <tr><td><input type="text" name="NIK" value="<%=nik%>" size="25" title=" обязательно для заполнения "/></td><td></td></tr>
                            <tr><td>Пароль</td><td> Пароль(Подтверждение)</td></tr>
                            <tr><td><input type="password" name="PASSWORD" value="<%=password%>" size="25" title=" обязательно для заполнения "/></td><td>  <input type="password" name="PASSWORD2" value="<%=password2%>" size="25" title=" обязательно для заполнения "/></td></tr>
                            <tr><td>Дата рождения (ГГГГ-ММ-ДД)</td><td></td></tr>
                            <tr><td><input type="date" name="BORN" value="<%=born%>" size="25" title="гггг-мм-дд" /></td><td></td></tr>
                            <tr><td>Телефон</td><td></td></tr>
                            <tr><td><input type="text" name="PHONE" value="<%=phone%>" size="25" title=" не обязательно "/></td><td></td></tr>
                            <tr><td>Электронная почта</td><td></td></tr>
                            <tr><td><input type="text" name="EMAIL" value="<%=email%>" size="25" title=" не обязательно " /></td><td></td></tr>

                            <tr><td><input type="submit" value=" Ввод " class="Button"/></td><td></td></tr>
                        </table>
                    </form>

                    <%if (request.getAttribute("result") != null) {
                    %><%=request.getAttribute("result")%><%
                                            }
                    %>
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
