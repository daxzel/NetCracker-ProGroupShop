<%-- 
    Document   : index
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource,DBClasses.UserInterface" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Интернет-магазин</title>
    </head>
    <body>
        <%if(session.getAttribute("user")==null){%>
            <p align="center"><a href ="image.jsp">Add image</a><br></p>
            <p align="center"><a href ="catalog.jsp">Каталог</a><br></p>
           
            <p align="center"><a href ="order.jsp">Order</a><br></p>
           
            <p align="center"><a href="registration.jsp">Регистрация</a></p>
            <p align="center"><a href="login.jsp">Вход</a></p>
            <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
            <p align="center"><a href="getOpinion.jsp">Вывод всех комментариев продута</a></p>
        <%}else{
            UserInterface usr = (UserInterface) session.getAttribute("user");
            if(usr.getRoleId()==1){
        %>
            <p align="center"><a href ="catalog.jsp">Каталог</a><br></p>
            <p align="center"><a href ="product.jsp">Добавление продукта</a><br></p>
            <p align="center"><a href="updateUser.jsp?DO=upProf">Редактирование профиля</a></p>
            <p align="center"><a href="updateUser.jsp?DO=upUser">update user</a></p>
            <p align="center"><a href="add_comment.jsp">addcomment</a></p>
            <p align="center"><a href="del_comment.jsp">delcomment</a></p>
            <p align="center"><a href="SELECT_PARAM.jsp">SELECT</a></p>
            <p align="center"><a href="getUsersByRole.jsp">Вывод пользователей по их ролям</a></p>
            <p align="center"><a href="deleteUser.jsp">Удалить пользователя</a></p>
            <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
            <p align="center"><a href="getOpinion.jsp">Вывод всех комментариев продута</a></p>

            <%} if(usr.getRoleId()==2){%>
            <p align="center"><a href ="catalog.jsp">Каталог</a><br></p>
            
            <p align="center"><a href="updateUser.jsp?DO=upProf">Редактирование профиля</a></p>
            <p align="center"><a href="add_comment.jsp">addcomment</a></p>
            <p align="center"><a href="del_comment.jsp">delcomment</a></p>
            <p align="center"><a href="SELECT_PARAM.jsp">SELECT</a></p>
            <p align="center"><a href="getUsersByRole.jsp">Вывод пользователей по их ролям</a></p>
            <p align="center"><a href="getFullList.jsp">Вывод всего</a></p>
            <p align="center"><a href="getOpinion.jsp">Вывод всех комментариев продута</a></p>
            <%
            }
        }%>
    </body>
</html>
