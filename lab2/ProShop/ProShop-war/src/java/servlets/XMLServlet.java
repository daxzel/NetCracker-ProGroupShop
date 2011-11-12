/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBeans.*;
import OtherBean.*;
import SessionBeans.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author Admin
 */
public class XMLServlet extends HttpServlet {

    protected void getProducts(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            try {
                java.io.PrintWriter writer = new java.io.PrintWriter("c:\\1.xml");
                ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) Helper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                Tools.XMLHelper.ProductToXml(productHome.findAll(), out);
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
                XmlBeanRemoteHome xmlHome = (XmlBeanRemoteHome) Helper.lookupHome("ejb/XmlBean", XmlBeanRemoteHome.class);
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
