/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DBClasses.*;
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
import java.text.ParsePosition;
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
        String born = request.getParameter("BORN");
        String phone = request.getParameter("PHONE");
        String email = request.getParameter("EMAIL");
        String role = request.getParameter("ROLE");

        request.setAttribute("NAME",name);
        request.setAttribute("SURNAME",surname);
        request.setAttribute("OTCHESTVO",otchestvo);
        request.setAttribute("NIK",nik);
        request.setAttribute("PASSWORD",password);
        request.setAttribute("PASSWORD2",password2);
        request.setAttribute("BORN",born);
        request.setAttribute("EMAIL",email);
        request.setAttribute("PHONE",phone);
        request.setAttribute("ROLE",role);


        try {
            if (name.isEmpty())
            {
                throw new RegistrationException("Поле имя не заполнено");
            }

            if (surname.isEmpty())
            {
                throw new RegistrationException("Поле фамилия не заполнено");
            }

            if (otchestvo.isEmpty())
            {
                throw new RegistrationException("Поле отчество не заполнено");
            }

            if (nik.isEmpty())
            {
                throw new RegistrationException("Поле ник не заполнено");
            }

            if (DBManager.IsThereUser(nik))
            {
                throw new RegistrationException("Пользователь с таким ником уже зарегестрирован");
            }


            if ((password.isEmpty())||(!password.equals(password2))) {
                throw new PasswordException();
            }

            SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");

            Date bornDate;

            try
            {
                bornDate = formt.parse(born);
            }
            catch(Exception ex)
            {
                throw new RegistrationException("Неверный формат даты");
            }

            DBManager.addUser(name, surname, otchestvo, nik, password, bornDate, phone, email, Integer.parseInt(role));

            result = "Пользователь зарегестрирован";

        } catch (RegistrationException ex){
            result= ex.getMessage();
        } catch (NikNameException ex) {
            result= ex.getMessage();
        } catch (PasswordException ex) {
            result= ex.getMessage();
        }catch(Exception ex){
            result="Неизвестная ошибка";
        }

        request.setAttribute("result", result);
        rd = request.getRequestDispatcher("registration.jsp");
        rd.forward(request, response);
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
                request.setAttribute("DO", "upUser");
                request.setAttribute("result", usr);
                rd = request.getRequestDispatcher(homepage);
                rd.forward(request, response);
            } catch (SQLException ex) {
                result = "ник введен не верно";
                request.setAttribute("result", result);
                rd = request.getRequestDispatcher(homepage);
                rd.forward(request, response);
            } catch (NamingException ex) {
                result = "произошла ошибка";
                request.setAttribute("result", result);
                rd = request.getRequestDispatcher(homepage);
                rd.forward(request, response);
            }
        } else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void addProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException {

        RequestDispatcher rd;
        String name = request.getParameter("NAME");
        String description = request.getParameter("DESCRIPTION");
        String priceS = request.getParameter("PRICE");
        String id_catalogS = request.getParameter("ID_CATALOG");



        String result;
        String page;

        try {
            double price = Double.parseDouble(priceS);
            int id_catalog = Integer.parseInt(id_catalogS);

            DBManager.addProduct(name, description, id_catalog, price);

            result = "uspeh";
            page = "index.jsp";

        } catch (Exception ex) {

            request.setAttribute("NAME",name);
            request.setAttribute("DESCRIPTION",description);
            request.setAttribute("PRICE",priceS);
            request.setAttribute("ID_CATALOG",id_catalogS);

            result="Ошибка";
            page="addProduct.jsp";

            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("result", result);
        rd = request.getRequestDispatcher(page);
        rd.forward(request, response);

    }

    protected void updateUser(HttpServletRequest request,
            HttpServletResponse response, String type) throws ServletException, ParseException, IOException {
        String result = "порофиль не отредактирован";
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
            User usrOld = (User) session.getAttribute("usrOld");
            String nikOld = usrOld.getNik();
            String name = request.getParameter("NAME");
            try {
                if ("".equals(name)) {
                    throw new UpdateException("Имя введено не верно");
                }
                String surname = request.getParameter("SURNAME");
                if ("".equals(surname)) {
                    throw new UpdateException("Фамилия ввдена не верно");
                }
                String otchestvo = request.getParameter("OTCHESTVO");
                if ("".equals(otchestvo)) {
                    throw new UpdateException("Отчество введено не верно");
                }
                String nik = request.getParameter("NIK");
                if ("".equals(nik)) {
                    throw new UpdateException("Ник введен не верно");
                }
                String password = request.getParameter("PASSWORD");
                String password2 = request.getParameter("PASSWORD2");
                if ("".equals(password) || password == null) {
                    password = usrOld.getPassword();
                    password2 = usrOld.getPassword();
                } else {
                    password = request.getParameter("PASSWORD");
                    password2 = request.getParameter("PASSWORD2");
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
                User usr = new User(usrOld.getId(), name, surname, otchestvo, nik, password, brn, phone, email, roleName);
                usr.setLogin();
                DBManager.updateUserbyNik(usr, nikOld);
                if (type.equals("updateProfil")) {
                    request.setAttribute("DO", "upProf");
                    session.setAttribute("user", usr);
                }
                if (type.equals("updateUser")) {
                    request.setAttribute("DO", "upUser");
                    session.setAttribute("usrOld",usr);
                }
                result = "профиль отредактирован";
            } catch (NikNameException ex) {
                result = "Пользователь с таким ником существует";
                if (type.equals("updateProfil")) {
                    request.setAttribute("DO", "upProf");
                    //  session.setAttribute("user",usr);
                }
                if (type.equals("updateUser")) {
                    request.setAttribute("DO", "upUser");
                    session.setAttribute("usrOld", usrOld);
                }

            } catch (UpdateException ex) {
                result = ex.getMessage();
                if (type.equals("updateUser")) {
                    request.setAttribute("DO", "upUser");
                    session.setAttribute("usrOld", usrOld);
                }
                if (type.equals("updateProfil")) {
                    request.setAttribute("DO", "upProf");
                    //  session.setAttribute("user",usr);
                }
            } catch (SQLException ex) {
                result = "неизвестная ошибка";
                if (type.equals("updateUser")) {
                    request.setAttribute("DO", "upUser");
                    session.setAttribute("usrOld", usrOld);
                }
                if (type.equals("updateProfil")) {
                    request.setAttribute("DO", "upProf");
                    //  session.setAttribute("user",usr);
                }
            } catch (NamingException ex) {
                result = "неизвестная ошибка";
                if (type.equals("updateUser")) {
                    request.setAttribute("DO", "upUser");
                    session.setAttribute("usrOld", usrOld);
                }
                if (type.equals("updateProfil")) {
                    request.setAttribute("DO", "upProf");
                    //  session.setAttribute("user",usr);
                }
            } catch (PasswordException ex) {
                result = ex.getMessage();
                if (type.equals("updateUser")) {
                    request.setAttribute("DO", "upUser");
                    session.setAttribute("usrOld", usrOld);
                }
                if (type.equals("updateProfil")) {
                    request.setAttribute("DO", "upProf");
                    //  session.setAttribute("user",usr);
                }
            } catch (ParseException ex) {
                result = "ошибка ввода даты рождения";
                if (type.equals("updateUser")) {
                    request.setAttribute("DO", "upUser");
                    session.setAttribute("usrOld", usrOld);
                }
                if (type.equals("updateProfil")) {
                    request.setAttribute("DO", "upProf");
                    //  session.setAttribute("user",usr);
                }
            }catch (Exception ex) {
                result = "ошибка ввода даты рождения";
                if (type.equals("updateUser")) {
                    request.setAttribute("DO", "upUser");
                    session.setAttribute("usrOld", usrOld);
                }
                if (type.equals("updateProfil")) {
                    request.setAttribute("DO", "upProf");
                    //  session.setAttribute("user",usr);
                }
            }
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("updateUser.jsp");
            rd.forward(request, response);
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
        } else {
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
        } else {
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
        } else {
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
            request.setAttribute("result", "не правильно введен пользователь и пароль");
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("result", "произошла ошибка");
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (NamingException ex) {
            request.setAttribute("result", "произошла ошибка");
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void addComment(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
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
                rd = request.getRequestDispatcher("index.jsp");
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
        } else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void delComment(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

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
        } else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }

    }

    protected void getOpinionByProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

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
        } else {
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }

    }

    protected void getProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        // if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        try {
            String id_product = request.getParameter("id");
            int id_pr = Integer.parseInt(id_product);
            Product prd = (Product) DBManager.getById("PRODUCT", id_pr);
            List<Opinion> list = DBManager.findOpinionByProduct(id_pr);
            request.setAttribute("product", prd);
            request.setAttribute("opinion", list);
            rd = request.getRequestDispatcher("getProduct.jsp");
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
        /*  } else {
        rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
        }*/

    }

    protected void addCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException {
        try {
            String result;
            RequestDispatcher rd;
            String idParent = request.getParameter("IDPARENT");
            String name = request.getParameter("NAME");
            DBManager.addCatalog(idParent, name);
            result = "ok";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("add_catalog.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CatalogException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void delCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("NAME");
        RequestDispatcher rd;
        try {
            int value = DBManager.delCatalog(name);
            request.setAttribute("result", value);
            rd = request.getRequestDispatcher("del_catalog.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void getFullCtg(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        int i = 1;
        try {
            if (request.getParameter("pid") != null) {
                i = Integer.parseInt(request.getParameter("pid"));
            }
            request.setAttribute("result", DBManager.findCatalogBuPid(i));
            rd = request.getRequestDispatcher("getCatalog.jsp");
            rd.forward(request, response);
        } catch (CatalogException ex) {
            try {
                request.setAttribute("result", DBManager.findProductByCatalog(i));
                rd = request.getRequestDispatcher("getCatalog.jsp");
                rd.forward(request, response);
            } catch (SQLException ex1) {
                Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (NamingException ex1) {
                Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void getChildCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            String result;
            String homepage;
            RequestDispatcher rd;
            String name = request.getParameter("NAME");
            request.setAttribute("result", DBManager.findChildCatalog(name));
            rd = request.getRequestDispatcher("getChild_catalog.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                updateUser(request, response, "updateUser");
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/updateProfil")) {
                updateUser(request, response, "updateProfil");
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
            if (request.getRequestURI().equals("/ProjectShop-war/getFullProductList")) {
                getFullList(request, response, "PRODUCT");
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getFullUserList")) {
                getFullList(request, response, "USER");
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/addComment")) {
                addComment(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/delComment")) {
                delComment(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getOpinion")) {
                getOpinionByProduct(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/product")) {
                getProduct(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/catalog")) {
                getFullCtg(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/add_catalog")) {
                addCatalog(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/del_catalog")) {
                delCatalog(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getFull_catalog")) {
                getFullCtg(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProjectShop-war/getChild_catalog")) {
                getChildCatalog(request, response);
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
