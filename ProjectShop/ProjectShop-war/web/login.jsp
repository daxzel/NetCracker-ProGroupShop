<%-- 
    Document   : login
    Created on : 15.10.2011, 14:12:27
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
       
         <form action="login">
             <p align="center">
            Введите ник:
            <input type="text" name="NIK" value="" size="20" /><br>
             Введите пароль:
            <input type="text" name="PASSWORD" value="" size="10" /><br>
            <input type="submit" value="Input" />
           </p>
        </form>
         <%if(request.getAttribute("result")!=null){%>
         <%=request.getAttribute("result")%><%}%>
         <p align="center"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
