<%-- 
    Document   : getUsersByRole
    Created on : 11.10.2011, 23:25:21
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "DBClasses.UserInterface"%>
<%@page import= "java.util.List"%>
<%@page import="java.text.SimpleDateFormat;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Поиск пользователей по роле</title>
    </head>
    <body>
        <%
                    UserInterface usr = null;
                    if (session.getAttribute("user") != null && session.getAttribute("user") instanceof UserInterface) {
                        usr = (UserInterface) session.getAttribute("user");
                        if (usr.getLogin() == true) {
                            if (request.getAttribute("result") == null) {
        %>
        <form action="getUsersByRole">
            Введите роль:
            <input type="text" name="ROLE" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
         <p align="left"><a href ="index.jsp">index</a><br></p>
        <%} else {
                    if (request.getAttribute("result") instanceof List) {
                        List<UserInterface> list = (List<UserInterface>) request.getAttribute("result");
                        SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
        %>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <%if (usr.getRoleId()==1){%>
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
            <% for (int i = 0; i <= (list.size() - 1); i++) {%>
            <tr align="center">
                <%if (usr.getRoleId()==1){%>
                <td><%= list.get(i).getId()%></td>
                <%}%>
                <td><%= list.get(i).getName()%></td>
                <td><%= list.get(i).getSurname()%></td>
                <td><%= list.get(i).getOtchestvo()%></td>
                <td><%= list.get(i).getNik()%></td>
                <td><%=formt.format(list.get(i).getBorn())%></td>
                <%if (list.get(i).getPhone() != null) {%>
                <td><%= list.get(i).getPhone()%></td>
                <%} else {%>
                <td></td>
                <%}
                     if (list.get(i).getEmail() != null) {%>
                <td><%= list.get(i).getEmail()%></td>
                <%} else {%>
                <td></td>
                <%}
                                }%>
            </tr>
        </table>
 <p align="left"><a href ="index.jsp">index</a><br></p>
        <%  }
                            }
                        }
                    }else {
                        RequestDispatcher rd;
                        rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }%>

    </body>
</html>
