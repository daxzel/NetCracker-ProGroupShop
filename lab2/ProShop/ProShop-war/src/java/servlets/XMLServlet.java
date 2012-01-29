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

    protected void importXML1(HttpServletRequest request,
            HttpServletResponse response) throws LoginException {
        try {
            UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
            if (usr.getRoleId() >= 2) {
                throw new LoginException("Вы не обладаете правами администратора");
            }
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
               // XMLHelper.importOfXML(xml2);
                out.write("<div class=\"success\"><p align=\"center\">Импорт успешно завершён</p></div>");
            } catch (Exception ex) {
                out.write(ex.getMessage());
            }

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

    protected void importXML(HttpServletRequest request,
            HttpServletResponse response) throws LoginException {
        try {
            UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
            if (usr.getRoleId() >= 2) {
                throw new LoginException("Вы не обладаете правами администратора");
            }
            PrintWriter out = null;
            try {
                out = response.getWriter();
                java.io.InputStream xml = null;
                java.io.InputStream xml2 = null;
                java.io.InputStream savingXml = null;
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
                        savingXml = item.getInputStream();
                    }
                }
                XMLHelper.CheckSchema(xml, StaticResourceHelper.getBASEXSD(request));
                AdderAndUpdater adder =  XMLHelper.ManagedImportOfXML(xml2);

                String uuid = StoreHelper.saveToTempFile(savingXml);
                request.setAttribute("UUIDOfTempFile", uuid);
                request.setAttribute("importObjects", adder);
                RequestDispatcher rd = request.getRequestDispatcher("/controlImport.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                out.write(ex.getMessage());
            }

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

      protected void managedImport(HttpServletRequest request,
            HttpServletResponse response) throws LoginException {
        try {
            UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
            if (usr.getRoleId() >= 2) {
                throw new LoginException("Вы не обладаете правами администратора");
            }
            PrintWriter out = null;
            try {
                 out = response.getWriter();

                String id_users[] = request.getParameterValues("id_user");

                String uuid = request.getParameter("uuid");

                java.io.InputStream in = StoreHelper.getTempFile(uuid);

                XMLHelper.importOfXML(in, id_users);

                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                out.write(ex.getMessage());
            }

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

    protected void history(HttpServletRequest request, HttpServletResponse response) throws ExportException, IOException, ServletException, LoginException {

        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() >= 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        RequestDispatcher rd;
        ServletOutputStream out = null;
        String result, id_s, table = null;
        long id;
        String t;
        result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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
                table = "\"PRODUCT\"";
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
            HttpServletResponse response) throws LoginException {
        try {
            UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
            if (usr.getRoleId() >= 2) {
                throw new LoginException("Вы не обладаете правами администратора");
            }
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
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    protected void exportUsers(HttpServletRequest request, HttpServletResponse response) throws ExportException, LoginException {
        {
            try {
                UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
                if (usr.getRoleId() >= 2) {
                    throw new LoginException("Вы не обладаете правами администратора");
                }
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
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void exportUsersP(HttpServletRequest request, HttpServletResponse response) throws ExportException, IOException, ServletException, LoginException {
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() >= 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
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

    protected void exportProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, LoginException {
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() >= 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        RequestDispatcher rd;
        ServletOutputStream out = null;
        response.setContentType("text/xml");
        response.setCharacterEncoding("utf-8");
        // response.addHeader("Content-Disposition", "attachment; filename=" + "test.xml");
        boolean flag, catalogFlag, orderFlag, commentFlag, allFlag;
        flag = catalogFlag = orderFlag = commentFlag = allFlag = false;
        String result, price, name, more, less, exportCatalog, exportOrder, exportComment, exportAll;
        double priceDouble = 0;
        String page = "exportProduct.jsp";
        String searchType = "";
        result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        name = null;
        try {

            ArrayList list = new ArrayList();
            price = request.getParameter("price");
            if (price == null) {
                searchType = "byName";
                name = request.getParameter("name");
                if (name == null || "".equals(name)) {

                    throw new ExportException("Введите название продукта");

                }
            } else {
                searchType = "byPrice";
                priceDouble = Double.parseDouble(price);
                if (priceDouble <= 0) {
                    throw new ExportException("Введите положительную цену продукта");
                }

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
            result = "<div class=\"warning\"><p align=\"center\">Не найдены записи</p></div>";
            rd = request.getRequestDispatcher("exportProduct.jsp" + "?searchType=" + searchType);
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (ExportException ex) {
            result = "<div class=\"warning\"><p align=\"center\">"+ex.getMessage()+"</p></div>";
            rd = request.getRequestDispatcher("exportProduct.jsp" + "?searchType=" + searchType);
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (NumberFormatException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Не правильно ввдена цена продукта</p></div>";
            rd = request.getRequestDispatcher("exportProduct.jsp" + "?searchType=" + searchType);
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (IOException ex) {
            rd = request.getRequestDispatcher("exportProduct.jsp" + "?searchType=" + searchType);
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (CreateException ex) {
            rd = request.getRequestDispatcher("exportProduct.jsp" + "?searchType=" + searchType);
            request.setAttribute("result", result);
            rd.forward(request, response);
        } catch (NamingException ex) {
            rd = request.getRequestDispatcher("exportProduct.jsp" + "?searchType=" + searchType);
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

    protected void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ExportException, LoginException {
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() >= 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        RequestDispatcher rd;
        ServletOutputStream out = null;
        String result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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

        } catch (ExportException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("exportProduct.jsp");
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
                exportProduct(request, response);
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
                exportProduct(request, response);
                return;
            }
            if (request.getRequestURI().equals(request.getContextPath() + "/XML/import")) {
                importXML(request, response);
                return;
            }

            if (request.getRequestURI().equals(request.getContextPath() + "/XML/managedImport"))
            {
                managedImport(request, response);
                return;
            }

        } catch (ExportException ex) {

            rd = request.getRequestDispatcher("/errorPage.jsp");
            request.setAttribute("exception", ex);
            rd.forward(request, response);
        } catch (LoginException ex) {

            rd = request.getRequestDispatcher("/login.jsp");
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
