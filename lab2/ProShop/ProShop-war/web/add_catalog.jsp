<%-- 
    Document   : add_catalog
    Created on : 15.10.2011, 22:24:17
    Author     : Pushok
--%>

<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "entityBeans.UserBeanRemote, helpers.*;"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE html>
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
                    <h1>Добавление нового каталога</h1>
                    <form name="addCatalog" action="add_catalog">
                        <table id="regOrLog">
                            <tr><td>Название родительского каталога</td><td></td></tr>
                            <tr><td><input type="text" name="PARENTNAME" value="" size="60" /></td><td></td></tr>
                            <tr><td>Название каталога</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="" size="60" /></td><td></td></tr>
                            <tr><td><input type="submit" value=" Ввод " class="Button" /></td><td></td></tr>
                        </table>
                    </form>


                    <%if (request.getAttribute("result") != null) {%>
                    <%=request.getAttribute("result")%>
                    <%}
                    %>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
