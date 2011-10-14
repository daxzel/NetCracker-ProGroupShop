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
            HttpServletResponse response) throws ServletException,ParseException,IOException
    {
        String result;
        RequestDispatcher rd;

        String name = request.getParameter("NAME");
        String surname = request.getParameter("SURNAME");
        String otchestvo = request.getParameter("OTCHESTVO");
        String nik = request.getParameter("NIK");
        String password = request.getParameter("PASSWORD");
        String password2 = request.getParameter("PASSWORD2");

        try
        {

            if (!password.equals(password2))
            {
                throw new PasswordException();
            }

            String brn = request.getParameter("BORN");
            String phone = request.getParameter("PHONE");
            String email = request.getParameter("EMAIL");
            SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
            Date born = formt.parse(brn);
            DBManager.addUser(name, surname, otchestvo, nik, password, born, phone, email, 1);
            result = "uspeh";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        catch (NikNameException ex)
        {
            request.setAttribute("result", ex);
            rd = request.getRequestDispatcher("registration.jsp");
            rd.forward(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NamingException ex)
        {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (PasswordException ex)
        {
            request.setAttribute("result", ex);
            rd = request.getRequestDispatcher("registration.jsp");
            rd.forward(request, response);
        }
    }

    protected void selectByNik(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,ParseException,IOException
    {
        String result, homepage;
        RequestDispatcher rd;
        HttpSession session = request.getSession();

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

     protected void addProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,ParseException,IOException
    {
        String result;
        RequestDispatcher rd;

        String name = request.getParameter("NAME");
        String description = request.getParameter("DESCRIPTION");
        double price = Double.parseDouble(request.getParameter("PRICE")) ;
        int id_catalog = Integer.parseInt(request.getParameter("ID_CATALOG"));

        try
        {
            
            ProductDAL.addProduct(name, description, id_catalog, price);


            result = "uspeh";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NamingException ex)
        {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       protected void updateUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,ParseException,IOException
    {
        String result;
        HttpSession session = request.getSession();
        RequestDispatcher rd;

        User usrOld= (User)session.getAttribute("usrOld");
                String nikOld= usrOld.getNik();
                String name = request.getParameter("NAME");
                String surname = request.getParameter("SURNAME");
                String otchestvo = request.getParameter("OTCHESTVO");
                String nik = request.getParameter("NIK");
                String password = request.getParameter("PASSWORD");
                String password2 = request.getParameter("PASSWORD2");
                try {
                    if (password.equals("")){
                        throw new PasswordException();
                    }
                    if (!password.equals(password2)) {
                        throw new PasswordException();
                    }
                    String brn = request.getParameter("BORN");
                    String phone = request.getParameter("PHONE");
                    String email = request.getParameter("EMAIL");
                    String roleName = request.getParameter("ID_ROLE");
                    DBManager.updateUserbyNik(new User(name, surname, otchestvo, nik, password, brn, phone, email, roleName),nikOld);
                    result = "uspeh";
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                } catch (NikNameException ex) {
                    result="Пользователь с таким ником существует";
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("updateUser.jsp");
                    rd.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PasswordException ex) {
                    result="Пароль введен не верно";
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("updateUser.jsp");
                    rd.forward(request, response);
                }
            }



    protected void getUserByRole(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{

     RequestDispatcher rd;
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
    }
    protected void deleteUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
         String nik = request.getParameter("NIK");
          RequestDispatcher rd;
                try {
                    int numDelete = DBManager.deleteUser(nik);
                    request.setAttribute("result", numDelete);
                    rd = request.getRequestDispatcher("deleteUser.jsp");
                    rd.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
     protected void getFullList (HttpServletRequest request,
            HttpServletResponse response, String table) throws ServletException, IOException{
                RequestDispatcher rd;
          try {
                    request.setAttribute("result", DBManager.getFullList(table));
                    rd = request.getRequestDispatcher("getFullList.jsp");
                    rd.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            
            RequestDispatcher rd;
            String result, homepage;
        try
        {
            response.setContentType("text/html;charset=UTF-8");

            if (request.getRequestURI().equals("/ProjectShop-war/registration"))
            {
                registration(request,response);
                return;
            }

            if (request.getRequestURI().equals("/ProjectShop-war/selectByNik"))
            {
                selectByNik(request,response);
                return;
            }

            if (request.getRequestURI().equals("/ProjectShop-war/addProduct"))
            {
                addProduct(request,response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/updateUser"))
            {
                updateUser(request,response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getUsersByRole")) 
            {
                getUserByRole(request,response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/deleteUser")) {
              deleteUser(request,response);
              return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getFullRoleList")) {
 getFullList(request,response,"ROLE");
              return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getFullUserList")) {
                getFullList(request,response,"USER");
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
 protected void processRequest2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher rd;
            String result, homepage;
            response.setContentType("text/html;charset=UTF-8");
            String str = request.getRequestURI();
            if (request.getRequestURI().equals("/ProjectShop-war/login")) {

            }
            if (request.getRequestURI().equals("/ProjectShop-war/registration")) {
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
                    DBManager.addUser(name, surname, otchestvo, nik, password, born, phone, email, 1);
                    result = "uspeh";
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("index.jsp");
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
            if (request.getRequestURI().equals("/ProjectShop-war/selectByNik")) {
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
            if (request.getRequestURI().equals("/ProjectShop-war/updateUser")) {
                User usrOld= (User)session.getAttribute("usrOld");
                String nikOld= usrOld.getNik();
                String name = request.getParameter("NAME");
                String surname = request.getParameter("SURNAME");
                String otchestvo = request.getParameter("OTCHESTVO");
                String nik = request.getParameter("NIK");
                String password = request.getParameter("PASSWORD");
                String password2 = request.getParameter("PASSWORD2");
                try {
                    if (password.equals("")){
                        throw new PasswordException();
                    }
                    if (!password.equals(password2)) {
                        throw new PasswordException();
                    }
                    String brn = request.getParameter("BORN");
                    String phone = request.getParameter("PHONE");
                    String email = request.getParameter("EMAIL");
                    String roleName = request.getParameter("ID_ROLE");
                    DBManager.updateUserbyNik(new User(name, surname, otchestvo, nik, password, brn, phone, email, roleName),nikOld);
                    result = "uspeh";
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                } catch (NikNameException ex) {
                    result="������������ � ����� ����� ����������";
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("updateUser.jsp");
                    rd.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PasswordException ex) {
                    result="������ ������ �� �����";
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("updateUser.jsp");
                    rd.forward(request, response);
                }
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getUsersByRole")) {
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

            }
            if (request.getRequestURI().equals("/ProjectShop-war/deleteUser")) {
               String nik = request.getParameter("NIK");
                try {
                    int numDelete = DBManager.deleteUser(nik);
                    request.setAttribute("result", numDelete);
                    rd = request.getRequestDispatcher("deleteUser.jsp");
                    rd.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getFullRoleList")) {
                try {
                    request.setAttribute("result", DBManager.getFullListRole());
                    rd = request.getRequestDispatcher("getFullList.jsp");
                    rd.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getFullUserList")) {
                try {
                    request.setAttribute("result", DBManager.getFullListUsers());
                    rd = request.getRequestDispatcher("getFullList.jsp");
                    rd.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
