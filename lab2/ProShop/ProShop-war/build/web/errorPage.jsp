<%-- 
    Document   : errorPage
    Created on : 22.10.2011, 23:32:15
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@ page import = "exceptions.*,javax.servlet.RequestDispatcher;"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error page</title>
    </head>
    <body>
        <br>
        <%if (exception instanceof LoginException) {
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    } else {
                        if (request.getAttribute("exception") == null) {
        %>
        <%=exception.getMessage()%>
        <%} else {
                                    Exception ex = (Exception) request.getAttribute("exception");%>
        <%=ex.getMessage()%>  
        <%}
                    }%>
        <p align="left"><a href ="/ProShop-war/index.jsp">index</a><br></p>
    </body>
</html>
