<%-- 
    Document   : deleteUser
    Created on : 12.10.2011, 13:15:57
    Author     : Yra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Удаление пользователя</title>
         </head>
    <body>
         <%
        if(request.getAttribute("result")==null){
        %>
        <form action="deleteUser">
            Введите ник:
            <input type="text" name="NIK" value="" size="20" />
            <input type="submit" value="Input" />
        </form>
        <%}else{
                if(Integer.parseInt(request.getAttribute("result").toString())==1){%>
                 Пользователь удален.
                <%}else{%>
                 Удаление прошло не успешно.
                <%}
        }%>
   
        
    </body>
</html>
