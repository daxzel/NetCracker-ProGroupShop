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
import exceptions.ExportException;
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
            HttpServletResponse response) {
        try {
            java.io.InputStream xml = null;
            java.io.InputStream xml2 = null;

            org.apache.commons.fileupload.disk.DiskFileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();

            //factory.setSizeThreshold(1024 * 1024);

            File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");

            factory.setRepository(tempDir);

            org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(factory);

            upload.setSizeMax(java.lang.Long.MAX_VALUE);

            upload.setFileSizeMax(java.lang.Long.MAX_VALUE);

            List items = upload.parseRequest(request);

            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                org.apache.commons.fileupload.FileItem item = (org.apache.commons.fileupload.FileItem) iter.next();
                if (!item.isFormField()) {
                    xml = item.getInputStream();
                    xml2 = item.getInputStream();
                }
            }

            XMLHelper.CheckSchema(xml,StaticResourceHelper.getBASEXSD(request));

            XMLHelper.importOfXML(xml2);

        } catch (Exception ex) {

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

    protected void exportUsers(HttpServletRequest request, HttpServletResponse response) throws ExportException {
        {
            ServletOutputStream out = null;
            try {
                boolean flag = true;
                Enumeration enumer = request.getParameterNames();
                ArrayList list = new ArrayList();
                String[] del = request.getParameterValues("DEL");

                for (int i = 0; i < del.length; i++) {
                    // String str = enumer.nextElement().toString();
                    try {
                        Long id_user = new Long(del[i]);
                        list.add(id_user);
                    } catch (NumberFormatException ex) {
                    }

                }
                if (request.getParameter("input2") != null) {
                    flag = false;
                }
                if (list.isEmpty()) {
                    throw new ExportException("Не выбранны пользователи");
                }
                XmlBeanRemoteHome xmlHome = (XmlBeanRemoteHome) EJBHelper.lookupHome("ejb/XmlBean", XmlBeanRemoteHome.class);
                XmlBeanRemote xmlBean = xmlHome.create();
                String xml = xmlBean.exportToXMLUser(list, flag);
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
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    protected void exportProductByPrice(HttpServletRequest request, HttpServletResponse response) throws ExportException, IOException, ServletException {
        RequestDispatcher rd;
        ServletOutputStream out = null;
        boolean flag,catalogFlag, orderFlag, commentFlag, allFlag;
        flag=catalogFlag = orderFlag = commentFlag = allFlag = false;
        String result, price, name, more, less, exportCatalog, exportOrder, exportComment, exportAll;
        double priceDouble=0;
        result = "Произошла ошибка";
        try {
       
            ArrayList list = new ArrayList();
            price = request.getParameter("price");
            if (price == null) {

                name = request.getParameter("name");
            } else {

                priceDouble = Double.parseDouble(price);
               
            } if(request.getParameter("more")!=null){
                flag=true;
            }
            exportCatalog = request.getParameter("exportCatalog");
            exportOrder = request.getParameter("exportOrder");
            exportComment = request.getParameter("exportComment");
            exportAll = request.getParameter("exportAll");
            if ("ON".equals(exportAll)) {
                allFlag = true;
            } else {
                if ("ON".equals(exportCatalog)) {
                    catalogFlag = true;
                }
                if ("ON".equals(exportOrder)) {
                    orderFlag = true;
                }
                if ("ON".equals(exportComment)) {
                    commentFlag = true;
                }
            }
            XmlBeanRemoteHome xmlHome = (XmlBeanRemoteHome) EJBHelper.lookupHome("ejb/XmlBean", XmlBeanRemoteHome.class);
            XmlBeanRemote xmlBean = xmlHome.create();
            String xml = xmlBean.exportToXMLProduct(priceDouble,flag, allFlag, catalogFlag, orderFlag, commentFlag);
            response.setContentType("text/xml");
            response.setCharacterEncoding("utf-8");
            out = response.getOutputStream();
            out.println(xml);
            out.flush();
        } catch (NumberFormatException ex) {
            result = "Не правильно ввдена цена продукта";
            rd = request.getRequestDispatcher("exportProduct.jsp");
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (IOException ex) {
            rd = request.getRequestDispatcher("exportProduct.jsp");
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (CreateException ex) {
            rd = request.getRequestDispatcher("exportProduct.jsp");
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (NamingException ex) {
            rd = request.getRequestDispatcher("exportProduct.jsp");
            request.setAttribute("result", result);
            rd.forward(request, response);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
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
        RequestDispatcher rd;
        try {
            if (request.getRequestURI().equals("/ProShop-war/XML/Products.xml")) {
                getProducts(request, response);
                return;
            }

            if (request.getRequestURI().equals("/ProShop-war/XML/exportUser")) {

                exportUsers(request, response);
                return;

            }

            if (request.getRequestURI().equals("/ProShop-war/XML/ExportProductByPrice")) {

                exportProductByPrice(request, response);
                return;

            }

            if (request.getRequestURI().equals("/ProShop-war/XML/import")) {
                importXML(request, response);
                return;
            }
        } catch (ExportException ex) {

            rd = request.getRequestDispatcher("/errorPage.jsp");
            request.setAttribute("exception", ex);
            rd.forward(request, response);
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
