<%-- 
    Document   : exportProduct
    Created on : 15.11.2011, 17:40:50
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="helpers.JSPHelper"%>
<%@page errorPage="/errorPage.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file='/head.jsp'%>
        <title>Экспорт продуктов в XML</title>
    </head>
    <body>
        <% UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
        %>
        <div id="container">
            <%@include file='/header.jspf'%>
            <div id="cols">
                <div id="menu">
                    <div class="catalog">
                        <%=Menu.getMenu()%>
                    </div>
                    <div class="user_menu">
                        <%if (usr != null) {%>
                        <%=JSPHelper.getMenu(usr.getRoleId())%>
                        <%} else {%>
                        <%=JSPHelper.getMenu(3)%>
                        <%}%>
                    </div>
                </div>
                <%@include file='/searchBlock.jspf'%>
                <div id="content">
                    <h1>Экспорт продуктов в XML</h1>
                    <%String type = request.getParameter("searchType");

                                if ("byPrice".equals(type)) {%>
                    <form action="ExportProductByPrice">
                        <table id="regOrLog">
                            <tr><td>Введите цену</td><td> <input type="text" name="price" value="" /></td></tr>
                            <tr><td>Экспортировать продукты с ценой больше заданной</td><td><input type="checkbox" name="more" value="ON" /> </td></tr>
                            <tr><td>Экспортировать продукты с ценой меньше заданной</td><td><input type="checkbox" name="less" value="ON" /> </td></tr>
                            <tr><td>Экспортировать продукты с каталогами</td><td><input type="checkbox" name="exportCatalog" value="ON" /> </td></tr>
                            <tr><td>Экспортировать продукты, заказы этих продуктов и пользователей которые эти заказы сделали</td><td><input type="checkbox" name="exportOrder" value="ON" /> </td></tr>
                            <tr><td>Экспортировать продукты, комментарии и пользователей</td><td><input type="checkbox" name="exportComment" value="ON" /> </td></tr>
                            <tr><td>Экспортировать продукты, и все связанные записи</td><td><input type="checkbox" name="exportAll" value="ON" /> </td></tr>
                            <tr><td>Показать записи</td><td><input type="submit" value=" Ввод " class="Button"/></td></tr>
                        </table>
                    </form>
                   
                    <%} else {
                                                        if ("byName".equals(type)) {%>
                    <form name="second" action="ExportProductByName">
                        <table id="regOrLog">
                            <tr><td>Введите наименование</td><td> <input type="text" name="name" value="" size="60" /></td></tr>
                            <tr><td>Экспортировать продукт с каталогом</td><td><input type="checkbox" name="exportCatalog" value="ON" /> </td></tr>
                            <tr><td>Экспортировать продукт, заказы этого продукта и пользователей которые эти заказы сделали</td><td><input type="checkbox" name="exportOrder" value="ON" /> </td></tr>
                            <tr><td>Экспортировать продукт, комментарии и пользователей</td><td><input type="checkbox" name="exportComment" value="ON" /> </td></tr>
                            <tr><td>Экспортировать продукт, и все связанные записи</td><td><input type="checkbox" name="exportAll" value="ON" /> </td></tr>
                            <tr><td>Показать записи</td><td><input type="submit" value=" Ввод " class="Button"/></td></tr>
                        </table>

                    </form>
                   
                    <%} else {
                    %><table id="regOrLog">
                        <tr><td><a href="/ProShop-war/XML/exportProduct.jsp?searchType=byPrice" class="first">Экспортировать продукты используя поиск по цене </a> </td></tr>
                        <tr><td><a href="/ProShop-war/XML/exportProduct.jsp?searchType=byName">Экспортировать продукты используя поиск по имени</a></td></tr>
                    </table>
                    <%   }}
                    %>
                     <%Object obj = request.getAttribute("result");
                                                        if (obj != null) {%>
                    <%=obj.toString()%>
                    <%}%>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
