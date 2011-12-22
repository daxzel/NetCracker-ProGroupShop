<%-- 
    Document   : registration
    Created on : 05.10.2011, 23:56:50
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="exceptions.*, helpers.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Регистрация пользователя</title>
    </head>
    <body>
        <%

        String name=JSPHelper.getRequestOrEmpty(request, "NAME");
        String surname=JSPHelper.getRequestOrEmpty(request, "SURNAME");
        String otchestvo=JSPHelper.getRequestOrEmpty(request, "OTCHESTVO");
        String nik=JSPHelper.getRequestOrEmpty(request, "NIK");
        String password=JSPHelper.getRequestOrEmpty(request, "PASSWORD");
        String password2=JSPHelper.getRequestOrEmpty(request, "PASSWORD2");
        String born=JSPHelper.getRequestOrEmpty(request, "BORN");
        String phone=JSPHelper.getRequestOrEmpty(request, "PHONE");
        String email=JSPHelper.getRequestOrEmpty(request, "EMAIL");
        String role=JSPHelper.getRequestOrEmpty(request, "ROLE");
        
        %>


        <H2>Регистрация</H2>

        <form name="myForm" action="registration">
            <table>
                <tr><td>Имя</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="<%=name%>" size="20" /></td><td></td></tr>
                <tr><td>Фамилия</td><td></td></tr>
                <tr><td><input type="text" name="SURNAME" value="<%=surname%>" size="25" /></td><td></td></tr>
                <tr><td>Отчество</td><td></td></tr>
                <tr><td><input type="text" name="OTCHESTVO" value="<%=otchestvo%>" size="20" /></td><td></td></tr>
                <tr><td>Ник</td><td></td></tr>
                <tr><td><input type="text" name="NIK" value="<%=nik%>" size="20" /></td><td></td></tr>
                <tr><td>Пароль</td><td> Пароль(еще раз)</td></tr>
                <tr><td><input type="password" name="PASSWORD" value="<%=password%>" size="10" /></td><td>  <input type="password" name="PASSWORD2" value="<%=password2%>" size="10" /></td></tr>
                <tr><td>Дата рождения</td><td></td></tr>
                <tr><td><input type="date" name="BORN" value="<%=born%>" size="15" /></td><td></td></tr>
                <tr><td>Телефон</td><td></td></tr>
                <tr><td><input type="text" name="PHONE" value="<%=phone%>" size="11" /></td><td></td></tr>
                <tr><td>Электронная почта</td><td></td></tr>
                <tr><td><input type="text" name="EMAIL" value="<%=email%>" size="25" /></td><td></td></tr>
                <tr><td><select name="ROLE" style="width : 200">
                    <% if (role.equals("1"))
                    {%>
                        <option value="1" selected>Админ</option>
                        <option value="2">Пользователь</option>
                    <%} else {%>
                        <option value="1">Админ</option>
                        <option value="3" selected>Пользователь</option>
                        <option value="2" selected>Менеджер</option>
                    <%}%>
                </select></td><td></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
             </table>
        </form>

        <%if(request.getAttribute("result")!=null){
                   %><%=request.getAttribute("result")%><%
        }
        %>
        <p align="left"><a href ="login.jsp">enter</a><br></p>
      <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
