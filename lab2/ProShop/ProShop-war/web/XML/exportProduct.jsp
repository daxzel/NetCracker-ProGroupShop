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
        <%%>
        <%if ("byPrice".equals(request.getParameter("poiskType"))) {%>
        <form action="ExportProductByPrice">
            <table>
                <tr><td>Введите цену</td><td> <input type="text" name="price" value="" /></td></tr>
                <tr><td>Экспортировать продукты с ценой больше заданной</td><td><input type="checkbox" name="more" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукты с ценой меньше заданной</td><td><input type="checkbox" name="less" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукты с каталогами</td><td><input type="checkbox" name="exportCatalog" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукты, заказы этих продуктов и пользователей которые эти заказы сделали</td><td><input type="checkbox" name="exportOrder" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукты, комментарии и пользователей</td><td><input type="checkbox" name="exportComment" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукты, и все связанные записи</td><td><input type="checkbox" name="exportAll" value="ON" /> </td></tr>
                <tr><td>Показать записи</td><td><input type="submit" value="Input" /></td></tr>
            </table>
        </form>
        <%Object obj = request.getAttribute("result");
            if (obj != null) {%>
        <%=obj.toString()%>
        <%}%>
        <%}
                    if ("byName".equals(request.getParameter("poiskType"))) {%>
        <form name="second" action="ExportProductByName">
            <table>
                <tr><td>Введите наименование</td><td> <input type="text" name="name" value="" /></td></tr>
                <tr><td>Экспортировать продукт с каталогом</td><td><input type="checkbox" name="exportCatalog" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукт, заказы этого продукта и пользователей которые эти заказы сделали</td><td><input type="checkbox" name="exportOrder" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукт, комментарии и пользователей</td><td><input type="checkbox" name="exportComment" value="ON" /> </td></tr>
                <tr><td>Экспортировать продукт, и все связанные записи</td><td><input type="checkbox" name="exportAll" value="ON" /> </td></tr>
                <tr><td>Показать записи</td><td><input type="submit" value="Input" /></td></tr>
            </table>

        </form>
        <%Object obj = request.getAttribute("result");
                                 if (obj != null) {%>
        <%=obj.toString()%>
        <%}%>
        <%}%>
    </body>
</html>
