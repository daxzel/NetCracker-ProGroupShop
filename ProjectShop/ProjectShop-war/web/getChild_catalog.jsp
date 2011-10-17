<%-- 
    Document   : getChild_catalog
    Created on : 17.10.2011, 0:14:18
    Author     : Pushok
--%>

<%@page import="DBClasses.CatalogInterface"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вывод потомков по наименованию</title>
    </head>
    <body>

        <%
            if (request.getAttribute("result") == null) {
        %>
        <form action="getChild_catalog">
            Введите наименование:
            <input type="text" name="NAME" value="" size="200" />
            <input type="submit" value="Input" />
        </form>
        <%} else {            
                List<CatalogInterface> list = (List<CatalogInterface>) request.getAttribute("result");                
        %>
        <table align="center"  border="1" width="80%">
            <tr align="center">                
                <td width="15%" align="center">id</td>
                <td width="30%" align="center">Name</td>                
            </tr>
            <% for (int i = 0; i <= (list.size() - 1); i++) {%>
            <tr align="center">
                <td><%= list.get(i).getId()%></td>
                <td><%= list.get(i).getName()%></td>                
                <%}%>
            </tr>
        </table>

        <%}%>
 <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
