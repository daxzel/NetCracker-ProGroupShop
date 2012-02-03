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
       <%@include file='head.jsp'%>
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
            <%@include file='header.jspf'%>
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
                <%@include file='searchBlock.jspf'%>
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
                                <td width="2%" align="center">ПК</td>
                                <td width="7%" align="center">Имя</td>
                                <td width="15%" align="center">Фамилия</td>
                                <td width="15%" align="center">Отчество</td>
                                <td width="15%" align="center">Никнейм</td>
                                <td width="20%" align="center">Дата рождения</td>
                                <td width="30%" align="center">Телефон</td>
                                <td width="30%" align="center">Email</td>
                                <td width="20%" align="center">Дата регистрации</td>
                                <td width="7%" align="center">Статус</td>
                                <%if (usr.getRoleId() == 1) {%>
                                <td width="30%" align="center">Экспорт</td>
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
                                <td><%= user.getRegistrationDate()%></td>
                                <td><%if (user.getRoleId() == 1) {%>
                                    admin
                                    <%} if (user.getRoleId() == 2) {%>
                                    manager
                                    <%} if (user.getRoleId() == 4) {%>
                                    block
                                    <%} if (user.getRoleId() == 3) {%>
                                    user
                                    <%}%>
                                </td>
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
                            <td width="5%" align="center">ПК Прав доступа</td>
                            <td width="15%" align="center">Название</td>

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
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
