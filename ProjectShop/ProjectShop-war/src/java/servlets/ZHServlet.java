/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import DBManager.ZhDBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


/**
 *
 * @author ололо
 */
public class ZHServlet extends HttpServlet {
   
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
          try{



         if (request.getRequestURI().equals("/ProjectShop-war/addComment")) {


           String id_product = request.getParameter("ID_PRODUCT");
                int id_pr = Integer.parseInt(id_product);
           String id_user = request.getParameter("ID_USER");
                int id_usr = Integer.parseInt(id_user);
           String text = request.getParameter("TEXT");


           ZhDBManager.addComment(id_pr, id_usr, text);

           rd = request.getRequestDispatcher("opinion.jsp");
            rd.forward(request, response);

         }

                  if (request.getRequestURI().equals("/ProjectShop-war/delComment")) {


           String id_opinion = request.getParameter("ID_OPINION");
                int id_op = Integer.parseInt(id_opinion);

           ZhDBManager.delComment(id_op);

           rd = request.getRequestDispatcher("opinion.jsp");
            rd.forward(request, response);

            //<delete dir="${dist.dir}"/>
         }
         if (request.getRequestURI().equals("/ProjectShop-war/addImage")) {
         String id_product = request.getParameter("ID_PRODUCT");
                int id_pr = Integer.parseInt(id_product);
           String name = request.getParameter("NAME");
           String path = request.getParameter("PATH");
           String width = request.getParameter("WIDTH");
           String height = request.getParameter("HEIGHT");

           ZhDBManager.addImage(id_pr, name, path, width, height);

           rd = request.getRequestDispatcher("image.jsp");
            rd.forward(request, response);
         }
                  if (request.getRequestURI().equals("/ProjectShop-war/delImage")) {


           response.setContentType("text/html;charset=UTF-8");

           String string_id_img = request.getParameter("ID_IMG");
                int id_img = Integer.getInteger(string_id_img);

                ZhDBManager.delImage(id_img);

             rd = request.getRequestDispatcher("image.jsp");
            rd.forward(request, response);

         }


       /*                    if (request.getRequestURI().equals("/ProjectShop-war/superSelect")) {


           String tables = request.getParameter("TABLES");
           String atr = request.getParameter("ATR");

           ZhDBManager.superSelect(tables, atr);

           rd = request.getRequestDispatcher("opinion.jsp");
            rd.forward(request, response);

            //<delete dir="${dist.dir}"/>
         }*/
        } catch(Exception ex){}

    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
