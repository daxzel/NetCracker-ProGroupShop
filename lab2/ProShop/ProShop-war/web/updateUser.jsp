<%-- 
    Document   : updateUser
    Created on : 07.10.2011, 21:29:52
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "entityBeans.UserBeanRemote, helpers.*"%>
<%@page import= "java.text.SimpleDateFormat"%>
<%@ page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       <%@include file='head.jsp'%>
       <title>Редактирование пользователя</title>
    </head>
    <body>

        <%
                    SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    session.setAttribute("homepage", "updateUser.jsp");
                    //  session.setAttribute("usrOld", usr);
        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="/ProShop-war/static/logo.jpg">
                            </td>

                            <td  class="current_user" align="right"><%if (usr == null) {%><a> </a><%} else {%>Текущий пользователь:<a href="<%=request.getContextPath()%>/updateUser.jsp?DO=updateProfil"> <%=usr.getNik()%></a>   Статус: <%=usr.getRoleName()%> <%}%></td>
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
                    <%  if ("updateProfil".equals(request.getParameter("DO"))) {
                    %>
                    <h1>Редактирование профиля</h1>
                    <form name="myForm" action="updateProfil" method ="post">
                        <table id="regOrLog">
                            <tr><td>Name</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="<%=usr.getName()%>" size="20" /></td><td></td></tr>
                            <tr><td>Surname</td><td></td></tr>
                            <tr><td><input type="text" name="SURNAME" value="<%=usr.getSurname()%>" size="25" /></td><td></td></tr>
                            <tr><td>Father name</td><td></td></tr>
                            <tr><td><input type="text" name="OTCHESTVO" value="<%=usr.getOtchestvo()%>" size="20" /></td><td></td></tr>
                            <tr><td>Nik</td><td></td></tr>
                            <tr><td><input type="text" name="NIK" value="<%=usr.getNik()%>" size="20" /></td><td></td></tr>
                            <tr><td>Password</td><td> Password(repeat)</td></tr>
                            <tr><td><input type="password" name="PASSWORD" value="" size="10" /></td><td>  <input type="password" name="PASSWORD2" value="" size="10" /></td></tr>
                            <tr><td>Born</td><td></td></tr>
                            <tr><td><input type="text" name="BORN" value="<%=formt.format(usr.getBorn())%>" size="10" /></td><td></td></tr>
                            <tr><td>Phone</td><td></td></tr>
                            <%if (usr.getPhone() == null) {%>
                            <tr><td><input type="text" name="PHONE" value="" size="11" /></td><td></td></tr>
                                    <%} else {%>
                            <tr><td><input type="text" name="PHONE" value="<%=usr.getPhone()%>" size="11" /></td><td></td></tr>
                                    <%}%>
                            <tr><td>Email</td><td></td></tr>
                            <%if (usr.getEmail() == null) {%>
                            <tr><td><input type="text" name="EMAIL" value="" size="25" /></td><td></td></tr>
                                    <%} else {%>
                            <tr><td><input type="text" name="EMAIL" value="<%=usr.getEmail()%>" size="25" /></td><td></td></tr>
                                    <%}%>
                            <tr><td><input type="submit" value=" Ввод " class="Button"/></td><td></td></tr>
                        </table>
                    </form>
                    <%if (request.getAttribute("result") instanceof String) {%>
                    <%=request.getAttribute("result").toString()%><%}
                                                                    }

                                if (("updateUser".equals(request.getParameter("DO")) && usr.getRoleId() == 1)) {
                                    if (session.getAttribute("userOld") == null) {
                                        if (usr.getRoleId() >= 2) {
                                            throw new LoginException("Вы не обладаете правами администратора");
                                        }
                    %>
                    <h1>Редактирование пользователя</h1>
                    <form action="selectByNik">
                        <table id="regOrLog">
                            <tr><td>
                                    Введите ник пользователя:</td></tr>
                            <tr><td>
                                    <input type="text" name="NIK" value="" size="20" />
                                </td></tr>
                            <tr><td>
                                    <input type="submit" value=" Ввод " class="Button"/>
                                </td></tr> 
                        </table>
                    </form>
                    <%                            } else {
                                                            if (usr.getRoleId() >= 2) {
                                                                throw new LoginException("Вы не обладаете правами администратора");
                                                            }
                                                            UserBeanRemote user;
                                                            if (session.getAttribute("userOld") instanceof UserBeanRemote) {
                                                                user = (UserBeanRemote) session.getAttribute("userOld");
                                                                //session.setAttribute("usrOld", user);
                                                   /* } else {
                                                                user = (UserBeanRemote) session.getAttribute("usrOld");
                                                                }*/
                    %>
                    <h1>Редактирование пользователя</h1>
                    <form name="myForm" action="updateUser">
                        <table id="regOrLog">
                            <tr><td>Name</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="<%=user.getName()%>" size="20" /></td><td></td></tr>
                            <tr><td>Surname</td><td></td></tr>
                            <tr><td><input type="text" name="SURNAME" value="<%=user.getSurname()%>" size="25" /></td><td></td></tr>
                            <tr><td>Father name</td><td></td></tr>
                            <tr><td><input type="text" name="OTCHESTVO" value="<%=user.getOtchestvo()%>" size="20" /></td><td></td></tr>
                            <tr><td>Nik</td><td></td></tr>
                            <tr><td><input type="text" name="NIK" value="<%=user.getNik()%>" size="20" /></td><td></td></tr>
                            <tr><td>Born</td><td></td></tr>
                            <tr><td><input type="text" name="BORN" value="<%=formt.format(user.getBorn())%>" size="10" /></td><td></td></tr>
                            <tr><td>Phone</td><td></td></tr>
                            <%if (user.getPhone() == null) {%>
                            <tr><td><input type="text" name="PHONE" value="" size="11" /></td><td></td></tr>
                                    <%} else {%>
                            <tr><td><input type="text" name="PHONE" value="<%=user.getPhone()%>" size="11" /></td><td></td></tr>
                                    <%}%>
                            <tr><td>Email</td><td></td></tr>
                            <%if (user.getEmail() == null) {%>
                            <tr><td><input type="text" name="EMAIL" value="" size="25" /></td><td></td></tr>
                                    <%} else {%>
                            <tr><td><input type="text" name="EMAIL" value="<%=user.getEmail()%>" size="25" /></td><td></td></tr>
                                    <%}%>
                            <tr><td>Role</td><td></td></tr>
                            <tr><td><select name="ID_ROLE" style="width : 200">
                                        <%if (user.getRoleId() == 1) {%>
                                        <option value="1" selected>Админ</option>
                                        <option value="3">Пользователь</option>
                                        <option value="2">Менеджер</option>
                                        <option value="4">Заблокированные</option>
                                        <%}%>
                                        <%if (user.getRoleId() == 3) {%>
                                        <option value="1">Админ</option>
                                        <option value="3" selected>Пользователь</option>
                                        <option value="2">Менеджер</option>
                                        <option value="4">Заблокированные</option>
                                        <%}%>
                                        <%if (user.getRoleId() == 2) {%>
                                        <option value="1">Админ</option>
                                        <option value="3">Пользователь</option>
                                        <option value="2" selected>Менеджер</option>
                                        <option value="4">Заблокированные</option>
                                        <%}%>
                                        <%if (user.getRoleId() == 4) {%>
                                        <option value="1">Админ</option>
                                        <option value="3">Пользователь</option>
                                        <option value="2">Менеджер</option>
                                        <option value="4" selected>Заблокированные</option>
                                        <%}%>
                                    </select></td><td></td></tr>

                            <tr><td><input type="submit" value=" Ввод " class="Button"/></td><td></td></tr>

                        </table>
                    </form>
                    <%
                                                            }
                                                        }

                                                        if (request.getAttribute("result") != null) {
                    %>
                    <%=request.getAttribute("result").toString()%><%}

                                                                    }
                    %>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>

    </body>
</html>
