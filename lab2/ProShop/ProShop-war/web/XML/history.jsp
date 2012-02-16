<%-- 
    Document   : history
    Created on : 01.12.2011, 0:18:46
    Author     : Pushok
--%>

<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="menu.Menu"%>
<%@page errorPage="/errorPage.jsp"%>
<%@page import="helpers.JSPHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <%@include file='/head.jsp'%>
       <title>История</title>
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
                    <form action="history">
                        <table id="regOrLog">
                            <tr><td>Выберите таблицу:</td><td>
                                    <select name="TABLE" style="width : 200">
                                        <option value="1" selected>Catalog</option>
                                        <option value="2" selected>Image</option>
                                        <option value="3" selected>Opinion</option>
                                        <option value="4" selected>Order</option>
                                        <option value="5" selected>Product</option>
                                        <option value="6" selected>Role</option>
                                        <option value="7" selected>User</option>
                                    </select></td></tr>
                            <tr><td>Введите ID обьекта:</td><td> <input type="text" name="ID" value="" /></td></tr>
                            <tr><td>Показать записи</td><td><input type="submit" value=" Ввод " class="Button"/></td></tr>
                        </table>
                    </form>
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
