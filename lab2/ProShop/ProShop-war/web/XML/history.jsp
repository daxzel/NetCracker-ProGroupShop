<%-- 
    Document   : history
    Created on : 01.12.2011, 0:18:46
    Author     : Pushok
--%>

<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="menu.Menu"%>
<%@page errorPage="/errorPage.jsp"%>
<%@page import="helpers.JSPHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />

        <title>История</title>
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
                        <%=JSPHelper.getMenu(3)%>
                        <%}%>
                    </div>
                </div>
                <div id="content">
                    <form action="history">
                        <table id="regOrLog">
                            <tr><td>Выберите таблицу:</td><td>
                                    <select name="TABLE" style="width : 200">
                                        <option value="1" selected>Catalog</option>
                                        <option value="2" selected>Image</option>
                                        <option value="3" selected>Opinion</option>
                                        <option value="4" selected>Order</option>
                                        <option value="5" selected>Product</option>
                                        <option value="6" selected>Role</option>
                                        <option value="7" selected>User</option>
                                    </select></td></tr>
                            <tr><td>Введите ID обьекта:</td><td> <input type="text" name="ID" value="" /></td></tr>
                            <tr><td>Показать записи</td><td><input type="submit" value=" Ввод " class="Button"/></td></tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>