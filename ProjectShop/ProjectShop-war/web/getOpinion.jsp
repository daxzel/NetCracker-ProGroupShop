<%-- 
    Document   : getOpinion
    Created on : 16.10.2011, 18:20:15
    Author     : ололо
--%>


<%@page import= "DBClasses.UserInterface"%>
<%@page import= "DBClasses.OpinionInterface"%>
<%@page import= "java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comments</title>
    </head>
 <body>
        <%
                    UserInterface usr = null;
                    if (session.getAttribute("user") != null && session.getAttribute("user") instanceof UserInterface) {
                        usr = (UserInterface) session.getAttribute("user");
                        if (usr.getLogin() == true) {
                            if (request.getAttribute("result") == null) {
        %>
        <form action="getOpinionByProduct">
            Введите ПК_ПРОДУКТА:
            <input type="text" name="ID_PRODUCT" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%} else {
                    if (request.getAttribute("result") instanceof List) {
                        List<OpinionInterface> list = (List<OpinionInterface>) request.getAttribute("result");

        %>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <%if (usr.getRoleId()==1){%>
                <td width="5%" align="center">Opinion id</td>
                <%}%>
                <td width="15%" align="center">Product id</td>
                <td width="25%" align="center">User id</td>
                <td width="20%" align="center">Text</td>

            </tr>
            <% for (int i = 0; i <= (list.size() - 1); i++) {%>
            <tr align="center">
                <%if (usr.getRoleId()==1){%>
                <td><%= list.get(i).getId()%></td>
                <%}%>
                <td><%= list.get(i).getIdProduct()%></td>
                <td><%= list.get(i).getIdUser()%></td>
                <td><%= list.get(i).getText()%></td>
<%}%>
            </tr>
        </table>

        <%  }
                            }
                        }
                    }{
                        RequestDispatcher rd;
                        rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }%>
    </body>
</html>
