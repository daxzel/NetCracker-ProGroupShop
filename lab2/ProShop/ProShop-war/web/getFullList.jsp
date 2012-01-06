<%-- 
    Document   : getFullList
    Created on : 12.10.2011, 14:28:18
    Author     : Yra
--%>


<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="entityBeans.CatalogBeanRemote,entityBeans.ProductBeanRemote"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "entityBeans.*,java.util.List,java.util.Iterator,java.text.SimpleDateFormat, helpers.*,exceptions.LoginException;"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />
        <title>Полный список</title>

    </head>
    <body>
        <%           UserBeanRemote usr = null;
                    try {
                        usr = JSPHelper.getUser2(session);
                    } catch (LoginException ex) {
                    }

        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="<%=request.getContextPath()%>/static/logo.jpg">
                            </td>
                            <td class="team" align="center"><a href="<%=request.getContextPath()%>/aboutTeam.jsp">Команда</a></td>
                            <td class="user_nav" align="right"><%if (usr == null) {%><a href="<%=request.getContextPath()%>/login.jsp">Вход</a>   <a href="<%=request.getContextPath()%>/registration.jsp">Регистрация</a><%} else {%><a href="<%=request.getContextPath()%>/logout">Выход</a><%}%></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="cols">
                <div id="menu">
                    <div class="catalog">
                        <%=Menu.getMenu()%>
                    </div>
                    <div class="user_menu">
                        <%if (usr != null) {%>
                        <%=JSPHelper.getMenu(usr.getRoleId())%>
                        <%} else {%>
                        <%=JSPHelper.getMenu(4)%>
                        <%}%>
                    </div>
                </div>
                <div id="content">
                    <%   if (request.getAttribute("result") == null) {%>
                    <p align="center"><a href ="getFullProductList">Вся продукция</a><br></p>
                        <% if (usr != null) {
                       if (usr.getRoleId() <= 3) {%>
                    <p align="center"><a href ="getFullUserList">Зарегистрированные  пользователи</a><br></p>
                        <%}%>
                        <%if (usr.getRoleId() == 1) {%>
                    <p align="center"><a href ="getFullRoleList">Роли</a><br></p>
                        <%}
                   }%>
                  
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
                    <form action="XML/exportUser">

                        <table align="center"  border="1" width="100%">
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
                                <%if (usr.getRoleId() == 1) {%>
                                <td width="30%" align="center">Export</td>
                                <%}%>
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
                                <%if (user.getBorn() != null) {%>
                                <td><%=formt.format(user.getBorn())%></td>
                                <%} else {%>
                                <td></td>
                                <%}
                         if (user.getPhone() != null) {%>
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
                                    <%if (usr.getRoleId() == 1) {%>
                                <td><input type="checkbox" name="<%=user.getId()%>" value="ON" /></td>
                                    <%}%>
                            </tr>
                            <%}%>
                            <%if (usr.getRoleId() == 1) {%>
                            <tr><td colspan="5"><input type="submit" value="Экспортировать пользователей и роли связанные с ними" name="input" /></td><td colspan="4"><input type="submit" value="Экспортировать только пользователей" name="input2" /></td></tr>
                                    <%}%>
                        </table>
                    </form>
                    
                        <%  }
                                                                    if (list1.get(0) instanceof ProductBeanRemote) {
                                                                        ProductBeanRemote prd;
                                                                        //  SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
%>
                    <table align="center"  border="1" width="100%">
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
                   
                        <%  }

                                                                    if (list1.get(0) instanceof RoleBeanRemote && usr.getRoleId() == 1) {
                                                                        RoleBeanRemote role;

                        %>
                    <table align="center"  border="1" width="100%">
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

                    <%  }

                                        }
                                    }
                                }
                    %>

                </div>
            </div>
        </div>
    </body>
</html>
