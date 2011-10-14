<%-- 
    Document   : getFullList
    Created on : 12.10.2011, 14:28:18
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "DBClasses.UserInterface"%>
<%@page import= "DBClasses.*"%>
<%@page import= "java.util.List"%>
<%@page import="java.text.SimpleDateFormat;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Полный список</title>

    </head>
    <body>
         <%
        if(request.getAttribute("result")==null){
        %>
        <form action="getFullUserList">
           <input type="submit" value="FullListOfUser" />
        </form>
        <form action="getFullRoleList">
           <input type="submit" value="FullListOfRole" />
        </form>
       <%}else{
            if(request.getAttribute("result") instanceof List){
              //  if()
                List list1 = (List) request.getAttribute("result");
                if(list1.get(1)  instanceof User){
                 List<UserInterface> list = (List<UserInterface>) request.getAttribute("result");
                 SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
                 %>
                 <table align="center"  border="1" width="80%">
            <tr align="center">
                <td width="5%" align="center">User id</td>
                <td width="15%" align="center">Name</td>
                <td width="25%" align="center">Surname</td>
                <td width="20%" align="center">Otchestvo</td>
                <td width="30%" align="center">Nik</td>
                <td width="30%" align="center">Born</td>
                <td width="30%" align="center">Phone</td>
                <td width="30%" align="center">Email</td>
                <td width="30%" align="center">Role</td>
            </tr>
            <% for (int i = 0; i <= (list.size() - 1); i++) {%>
            <tr align="center">
                <td><%= list.get(i).getId() %></td>
                <td><%= list.get(i).getName()  %></td>
                <td><%= list.get(i).getSurname() %></td>
                <td><%= list.get(i).getOtchestvo() %></td>
                <td><%= list.get(i).getNik() %></td>
                <td><%=formt.format( list.get(i).getBorn()) %></td>
                <%if(list.get(i).getPhone()!=null){%>
                <td><%= list.get(i).getPhone() %></td>
                <%}else{%>
                <td></td>
                <%}if(list.get(i).getEmail()!=null){%>
                <td><%= list.get(i).getEmail() %></td>
                <%}else{%>
                <td></td>
                <%}%>
                <td><%= list.get(i).getRoleName() %></td>
            </tr>
            <%}%>
          
        </table>

        <%  }  if(list1.get(1)  instanceof Role){
                 List<Role> list = (List<Role>) request.getAttribute("result");
                 SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
                 %>
                 <table align="center"  border="1" width="80%">
            <tr align="center">
                <td width="5%" align="center">Role id</td>
                <td width="15%" align="center">Name</td>
                
            </tr>
            <% for (int i = 0; i <= (list.size() - 1); i++) {%>
            <tr align="center">
                <td><%= list.get(i).getId() %></td>
                <td><%= list.get(i).getName()  %></td>
            </tr>
            <%}%>
           
        </table>

        <%  }

        }
}%>
    </body>
</html>
