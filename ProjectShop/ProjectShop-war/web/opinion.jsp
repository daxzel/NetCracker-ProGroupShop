<%-- 
    Document   : opinion
    Created on : 06.10.2011, 20:35:47
    Author     : ололо
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource, DBManager.*;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p align="center">
            <%
               DBManager.getConnection();


               Connection conn = DBManager.getConnection();
               Statement statS = conn.createStatement();
               ResultSet rs = statS.executeQuery("SELECT * FROM OPINION");
            %>
        <table align="center" title="" border="1" width="80%">
            <tr>
                <td>ID_OPINION</td>
                <td>ID_PRODUCT</td>
                <td>ID_USER</td>
                <td>TEXT</td>


            </tr>
            <%
              while (rs.next()) {%>
              <tr>
                  <td><%= rs.getInt(1) %></td>
              <td><%= rs.getInt(2)%></td>
              <td><%= rs.getInt(3) %></td>
              <td><%= rs.getString(4) %></td>


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
                            <form action="add_comment.jsp">
                                <input type="submit" value="add" name="add" />
                            </form>
                        </td>
                        <td>
                            <form action="del_comment.jsp">
                                <input type="submit" value="del" name="del" />
                            </form>

                        </td>
                        <td>
                            <form action="update_comment.jsp">
                                <input type="submit" value="update" name="update" />
                            </form>

                        </td>
                    </tr>
                </tbody>
            </table></CENTER>
    </body>
</html>
