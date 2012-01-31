<%-- 
    Document   : exportUser
    Created on : 17.11.2011, 16:04:32
    Author     : Pushok
--%>

<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page errorPage="/errorPage.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <%@include file='head.jsp'%>
       <title>Экспорт пользователей</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
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
                        <%=JSPHelper.getMenu(3)%>
                        <%}%>
                    </div>
                </div>
                <%@include file='searchBlock.jspf'%>
                <div id="content">
                    <form action="exportUsersP">
                        <table id="regOrLog">
                            <tr><td>Введите имя:</td><td> <input type="text" name="NAME" value="" /></td></tr>
                            <tr><td>Введите роль:
                                    <select name="ROLE" style="width : 200">
                                        <option value="admin" selected>Админ</option>
                                        <option value="manager" selected>Менеджер</option>
                                        <option value="user" selected>Пользователь</option>
                                    </select></td><td></td></tr>
                            <tr><td>Выбор пользователей по имени</td><td><input type="checkbox" name="USERSBYNAME" value="ON" /> </td></tr>
                            <tr><td>Выбор пользователей по роли</td><td><input type="checkbox" name="USERSBYROLE" value="ON" /> </td></tr>
                            <tr><td>Экспортировать выбранных пользователей и связанные роли</td><td><input type="checkbox" name="ROLES" value="ON" /> </td></tr>
                            <tr><td>Экспортировать выбранных пользователей и связанные отзывы</td><td><input type="checkbox" name="OPINIONS" value="ON" /> </td></tr>
                            <tr><td>Экспортировать выбранных пользователей и связанные каталоги</td><td><input type="checkbox" name="CATALOGS" value="ON" /> </td></tr>
                            <tr><td>Экспортировать выбранных пользователей и связанные продукты</td><td><input type="checkbox" name="PRODUCTS" value="ON" /> </td></tr>
                            <tr><td>Экспортировать выбранных пользователей и связанные заказы</td><td><input type="checkbox" name="ORDERS" value="ON" /> </td></tr>
                            <tr><td>Показать записи</td><td><input type="submit" value=" Ввод " class="Button"/></td></tr>
                        </table>
                    </form>
                    <%Object obj = request.getAttribute("result");
                        if (obj != null) {%>
                    <%=obj.toString()%>
                    <%}%>
                </div>
            </div>
                    <div class="team" align="center">
               <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
        </div>
        </div>
    </body>
</html>
