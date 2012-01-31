<%-- 
    Document   : controlImport
    Created on : 29.01.2012, 14:00:26
    Author     : Admin
--%>

<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "entityBeans.*, helpers.*"%>
<%@page errorPage="errorPage.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
         <%@include file='head.jsp'%>
        <title>Интернет магазин</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
           Object obj = request.getAttribute("importObjects");
           String uuid = (String)request.getAttribute("UUIDOfTempFile");

           AdderAndUpdater importObj = (AdderAndUpdater)obj;
           List catalogs = importObj.getCatalogs();
           List images = importObj.getImages();
           List opinions = importObj.getOpinions();
           List orders = importObj.getOrders();
           List roles = importObj.getRoles();
           List products = importObj.getProducts();
           List users = importObj.getUsers();
           
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
                    <form action="/ProShop-war/XML/managedImport">
                        <input type="hidden" name="uuid" value="<%=uuid%>"/>
                        <% if (users.size()>0)  {%>
                            <div id="users">
                                <h1>Продукты</h1>
                                 <table align="center"  border="1" width="100%">
                                    <tr align="center">
                                        <td align="center" width="5%">ID</td>
                                        <td align="center" width="20%">Имя</td>
                                        <td align="center" width="20%">Фамилия</td>
                                        <td align="center" width="15%">Отчество</td>
                                        <td align="center" width="15%">Никнейм</td>
                                        <td align="center" width="15%">Дата рождения</td>
                                        <td align="center" width="5%">Роль</td>
                                        <td align="center" width="5%">Выполнить замену</td>
                                    </tr>
                                    <%
                                    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                    for(int i = 0; i<users.size(); i++) {
                                        UserBeanRemote user  = (UserBeanRemote)users.get(i); %>
                                        <tr align="center">
                                            <td><%= Long.toString(user.getId())%></td>
                                            <td><%= user.getName()%></td>
                                            <td><%= user.getSurname()%></td>
                                            <td><%= user.getOtchestvo()%></td>
                                            <td><%= user.getNik()%></td>
                                            <td><%=format.format(user.getBorn())%></td>
                                            <td><%= user.getRoleId()%></td>
                                            <td  align="center"><input type="checkbox" name="id_user" value="<%=user.getId()%>"/></td>
                                         </tr>
                                    <%}%>
                                 </table>
                            </div>
                        <% }%>
                         <% if (catalogs.size()>0)  {%>
                            <div id="catalogs">
                                <h1>Каталоги</h1>
                                 <table align="center"  border="1" width="100%">
                                    <tr align="center">
                                        <td align="center" width="5%">ID</td>
                                        <td align="center" width="5%">ID родителя</td>
                                        <td align="center" width="85%">Название</td>
                                        <td align="center" width="5%">Выполнить замену</td>
                                    </tr>
                                    <%
                                    for(int i = 0; i<catalogs.size(); i++) {
                                        CatalogBeanRemote catalog  = (CatalogBeanRemote)catalogs.get(i);%>
                                        <tr align="center">
                                            <td><%= Long.toString(catalog.getId())%></td>
                                            <td><%= catalog.getParentId()%></td>
                                            <td><%= catalog.getName()%></td>
                                            <td  align="center"><input type="checkbox" name="id_catalog" value="<%=catalog.getId()%>"/></td>
                                         </tr>
                                    <%}%>
                                 </table>
                            </div>
                        <% }%>
                        <% if (images.size()>0)  {%>
                            <div id="images">
                                <h1>Картинки</h1>
                                 <table align="center"  border="1" width="100%">
                                    <tr align="center">
                                        <td align="center" width="5%">ID</td>
                                        <td align="center" width="5%">ID продукта</td>
                                        <td align="center" width="5%">Высота</td>
                                        <td align="center" width="5%">Ширина</td>
                                        <td align="center" width="75%">Картинка</td>
                                        <td align="center" width="5%">Выполнить замену</td>
                                    </tr>
                                    <%
                                    for(int i = 0; i<images.size(); i++) {
                                        ImageBeanRemote readedImage = (ImageBeanRemote)images.get(i);%>
                                        <tr align="center">
                                            <td><%= Long.toString(readedImage.getId_img())%></td>
                                            <td><%= Long.toString(readedImage.getId_product())%></td>
                                            <td><%= Long.toString(readedImage.getHeight())%></td>
                                            <td><%= Long.toString(readedImage.getWidth())%></td>
                                            <td>
                                                <a href="<%=request.getContextPath()%>/image.jpg?ID=<%= readedImage.getId_img()%>" title="" class="fancybox" >
                                                    <img width="50%"  src="<%=request.getContextPath()%>/image?ID=<%= readedImage.getId_img()%>"/>
                                                </a>
                                            </td>
                                            <td  align="center"><input type="checkbox" name="id_image" value="<%=readedImage.getId_img()%>"/></td>
                                         </tr>
                                    <%}%>
                                 </table>
                            </div>
                        <% }%>

                        <% if (orders.size()>0)  {%>
                            <div id="orders">
                                <h1>Покупки</h1>
                                 <table align="center"  border="1" width="100%">
                                    <tr align="center">
                                        <td align="center" width="5%">ID</td>
                                        <td align="center" width="10%">ID пользователя</td>
                                        <td align="center" width="10%">ID продукта</td>
                                        <td align="center" width="10%">Статус</td>
                                        <td align="center" width="20%">Количество</td>
                                        <td align="center" width="40%">Дата</td>
                                        <td align="center" width="5%">Выполнить замену</td>
                                    </tr>
                                    <%
                                    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                    for(int i = 0; i<orders.size(); i++) {
                                        OrderBeanRemote order = (OrderBeanRemote)orders.get(i);%>
                                        <tr align="center">
                                            <td><%= Long.toString(order.getId())%></td>
                                            <td><%= Long.toString(order.getIdUser())%></td>
                                            <td><%= order.getIdProduct().toString()%></td>
                                            <td><%= Boolean.toString(order.getStatus())%></td>
                                            <td><%= Integer.toString(order.getAmount())%></td>
                                            <td><%= format.format(order.getOrderByDate())%></td>
                                            <td  align="center"><input type="checkbox" name="id_order" value="<%=order.getId()%>"/></td>
                                         </tr>
                                    <%}%>
                                 </table>
                            </div>
                        <% }%>

                        <% if (opinions.size()>0)  {%>
                            <div id="opinions">
                                <h1>Комментарии</h1>
                                 <table align="center"  border="1" width="100%">
                                    <tr align="center">
                                        <td align="center" width="5%">ID</td>
                                        <td align="center" width="20%">ID продукта</td>
                                        <td align="center" width="20%">ID пользователя</td>
                                        <td align="center" width="50%">Текст</td>
                                        <td align="center" width="5%">Выполнить замену</td>
                                    </tr>
                                    <%
                                    for(int i = 0; i<opinions.size(); i++) {
                                        OpinionBeanRemote opinion = (OpinionBeanRemote)opinions.get(i);%>
                                        <tr align="center">
                                            <td><%= Long.toString(opinion.getIdOpinion())%></td>
                                            <td><%= Long.toString(opinion.getIdProduct())%></td>
                                            <td><%= Long.toString(opinion.getIdUser())%></td>
                                            <td><%= opinion.getText()%></td>
                                            <td  align="center"><input type="checkbox" name="id_opinion" value="<%=Long.toString(opinion.getIdOpinion())%>"/></td>
                                         </tr>
                                    <%}%>
                                 </table>
                            </div>
                        <% }%>

                        <% if (roles.size()>0)  {%>
                            <div id="roles">
                                <h1>Роли</h1>
                                 <table align="center"  border="1" width="100%">
                                    <tr align="center">
                                        <td align="center" width="5%">ID</td>
                                        <td align="center" width="90%">Название</td>
                                        <td align="center" width="5%">Выполнить замену</td>
                                    </tr>
                                    <%
                                    for(int i = 0; i<roles.size(); i++) {
                                        RoleBeanRemote role = (RoleBeanRemote)roles.get(i); %>
                                        <tr align="center">
                                            <td><%= role.getId().toString() %></td>
                                            <td><%= role.getName()%></td>
                                            <td  align="center"><input type="checkbox" name="id_role" value="<%=role.getId().toString()%>"/></td>
                                         </tr>
                                    <%}%>
                                 </table>
                            </div>
                        <% }%>

                        <% if (roles.size()>0)  {%>
                            <div id="products">
                                <h1>Продукты</h1>
                                 <table align="center"  border="1" width="100%">
                                    <tr align="center">
                                        <td align="center" width="5%">ID</td>
                                        <td align="center" width="5%">ID каталога</td>
                                        <td align="center" width="20%">Название</td>
                                        <td align="center" width="40%">Описание</td>
                                        <td align="center" width="25%">Цена</td>
                                        <td align="center" width="5%">Выполнить замену</td>
                                    </tr>
                                    <%
                                    for(int i = 0; i<products.size(); i++) {
                                        ProductBeanRemote product = (ProductBeanRemote)products.get(i); %>
                                        <tr align="center">
                                            <td><%= Long.toString(product.getId()) %></td>
                                            <td><%= Long.toString(product.getIdCatalog())%></td>
                                            <td><%= product.getName() %></td>
                                            <td><%= product.getDescription() %></td>
                                            <td><%= product.getPrice()%></td>
                                            <td  align="center"><input type="checkbox" name="id_product" value="<%=Long.toString(product.getId())%>"/></td>
                                        </tr>
                                    <%}%>
                                 </table>
                            </div>
                        <% }%>
                        <input type="submit" value="Выполнить импорт" class="Button"/>
                    </form>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
