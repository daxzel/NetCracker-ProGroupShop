<%-- 
    Document   : history
    Created on : 01.12.2011, 0:18:46
    Author     : Pushok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
        <form action="history">
            <table>
                <tr><td>Выберите таблицу:
                <select name="TABLE" style="width : 200">
                <option value="1" selected>Catalog</option>
                <option value="2" selected>Image</option>
                <option value="3" selected>Opinion</option>
                <option value="4" selected>Order</option>
                <option value="5" selected>Product</option>
                <option value="6" selected>Role</option>
                <option value="7" selected>User</option>
                </select></td><td></td></tr> 
                <tr><td>Введите ID обьекта:</td><td> <input type="text" name="ID" value="" /></td></tr> 
                <tr><td>Показать записи</td><td><input type="submit" value="Input" /></td></tr>
            </table>            
        </form>  
    </body>
</html>
