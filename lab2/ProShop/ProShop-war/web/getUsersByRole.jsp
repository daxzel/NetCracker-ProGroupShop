<%-- 
    Document   : getUsersByRole
    Created on : 11.10.2011, 23:25:21
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*,java.text.SimpleDateFormat,Other.JSPHelper,entityBeans.UserBeanRemote;"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Поиск пользователей по роле</title>
    </head>
    <body>
        <%
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    UserBeanRemote user;
                    if (request.getAttribute("result") == null || request.getAttribute("result") instanceof String) {
        %>
        <form action="getUsersByRole">
            Введите роль:
            <tr><td><select name="ROLE" style="width : 200">
                        <option value="admin" selected>Админ</option>
                        <option value="manager" selected>Менеджер</option>
                        <option value="user">Пользователь</option>
                    </select></td><td></td></tr>
            <input type="submit" value="Input" />
        </form>
        <%if (request.getAttribute("result") != null) {%>
        <%=request.getAttribute("result").toString()%>
        <%}%>
        <p align="left"><a href ="index.jsp">index</a><br></p>
            <%            } else {
                                    if (request.getAttribute("result") instanceof Collection) {
                                        Collection list = (Collection) request.getAttribute("result");
                                        SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
            %>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <%if (usr.getRoleId() == 1) {%>
                <td width="5%" align="center">User id</td>
                <%}%>
                <td width="15%" align="center">Name</td>
                <td width="25%" align="center">Surname</td>
                <td width="20%" align="center">Otchestvo</td>
                <td width="30%" align="center">Nik</td>
                <td width="30%" align="center">Born</td>
                <td width="30%" align="center">Phone</td>
                <td width="30%" align="center">Email</td>
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
                                                        }%>
            </tr>
        </table>
        <p align="center"><a href ="index.jsp">index</a><br></p>
            <%  }
                        }
            %>

    </body>
</html>
