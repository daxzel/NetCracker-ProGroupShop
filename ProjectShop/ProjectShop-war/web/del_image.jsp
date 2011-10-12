<%-- 
    Document   : del_image
    Created on : 09.10.2011, 16:02:48
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
  <form action="DelImageServlet">

            <table border="0">

                <tbody>
                    <tr>
                        <td>ПК изображения</td>
                        <td><input type="text" name="ID_IMG" value=""/> </td>
                    </tr>

                </tbody>
            </table>

            <input type="submit" value="OK" />
        </form>
    </body>
</html>
