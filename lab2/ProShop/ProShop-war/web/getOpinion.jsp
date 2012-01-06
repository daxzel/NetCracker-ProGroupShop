<%-- 
    Document   : getOpinion
    Created on : 16.10.2011, 18:20:15
    Author     : ололо
--%>


<%@page import="menu.Menu"%>
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
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />
        <title>Продукт</title>
    </head>
    <body>

        <%  UserBeanRemote usr = null;
                    try {
                        usr = JSPHelper.getUser2(session);
                    } catch (LoginException ex) {
                    } finally {

        %>
        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>
                        <tr>
                            <td class="logo">
                                <img src="<%=request.getContextPath()%>/static/logo.jpg">
                            </td>
                            <td class="team" align="center"><a href="<%=request.getContextPath()%>/aboutTeam.jsp">Команда</a></td>
                            <td class="user_nav" align="right"><%if (usr == null) {%><a href="<%=request.getContextPath()%>/login.jsp">Вход</a>   <a href="<%=request.getContextPath()%>/registration.jsp">Регистрация</a><%} else {%><a href="<%=request.getContextPath()%>/logout">Выход</a><%}%></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="cols">
                <div id="menu">
                    <div class="catalog">
                        <%=Menu.getMenu()%>
                    </div>
                    <div class="user_menu">
                        <%if (usr != null) {%>
                        <%=JSPHelper.getMenu(usr.getRoleId())%>
                        <%} else {%>
                        <%=JSPHelper.getMenu(4)%>
                        <%}%>
                    </div>
                </div>
                <div id="content">
                    <%  if (request.getAttribute("result") == null) {%>
                    <form action="getOpinionByProduct">
                        <table id="regOrLog">
                            <tr><td>Введите название</td></tr>
                            <tr><td><input type="text" name="NAME" value="" size="20" /></td></tr>
                            <tr><td><input type="submit" value=" Ввод " class="Button"/></td></tr>
                        </table>
                    </form>
                    <%} else {
                          if (request.getAttribute("result") instanceof ProductBeanRemote) {
                              ProductBeanRemote prd = (ProductBeanRemote) request.getAttribute("result");
                              List list = prd.getOpinionList();
                              OpinionBeanRemote opn;
                              session.setAttribute("product", prd);

                    %>
                    <table align="center" border="1" width="100%">
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
                    <table align="center"  border="1" width="100%">
                        <tr align="center">
                            <td colspan="3">Comments to <%=prd.getName()%></td>

                        </tr>
                        <tr align="center">
                            <td width ="10%" align="center">User nik</td>
                            <td align="center">Text</td>
                            <%if (usr != null) {%>
                            <td width="20%" align="center">Delete</td>
                            <%}%>
                        </tr>
                        <% for (int i = 0; i <= (list.size() - 1); i++) {
                                                          opn = (OpinionBeanRemote) list.get(i);%>
                        <tr align="center">
                            <td width ="10%" ><%= opn.getUserName()%></td>
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
                    <%if (usr != null) {%>
                    <form action="addComment">
                        <table align="center"  border="0" width="100%">
                            <tr align="center">
                                <td colspan="2">Add you comment to <%=prd.getName()%></td>

                            </tr>
                            <tr>
                                <td align="center">
                                    <textarea name="COMMENT" rows="4" cols="110">
                                    </textarea></td><td>
                                    <input type="submit" value=" Ввод " class="Button"/>
                                </td></tr>
                        </table>
                    </form>
                                                  
                    <%}
                                        }
                                    }
                                }
                                /*}else{
                                RequestDispatcher rd;
                                rd = request.getRequestDispatcher("login.jsp");
                                rd.forward(request, response);
                                }*/%>
                </div>
            </div>
        </div>
    </body>
</html>
