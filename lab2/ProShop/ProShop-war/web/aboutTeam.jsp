<%-- 
    Document   : aboutTeam
    Created on : Jan 24, 2012, 4:47:57 AM
    Author     : пк
--%>

<%@page import="exceptions.LoginException"%>
<%@page import="entityBeans.UserBeanRemote"%>
<%@page import="helpers.JSPHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="menu.Menu"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       <%@include file='head.jsp'%>
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
                    <div id="content">
<h1>ProGroupTeam</h1>
                    <table align="center"  frame="border" rules="all" width="100%" bordercolor="#c0c0c0">
                            <tr align="center">

                                <td width="30%" align="center"><img align="right" alt="Юра"  width="50%" src="<%=request.getContextPath()%>/Team/Jurapro.jpg"></td>
                                <td width="70%" align="center">Бернацкий Юрий Игоревич<br>Ник: JuraPro<br>СГАУ, Магистратура 1 курс, ИБ</td>

                            </tr>
                                                        <tr align="center">

                                <td width="30%" align="center"><img align="right" alt="Женя"  width="50%" src="<%=request.getContextPath()%>/Team/zhenek.jpg"></td>
                                <td width="70%" align="center">Поляков Евгений Алексеевич<br>Ник: ZhenyaPolyakov<br>СГАУ, Бакалавриат 4 курс, ПМФ</td>

                            </tr>
                                                        <tr align="center">

                                <td width="30%" align="center"><img align="right" alt="Паша"  width="50%" src="<%=request.getContextPath()%>/Team/pushok.jpg"></td>
                                <td width="70%" align="center">Печерских Павел<br>Ник: Pushok<br>СГАУ, Магистратура 1 курс, ИБ</td>

                            </tr>
                                                        <tr align="center">

                                <td width="30%" align="center"><img align="right" alt="Андрей"  width="50%" src="<%=request.getContextPath()%>/Team/daxzel.jpg"></td>
                                <td width="70%" align="center">Царевский Андрей<br>Ник: Daxzel<br>СГАУ, Специалист 5 курс, АСОИУ</td>


                            </table>



                                
        </div>

        </div>

                            <%}
        %>
                          <div class="team" align="center">
                <a href="<%=request.getContextPath()%>/aboutTeam.jsp"><font size="2">Команда</font></a>
            </div>

        </div>
    </body>
</html>
