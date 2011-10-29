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
import Other.JSPHelper;
import entityBeans.*;
import Other.JSPHelper;
import javax.naming.InitialContext;
import java.sql.*;
import javax.sql.*;
import javax.ejb.*;
import OtherBean.Helper;

/**
 *
 * @author Yra
 */
//@WebServlet(name = "ExecServlet", urlPatterns = {"/ExecServlet"})
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

        request.setAttribute("NAME", name);
        request.setAttribute("SURNAME", surname);
        request.setAttribute("OTCHESTVO", otchestvo);
        request.setAttribute("NIK", nik);
        request.setAttribute("PASSWORD", password);
        request.setAttribute("PASSWORD2", password2);
        request.setAttribute("BORN", born);
        request.setAttribute("EMAIL", email);
        request.setAttribute("PHONE", phone);
        request.setAttribute("ROLE", role);


        try {
            if (name.isEmpty()) {
                throw new RegistrationException("Поле имя не заполнено");
            }

            if (surname.isEmpty()) {
                throw new RegistrationException("Поле фамилия не заполнено");
            }

            if (otchestvo.isEmpty()) {
                throw new RegistrationException("Поле отчество не заполнено");
            }

            if (nik.isEmpty()) {
                throw new RegistrationException("Поле ник не заполнено");
            }

            if (DBManager.IsThereUser(nik)) {
                throw new RegistrationException("Пользователь с таким ником уже зарегестрирован");
            }


            if ((password.isEmpty()) || (!password.equals(password2))) {
                throw new PasswordException();
            }

            SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");

            Date bornDate;

            try {
                bornDate = formt.parse(born);
            } catch (Exception ex) {
                throw new RegistrationException("Неверный формат даты");
            }


            //DBManager.addUser(name, surname, otchestvo, nik, password, bornDate, phone, email, Integer.parseInt(role));
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) Helper.lookupHome("ejb/UserBean",  UserBeanRemoteHome.class);
            userHome.create(name, surname, otchestvo, nik, password, new java.sql.Date(bornDate.getTime()), phone, email, new Long(Long.parseLong(role)));
            result = "Пользователь зарегестрирован";

        } catch (RegistrationException ex) {
            result = ex.getMessage();
        }/* catch (NikNameException ex) {
            result = ex.getMessage();
        } */catch (PasswordException ex) {
            result = ex.getMessage();
        } catch (Exception ex) {
            result = "Неизвестная ошибка";
        }

        request.setAttribute("result", result);
        rd = request.getRequestDispatcher("registration.jsp");
        rd.forward(request, response);
    }

    protected void selectByNik(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        UserInterface usr = JSPHelper.getUser(request.getSession());
        if (2 == usr.getRoleId()) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result, homepage;
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
            homepage = session.getAttribute("homepage").toString();
            String nik = request.getParameter("NIK");
            try {
                User user = DBManager.findUserByNik(nik);
                request.setAttribute("DO", "upUser");
                request.setAttribute("result", user);
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
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        UserInterface usr = JSPHelper.getUser(request.getSession());
        if (2 == usr.getRoleId()) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String name = request.getParameter("NAME");
        String description = request.getParameter("DESCRIPTION");
        String priceS = request.getParameter("PRICE");
        String name_catalog = request.getParameter("NAME_CATALOG");
        String result = "Произошла ошибка при добавлении продукта";
        String page = "addProduct.jsp";
        double price = 0;
        int id_catalog = 0;
        try {
            if ("".equals(name)) {
                throw new ProductException("Название продукта не может быть пустым полем");
            }
            if ("".equals(priceS)) {
                throw new ProductException("Цена  продукта не может быть пустым полем");
            }
            if ("".equals(name_catalog)) {
                throw new ProductException("Название каталога продукта не может быть пустым полем");
            }

            price = Double.parseDouble(priceS);
            DBManager.addProduct(name, description, name_catalog, price);
            result = "Продукт добавлен";
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            page = "addProduct.jsp";

        } catch (NumberFormatException ex) {
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            result = "Ошибка в указании цены";
            page = "addProduct.jsp";
        } catch (ProductException ex) {
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            result = ex.getMessage();
            page = "addProduct.jsp";
        } catch (CatalogException ex) {
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            result = ex.getMessage();
            page = "addProduct.jsp";

        } catch (Exception ex) {

            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);

            result = "Ошибка";
            page = "addProduct.jsp";

            // Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }



    }

    protected void updateUser(HttpServletRequest request,
            HttpServletResponse response, String type) throws ServletException, ParseException, IOException, LoginException {
        String result = "порофиль не отредактирован";
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserInterface user = JSPHelper.getUser(request.getSession());
        if (type.equals("updateUser") && 2 == user.getRoleId()) {
            throw new LoginException("Вы не обладаете правами администратора");

        }

        // if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
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
                session.setAttribute("usrOld", usr);
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
        } catch (Exception ex) {
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

    }

    protected void getUserByRole(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
            String rolename = request.getParameter("ROLE");
            try {
                List list = DBManager.findUsersByRole(rolename);
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
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserInterface usr = JSPHelper.getUser(request.getSession());
        if (2 == usr.getRoleId()) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        try {
            String nik = request.getParameter("NIK");

            int numDelete = DBManager.deleteUser(nik);
            request.setAttribute("result", new Integer(numDelete));
            rd = request.getRequestDispatcher("deleteUser.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        //  if (session.getAttribute("user") != null && session.getAttribute("user") instanceof User) {
        try {

            String name_pr = request.getParameter("NAME");
            // int id_pr = Integer.parseInt(id_product);
            Product prd = DBManager.findOpinionByProductName(name_pr);
            request.setAttribute("result", prd);
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
        /*} else {
        rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
        }*/

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
            List list = DBManager.findOpinionByProduct(id_pr);
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
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserInterface usr = JSPHelper.getUser(session);
        if (2 == usr.getRoleId()) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "Добавление каталога прошло не успешно";
        try {
            String nameParent = request.getParameter("PARENTNAME");
            String name = request.getParameter("NAME");
            if ("".equals(name)) {
                throw new CatalogException("Название каталога не может быть пустым");
            }
            DBManager.addCatalog(nameParent, name);
            result = "Добавление каталога завершено";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("add_catalog.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            result = "Произошла ошибка при добавлении каталога";
        } catch (NamingException ex) {
            result = "Произошла ошибка при добавлении каталога";
        } catch (CatalogException ex) {
            result = ex.getMessage();
            //Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("add_catalog.jsp");
            rd.forward(request, response);
        }

    }

    protected void delCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        String name = request.getParameter("NAME");
        UserInterface usr = JSPHelper.getUser(request.getSession());
        if (2 == usr.getRoleId()) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        RequestDispatcher rd;
        String result = "Произошла ошибка";
        try {
            if ("".equals(name)) {
                throw new CatalogException("Имя каталога не может быть пустым");
            }
            int value = DBManager.delCatalog(name);
            if (value > 0) {
                result = "Удаление завершено";
            }
        } catch (CatalogException ex) {
            result = ex.getMessage();
        } catch (SQLException ex) {
            // Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            //  Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("del_catalog.jsp");
            rd.forward(request, response);
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

    protected void addOrder(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        String result = "заказ не добавлен";
        UserInterface usr = JSPHelper.getUser(request.getSession());
        RequestDispatcher rd;
        String kol_vo = "";
        try {
            HttpSession session = request.getSession();
            kol_vo = request.getParameter("KOL");
            String status = request.getParameter("STATUS");
            String id_product = session.getAttribute("ID_PRODUCT").toString();
            //   User usr = (User) session.getAttribute("user");
            DBManager.addOrder(id_product, usr.getNik(), kol_vo, status);
            if ("false".equals(status)) {
                result = "Заказ добавлен в корзину";
            } else {
                result = "Заказ оформлен";
            }
        } catch (CatalogException ex) {
            result = "произошла ошибка при добавлении заказа";
        } catch (NikNameException ex) {
            result = "произошла ошибка при добавлении заказа";
        } catch (SQLException ex) {
            result = "произошла ошибка при добавлении заказа";
        } catch (NamingException ex) {
            result = "произошла ошибка при добавлении заказа";
        } finally {
            request.setAttribute("kol_vo", kol_vo);
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("addOrder.jsp");
            rd.forward(request, response);
        }

    }

    protected void getOrders(HttpServletRequest request,
            HttpServletResponse response, String status) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserInterface usr = JSPHelper.getUser(session);
        try {
            request.setAttribute("result", DBManager.findOrderByUser(usr.getId(), status));
            rd = request.getRequestDispatcher("getBasket.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void updateStatusOrders(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserInterface usr = JSPHelper.getUser(session);
        try {
            String id_order = request.getParameter("id_order");

            request.setAttribute("result2", DBManager.updateOrderStatus(id_order));
            request.setAttribute("result", DBManager.findOrderByUser(usr.getId(), "false"));
            rd = request.getRequestDispatcher("getBasket.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void deleteOrder(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserInterface usr = JSPHelper.getUser(session);
        try {
            String id_order = request.getParameter("id_order");
            String result2;
            result2 = DBManager.deleteOrder(id_order);
            request.setAttribute("result", DBManager.findOrderByUser(usr.getId(), "false"));
            request.setAttribute("result2", result2);
            rd = request.getRequestDispatcher("getBasket.jsp");
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
        String result, homepage, forwardAddress;
        String ee =request.getRequestURI();
        try {
          

            if (request.getRequestURI().equals("/ProShop-war/login")) {
                login(request, response);
                return;
            }

            if (request.getRequestURI().equals("/ProShop-war/registration")) {
                registration(request, response);
                return;
            }

            if (request.getRequestURI().equals("/ProShop-war/selectByNik")) {
                selectByNik(request, response);
                return;
            }

            if (request.getRequestURI().equals("/ProShop-war/addProduct")) {
                addProduct(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/updateUser")) {
                updateUser(request, response, "updateUser");
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/updateProfil")) {
                updateUser(request, response, "updateProfil");
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getUsersByRole")) {
                getUserByRole(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/deleteUser")) {
                deleteUser(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getFullRoleList")) {
                getFullList(request, response, "ROLE");
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getFullProductList")) {
                getFullList(request, response, "PRODUCT");
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getFullUserList")) {
                getFullList(request, response, "USER");
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/addComment")) {
                addComment(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/delComment")) {
                delComment(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getOpinionByProduct")) {
                getOpinionByProduct(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/product")) {
                getOpinionByProduct(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/catalog")) {
                getFullCtg(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/add_catalog")) {
                addCatalog(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/del_catalog")) {
                delCatalog(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getFull_catalog")) {
                getFullCtg(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getChild_catalog")) {
                getChildCatalog(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/order")) {
                addOrder(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/basket")) {
                getOrders(request, response, "false");
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/updateStatusOrder")) {
                updateStatusOrders(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getOrders")) {
                getOrders(request, response, "true");
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/deleteOrder")) {
                deleteOrder(request, response);
                return;
            }



        } catch (ParseException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginException ex) {
            rd = request.getRequestDispatcher("login.jsp");
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
    //@Override
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
    //@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    //@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
