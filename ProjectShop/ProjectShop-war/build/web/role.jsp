<%--
    Document   : role
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,getCon.*,javax.sql.DataSource;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p align="center">
            <%
               DataSource ds = GetConnect.getDataSource();
               Connection conn = ds.getConnection();
               Statement statS = conn.createStatement();
               ResultSet rs = statS.executeQuery("SELECT * FROM ROLE");
                 //ResultSet rs = statS.getResultSet();
               int id;
               String name;
            %>
        <table align="center" title="Full list of users" border="1" width="80%">
            <tr>
                <td width="15%">id</td>
                <td width="30%">name</td>
            </tr>
            <%
              while (rs.next()) {%>
              <tr>
              <td><%= rs.getInt(1)%></td>
              <td><%= rs.getString(2)%></td>
              </tr>
                               <%}%>
        </table>
            <%
                conn.close();
            %>

    </body>
</html>