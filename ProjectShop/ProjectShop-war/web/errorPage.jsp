<%-- 
    Document   : errorPage
    Created on : 22.10.2011, 23:32:15
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@ page import = "exceptions.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error page</title>
    </head>
    <body>
        <%=exception.getMessage() %><br>
        <%if(exception instanceof LoginException){%>
        Для входа в систему перейдите по ссылке<br>
        <p align="left"><a href ="login.jsp">login</a><br></p>
        <%    }
        %>
    </body>
</html>
