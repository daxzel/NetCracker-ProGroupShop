<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : product
    Created on : 06.10.2011, 20:35:21
    Author     : �����
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
               ResultSet rs = DBManager.ProductDAL.getAllProducts();
          %>

         <table align="center" title="Full list of image" border="1" width="80%">
            <tr>
                <td>NAME</td>
                <td>DESCRIPTION</td>
                <td>ID_CATALOG</td>
                <td>PRICE</td>
            </tr>
            <%
              while (rs.next()) {%>
              <tr>
                  <td><%= rs.getInt(1) %></td>
                  <td><%= rs.getInt(2)%></td>
                  <td><%= rs.getString(3) %></td>
                  <td><%= rs.getBlob(4) %></td>
              </tr>
                               <%}%>
        </table>


        <form name="addProductForm" action="olololo">
            <table>
                <tr><td>Имя</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="" size="20" /></td><td></td></tr>
                <tr><td>Описание</td><td></td></tr>
                <tr><td><input type="text" name="DESCRIPTION" value="" size="100" /></td><td></td></tr>
                <tr><td>Цена</td><td></td></tr>
                <tr><td><input type="text" name="PRICE" value="" size="20" /></td><td></td></tr>
                <tr><td>Ид каталога</td><td></td></tr>
                <tr><td><input type="text" name="ID_CATALOG" value="" size="10" /></td><td></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
             </table>
        </form>
    </body>
</html>
