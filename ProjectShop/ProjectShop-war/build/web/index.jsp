<%-- 
    Document   : index
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Интернет-магазин</title>
    </head>
    <body>
            <p align="center"><a href ="user.jsp">Пользователи</a><br></p>
            <p align="center"><a href ="image.jsp">Изображения продуктов</a><br></p>
            <p align="center"><a href ="catalog.jsp">Каталог</a><br></p>
            <p align="center"><a href ="opinion.jsp">Комментарии пользователей</a><br></p>
            <p align="center"><a href ="order.jsp">Заказ</a><br></p>
            <p align="center"><a href ="product.jsp">Продукт</a><br></p>
            <p align="center"><a href ="role.jsp">Поиск по ролям пользователей</a><br></p>
            <p align="center"><a href="registration.jsp">Регистрация</a></p>
            <p align="center"><a href="updateUser.jsp">Редактирование пользователя</a></p>
            <p align="center"><a href="getUsersByRole.jsp">Поиск пользователей по роле</a></p>
            <p align="center"><a href="deleteUser.jsp">Удаление пользователя</a></p>
            <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>

    </body>
</html>
