<%-- 
    Document   : del_comment
    Created on : 10.10.2011, 20:42:11
    Author     : ололо
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <form action="delComment">

            <table border="0">

                <tbody>
                    <tr>
                        <td>ПК комментария</td>
                        <td><input type="text" name="ID_OPINION" value=""/> </td>
                    </tr>

                </tbody>
            </table>

            <input type="submit" value="OK" />
        </form>
    </body>
</html>
