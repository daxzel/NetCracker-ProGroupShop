<%-- 
    Document   : SELECT_PARAM
    Created on : 11.10.2011, 19:35:43
    Author     : ололо
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <form action="superSelect">

            <table border="0">

                <tbody>
                    <tr>
                        <td>Выводимые таблицы (через запятую)</td>
                        <td><input type="text" name="TABLES" value=""/> </td>
                    </tr>
                    <tr>
                        <td>Выводимые атрибуты (через запятую)</td>
                        <td><input type="text" name="ATR" value="" /></td>
                    </tr>
                </tbody>
            </table>

            <input type="submit" value="OK" />
        </form>
    </body>
</html>
