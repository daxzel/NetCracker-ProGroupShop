<%-- 
    Document   : importProducts
    Created on : 11.11.2011, 21:47:20
    Author     : Admin
--%>

<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="menu.Menu"%>
<%@page errorPage="/errorPage.jsp"%>
<%@page import="helpers.JSPHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       <%@include file='/head.jsp'%>
       <title>Импорт из XML</title>
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
                    <form name="import" method="post" action="import" enctype="multipart/form-data">

                        <table id="regOrLog"><tr><td>Файл XML <input type="file" name="xml" value="" /></td></tr>
                            <tr><td> <input type="submit" value=" Ввод " class="Button"/></td></tr></table>
                    </form>
                </div>
            </div>
                    <div class="team" align="center">
               <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
        </div>
        </div>
    </body>
</html>
