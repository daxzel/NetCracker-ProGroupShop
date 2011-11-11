<%-- 
    Document   : getFullList
    Created on : 12.10.2011, 14:28:18
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "entityBeans.*,java.util.List,java.util.Iterator,java.text.SimpleDateFormat,Other.JSPHelper,exceptions.LoginException;"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Полный список</title>

    </head>
    <body>
        <%           UserBeanRemote usr = null;
                    try {
                        usr = JSPHelper.getUser2(session);
                    } catch (LoginException ex) {
                    }
                    if (request.getAttribute("result") == null) {
        %>

        <p align="center"><a href ="getFullProductList">Get full list of product</a><br></p>
            <% if (usr != null) {
                                        if (usr.getRoleId() <= 3) {%>
        <p align="center"><a href ="getFullUserList">Get full list of user</a><br></p>
            <%}%>
            <%if (usr.getRoleId() == 1) {%>
        <p align="center"><a href ="getFullRoleList">Get full list of role</a><br></p>
            <%}
                                    }%>
        <p align="center"><a href ="index.jsp">index</a><br></p>
            <%} else {
                                    if (request.getAttribute("result") instanceof List) {
                                        List list1 = (List) request.getAttribute("result");
                                        if (list1.isEmpty()) {%>
        <p align="center">Таблица не содержит данных</p>
        <%} else {
                                                    if (list1.get(0) instanceof UserBeanRemote) {
                                                        UserBeanRemote user;
                                                        SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
        %>
        <form action="exportUser">

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
                    <td width="30%" align="center">Export</td>
                </tr>
                <% for (int i = 0; i <= (list1.size() - 1); i++) {
                                                                            user = (UserBeanRemote) list1.get(i);
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
                    <td><%if (user.getRoleId() == 1) {%>
                        admin
                        <%} else {%>
                        user
                        <%}%></td>
                    <td><a href ="XML/exportUser?ID=<%=usr.getId() %>">Export</a></td>
                </tr>
                <%}%>

            </table>
        </form>
        <p align="center"><a href ="index.jsp">index</a><br></p>
            <%  }
                                                        if (list1.get(0) instanceof ProductBeanRemote) {
                                                            ProductBeanRemote prd;
                                                            //  SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
%>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <td width="15%" align="center">Name</td>
            </tr>
            <% for (int i = 0; i <= (list1.size() - 1); i++) {
                                                                            prd = (ProductBeanRemote) list1.get(i);%>
            <tr align="center">
                <td><p align="center"><a href ="product?ID=<%=prd.getId()%>"><%= prd.getName()%></a></p></td>
            </tr>
            <%}%>

        </table>
        <p align="center"><a href ="index.jsp">index</a><br></p>
            <%  }

                                                        if (list1.get(0) instanceof RoleBeanRemote && usr.getRoleId() == 1) {
                                                            RoleBeanRemote role;

            %>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <td width="5%" align="center">Role id</td>
                <td width="15%" align="center">Name</td>

            </tr>
            <% for (int i = 0; i <= (list1.size() - 1); i++) {
                                                                            role = (RoleBeanRemote) list1.get(i);%>
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
            %>
    </body>
</html>
