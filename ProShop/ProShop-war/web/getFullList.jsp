<%-- 
    Document   : getFullList
    Created on : 12.10.2011, 14:28:18
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "DBClasses.UserInterface"%>
<%@page import= "DBClasses.RoleInterface"%>
<%@page import= "DBClasses.ProductInterface"%>
<%@page import= "DBClasses.CatalogInterface"%>
<%@page import= "java.util.List"%>
<%@page import="java.text.SimpleDateFormat;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Полный список</title>

    </head>
    <body>
        <%
                    UserInterface usr = null;
                    if (session.getAttribute("user") != null && session.getAttribute("user") instanceof UserInterface) {
                        usr = (UserInterface) session.getAttribute("user");
                        if (usr.getLogin() == true) {
                            if (request.getAttribute("result") == null) {
        %>

            <p align="center"><a href ="getFullProductList">Get full list of product</a><br></p>
        <%if(usr.getRoleId()<=2){%>
             <p align="center"><a href ="getFullUserList">Get full list of user</a><br></p>
            <%}%>
        <%if(usr.getRoleId()==1){%>
             <p align="center"><a href ="getFullRoleList">Get full list of role</a><br></p>
        <%}%>
   <p align="center"><a href ="index.jsp">index</a><br></p>
        <%} else {
                    if (request.getAttribute("result") instanceof List) {
                        //  if()
                        List list1 = (List) request.getAttribute("result");
                        if (list1.get(0) instanceof UserInterface) {
                         UserInterface user;
                           SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
        %>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <td width="5%" align="center">User id</td>
                <td width="15%" align="center">Name</td>
                <td width="25%" align="center">Surname</td>
                <td width="20%" align="center">Otchestvo</td>
                <td width="30%" align="center">Nik</td>
                <td width="30%" align="center">Born</td>
                <td width="30%" align="center">Phone</td>
                <td width="30%" align="center">Email</td>
                <td width="30%" align="center">Role</td>
            </tr>
            <% for (int i = 0; i <= (list1.size() - 1); i++) {
                user= (UserInterface)list1.get(i);
                %>
            <tr align="center">
                <td><%= user.getId()%></td>
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
                <%}%>
                <td><%= user.getRoleName()%></td>
            </tr>
            <%}%>

        </table>
<p align="center"><a href ="index.jsp">index</a><br></p>
        <%  } if (list1.get(0) instanceof ProductInterface) {
                           ProductInterface prd;
                          //  SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
        %>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <td width="15%" align="center">Name</td>
            </tr>
            <% for (int i = 0; i <= (list1.size() - 1); i++) {
                prd= (ProductInterface) list1.get(i); %>
            <tr align="center">
                  <td><p align="center"><a href ="product?NAME=<%=prd.getName() %>"><%= prd.getName()%></a></p></td>
            </tr>
            <%}%>

        </table>
<p align="center"><a href ="index.jsp">index</a><br></p>
        <%  }

                        if (list1.get(0) instanceof RoleInterface&&usr.getRoleId()==1) {
                            RoleInterface role;
                            
        %>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <td width="5%" align="center">Role id</td>
                <td width="15%" align="center">Name</td>

            </tr>
            <% for (int i = 0; i <= (list1.size() - 1); i++) {
                role=(RoleInterface)list1.get(i);%>
            <tr align="center">
                <td><%= role.getId()%></td>
                <td><%= role.getName()%></td>
            </tr>
            <%}%>

        </table>
<p align="center"><a href ="index.jsp">index</a><br></p>
        <%  }

                                }
                            }
                        }
                    }else {
                        RequestDispatcher rd;
                        rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }%>
    </body>
</html>