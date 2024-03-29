<%--
    Document   : getOpinion
    Created on : 16.10.2011, 18:20:15
    Author     : ололо
--%>


<%@page import="java.math.BigDecimal"%>
<%@page import="entityBeans.ImageBeanRemote"%>
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
        <%@include file='head.jsp'%>
        <title>Интернет-магазин</title>
    </head>
    <body>

        <%  UserBeanRemote usr = null;
                    long r = 5;
                    try {
                        usr = JSPHelper.getUser2(session);
                        r = usr.getRoleId();
                    } catch (LoginException ex) {
                    } finally {

        %>
        <div id="container">
            <%@include file='header.jspf'%>
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
                <%@include file='searchBlock.jspf'%>
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
                              List list1 = prd.getImageList();
                              OpinionBeanRemote opn;
                              ImageBeanRemote img;
                              session.setAttribute("product", prd);

                    %>
                    <h1><%= prd.getName()%></h1>
                    <table align="center" border="0">
                        <% for (int n = 0; n <= (list1.size() - 1); n++) {
                                                          img = (ImageBeanRemote) list1.get(n);%>
                        <td><a href="<%=request.getContextPath()%>/image.jpg?ID=<%= img.getId_img()%>" title="" data-fancybox-group="gallery" class="fancybox" > <img width="50%"  src="<%=request.getContextPath()%>/image?ID=<%= img.getId_img()%>"/></a></td>
                            <%}%>
                    </table><br>
                    <h1>Цена</h1>
                    <% BigDecimal priceProd = new BigDecimal(prd.getPrice());%>
                    <p><font sizel="6"><%= priceProd.toBigInteger()%></font>руб. </p><br>
                    <%if (prd.getDescription() != null) {%>
                    <h1>Описание</h1><br>
                    <table><tr>

                            <td width="80%"><%= prd.getDescription()%></td>
                            <%} else {%>
                            <td></td>
                            <%}%>
                            <td width="20%"><a href ="addOrder.jsp"><img alt="В корзину"   src="<%=request.getContextPath()%>/static/cart.jpg"></a>
                            </td>
                        </tr>
                    </table>
                    <br><br>



                    <h1>Комментарии</h1>
                    <table align="center" rules="rows" frame="below" bordercolor="#c0c0c0" width="700px">

                        <tr align="center">
                            <td width ="10%" align="center">Никнейм</td>
                            <td align="center">Комментарий</td>
                            <%if (usr != null) {%>
                            <td width="20%" align="center"></td>
                            <%}%>
                        </tr>
                        <%
                                                      for (int i = 0; i <= (list.size() - 1); i++) {
                                                          opn = (OpinionBeanRemote) list.get(i);%>
                        <tr>
                            <td width ="10%" align="center"><%= opn.getUserName()%></td>
                            <td align="center"><%= opn.getText()%></td>
                            <td align="center"><%if (usr != null) {
                                                                                          if (opn.getIdUser() == usr.getId() || usr.getRoleId() <= 2) {%>
                                <a href ="delComment?ID=<%=opn.getIdOpinion()%>">Удалить комментарий</a>
                                <%}
                                                                                          }%></td>

                        </tr>
                        <%}%>
                    </table>
                    <br>
                    <%if (usr != null) {%>
                    <form action="addComment" method="post">
                        <table align=  border="0">
                            <tr >
                                <td colspan="2">Добавить комментарий к <%=prd.getName()%></td>

                            </tr>
                            <tr>
                                <td>
                                    <textarea name="COMMENT" rows="4" cols="90">
                                    </textarea></td><td>
                                    <input type="submit" value=" Ввод " class="Button"/>
                                </td></tr>
                        </table>
                    </form>

                    <%}
                          }
                          if (request.getAttribute("result2") instanceof String) {
                    %>
                    <%=request.getAttribute("result2")%>
                    <%}
                                    }
                                }
                                /*}else{
                                RequestDispatcher rd;
                                rd = request.getRequestDispatcher("login.jsp");
                                rd.forward(request, response);
                                }*/%>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
