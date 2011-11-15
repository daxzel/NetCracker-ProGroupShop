<%-- 
    Document   : exportProduct
    Created on : 15.11.2011, 17:40:50
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Export products to XML</title>
    </head>
    <body>
        <form action="SelectProductByPrice">
            <table>
                <tr><td>Введите цену</td><td> <input type="text" name="price" value="" /></td></tr>
                <tr><td>Экспортировать продукты с ценой больше заданной</td><td><input type="checkbox" name="more" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукты с ценой больше заданной</td><td><input type="checkbox" name="less" value="ON" /> </td></tr>
            </table>
        </form>
    </body>
</html>
