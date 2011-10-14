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
            <p align="center"><a href ="image.jsp">����������� ���������</a><br></p>
            <p align="center"><a href ="catalog.jsp">Каталог</a><br></p>
            <p align="center"><a href ="opinion.jsp">����������� �������������</a><br></p>
            <p align="center"><a href ="order.jsp">�����</a><br></p>
            <p align="center"><a href ="product.jsp">Продукты</a><br></p>
            <p align="center"><a href ="role.jsp">���� �������������</a><br></p>
            <p align="center"><a href="registration.jsp">Регистрация</a></p>
            <p align="center"><a href="updateUser.jsp">update user</a></p>
            <p align="center"><a href="add_comment.jsp">addcomment</a></p>
            <p align="center"><a href="del_comment.jsp">delcomment</a></p>
            <p align="center"><a href="SELECT_PARAM.jsp">SELECT</a></p>
            <p align="center"><a href="getUsersByRole.jsp">Вывод пользователей по их ролям</a></p>
            <p align="center"><a href="deleteUser.jsp">Удалить пользователя</a></p>
            <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
    </body>
</html>
