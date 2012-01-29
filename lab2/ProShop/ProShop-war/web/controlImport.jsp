<%-- 
    Document   : controlImport
    Created on : 29.01.2012, 14:00:26
    Author     : Admin
--%>

<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "entityBeans.UserBeanRemote, helpers.*"%>
<%@page errorPage="errorPage.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
         <%@include file='head.jsp'%>
        <title>Интернет магазин</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
           Object obj = request.getAttribute("importObjects");
           String uuid = (String)request.getAttribute("UUIDOfTempFile");

           AdderAndUpdater importObj = (AdderAndUpdater)obj;
           List catalogs = importObj.getCatalogs();
           List images = importObj.getImages();
           List opinions = importObj.getOpinions();
           List orders = importObj.getOrders();
           List roles = importObj.getRoles();
           List products = importObj.getProducts();
           List users = importObj.getUsers();
           
        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="<%=request.getContextPath()%>/static/logo.jpg">
                            </td>

                            <td  class="current_user" align="right"><%if (usr == null) {%><a> </a><%} else {%>Текущий пользователь:<a href="<%=request.getContextPath()%>/updateUser.jsp?DO=updateProfil"> <%=usr.getNik()%></a>   Статус: <%=usr.getRoleName()%> <%}%></td>
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
                    <form action="/ProShop-war/XML/managedImport">
                        <input type="hidden" name="uuid" value="<%=uuid%>"/>
                        <% if (users.size()>0)  {%>
                            <div id="users">
                                <h1>Продукты</h1>
                                 <table align="center"  border="1" width="100%">
                                    <tr align="center">
                                        <td align="center" width="5%">ID</td>
                                        <td align="center" width="20%">Имя</td>
                                        <td align="center" width="20%">Фамилия</td>
                                        <td align="center" width="15%">Отчество</td>
                                        <td align="center" width="15%">Никнейм</td>
                                        <td align="center" width="15%">Дата рождения</td>
                                        <td align="center" width="5%">Роль</td>
                                        <td align="center" width="5%">Выполнить замену</td>
                                    </tr>
                                    <%
                                    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                    for(int i = 0; i<users.size(); i++) {
                                        UserBeanRemote user  = (UserBeanRemote)users.get(i); %>
                                        <tr align="center">
                                            <td><%= Long.toString(user.getId())%></td>
                                            <td><%= user.getName()%></td>
                                            <td><%= user.getSurname()%></td>
                                            <td><%= user.getOtchestvo()%></td>
                                            <td><%= user.getNik()%></td>
                                            <td><%=format.format(user.getBorn())%></td>
                                            <td><%= user.getRoleId()%></td>
                                            <td  align="center"><input type="checkbox" name="id_user" value=<%=user.getId()%>/></td>
                                         </tr>
                                    <%}%>
                                 </table>
                            </div>
                        <% }%>
                        <input type="submit" value="Выполнить импорт" class="Button"/>
                    </form>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
