<%-- 
    Document   : getFull_catalog
    Created on : 16.10.2011, 17:41:55
    Author     : Pushok
--%>

<%@page import="DBClasses.CatalogInterface"%>
<%@page import="DBClasses.ProductInterface"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Каталог</title>
    </head>
    <body>

        <% if (request.getAttribute("result") != null && request.getAttribute("result") instanceof List) {

                        List list1 = (List) request.getAttribute("result");
                        if (list1.size() > 0) {
                            if (list1.get(0) instanceof CatalogInterface) {
                                CatalogInterface ctg = (CatalogInterface) list1.get(0);
        %>
        <table align="center"  border="1" width="80%">
            <% for (int i = 0; i <= (list1.size() - 1); i++) {
                ctg = (CatalogInterface) list1.get(i);%>
            <tr align="center">
                <td><p align="center"><a href ="catalog?pid=<%= ctg.getId()%>"><%=  ctg.getName()%></a></p></td>
            </tr>

            <%}%>
        </table>

        <%}
                                    if (list1.get(0) instanceof ProductInterface) {
                                        ProductInterface prd = (ProductInterface)list1.get(0);
        %>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <td width="15%" align="center">Name</td>
            </tr>
            <% for (int i = 0; i <= (list1.size() - 1); i++) {
                 prd = (ProductInterface)list1.get(i);%>
            <tr align="center">
                <td><p align="center"><a href ="product?NAME=<%=prd.getName()%>"><%= prd.getName()%></a></p></td>
            </tr>
            <%}%>

        </table>

            <br><%
                                    }%><p align="center"><a href ="index.jsp">index</a><br></p>
            <%
                                    } else {%><br><p align="center">Каталог не содержит никакой информации<br><a href ="index.jsp">index</a><br></p><%}
                                     } else {%><br><p align="center">произошла ошибка<br><a href ="index.jsp">index</a><br></p><%                                                                 }%>
    </body>
</html>
