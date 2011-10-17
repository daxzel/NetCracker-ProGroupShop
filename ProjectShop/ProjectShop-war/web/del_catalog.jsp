<%-- 
    Document   : del_catalog
    Created on : 16.10.2011, 16:20:53
    Author     : Pushok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Удаление записи из каталога</title>
    </head>
    <body>

        <H2>Удаление записи из каталога</H2>
        <%
            if (request.getAttribute("result") == null) {
        %>
        <form name="delCatalog" action="del_catalog">
            <table>                
                <tr><td>Name</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="" size="200" /></td><td></td></tr>                
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>
        </form>


        <%} else {
            if (Integer.parseInt(request.getAttribute("result").toString()) == 1) {%>
        Запись удалена.
        <%} else {%>
        Удаление не успешно.
        <%}
            }%>
 <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
