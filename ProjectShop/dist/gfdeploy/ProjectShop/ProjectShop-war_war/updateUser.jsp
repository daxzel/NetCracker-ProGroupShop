<%-- 
    Document   : updateUser
    Created on : 07.10.2011, 21:29:52
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "DBClasses.*"%>
<%@page import= "java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование пользователя</title>
    </head>
    <body>
        <%
                    session.setAttribute("homepage", "updateUser.jsp");
        %>
        <h2>Редактирование пользователя</h2>
        <%
                    if (request.getAttribute("result") == null) {
        %>
        <form action="selectByNik">
            Введите ник пользователя:
            <input type="text" name="NIK" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%                } else {
                    if (request.getAttribute("result") instanceof User) {
                        User usr = (User) request.getAttribute("result");
                        SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
                        session.setAttribute("usrOld", usr);
        %>
        <form name="myForm" action="updateUser">
            <table>
                <tr><td>Имя</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="<%=usr.getName()%>" size="20" /></td><td></td></tr>
                <tr><td>Фамилия</td><td></td></tr>
                <tr><td><input type="text" name="SURNAME" value="<%=usr.getSurname()%>" size="25" /></td><td></td></tr>
                <tr><td>Отчество</td><td></td></tr>
                <tr><td><input type="text" name="OTCHESTVO" value="<%=usr.getOtchestvo()%>" size="20" /></td><td></td></tr>
                <tr><td>Ник</td><td></td></tr>
                <tr><td><input type="text" name="NIK" value="<%=usr.getNik()%>" size="20" /></td><td></td></tr>
                <tr><td>Пароль</td><td> Пароль(еще раз)</td></tr>
                <tr><td><input type="password" name="PASSWORD" value="" size="10" /></td><td>  <input type="password" name="PASSWORD2" value="" size="10" /></td></tr>
                <tr><td>Дата рождения</td><td></td></tr>
                <tr><td><input type="text" name="BORN" value="<%=formt.format(usr.getBorn())%>" size="10" /></td><td></td></tr>
                <tr><td>Телефон</td><td></td></tr>
                <%if(usr.getPhone()==null){ %>
                <tr><td><input type="text" name="PHONE" value="" size="11" /></td><td></td></tr>
                <%}else{%>
                <tr><td><input type="text" name="PHONE" value="<%=usr.getPhone()%>" size="11" /></td><td></td></tr>
                <%}%>
                <tr><td>Электронная почта</td><td></td></tr>
                <%if(usr.getEmail()==null){ %>
                <tr><td><input type="text" name="EMAIL" value="" size="25" /></td><td></td></tr>
                <%}else{%>
                <tr><td><input type="text" name="EMAIL" value="<%=usr.getEmail()%>" size="25" /></td><td></td></tr>
                <%}%>
                <tr><td>Уровень прав</td><td></td></tr>
                <tr><td><input type="text" name="ID_ROLE" value="<%=usr.getRoleName()%>" size="25" /></td><td></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>
        </form>
                <%                                } else {
                        %>
                <form action="selectByNik">
                    Введите ник пользователя:
                    <input type="text" name="NIK" value="" size="20" />
                    <input type="submit" value="Input" />
                </form>  <br><%=request.getAttribute("result")%>
                        <%
                                              }
                            }
                %>

    </body>
</html>
