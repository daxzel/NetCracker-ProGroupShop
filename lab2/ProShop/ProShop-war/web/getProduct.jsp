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
            if (request.getAttribute("product") != null&&request.getAttribute("opinion")!=null) {
                if (request.getAttribute("opinion") instanceof List &&request.getAttribute("product")instanceof ProductInterface) {
                    ProductInterface prd = (ProductInterface)request.getAttribute("product");
                    List list = (List) request.getAttribute("opinion");
                    OpinionInterface opn;
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
                <%if(prd.getDescription()!=null){%>
                <td><%= prd.getDescription() %></td>
                <%}else{%>
                <td></td>
                <%}%>
                <td><%= prd.getIdCatalog() %></td>
                <td><%= prd.getPrice() %></td>
            </tr>
            </table>
            <br><br>
            <table align="center" border="1" width="80%">
                <tr align="center">
                <td colspan="2">Comments</td>
                </tr>
              <% for (int i = 0; i <= (list.size() - 1); i++) {
                  opn=(OpinionInterface)list.get(i);%>
            <tr align="center">
                <td><%= opn.getIdUser()%></td>
                <td><%= opn.getText()%></td>
<%}%>
            </tr>
        </table><%
                            }
                        }
                   %>
                    <p align="center"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
