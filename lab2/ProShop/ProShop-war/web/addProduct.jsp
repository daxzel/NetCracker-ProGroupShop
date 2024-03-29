
<%@page import="menu.Menu"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : product
    Created on : 06.10.2011, 20:35:21
    Author     : �����
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource, helpers.*,entityBeans.UserBeanRemote;" %>
<%@page errorPage="errorPage.jsp"%>
<%@page import="exceptions.LoginException"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file='head.jsp'%>
       <title>Интернет-магазин</title>
    </head>
    <body>
        <%
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
                    String name = JSPHelper.getRequestOrEmpty(request, "NAME");
                    String description = JSPHelper.getRequestOrEmpty(request, "DESCRIPTION");
                    String price = JSPHelper.getRequestOrEmpty(request, "PRICE");
                    String name_catalog = JSPHelper.getRequestOrEmpty(request, "NAME_CATALOG");
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
                    <h1>Добавление продукта</h1>
                    <form name="addProductForm" action="addProduct" method="post" enctype="multipart/form-data">
                        <table id="regOrLog">
                            <tr><td>Имя</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="<%=name%>" size="60" /></td><td></td></tr>
                            <tr><td>Описание</td><td></td></tr>
                            <tr><td><input type="text" name="DESCRIPTION" value="<%=description%>" size="100" /></td><td></td></tr>
                            <tr><td>Цена</td><td></td></tr>
                            <tr><td><input type="text" name="PRICE" value="<%=price%>" size="20" /></td><td></td></tr>
                            <tr><td>Название каталога</td><td></td></tr>
                            <tr><td><input type="text" name="NAME_CATALOG" value="<%=name_catalog%>" size="60" /></td><td></td></tr>
                            <tr><td>Изображения</td><td></td></tr>
                                 <tr><td><input type="file" name="IMAGE"  accept="image/jpeg" multiple  title="Выберите одно или несколько изображений"/></td><td></td></tr>
                            <tr><td><input type="submit" value=" Ввод " class="Button" /></td><td></td></tr>

                        </table>

                    </form>
                    <%if (request.getAttribute("result") != null) {
                    %><%=request.getAttribute("result")%><%
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
