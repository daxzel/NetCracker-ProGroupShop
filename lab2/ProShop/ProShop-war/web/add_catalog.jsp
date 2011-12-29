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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="static/main.css">
        <link href="static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="static/default.css" media="all" rel="stylesheet" type="text/css" />
        <title>Добавление новой записи в каталог</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="/ProShop-war/static/logo.jpg">
                            </td>
                            <td class="team" align="center"><a href="aboutTeam.jsp">Команда</a></td>
                            <td class="user_nav" align="right"><%if (usr == null) {%><a href="login.jsp">Вход</a>   <a href="registration.jsp">Регистрация</a><%} else {%><a href="logout">Выход</a><%}%></td>
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
                        <%=JSPHelper.getMenu(3)%>
                        <%}%>
                    </div>
                </div>
                <div id="content">
                    <h1>Добавление нового каталога</h1>
                    <form name="addCatalog" action="add_catalog">
                        <table id="regOrLog">
                            <tr><td>Название родительского каталога</td><td></td></tr>
                            <tr><td><input type="text" name="PARENTNAME" value="" size="10" /></td><td></td></tr>
                            <tr><td>Название каталога</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="" size="200" /></td><td></td></tr>
                            <tr><td><input type="submit" value=" Ввод " class="Button" /></td><td></td></tr>
                        </table>
                    </form>


                    <%if (request.getAttribute("result") != null) {%>
                    <%=request.getAttribute("result")%>
                    <%}
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
