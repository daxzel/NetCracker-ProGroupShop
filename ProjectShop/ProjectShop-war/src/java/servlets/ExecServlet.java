/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DBClasses.Opinion;
import java.io.FileNotFoundException;
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
import java.util.*;
import exceptions.*;
import javax.servlet.http.HttpSession;
import DBClasses.User;
import DBManager.*;

/**
 *
 * @author Yra
 */
@WebServlet(name = "ExecServlet", urlPatterns = {"/ExecServlet"})
public class ExecServlet extends HttpServlet {

    protected void registration(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException {
        String result;
        RequestDispatcher rd;

        String name = request.getParameter("NAME");
        String surname = request.getParameter("SURNAME");
        String otchestvo = request.getParameter("OTCHESTVO");
        String nik = request.getParameter("NIK");
        String password = request.getParameter("PASSWORD");
        String password2 = request.getParameter("PASSWORD2");

        try {

            if (!password.equals(password2)) {
                throw new PasswordException();
            }

            String brn = request.getParameter("BORN");
            String phone = request.getParameter("PHONE");
            String email = request.getParameter("EMAIL");
            SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
            Date born = formt.parse(brn);
            DBManager.addUser(name, surname, otchestvo, nik, password, born, phone, email, 2);
            result = "uspeh";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("registration.jsp");
            rd.forward(request, response);
        } catch (NikNameException ex) {
            request.setAttribute("result", ex);
            rd = request.getRequestDispatcher("registration.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PasswordException ex) {
            request.setAttribute("result", ex);
            rd = request.getRequestDispatcher("registration.jsp");
            rd.forward(request, response);
        }
    }

    protected void selectByNik(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException {
        String result, homepage;
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        homepage = session.getAttribute("homepage").toString();
        String nik = request.getParameter("NIK");
        try {
            User usr = DBManager.findUserByNik(nik);
            request.setAttribute("result", usr);
            rd = request.getRequestDispatcher(homepage);
            rd.forward(request, response);
        } catch (SQLException ex) {
            result = "������ �� �������";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher(homepage);
            rd.forward(request, response);
        } catch (NamingException ex) {
            result = "��������� ������";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher(homepage);
            rd.forward(request, response);
        }
        }
         else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void addProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException {
        String result;
        RequestDispatcher rd;

        String name = request.getParameter("NAME");
        String description = request.getParameter("DESCRIPTION");
        double price = Double.parseDouble(request.getParameter("PRICE"));
        int id_catalog = Integer.parseInt(request.getParameter("ID_CATALOG"));

        try {

            ProductDAL.addProduct(name, description, id_catalog, price);


            result = "uspeh";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void updateUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException {
        String result;
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
            User usrOld = (User) session.getAttribute("usrOld");
            String nikOld = usrOld.getNik();
            String name = request.getParameter("NAME");
            String surname = request.getParameter("SURNAME");
            String otchestvo = request.getParameter("OTCHESTVO");
            String nik = request.getParameter("NIK");
            String password = null;
            String password2 = null;

            if (request.getParameter("PASSWORD") != null) {
                if (request.getParameter("PASSWORD").equals("")) {
                    password = usrOld.getPassword();
                    password2 = usrOld.getPassword();
                } else {
                    password = request.getParameter("PASSWORD");
                    password2 = request.getParameter("PASSWORD2");
                }
            } else {
                password = usrOld.getPassword();
                password2 = usrOld.getPassword();
            }
            try {
                if (password.equals("")) {
                    throw new PasswordException();
                }
                if (!password.equals(password2)) {
                    throw new PasswordException();
                }
                String brn = request.getParameter("BORN");
                String phone = request.getParameter("PHONE");
                String email = request.getParameter("EMAIL");
                String roleName = usrOld.getRoleName();
                if (request.getParameter("ID_ROLE") != null) {
                    roleName = request.getParameter("ID_ROLE");
                }
                DBManager.updateUserbyNik(new User(usrOld.getId(), name, surname, otchestvo, nik, password, brn, phone, email, roleName), nikOld);
                result = "uspeh";
                request.setAttribute("result", result);
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            } catch (NikNameException ex) {
                result = "Пользователь с таким ником существует";
                request.setAttribute("result", result);
                rd = request.getRequestDispatcher("updateUser.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PasswordException ex) {
                result = "Пароль введен не верно";
                request.setAttribute("result", result);
                rd = request.getRequestDispatcher("updateUser.jsp");
                rd.forward(request, response);
            }
        } else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void getUserByRole(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;
         HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        String rolename = request.getParameter("ROLE");
        try {
            List<User> list = DBManager.findUsersByRole(rolename);
            request.setAttribute("result", list);
            rd = request.getRequestDispatcher("getUsersByRole.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void deleteUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        try {
            String nik = request.getParameter("NIK");

            int numDelete = DBManager.deleteUser(nik);
            request.setAttribute("result", numDelete);
            rd = request.getRequestDispatcher("deleteUser.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void getFullList(HttpServletRequest request,
            HttpServletResponse response, String table) throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        try {
            request.setAttribute("result", DBManager.getFullList(table));
            rd = request.getRequestDispatcher("getFullList.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
     }else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
        }

    protected void login(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        try {

            String nik = request.getParameter("NIK");
            String password = request.getParameter("PASSWORD");
            User usr = DBManager.findUserByNik(nik);
            usr.setLogin();
            if (usr.getPassword().equals(password)) {
                session.setAttribute("user", usr);
                session.setAttribute("login", true);
            } else {
                throw new NikNameException();
            }
            if (session.getAttribute("homepage") != null) {
                String homepage = session.getAttribute("homepage").toString();
                rd = request.getRequestDispatcher(homepage);
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        } catch (NikNameException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     protected void addComment(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
           RequestDispatcher rd;
            HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        try {
          
            String id_product = request.getParameter("ID_PRODUCT");
            int id_pr = Integer.parseInt(id_product);
            String id_user = request.getParameter("ID_USER");
            int id_usr = Integer.parseInt(id_user);
            String text = request.getParameter("TEXT");
            DBManager.addComment(id_pr, id_usr, text);
            rd = request.getRequestDispatcher("opinion.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         }else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
     }
     protected void delComment(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,IOException{

        RequestDispatcher rd;
            HttpSession session = request.getSession();
         if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        try {

            String id_opinion = request.getParameter("ID_OPINION");
            int id_op = Integer.parseInt(id_opinion);
            DBManager.delComment(id_op);
            rd = request.getRequestDispatcher("opinion.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }

     }
      protected void getOpinionByProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,IOException{

                  RequestDispatcher rd;
            HttpSession session = request.getSession();
         if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        try {

            String id_product = request.getParameter("ID_PRODUCT");
            int id_pr = Integer.parseInt(id_product);
            List<Opinion> list = DBManager.findOpinionByProduct(id_pr);
            request.setAttribute("result", list);
            rd = request.getRequestDispatcher("getOpinion.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }

      }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        String result, homepage;
        try {
            response.setContentType("text/html;charset=UTF-8");

            if (request.getRequestURI().equals("/ProjectShop-war/login")) {
                login(request, response);
                return;
            }

            if (request.getRequestURI().equals("/ProjectShop-war/registration")) {
                registration(request, response);
                return;
            }

            if (request.getRequestURI().equals("/ProjectShop-war/selectByNik")) {
                selectByNik(request, response);
                return;
            }

            if (request.getRequestURI().equals("/ProjectShop-war/addProduct")) {
                addProduct(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/updateUser")) {
                updateUser(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getUsersByRole")) {
                getUserByRole(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/deleteUser")) {
                deleteUser(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getFullRoleList")) {
                getFullList(request, response, "ROLE");
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getFullUserList")) {
                getFullList(request, response, "USER");
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/addComment")) {
                 addComment(request,response);
                 return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/delComment")) {
                 delComment(request,response);
                 return;
            }
                        if (request.getRequestURI().equals("/ProjectShop-war/getOpinion")) {
                 getOpinionByProduct(request,response);
                 return;
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
