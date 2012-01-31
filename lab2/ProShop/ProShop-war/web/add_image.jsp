<%@page import="menu.Menu"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : add_image
    Created on : 06.10.2011, 21:05:24
    Author     : �����
--%>

<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="exceptions.LoginException"%>
<%@page import="helpers.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file='head.jsp'%>
       <title>Интернет-магазин</title>
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
                        <%=JSPHelper.getMenu(4)%>
                        <%}%>
                    </div>
                </div>
                    <%@include file='searchBlock.jspf'%>
                    <div id="content">
                    <h1>Добавление изображений</h1>
        <form name="add_image" method="post" action="addImage" enctype="multipart/form-data">

            <table border="0">
                <tbody>
                    <tr>
                        <td>Название продукта</td>
                        <td><input type="text" name="NAMEPRODUCT" value="" size="60"/></td>
                    </tr>
                    <tr>
                        <td>Изображения</td>
                        <td><input type="file" name="IMAGE" accept="image/jpeg" multiple title="Выберите одно или несколько изображений" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit"  value="Ввод" class="Button" />
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
