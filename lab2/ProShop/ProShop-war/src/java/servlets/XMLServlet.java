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
import exceptions.*;
import entityBeans.*;
import helpers.*;
import SessionBeans.*;
import exceptions.ExportException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import helpers.*;
import javax.ejb.FinderException;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Admin
 */
public class XMLServlet extends HttpServlet {

    protected void importXML(HttpServletRequest request,
            HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();

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

            XMLHelper.CheckSchema(xml, StaticResourceHelper.getBASEXSD(request));

            XMLHelper.importOfXML(xml2);

            out.write("Импорт успешно завершён");

        } catch (Exception ex) {
            out.write(ex.getMessage());
        }

    }

    protected void history(HttpServletRequest request, HttpServletResponse response) throws ExportException, IOException, ServletException {
        RequestDispatcher rd;
        ServletOutputStream out = null;
        String result, id_s, table = null;
        long id;
        String t;
        result = "Произошла ошибка";
        try {

            ArrayList list = new ArrayList();
            t = request.getParameter("TABLE").toString();
            id_s = request.getParameter("ID").toString();
            id = Long.parseLong(id_s);
            if ("1".equals(t)) {
                table = "\"CATALOG\"";
            }
            if ("2".equals(t)) {
                table = "\"IMAGE\"";
            }
            if ("3".equals(t)) {
                table = "\"OPINION\"";
            }
            if ("4".equals(t)) {
                table = "\"ORDER\"";
            }
            if ("5".equals(t)) {
                table = "PRODUCT";
            }
            if ("6".equals(t)) {
                table = "\"ROLE\"";
            }
            if ("7".equals(t)) {
                table = "\"USER\"";
            }
            HistoryBeanRemoteHome historyHome = (HistoryBeanRemoteHome) EJBHelper.lookupHome("ejb/HistoryBean", HistoryBeanRemoteHome.class);
            HistoryBeanRemote historyBean = historyHome.create();
            String xml = historyBean.exportToXML(table, id);
            response.setContentType("text/xml");
            response.setCharacterEncoding("utf-8");
            out = response.getOutputStream();
            out.println(xml);
            out.flush();

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
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

    protected void exportUsersP(HttpServletRequest request, HttpServletResponse response) throws ExportException, IOException, ServletException {
        RequestDispatcher rd;
        ServletOutputStream out = null;
        boolean flag1, flag2, usersFlag, rolesFlag, opinionsFlag, productsFlag, ordersFlag, catalogsFlag;
        flag1 = flag2 = rolesFlag = opinionsFlag = productsFlag = ordersFlag = catalogsFlag = false;
        int id_role = 0;
        String result, name, role, exportRoles, searchByName, searchByRole, exportOpinions, exportProducts, exportOrders, exportCatalogs;
        result = "Произошла ошибка";
        try {
            ArrayList list = new ArrayList();
            name = request.getParameter("NAME").toString();
            role = request.getParameter("ROLE").toString();
            if ("admin".equals(role)) {
                id_role = 1;
            } else {
                if ("manager".equals(role)) {
                    id_role = 2;
                } else {
                    if ("user".equals(role)) {
                        id_role = 3;
                    }
                }
            }
            searchByName = request.getParameter("USERSBYNAME");
            searchByRole = request.getParameter("USERSBYROLE");
            if (request.getParameter("USERSBYNAME") != null) {
                flag1 = true;
            }
            if (request.getParameter("USERSBYROLE") != null) {
                flag2 = true;
            }
            exportRoles = request.getParameter("ROLES");
            if ("ON".equals(exportRoles)) {
                rolesFlag = true;
            }
            exportOpinions = request.getParameter("OPINIONS");
            if ("ON".equals(exportOpinions)) {
                opinionsFlag = true;
            }
            exportProducts = request.getParameter("PRODUCTS");
            if ("ON".equals(exportProducts)) {
                productsFlag = true;
            }
            exportCatalogs = request.getParameter("CATALOGS");
            if ("ON".equals(exportCatalogs)) {
                catalogsFlag = true;
            }
            exportOrders = request.getParameter("ORDERS");
            if ("ON".equals(exportOrders)) {
                ordersFlag = true;
            }
            ////////////////////////////////
            XmlBeanRemoteHome xmlHome = (XmlBeanRemoteHome) EJBHelper.lookupHome("ejb/XmlBean", XmlBeanRemoteHome.class);
            XmlBeanRemote xmlBean = xmlHome.create();
            String xml = xmlBean.exportToXMLUsersP(name, id_role, flag1, flag2, rolesFlag, opinionsFlag, productsFlag, ordersFlag, catalogsFlag);
            response.setContentType("text/xml");
            response.setCharacterEncoding("utf-8");
            out = response.getOutputStream();
            request.getSession().setAttribute("XMLDoc", xml);
            out.println(xml);
            out.flush();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
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

    protected void exportProductByPrice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd;
        ServletOutputStream out = null;
        response.setContentType("text/xml");
        response.setCharacterEncoding("utf-8");
        // response.addHeader("Content-Disposition", "attachment; filename=" + "test.xml");
        boolean flag, catalogFlag, orderFlag, commentFlag, allFlag;
        flag = catalogFlag = orderFlag = commentFlag = allFlag = false;
        String result, price, name, more, less, exportCatalog, exportOrder, exportComment, exportAll;
        double priceDouble = 0;
        result = "Произошла ошибка";
        name = null;
        try {

            ArrayList list = new ArrayList();
            price = request.getParameter("price");
            if (price == null) {

                name = request.getParameter("name");
                if (name == null || "".equals(name)) {

                    throw new ExportException("Введите название продукта");

                }
            } else {

                priceDouble = Double.parseDouble(price);

            }
            if (request.getParameter("more") != null) {
                flag = true;
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
            List products = null;
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            if (price != null) {
                products = productHome.findByPrice(priceDouble, flag);
            }
            if (name != null) {
                ProductBeanRemote product = productHome.findByName(name);
                products = new ArrayList();
                products.add(product);
            }
            XmlBeanRemoteHome xmlHome = (XmlBeanRemoteHome) EJBHelper.lookupHome("ejb/XmlBean", XmlBeanRemoteHome.class);
            XmlBeanRemote xmlBean = xmlHome.create();
            //   String xml = xmlBean.exportToXMLProduct(products, allFlag, catalogFlag, orderFlag, commentFlag);
            result = xmlBean.exportToXMLProduct(products, allFlag, catalogFlag, orderFlag, commentFlag);

            request.getSession().setAttribute("XMLDoc", result);

            out = response.getOutputStream();
            out.println(result);
            out.flush();
        } catch (FinderException ex) {
            result = "Не найдены записи";
            rd = request.getRequestDispatcher("exportProduct.jsp");
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (ExportException ex) {
            result = "Введите название продукта";
            rd = request.getRequestDispatcher("exportProduct.jsp");
            request.setAttribute("result", result);
            rd.forward(request, response);
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

    protected void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ExportException {
        RequestDispatcher rd;
        ServletOutputStream out = null;

        Object obj = request.getSession().getAttribute("XMLDoc");
        try {
            if (obj instanceof String) {
                String XMLDoc = obj.toString();
                response.addHeader("Content-Disposition", "attachment; filename=" + "export.xml");
                out = response.getOutputStream();
      //          request.getSession().removeAttribute("XMLDoc");
                out.println(XMLDoc);

                if (XMLDoc == null) {

                    throw new ExportException("Произошла ошибка, файл не был записан");
                }
            } else {
                throw new ExportException("Произошла ошибка, файл не был записан");
            }
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
        request.setCharacterEncoding("UTF-8");

        RequestDispatcher rd;


        try {
            if (request.getRequestURI().equals(request.getContextPath() + "/XML/Products.xml")) {
                getProducts(request, response);
                return;
            }

            if (request.getRequestURI().equals(request.getContextPath() + "/XML/exportUser")) {
                exportUsers(request, response);
                return;
            }

            if (request.getRequestURI().equals(request.getContextPath() + "/XML/ExportProductByPrice")) {
                exportProductByPrice(request, response);
                return;
            }
            if (request.getRequestURI().equals(request.getContextPath() + "/XML/getFile")) {
                getFile(request, response);
                return;
            }


            if (request.getRequestURI().equals(request.getContextPath() + "/XML/exportUsersP")) {
                exportUsersP(request, response);
                return;
            }
            if (request.getRequestURI().equals(request.getContextPath() + "/XML/history")) {
                history(request, response);
                return;
            }
            if (request.getRequestURI().equals(request.getContextPath() + "/XML/ExportProductByName")) {
                exportProductByPrice(request, response);
                return;
            }
            if (request.getRequestURI().equals(request.getContextPath() + "/XML/import")) {
                importXML(request, response);
                return;
            }
        } catch (ExportException ex) {
            rd = request.getRequestDispatcher(request.getContextPath() + "/errorPage.jsp");
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
