<%-- 
    Document   : index
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,getCon.*,javax.sql.DataSource;" %>
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
            <p align="center"><a href ="role.jsp">Роль пользователей</a><br></p>
         

    </body>
</html>
