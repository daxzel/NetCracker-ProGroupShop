<%-- 
    Document   : getProduct
    Created on : 17.10.2011, 1:12:33
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "DBClasses.UserInterface"%>
<%@page import= "DBClasses.RoleInterface"%>
<%@page import= "DBClasses.ProductInterface"%>
<%@page import= "DBClasses.OpinionInterface"%>
<%@page import= "java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <%
                    UserInterface usr = null;
                    if (session.getAttribute("user") != null && session.getAttribute("user") instanceof UserInterface) {
                        usr = (UserInterface) session.getAttribute("user");
                        if (usr.getLogin() == true) {
                            if (request.getAttribute("product") != null&&request.getAttribute("opinion")!=null) {
                                if (request.getAttribute("opinion") instanceof List &&request.getAttribute("product")instanceof ProductInterface) {
                                    ProductInterface prd = (ProductInterface)request.getAttribute("product");
                                    List<OpinionInterface> list = (List<OpinionInterface>) request.getAttribute("opinion");
        %>
        <table align="center" border="1" width="80%">
            <tr align="center">
                <td>NAME</td>
                <td>DESCRIPTION</td>
                <td>ID_CATALOG</td>
                <td>PRICE</td>
            </tr>
            <tr align="center">
                <td><%= prd.getName() %></td>
                <td><%= prd.getDescription() %></td>
                <td><%= prd.getIdCatalog() %></td>
                <td><%= prd.getPrice() %></td>
            </tr>
            </table>
            <br><br>
            <table align="center" border="1" width="80%">
                <tr align="center">
                <td colspan="2">Comments</td>
                </tr>
              <% for (int i = 0; i <= (list.size() - 1); i++) {%>
            <tr align="center">
                <td><%= list.get(i).getIdUser()%></td>
                <td><%= list.get(i).getText()%></td>
<%}%>
            </tr>
        </table><%
                            }
                        }
                    }
                    } else {
                        RequestDispatcher rd;
                        rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }%>
    </body>
</html>
