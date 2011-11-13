/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import helpers.EJBHelper;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBeans.*;
import helpers.*;
import SessionBeans.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import helpers.*;

/**
 *
 * @author Admin
 */
public class XMLServlet extends HttpServlet {

    protected void importXML(HttpServletRequest request,
            HttpServletResponse response)
    {
        try
        {
            java.io.InputStream xml= null;

            org.apache.commons.fileupload.disk.DiskFileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();

            factory.setSizeThreshold(1024*1024);

            File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");

            factory.setRepository(tempDir);
            
            org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(factory);

            upload.setSizeMax(1024 * 1024 * 10);

            List items = upload.parseRequest(request);
            
            Iterator iter = items.iterator();

            while (iter.hasNext())
            {
                org.apache.commons.fileupload.FileItem item = (org.apache.commons.fileupload.FileItem) iter.next();
                if (!item.isFormField())
                {
                     xml= item.getInputStream();
                }
            }

            File schema  = new File(StaticResourceHelper.getStaticDirectory(request)+StaticResourceHelper.getProdudctsXSD());

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
     
    }

    protected void getProducts(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            try {
                XmlBeanRemoteHome xmlHome = (XmlBeanRemoteHome) EJBHelper.lookupHome("ejb/XmlBean", XmlBeanRemoteHome.class);
                XmlBeanRemote xmlBean = xmlHome.create();
                String xml = xmlBean.exportAllProducts();
                response.setContentType("text/xml");
                response.setCharacterEncoding("utf-8");
                out.println(xml);
            } catch (Exception ex) {
                out.write(ex.getMessage());
            }
            out.close();
        } catch (IOException ex) {
        }
    }

    protected void exportUsers(HttpServletRequest request, HttpServletResponse response) {
        {
            ServletOutputStream out = null;
            try {
                Enumeration enumer = request.getParameterNames();
                ArrayList list = new ArrayList();
                while (enumer.hasMoreElements()) {
                    String str = enumer.nextElement().toString();
                    if (!"input".equals(str)) {
                        Long id_user = new Long(Long.parseLong(str));
                        list.add(id_user);
                    } else {
                    }
                }
                XmlBeanRemoteHome xmlHome = (XmlBeanRemoteHome) EJBHelper.lookupHome("ejb/XmlBean", XmlBeanRemoteHome.class);
                XmlBeanRemote xmlBean = xmlHome.create();
                String xml = xmlBean.exportToXML(list,true);
                response.setContentType("text/xml");
                response.setCharacterEncoding("utf-8");
                out = response.getOutputStream();
                out.println(xml);
                out.flush();
                // out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (CreateException ex) {
                ex.printStackTrace();
            } catch (NamingException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                /*  RequestDispatcher dispatcher = request.getRequestDispatcher("xml");
                try {
                dispatcher.forward(request, response);
                } catch (ServletException ex) {
                ex.printStackTrace();
                } catch (IOException ex) {
                ex.printStackTrace();
                }*/
            }
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
        response.setContentType("text/html;charset=UTF-8");
        if (request.getRequestURI().equals("/ProShop-war/XML/Products.xml")) {
            getProducts(request, response);
            return;
        }

        if (request.getRequestURI().equals("/ProShop-war/XML/exportUser")) {
            exportUsers(request, response);
            return;
        }

        if (request.getRequestURI().equals("/ProShop-war/XML/import")) {
            exportUsers(request, response);
            return;
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
