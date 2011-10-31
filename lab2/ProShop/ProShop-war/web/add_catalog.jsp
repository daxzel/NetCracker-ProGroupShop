<%-- 
    Document   : add_catalog
    Created on : 15.10.2011, 22:24:17
    Author     : Pushok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление новой записи в каталог</title>
    </head>
    <body>
        
               
        <H2>Добавление новой записи в каталог</H2>
        <form name="addCatalog" action="add_catalog">
            <table>
                <tr><td>Name parent catalog</td><td></td></tr>
                <tr><td><input type="text" name="PARENTNAME" value="" size="10" /></td><td></td></tr>
                <tr><td>Name</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="" size="200" /></td><td></td></tr>                
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
             </table>
        </form>

        
        <%if(request.getAttribute("result")!=null){%>
         <%=request.getAttribute("result")%>
        <%}
        %>
       <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
