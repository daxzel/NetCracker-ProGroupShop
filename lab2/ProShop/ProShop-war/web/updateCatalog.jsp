<%-- 
    Document   : updateCatalog
    Created on : 25.12.2011, 17:25:21
    Author     : Yra
--%>

<%@page import="menu.Menu"%>
<%@page import="entityBeans.CatalogBeanRemote"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file='head.jsp'%>
        <title>обновление каталога</title>
    </head>
    <body>
        <%
                    UserBeanRemote usr = JSPHelper.getUser2(session);
                    if (usr.getRoleId() > 2) {
                        throw new LoginException("Вы не обладаете правами администратора");
                    }
                    String type = request.getParameter("DO");
                    Object result = request.getAttribute("result");


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
                    <h1>Редактирование каталога</h1>
                    <%  if ("select".equals(type) || (type == null)) {%>

                    <form action="selectCatalog">
                        <table id="regOrLog">
                            <tr><td> Введите название каталога </td></tr>
                            <tr><td>  <input type="text" name="nameCatalog" value="" size="60" /> </td></tr>
                            <tr><td> <input type="submit" value=" Ввод "  class="Button"/></td></tr>
                        </table>
                    </form>

                    <%                      } else {
                          CatalogBeanRemote catalog = (CatalogBeanRemote) session.getAttribute("catalog");
                          String name = "";
                          String parentName = "";
                          String price = "";
                          String nameCatalog = "";
                          if (catalog != null) {
                              //   product = (ProductBeanRemote) result;
                              name = catalog.getName();

                              if (catalog.getParentId() != 0) {
                                  parentName = catalog.getParentName();
                              }


                              // String g = product.getName();
                              //   session.setAttribute("product", product);
                          }

                    %>
                    <form name="updateCatalogForm" action="updateCatalog">
                        <table id="regOrLog">
                            <tr><td>Имя</td><td></td></tr>
                            <tr><td><input type="text" name="NAME" value="<%= name%>" size="80" /></td><td></td></tr>
                            <tr><td>Имя родительского каталога </td><td></td></tr>
                            <tr><td><input type="text" name="PARENT" value="<%= parentName%>" size="100" /></td><td></td></tr>
                            <tr><td><input type="submit" value=" Ввод "  class="Button" /></td><td></td></tr>
                        </table>

                    </form>
                    <%
                                }%>
                    <%if (result instanceof String) {%>
                    <%=result%>
                    <%}%>
                </div>
            </div>
            <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда </font></a>
            </div>
        </div>
    </body>
</html>
