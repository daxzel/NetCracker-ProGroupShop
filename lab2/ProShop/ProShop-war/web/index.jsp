<%-- 
    Document   : index
    Created on : 29.09.2011, 17:09:45
    Author     : daxzel
--%>

<%@page import="entityBeans.ImageBeanRemote"%>
<%@page import="entityBeans.HistoryEntityBeanRemote"%>
<%@page import="entityBeans.HistoryEntityBean"%>
<%@page import="entityBeans.ProductBeanRemote"%>
<%@page import="java.util.List"%>
<%@page import="entityBeans.RoleBeanRemote"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="menu.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="errorPage.jsp"%>
<%@page import="java.sql.*,javax.sql.*,javax.naming.*,javax.sql.DataSource,entityBeans.UserBeanRemoteHome,entityBeans.UserBeanRemote, helpers.*,exceptions.LoginException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
        <link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />
        <title>Интернет-магазин</title>
    </head>
    <body>
        <%
                    //    PrintWriter pw = response.getWriter();

                    UserBeanRemote usr = null;
long r = 5;
                    try {
                        usr = JSPHelper.getUser2(session);
                         r = usr.getRoleId();

                    } catch (LoginException ex) {
                    } finally {%>

        <div id="container">
            <div id="header">
                <table class="top_nav">
                    <tbody>


                        <tr>
                            <td class="logo" align="left">
                                <img src="<%=request.getContextPath()%>/static/logo.jpg"
                            </td>
                            <td  class="current_user" align="right"><%if (usr == null) {%><a> </a><%} else {%>Текущий пользователь:<a href="<%=request.getContextPath()%>/updateUser.jsp?DO=updateProfil"> <%=usr.getNik()%></a>   Статус: <%=usr.getRoleName()%> <%}%></td>
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
                <div id="find">
                    <form action="<%=request.getContextPath()%>/find">
                        <input type="text" name="sabstrName" value="" size="100" class="in"/>
                        <input type="submit" value=" Найти " class="Button1" />
                    </form>
                </div>
                <div id="content">
                    <%
                                            List products = (List) request.getAttribute("products");
                                            String result = (String) request.getAttribute("result");
                                            List history = JSPHelper.getHistory();
                                            List product = JSPHelper.getProduct();

                                            List historyByProduct = JSPHelper.getHistory("PRODUCT");
                                              HistoryEntityBeanRemote his;
                                              ProductBeanRemote pbr;
                                              ImageBeanRemote img;

                                            if (products == null) {
                                                if (result != null) {%>
                    <%=result%>
                    <%} else {%>
                    
                    <%
                    // usr = JSPHelper.getUser2(session);
if (r<= 2){
%>
<h1>История</h1>
<table align="center" border="0">
                         <tr>
                            <td>Автор изменения</td>
                            <td align="center">Статус</td>
                            <td>Дата изменения</td>
                         </tr>
                         
                         
                        <% for (int n = 0; n <= (history.size() - 1); n++) {
                                                          his = (HistoryEntityBeanRemote) history.get(n);%>
                       <tr>
                                                          <td><%= his.getUserName()%></td>
                        <td><%= his.getStatus()%></td>
                        <td><%= his.getTimestampSaved()%></td>
                        </tr>    
                        <%}%>
                            
                    </table><br>

                    <%
} else {
                    %>
                    
                    <table align="center" border="1" rules="rows" frame="void" bordercolor="#c0c0c0" cols="2">
                      

                                          <%
                                          //
                                          for (int n = 0; n <= (product.size() - 1); n++) {
                                                          pbr = (ProductBeanRemote) product.get(n);
                                                         
                                                          List list1 = pbr.getImageList();
                                                         img = (ImageBeanRemote) list1.get(0);
                %>


                <tr  align="justify">

                    <td width="20%">
                        <a href ="product?ID=<%=pbr.getId()%>"><img width="55%" align="center" alt="Картинка"   src="<%=request.getContextPath()%>/image?ID=<%= img.getId_img()%>"</a>
                    </td>
                    <td width="65%">
                        <a href ="product?ID=<%=pbr.getId()%>"><font size="5"><%= pbr.getName()%></font></a><br><br>
                        <%= pbr.getDescription()%><br><br>
                       <p align="right"> <font size="5"><%= pbr.getPrice()%></font> руб.</p>
                    </td>
                    <% if  (r<= 3){ %>
                    <td  width="15%" align="center"><a href ="order?VOL=1&ID_PRODUCT=<%=pbr.getId()%>" class="Button">В корзину</a></td>
                     <% } %>
                </tr>
                        <%}%>
                        
                    </table><br>
                    <%
}
%>
                    <%}
                                                                } else {
                                                                    ProductBeanRemote prd;//= (ProductBeanRemote) products.get(0);
                                                                    if (products.isEmpty()) {                                              //session.setAttribute("catalog", String.valueOf(prd.getIdCatalog()));
                    %>
                    По вашему запросу не найдено продуктов.
                    <%} else {%>
                    <table  align="center" rules="rows" frame="void" bordercolor="#c0c0c0">

                        <% for (int i = 0; i <= (products.size() - 1); i++) {
                                                                                                    prd = (ProductBeanRemote) products.get(i);
                                                                                                     List list1 = prd.getImageList();
                                                         img = (ImageBeanRemote) list1.get(0);

                                                                            %>
                        <tr align="justify">
                            <td align="center" width="40%" >
                        <a href ="product?ID=<%=prd.getId()%>"><img width="50%" align="center" alt="Картинка"   src="<%=request.getContextPath()%>/image?ID=<%= img.getId_img()%>"</a>
                    </td>
                    <td width="40%">
                        <a href ="product?ID=<%=prd.getId()%>"><font size="5"><%= prd.getName()%></font></a><br><br>
                        <%= prd.getDescription()%><br><br>
                       <p align="right"> <font size="5"><%= prd.getPrice()%></font> руб.</p>
                    </td>
                    <td  width="20%" align="center"><a href ="order?VOL=1&ID_PRODUCT=<%=prd.getId()%>" class="Button">В корзину</a></td>
                        </tr>
                        <%}%>

                    </table>
                    <%}
                                            }%>
                </div>
            </div>

            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>

        <%}
        %>

    </body>
</html>
