<%-- 
    Document   : exportUser
    Created on : 17.11.2011, 16:04:32
    Author     : Pushok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Выбор экспорта</title>
    </head>
    <body>
        <form action="exportUsersP">
            <table>
                <tr><td>Введите имя:</td><td> <input type="text" name="NAME" value="" /></td></tr>                
                <tr><td>Введите роль:
                <select name="ROLE" style="width : 200">
                <option value="admin" selected>Админ</option>
                <option value="manager" selected>Менеджер</option>
                <option value="user" selected>Пользователь</option>
                </select></td><td></td></tr>
                <tr><td>Выбор пользователей по имени</td><td><input type="checkbox" name="USERSBYNAME" value="ON" /> </td></tr>
                <tr><td>Выбор пользователей по роли</td><td><input type="checkbox" name="USERSBYROLE" value="ON" /> </td></tr>                
                <tr><td>Экспортировать выбранных пользователей и связанные роли</td><td><input type="checkbox" name="ROLES" value="ON" /> </td></tr>
                <tr><td>Экспортировать выбранных пользователей и связанные отзывы</td><td><input type="checkbox" name="OPINIONS" value="ON" /> </td></tr>
                <tr><td>Экспортировать выбранных пользователей и связанные каталоги</td><td><input type="checkbox" name="CATALOGS" value="ON" /> </td></tr>                
                <tr><td>Экспортировать выбранных пользователей и связанные продукты</td><td><input type="checkbox" name="PRODUCTS" value="ON" /> </td></tr> 
                <tr><td>Экспортировать выбранных пользователей и связанные заказы</td><td><input type="checkbox" name="ORDERS" value="ON" /> </td></tr>                
                <tr><td>Показать записи</td><td><input type="submit" value="Input" /></td></tr>
            </table>
        </form>     
    </body>
</html>
