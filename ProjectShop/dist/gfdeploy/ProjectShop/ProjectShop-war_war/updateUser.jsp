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
        <%
                    session.setAttribute("homepage", "updateUser.jsp");
        %>
        <h2>User update</h2>
        <%
                    if (request.getAttribute("result") == null) {
        %>
        <form action="selectByNik">
            Input nik:
            <input type="text" name="NIK" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%                } else {
                    if (request.getAttribute("result") instanceof User) {
                        User usr = (User) request.getAttribute("result");
                        SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
                        session.setAttribute("usrOld", usr);
        %>
        <form name="myForm" action="updateUser">
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
                <%if(usr.getPhone()==null){ %>
                <tr><td><input type="text" name="PHONE" value="" size="11" /></td><td></td></tr>
                <%}else{%>
                <tr><td><input type="text" name="PHONE" value="<%=usr.getPhone()%>" size="11" /></td><td></td></tr>
                <%}%>
                <tr><td>Email</td><td></td></tr>
                <%if(usr.getEmail()==null){ %>
                <tr><td><input type="text" name="EMAIL" value="" size="25" /></td><td></td></tr>
                <%}else{%>
                <tr><td><input type="text" name="EMAIL" value="<%=usr.getEmail()%>" size="25" /></td><td></td></tr>
                <%}%>
                <tr><td>Role</td><td></td></tr>
                <tr><td><input type="text" name="ID_ROLE" value="<%=usr.getRoleName()%>" size="25" /></td><td></td></tr>
                <tr><td><input type="submit" value="Input" /></td><td></td></tr>
            </table>
        </form>
                <%                                } else {
                        %>
                <form action="selectByNik">
                    Input nik of user:
                    <input type="text" name="NIK" value="" size="20" />
                    <input type="submit" value="Input" />
                </form>  <br><%=request.getAttribute("result")%>
                        <%
                                              }
                            }
                %>

    </body>
</html>
