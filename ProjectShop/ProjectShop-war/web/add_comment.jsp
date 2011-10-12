<%-- 
    Document   : add_comment
    Created on : 10.10.2011, 19:30:12
    Author     : ололо
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>addComment</title>
    </head>
    <body>
     <form action="addComment">

            <table border="0">

                <tbody>
                    <tr>
                        <td>ПК продукта</td>
                        <td><input type="text" name="ID_PRODUCT" value=""/> </td>
                    </tr>
                    <tr>
                        <td>ПК пользователя</td>
                        <td><input type="text" name="ID_USER" value="" /></td>
                    </tr>
                    <tr>
                        <td>Комментарий</td>
                        <td><input type="text" name="TEXT" value="" /></td>
                    </tr>

                </tbody>
            </table>

            <input type="submit" value="OK" />
        </form>
    </body>
</html>
