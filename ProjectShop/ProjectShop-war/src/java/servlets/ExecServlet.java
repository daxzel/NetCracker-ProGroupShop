/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import exceptions.*;

/**
 *
 * @author Yra
 */
@WebServlet(name="ExecServlet", urlPatterns={"/ExecServlet"})
public class ExecServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            RequestDispatcher rd;
            String result;
            response.setContentType("text/html;charset=UTF-8");
            String name = request.getParameter("NAME");
            String surname = request.getParameter("SURNAME");
            String otchestvo = request.getParameter("OTCHESTVO");
            String nik = request.getParameter("NIK");
            String password = request.getParameter("PASSWORD");
            String password2 = request.getParameter("PASSWORD2");
            try{
                if(!password.equals(password2)){
                    throw new PasswordException();}
            String brn = request.getParameter("BORN");
            String phone = request.getParameter("PHONE");
            String email = request.getParameter("EMAIL");
            SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
            Date born = formt.parse(brn);
            DBManager.DBManager.addUser(name, surname, otchestvo, nik, password, born, phone, email, 1);
            result ="uspeh";
            request.setAttribute("result",result);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
            } catch (NikNameException ex) {
                request.setAttribute("result",ex);
                rd = request.getRequestDispatcher("registration.jsp");
                rd.forward(request, response);
            }
            catch (SQLException ex) {
                Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (PasswordException ex) {
                request.setAttribute("result",ex);
                rd = request.getRequestDispatcher("registration.jsp");
                rd.forward(request, response);
            }
            } catch (ParseException ex) {
                 Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException  {
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
