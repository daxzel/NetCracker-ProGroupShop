<%-- 
    Document   : index
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource,DBClasses.UserInterface, Other.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Интернет-магазин</title>
    </head>
    <body>
        <%EnumRole role = JSPHelper.Role(session);%>

        <%if (role == EnumRole.guest) {%>

        <p align="center"><a href="registration.jsp">Регистрация</a></p>
        <p align="center"><a href="login.jsp">Вход</a></p>
        <p align="center"><a href="getFull_catalog">Каталог</a></p>

        <%} else {
            if (role == EnumRole.admin) {
        %>

        <p align="center"><a href="getFull_catalog">Каталог</a></p>
        <p align="center"><a href="basket">Корзина</a></p>
        <p align="center"><a href="getOrders">Заказы</a></p>
        <p align="center"><a href="add_catalog.jsp">Добавление нового каталога</a></p>
        <p align="center"><a href="del_catalog.jsp">Удаление каталога</a></p>
        <p align="center"><a href ="addProduct.jsp">Добавление продукта</a><br></p>
        <p align="center"><a href="updateUser.jsp?DO=upProf">Редактирование профиля</a></p>
        <p align="center"><a href="updateUser.jsp?DO=upUser">update user</a></p>
        <p align="center"><a href="add_comment.jsp">addcomment</a></p>
        <p align="center"><a href="del_comment.jsp">delcomment</a></p>
        <p align="center"><a href="getUsersByRole.jsp">Вывод пользователей по их ролям</a></p>
        <p align="center"><a href="deleteUser.jsp">Удалить пользователя</a></p>
        <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
        <p align="center"><a href="getOpinion.jsp">Вывод всех комментариев продута</a></p>
        <p align="center"><a href="getFull_catalog">Вывести каталог</a></p>
        <p align="center"><a href="getChild_catalog.jsp">Вывести потомков</a></p>

        <%} else {
                        if (role == EnumRole.user) {%>
        <p align="center"><a href="basket">Корзина</a></p>
        <p align="center"><a href="getOrders">Заказы</a></p>
        <p align="center"><a href="getFull_catalog">Каталог</a></p>
        <p align="center"><a href="getFull_catalog">Вывести весь список каталогов</a></p>
        <p align="center"><a href="getChild_catalog.jsp">Вывести потомков</a></p>
        <p align="center"><a href="updateUser.jsp?DO=upProf">Редактирование профиля</a></p>
        <p align="center"><a href="add_comment.jsp">addcomment</a></p>
        <p align="center"><a href="del_comment.jsp">delcomment</a></p>
        <p align="center"><a href="getUsersByRole.jsp">Вывод пользователей по их ролям</a></p>
        <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
        <p align="center"><a href="getOpinion.jsp">Вывод всех комментариев продута</a></p>

        <%                                    }
                        }
                    }%>
    </body>
</html>
