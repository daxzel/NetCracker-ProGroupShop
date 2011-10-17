<%-- 
    Document   : deleteUser
    Created on : 12.10.2011, 13:15:57
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "DBClasses.UserInterface"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Удаление пользователя</title>
    </head>
    <body>
        <%UserInterface usr = null;
                    if (session.getAttribute("user") != null && session.getAttribute("user") instanceof UserInterface) {
                        usr = (UserInterface) session.getAttribute("user");
                        if (usr.getLogin() == true) {
                            if (request.getAttribute("result") == null) {
        %>
        <form action="deleteUser">
            Введите ник:
            <input type="text" name="NIK" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%} else {
                    if (Integer.parseInt(request.getAttribute("result").toString()) == 1) {%>
        Пользователь удален.
        <%} else {%>
        Удаление прошло не успешно.
        <%}
                            }
                        }
                    } else {
                        RequestDispatcher rd;
                        rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }%>
                     <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
