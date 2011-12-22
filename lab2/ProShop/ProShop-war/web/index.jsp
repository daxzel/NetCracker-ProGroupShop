<%-- 
    Document   : index
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="menu.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="errorPage.jsp"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource,entityBeans.UserBeanRemoteHome,entityBeans.UserBeanRemote, helpers.*,exceptions.LoginException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/ProShop-war/static/main.css">
        <link href="/ProShop-war/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="/ProShop-war/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="/ProShop-war/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <title>Интернет-магазин</title>
    </head>
    <body>
        <%
                    //    PrintWriter pw = response.getWriter();
                    UserBeanRemote usr = null;
                    try {
                        usr = JSPHelper.getUser2(session);
                        if (usr.getRoleId() == 1) {
        %>


        <p align="center"><a href="getFull_catalogNew">Каталог</a></p>
        <p align="center"><a href="basket">Корзина</a></p>
        <p align="center"><a href="getOrders">Заказы</a></p>
        <p align="center"><a href="add_catalog.jsp">Добавление нового каталога</a></p>
        <p align="center"><a href="del_catalog.jsp">Удаление каталога</a></p>
        <p align="center"><a href ="addProduct.jsp">Добавление продукта</a><br></p>
        <p align="center"><a href="delProduct.jsp">Удаление продукта</a></p>
        <p align="center"><a href="updateUser.jsp?DO=upProf">Редактирование профиля</a></p>
        <p align="center"><a href="updateUser.jsp?DO=upUser">update user</a></p>
        <p align="center"><a href="updateProduct.jsp">Редактировать продукт</a></p>
        <p align="center"><a href="getUsersByRole.jsp">Вывод пользователей по их ролям</a></p>
        <p align="center"><a href="deleteUser.jsp">Удалить пользователя</a></p>
        <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
        <p align="center"><a href="add_image.jsp">Добавить картинку</a></p>
        <p align="center"><a href="XML/export.jsp">Экспорт</a></p>
        <p align="center"><a href="XML/import.jsp">Импорт</a></p>
        <p align="center"><a href="logout">Выход</a></p>

        <%} else {
                                    if (usr.getRoleId() == 3) {%>
        <p align="center"><a href="basket">Корзина</a></p>
        <p align="center"><a href="getOrders">Заказы</a></p>
        <p align="center"><a href="getFull_catalogNew">Каталог</a></p>
        <p align="center"><a href="updateUser.jsp?DO=upProf">Редактирование профиля</a></p>
        <p align="center"><a href="getUsersByRole.jsp">Вывод пользователей по их ролям</a></p>
        <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
        <p align="center"><a href="logout">Выход</a></p>
        <%                                    }
                                    if (usr.getRoleId() == 2) {%>
        <p align="center"><a href="basket">Корзина</a></p>
        <p align="center"><a href="getOrders">Заказы</a></p>
        <p align="center"><a href="getFull_catalogNew">Каталог</a></p>
        <p align="center"><a href="add_catalog.jsp">Добавление нового каталога</a></p>
        <p align="center"><a href="del_catalog.jsp">Удаление каталога</a></p>
        <p align="center"><a href ="addProduct.jsp">Добавление продукта</a><br></p>
        <p align="center"><a href="delProduct.jsp">Удаление продукта</a></p>
        <p align="center"><a href="updateUser.jsp?DO=upProf">Редактирование профиля</a></p>
        <p align="center"><a href="getUsersByRole.jsp">Вывод пользователей по их ролям</a></p>
        <p align="center"><a href="add_image.jsp">Добавить картинку</a></p>
        <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
        <p align="center"><a href="logout">Выход</a></p>
        <%}
                                }
                            } catch (LoginException ex) {%>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="/ProShop-war/static/logo.jpg">
                            </td>
                            <td class="team" align="center"><a href="aboutTeam.jsp">Команда</a></td>
                            <td class="user_nav" align="right"><%%><a href="login.jsp">Вход</a>  <a href="login.jsp">Регистрация</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="catalog">
                <%=Menu.getMenu()%>
            </div>
        </div>

        <%} finally {
                    }
        %>
    </body>
</html>
