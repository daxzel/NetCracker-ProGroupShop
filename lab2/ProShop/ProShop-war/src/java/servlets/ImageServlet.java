/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParsePosition;
import java.util.*;
import exceptions.*;
import javax.servlet.http.HttpSession;

import Other.JSPHelper;
import entityBeans.*;
import Other.JSPHelper;
import javax.naming.InitialContext;
import java.sql.*;
import javax.sql.*;
import javax.ejb.*;
import OtherBean.Helper;
import java.io.*;

/**
 *
 * @author Admin
 */
public class ImageServlet extends HttpServlet {

    protected void addImage(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        RequestDispatcher rd;

        String id_productS =null;
        String name = null;
        String widthS = null;
        String heightS = null;
        String result = "Произошла ошибка при добавлении картинки";
        String page = "add_image.jsp";

        org.apache.commons.fileupload.disk.DiskFileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();

        factory.setSizeThreshold(1024*1024);

        File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");

        factory.setRepository(tempDir);
        org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(factory);

        upload.setSizeMax(1024 * 1024 * 10);

        Tools.SerializbleImage im = null;

        try
        {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext())
            {
                org.apache.commons.fileupload.FileItem item = (org.apache.commons.fileupload.FileItem) iter.next();
                if (!item.isFormField())
                {
                    im = new Tools.SerializbleImage(item.getInputStream());
                }
                else
                {
                    String fieldName = item.getFieldName();
                    if (fieldName.equals("NAME"))
                    {
                        name = item.getString();
                    }
                    else
                    {
                        if (fieldName.equals("ID_PRODUCT"))
                        {
                            id_productS = item.getString();
                        }
                        else
                        {
                            if (fieldName.equals("WIDTH"))
                            {
                                widthS = item.getString();
                            }
                            else
                            {
                                if (fieldName.equals("HEIGHT"))
                                {
                                    heightS = item.getString();
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            int x = 0;
        }

        long id_product;
        int width=0;
        int height=0;

        try {

            if ("".equals(id_productS)) {
                throw new Exception("Продукт не может быть пустым полем");
            }

            if ("".equals(name)) {
                throw new Exception("Название картинки не может быть пустым полем");
            }

            if ("".equals(widthS)) {
                throw new Exception("Ширина не может быть пустым полем");
            }

            if ("".equals(heightS)) {
                throw new Exception("Высота не может быть пустым полем");
            }

            height = Integer.parseInt(heightS);
            id_product=Long.parseLong(id_productS);
            width=Integer.parseInt(widthS);

            ImageBeanRemoteHome imageHome = (ImageBeanRemoteHome) Helper.lookupHome("ejb/ImageBean", ImageBeanRemoteHome.class);

            ImageBeanRemote imageBean=imageHome.create(id_product, name, im , width, height);

            result = "Продукт добавлен";
            page = "index.jsp";

        } catch (FinderException ex) {
            result = "Имя каталога указанно не верно";
        } catch (NamingException ex) {
            result = "Ошибка";
        } catch (CreateException ex) {
            result = ex.getMessage();
        } catch (NumberFormatException ex) {
            result = "Ошибка в указании цены";
        } catch (Throwable ex) {
            result = ex.getMessage();
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }


    }


    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        javax.servlet.ServletOutputStream out =  response.getOutputStream();
        try
        {
            long id = Long.parseLong(request.getParameter("ID"));
            ImageBeanRemoteHome imageHome = (ImageBeanRemoteHome) Helper.lookupHome("ejb/ImageBean", ImageBeanRemoteHome.class);
            ImageBeanRemote imageBean=imageHome.findByPrimaryKey(new Long(id));
            Tools.SerializbleImage image = imageBean.getImageI();
            response.setContentType("text/html;charset=UTF-8");
            
            com.sun.image.codec.jpeg.JPEGImageEncoder jie = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(out);
            jie.encode(image.getImage());
        }
        catch(Exception ex)
        {
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try
        {
            if (request.getRequestURI().equals("/ProShop-war/add_image"))
            {
               addImage(request, response);
               return;
            }
        }
        catch(Exception ex)
        {
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }

}
