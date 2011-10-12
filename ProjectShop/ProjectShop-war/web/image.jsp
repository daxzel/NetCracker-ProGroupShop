<%--
    Document   : image
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image</title>
    </head>
    <body>
        <p align="center">
            <%
               
               
               
               Connection conn = DBManager.getConnection();
               Statement statS = conn.createStatement();
               ResultSet rs = statS.executeQuery("SELECT * FROM IMAGE");
            %>
        <table align="center" title="Full list of image" border="1" width="80%">
            <tr>
                <td>ID_IMG</td>
                <td>ID_PRODUCT</td>
                <td>NAME</td>
                <td>IMAGE</td>
                <td>WIDTH</td>
                <td>HEIGHT</td>

            </tr>
            <%
              while (rs.next()) {%>
              <tr>
                  <td><%= rs.getInt(1) %></td>
              <td><%= rs.getInt(2)%></td>
              <td><%= rs.getString(3) %></td>
              <td><%= rs.getBlob(4) %></td>
              <td><%= rs.getString(5) %></td>
              <td><%= rs.getString(6)%></td>

              </tr>
                               <%}%>
        </table>
            <%
                conn.close();
            %>

<br>
        <br>
        <CENTER><table border="0">

                <tbody>
                    <tr>
                        <td>
                            <form action="add_image.jsp">
                                <input type="submit" value="add" name="add" />
                            </form>
                        </td>
                        <td>
                            <form action="del_image.jsp">
                                <input type="submit" value="del" name="del" />
                            </form>

                        </td>
                        <td>
                            <form action="update_image.jsp">
                                <input type="submit" value="update" name="update" />
                            </form>

                        </td>
                        <td>
                            <form action="alter_image.jsp">
                                <input type="submit" value="alter" name="alter" />
                            </form>

                        </td>
                    </tr>
                </tbody>
            </table></CENTER>
    </body>
</html>
