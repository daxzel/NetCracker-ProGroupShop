<%-- 
    Document   : del_catalog
    Created on : 16.10.2011, 16:20:53
    Author     : Pushok
--%>

<%@page import="menu.Menu"%>
<%@page import="helpers.*"%>
<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>
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
                    <h1>Удаление каталога</h1>

                    <form name="delCatalog" action="del_catalog">
                        <table id="regOrLog">
                            <tr><td>Название каталога</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="" size="50" /></td><td></td></tr>
                            <tr><td><input type="submit" value=" Ввод " class="Button" /></td><td></td></tr>
                        </table>
                    </form>


                    <%if (request.getAttribute("result") != null) {%>
                    <%=request.getAttribute("result")%>

                    <%}%>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
