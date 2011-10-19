<%-- 
    Document   : registration
    Created on : 05.10.2011, 23:56:50
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="exceptions.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Регистрация пользователя</title>
    </head>
    <body>
        <%

        String name="";
        String surname="";
        String otchestvo="";
        String nik="";
        String password="";
        String password2="";
        String born="";
        String phone="";
        String email="";
        
        if(request.getAttribute("NAME")!=null)
        {
            name=request.getAttribute("NAME").toString();
        }
        
        if(request.getAttribute("SURNAME")!=null)
        {
            surname=request.getAttribute("SURNAME").toString();
        }
        
        if(request.getAttribute("OTCHESTVO")!=null)
        {
            otchestvo=request.getAttribute("OTCHESTVO").toString();
        }
        
        if(request.getAttribute("NIK")!=null)
        {
            nik=request.getAttribute("NIK").toString();
        }

        if(request.getAttribute("PASSWORD")!=null)
        {
            password=request.getAttribute("PASSWORD").toString();
        }

        if(request.getAttribute("PASSWORD2")!=null)
        {
            password2=request.getAttribute("PASSWORD2").toString();
        }

        if(request.getAttribute("BORN")!=null)
        {
            born=request.getAttribute("BORN").toString();
        }

        if(request.getAttribute("PHONE")!=null)
        {
            phone=request.getAttribute("PHONE").toString();
        }

        if(request.getAttribute("EMAIL")!=null)
        {
            email=request.getAttribute("EMAIL").toString();
        }
        
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
