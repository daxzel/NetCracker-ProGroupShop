<%-- 
    Document   : getOpinion
    Created on : 16.10.2011, 18:20:15
    Author     : ололо
--%>


<%@page import= "DBClasses.UserInterface"%>
<%@page import= "DBClasses.ProductInterface"%>
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
                  /*  UserInterface usr = null;
                    if (session.getAttribute("user") != null && session.getAttribute("user") instanceof UserInterface) {
                        usr = (UserInterface) session.getAttribute("user");

  */
                            if (request.getAttribute("result") == null) {
        %>
        <form action="getOpinionByProduct">
            Введите название:
            <input type="text" name="NAME" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%} else {
                    if (request.getAttribute("result") instanceof ProductInterface) {
                        ProductInterface prd = (ProductInterface)request.getAttribute("result");
                        List list = prd.getOpinionList();
                        OpinionInterface opn;
                        session.setAttribute("product", prd);

        %>
        <table align="center" border="1" width="80%">
            <tr align="center">
                <td>NAME</td>
                <td>DESCRIPTION</td>
                <td>NAME_CATALOG</td>
                <td>PRICE</td>
                <td rowspan="2"><a href ="addOrder.jsp" >order</a></td>
            </tr>
            <tr align="center">
                <td><%= prd.getName() %></td>
                <%if(prd.getDescription()!=null){%>
                <td><%= prd.getDescription() %></td>
                <%}else{%>
                <td></td>
                <%}%>
                <td><%= prd.getNameCatalog() %></td>
                <td><%= prd.getPrice() %></td>
                
            </tr>
            </table><br><br>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                  <td colspan="2">Comments to <%=prd.getName() %></td>

            </tr>
            <tr align="center">
                <td width="25%" align="center">User nik</td>
                <td width="20%" align="center">Text</td>

            </tr>
            <% for (int i = 0; i <= (list.size() - 1); i++) {
                opn=(OpinionInterface)list.get(i);%>
            <tr align="center">
                <td><%= opn.getUserNik() %></td>
                <td><%= opn.getText()%></td>
<%}%>
            </tr>
        </table>

        <%  }
                            }
                        
                    /*}else{
                        RequestDispatcher rd;
                        rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }*/%>
                    <p align="center"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
