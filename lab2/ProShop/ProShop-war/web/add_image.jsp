<%-- 
    Document   : add_image
    Created on : 06.10.2011, 21:05:24
    Author     : �����
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>add_image</title>
    </head>
    <body>
        <form name="add_image" action="add_image">
            
            <table border="0">
                <tbody>
                    <tr>
                        <td>Номер продукта</td>
                        <td><input type="text" name="ID_PRODUCT" value=""/></td>
                    </tr>
                    <tr>
                        <td>Название</td>
                        <td><input type="text" name="NAME" value="" /></td>
                    </tr>
                    <tr>
                        <td>Путь к картинке</td>
                        <td><input type="text" name="PATH" value="" /></td>
                    </tr>
                    <tr>
                        <td>ширина</td>
                        <td><input type="text" name="WIDTH" value="" /></td>
                    </tr>
                    <tr>
                        <td>высота</td>
                        <td><input type="text" name="HEIGHT" value=""/> </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit"  value="Input" />
        </form>
        <p align="left"><a href ="index.jsp">index</a><br></p>
    </body>
</html>
