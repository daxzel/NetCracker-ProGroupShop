<%-- 
    Document   : updateUser
    Created on : 07.10.2011, 21:29:52
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "DBClasses.*"%>
<%@page import= "java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>user update</title>
    </head>
    <body>
        <h2>User update</h2>
        <%SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
                    UserInterface usr = null;
                    if (session.getAttribute("user") instanceof UserInterface) {
                        usr = (UserInterface) session.getAttribute("user");
                        if (usr.getLogin() == true) {
                            session.setAttribute("homepage", "updateUser.jsp");
                            if ("upProf".equals(request.getParameter("DO"))||("upProf".equals(request.getAttribute("DO")))) {
                                session.setAttribute("usrOld", usr);
        %>
        <form name="myForm" action="updateProfil">
            <table>
                <tr><td>Name</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="<%=usr.getName()%>" size="20" /></td><td></td></tr>
                <tr><td>Surname</td><td></td></tr>
                <tr><td><input type="text" name="SURNAME" value="<%=usr.getSurname()%>" size="25" /></td><td></td></tr>
                <tr><td>Father name</td><td></td></tr>
                <tr><td><input type="text" name="OTCHESTVO" value="<%=usr.getOtchestvo()%>" size="20" /></td><td></td></tr>
                <tr><td>Nik</td><td></td></tr>
                <tr><td><input type="text" name="NIK" value="<%=usr.getNik()%>" size="20" /></td><td></td></tr>
                <tr><td>Password</td><td> Password(repeat)</td></tr>
                <tr><td><input type="password" name="PASSWORD" value="" size="10" /></td><td>  <input type="password" name="PASSWORD2" value="" size="10" /></td></tr>
                <tr><td>Born</td><td></td></tr>
                <tr><td><input type="text" name="BORN" value="<%=formt.format(usr.getBorn())%>" size="10" /></td><td></td></tr>
                <tr><td>Phone</td><td></td></tr>
                <%if (usr.getPhone() == null) {%>
                <tr><td><input type="text" name="PHONE" value="" size="11" /></td><td></td></tr>
                        <%} else {%>
                <tr><td><input type="text" name="PHONE" value="<%=usr.getPhone()%>" size="11" /></td><td></td></tr>
                        <%}%>
                <tr><td>Email</td><td></td></tr>
                <%if (usr.getEmail() == null) {%>
                <tr><td><input type="text" name="EMAIL" value="" size="25" /></td><td></td></tr>
                        <%} else {%>
                <tr><td><input type="text" name="EMAIL" value="<%=usr.getEmail()%>" size="25" /></td><td></td></tr>
                        <%}%>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>
        </form>
        <%if (request.getAttribute("result") instanceof String) {%>
        <%=request.getAttribute("result").toString()%><%}%>
        <%                                }
                            String str =null;
                            if(request.getAttribute("DO")!=null){
                                str =request.getAttribute("DO").toString();
                                }
                                    if (("upUser".equals(request.getParameter("DO")) && usr.getRoleId() == 1)||("upUser".equals(str) && usr.getRoleId() == 1)) {
                                        if (request.getAttribute("result") == null) {
        %>
        <form action="selectByNik">
            Input nik:
            <input type="text" name="NIK" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%                            } else {
                                                    if (usr.getRoleId() == 1) {
                                                        UserInterface user;
                                                        if (request.getAttribute("result") instanceof UserInterface) {
                                                            user = (UserInterface) request.getAttribute("result");
                                                            session.setAttribute("usrOld", user);
                                                        } else {
                                                            user = (UserInterface) session.getAttribute("usrOld");
                                                        }
                                                      %>
        <form name="myForm" action="updateUser">
            <table>
                <tr><td>Name</td><td></td></tr>
                <tr><td><input type="text" name="NAME" value="<%=user.getName()%>" size="20" /></td><td></td></tr>
                <tr><td>Surname</td><td></td></tr>
                <tr><td><input type="text" name="SURNAME" value="<%=user.getSurname()%>" size="25" /></td><td></td></tr>
                <tr><td>Father name</td><td></td></tr>
                <tr><td><input type="text" name="OTCHESTVO" value="<%=user.getOtchestvo()%>" size="20" /></td><td></td></tr>
                <tr><td>Nik</td><td></td></tr>
                <tr><td><input type="text" name="NIK" value="<%=user.getNik()%>" size="20" /></td><td></td></tr>
                <tr><td>Born</td><td></td></tr>
                <tr><td><input type="text" name="BORN" value="<%=formt.format(user.getBorn())%>" size="10" /></td><td></td></tr>
                <tr><td>Phone</td><td></td></tr>
                <%if (user.getPhone() == null) {%>
                <tr><td><input type="text" name="PHONE" value="" size="11" /></td><td></td></tr>
                        <%} else {%>
                <tr><td><input type="text" name="PHONE" value="<%=user.getPhone()%>" size="11" /></td><td></td></tr>
                        <%}%>
                <tr><td>Email</td><td></td></tr>
                <%if (user.getEmail() == null) {%>
                <tr><td><input type="text" name="EMAIL" value="" size="25" /></td><td></td></tr>
                        <%} else {%>
                <tr><td><input type="text" name="EMAIL" value="<%=user.getEmail()%>" size="25" /></td><td></td></tr>
                        <%}%>
                <tr><td>Role</td><td></td></tr>
                <tr><td><input type="text" name="ID_ROLE" value="<%=user.getRoleName()%>" size="25" /></td><td></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>

            </table>
        </form>
        <%if (request.getAttribute("result") != null&&request.getAttribute("result")instanceof String) {%>
        <%=request.getAttribute("result").toString()%><%}%>
        <%  }
                                                }}
        %><br><p align="left"><a href ="index.jsp">index</a><br></p><% 
                                   }
                               } else {
                                   RequestDispatcher rd;
                                   rd = request.getRequestDispatcher("login.jsp");
                                   rd.forward(request, response);
                               }
            %>

    </body>
</html>
