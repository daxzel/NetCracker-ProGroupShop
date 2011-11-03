<%-- 
    Document   : add_catalog
    Created on : 15.10.2011, 22:24:17
    Author     : Pushok
--%>

<%@page import="exceptions.LoginException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "entityBeans.UserBeanRemote,Other.*;"%>
<%@page errorPage="errorPage.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление новой записи в каталог</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (2 == usr.getRoleId()) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
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


        <%if (request.getAttribute("result") != null) {%>
        <%=request.getAttribute("result")%>
        <%}
        %>
        <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
