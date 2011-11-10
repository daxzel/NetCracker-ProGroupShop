<%-- 
    Document   : del_catalog
    Created on : 16.10.2011, 16:20:53
    Author     : Pushok
--%>

<%@page import="Other.JSPHelper"%>
<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Удаление записи из каталога</title>
    </head>
    <body>

        <H2>Удаление записи из каталога</H2>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
        <form name="delCatalog" action="del_catalog">
            <table>                
                <tr><td>Name</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="" size="200" /></td><td></td></tr>                
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>
        </form>


        <%if (request.getAttribute("result") != null) {%>
        <%=request.getAttribute("result")%>

        <%}%>
        <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
