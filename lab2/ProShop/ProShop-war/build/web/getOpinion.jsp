<%-- 
    Document   : getOpinion
    Created on : 16.10.2011, 18:20:15
    Author     : ололо
--%>


<%@page import="exceptions.LoginException"%>
<%@page import="helpers.*"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import= "entityBeans.ProductBeanRemote,entityBeans.OpinionBeanRemote,java.util.List;"%>

<%@page errorPage= "errorPage.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <%  UserBeanRemote usr = null;
                    try {
                        usr = JSPHelper.getUser2(session);
                    } catch (LoginException ex) {
                    }
                    if (request.getAttribute("result") == null) {
        %>
        <form action="getOpinionByProduct">
            Введите название:
            <input type="text" name="NAME" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%} else {
                                if (request.getAttribute("result") instanceof ProductBeanRemote) {
                                    ProductBeanRemote prd = (ProductBeanRemote) request.getAttribute("result");
                                    List list = prd.getOpinionList();
                                    OpinionBeanRemote opn;
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
                <td><%= prd.getName()%></td>
                <%if (prd.getDescription() != null) {%>
                <td><%= prd.getDescription()%></td>
                <%} else {%>
                <td></td>
                <%}%>
                <td><%= prd.getNameCatalog()%></td>
                <td><%= prd.getPrice()%></td>

            </tr>
        </table><br><br>
        <table align="center"  border="1" width="80%">
            <tr align="center">
                <td colspan="3">Comments to <%=prd.getName()%></td>

            </tr>
            <tr align="center">
                <td width="25%" align="center">User nik</td>
                <td width="20%" align="center">Text</td>
                <%if (usr != null) {%>
                <td width="20%" align="center">Delete</td>
                <%}%>
            </tr>
            <% for (int i = 0; i <= (list.size() - 1); i++) {
                                                    opn = (OpinionBeanRemote) list.get(i);%>
            <tr align="center">
                <td><%= opn.getUserName()%></td>
                <td><%= opn.getText()%></td>
                <td><%if (usr != null) {
                         if (opn.getIdUser() == usr.getId() || usr.getRoleId() <= 2) {%>
                    <a href ="delComment?ID=<%=opn.getIdOpinion()%>">del this comment</a>
                    <%}
                              }%></td>
                    <%}%>
            </tr>
        </table>
        <br>
        <form action="addComment">
            <table align="center"  border="1" width="80%">
                <tr align="center">
                    <td colspan="2">Add you comment to <%=prd.getName()%></td>

                </tr>
                <tr align="center">
                    <td>
                        <textarea name="COMMENT" rows="4" cols="120">
                        </textarea></td><td>
                        <input type="submit" value="Input" />
                    </td></tr>
            </table>
        </form>
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
