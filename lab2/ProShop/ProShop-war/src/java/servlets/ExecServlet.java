/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.rmi.RemoteException;

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

import helpers.JSPHelper;
import entityBeans.*;
import helpers.JSPHelper;
import javax.naming.InitialContext;
import java.sql.*;
import javax.sql.*;
import javax.ejb.*;
import helpers.EJBHelper;
import java.io.*;
import javax.ejb.*;
import menu.Menu;
import moreTools.CatalogNode;

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
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            java.sql.Date sqlDate = new java.sql.Date(bornDate.getTime());
            java.lang.Long idRole = new Long(Long.parseLong(role));

            UserBeanRemote usr = userHome.create(name, surname, otchestvo, nik, password, sqlDate, phone, email, idRole);

            Long ido = new Long(usr.getId());
            usr.sendMessage(new Long(usr.getId()), "\"USER\"", "Зарегестрирован пользователь " + nik, ido, 1);


            result = "Пользователь зарегестрирован";
        } catch (DuplicateKeyException ex) {
            result = ex.getMessage();
        } catch (CreateException ex) {
            result = "Ошибка при создании пользователя";
        } catch (RegistrationException ex) {
            result = ex.getMessage();
        }/* catch (NikNameException ex) {
        result = ex.getMessage();
        } */ catch (PasswordException ex) {
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
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "Пользователь не найден";
        String homepage;
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        homepage = session.getAttribute("homepage").toString();
        String nik = request.getParameter("NIK");
        try {
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            UserBeanRemote user = userHome.findByNik(nik);
            //request.setAttribute("DO", "upUser");
            session.setAttribute("userOld", user);
        } catch (ObjectNotFoundException ex) {
            result = "не существует пользователя с таким ником";
            request.setAttribute("result", result);
        } catch (FinderException ex) {
            result = "ник введен не верно";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "произошла ошибка";
            request.setAttribute("result", result);
        } finally {
            request.setAttribute("DO", "upUser");
            rd = request.getRequestDispatcher(homepage);
            rd.forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("homepage") != null) {
                session.removeAttribute("homepage");
            }
            session.removeAttribute("user");

        }

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    protected void addProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
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

            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            // productHome.setParamMessage(usr.getId());
            // productHome.setParamMessage(usr.getId());
            ProductBeanRemote pbr = productHome.create(description, name_catalog, name, price);

            Long idu = new Long(usr.getId());
            // Long idu = new Long(usr.getId());
            Long ido = new Long(pbr.getId());
            pbr.sendMessage(idu, "PRODUCT", "Добавлен продукт" + name, ido, 1);


            result = "Продукт добавлен";
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            page = "addProduct.jsp";

        } catch (FinderException ex) {
            result = "Имя каталога указанно не верно";
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            page = "addProduct.jsp";
        } catch (NamingException ex) {
            result = "Ошибка";
        } catch (CreateException ex) {
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            result = ex.getMessage();
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
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }



    }
protected void delProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException, RemoteException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        String result = "Продукт не удален";
        if (usr.getRoleId() >= 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        try {

          try{
            String value = request.getParameter("VALUE");
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            ProductBeanRemote pr = productHome.findByPrimaryKey(new Long (Long.parseLong(value)));

            productHome.remove(new Long (Long.parseLong(value)));
             result = "Удаление завершено";
            pr.sendMessage(new Long (Long.parseLong(value)), "PRODUCT", "Удален продукт " + pr.getName(), null, 2);

          } catch (NumberFormatException ex) {


            String value = request.getParameter("VALUE");
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            ProductBeanRemote pr = productHome.findByName(value);
            productHome.remove(new Long (pr.getId()));
             result = "Удаление завершено";
            pr.sendMessage(new Long (pr.getId()), "PRODUCT", "Удален продукт " + pr.getName(), null, 2);
          }



        } catch (ObjectNotFoundException ex) {
            result = "Продукта не существует";
        } catch (RemoveException ex) {
            result = "Ошибка при удалении";
        } catch (FinderException ex) {
            result = "Ошибка при поиске";
        } catch (NamingException ex) {
            result = "Произошла ошибка";
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("delProduct.jsp");
            rd.forward(request, response);
        }

    }
    protected void updateUser(HttpServletRequest request,
            HttpServletResponse response, String type) throws ServletException, ParseException, IOException, LoginException {
        String result = "порофиль не отредактирован";
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (type.equals("updateUser")) {
            if (usr.getRoleId() >= 2) {
                throw new LoginException("Вы не обладаете правами администратора");
            }
            usr = (UserBeanRemote) session.getAttribute("userOld");
        }
        //   UserBeanRemote usrOld = (UserBeanRemote) session.getAttribute("usrOld");
        // String nikOld = usrOld.getNik();
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
            String brn = request.getParameter("BORN");
            String phone = request.getParameter("PHONE");
            String email = request.getParameter("EMAIL");
            SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
            Date born = formt.parse(brn);
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            try {
                userHome.findByNikAndId(nik, new Long(usr.getId()));
                throw new UpdateException("Пользователь с таким ником уже существует");
            } catch (ObjectNotFoundException ex) {
            }
            if ("".equals(password) || password == null) {
            } else {
                if (!password.equals(password2)) {
                    throw new PasswordException();
                }
                usr.setPassword(password);
            }
            usr.setName(name);
            usr.setSurname(surname);
            usr.setOtchestvo(otchestvo);
            usr.setNik(nik);

            usr.setBorn(new java.sql.Date(born.getTime()));
            usr.setPhone(phone);
            usr.setEmail(email);



            if (type.equals("updateUser")) {
                long id_role = Long.parseLong(request.getParameter("ID_ROLE"));
                usr.setRoleId(new Long(id_role));
            }

            if (type.equals("updateProfil")) {
                request.setAttribute("DO", "upProf");
                session.setAttribute("user", usr);
            }
            if (type.equals("updateUser")) {
                request.setAttribute("DO", "upUser");
                session.setAttribute("usrOld", usr);
            }

            //  usr.sendMessage(null, "\"USER\"", "Отредактирован пользователь " + usr.getNik(), null);

            result = "профиль отредактирован";
        } catch (UpdateException ex) {
            result = ex.getMessage();
            if (type.equals("updateUser")) {
                request.setAttribute("DO", "upUser");
                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                request.setAttribute("DO", "upProf");
                //  session.setAttribute("user",usr);
            }
        } catch (NamingException ex) {
            result = "неизвестная ошибка";
            if (type.equals("updateUser")) {
                request.setAttribute("DO", "upUser");
                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                request.setAttribute("DO", "upProf");
                //  session.setAttribute("user",usr);
            }
        } catch (PasswordException ex) {
            result = ex.getMessage();
            if (type.equals("updateUser")) {
                request.setAttribute("DO", "upUser");
                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                request.setAttribute("DO", "upProf");
                //  session.setAttribute("user",usr);
            }
        } catch (ParseException ex) {
            result = "ошибка ввода даты рождения";
            if (type.equals("updateUser")) {
                request.setAttribute("DO", "upUser");
                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                request.setAttribute("DO", "upProf");
                //  session.setAttribute("user",usr);
            }
        } catch (Exception ex) {
            result = "ошибка";
            if (type.equals("updateUser")) {
                request.setAttribute("DO", "upUser");
                session.setAttribute("usrOld", usr);
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
            HttpServletResponse response) throws ServletException, IOException, LoginException {

        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(session);
        String rolename = request.getParameter("ROLE");
        String result = "Пользователи не найденны";
        try {
            long role_id = 0;
            if ("admin".equals(rolename)) {
                role_id = 1;
            } else {
                if ("user".equals(rolename)) {
                    role_id = 3;
                } else {
                    if ("manager".equals(rolename)) {
                        role_id = 2;
                    }
                }
            }
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            Collection list = userHome.findByRole(new Long(role_id));

            request.setAttribute("result", list);
        } catch (FinderException ex) {
            request.setAttribute("result", "Ошибка поиска");
        } catch (NamingException ex) {
            request.setAttribute("result", "Ошибка поиска");
        } finally {
            rd = request.getRequestDispatcher("getUsersByRole.jsp");
            rd.forward(request, response);
        }

    }

    protected void deleteUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        String result = "Удаление не выполнено";
        if (usr.getRoleId() >= 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        try {
            String nik = request.getParameter("NIK");
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            UserBeanRemote user = userHome.findByNik(nik);
            //   Long a =
            //  user.sendMessage(new Long(usr.getId()), "\"USER\"", "Удален пользователь " + nik, null);
            userHome.remove(new Long(user.getId()));
            usr.sendMessage(new Long(usr.getId()), "\"USER\"", "Удален пользователь " + nik, null, 2);

            result = "Удаление завершено";
        } catch (ObjectNotFoundException ex) {
            result = "Пользователя с таким ником не существует";
        } catch (RemoteException ex) {
            result = "Ошибка при удалении";
        } catch (RemoveException ex) {
            result = "Ошибка при удалении";
        } catch (FinderException ex) {
            result = "Ошибка при поиске";
        } catch (NamingException ex) {
            result = "Произошла ошибка попробуйте еще раз)";
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("deleteUser.jsp");
            rd.forward(request, response);
        }

    }

    protected void getFullList(HttpServletRequest request,
            HttpServletResponse response, String table) throws ServletException, LoginException, IOException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        String result = "Ошибка поиска";
        try {
            List list = null;
            if ("PRODUCT".equals(table)) {
                ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                list = productHome.findAll();
            } else {
                UserBeanRemote usr = JSPHelper.getUser2(request.getSession());

                if ("USER".equals(table)) {
                    UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
                    list = userHome.findAll();
                } else {
                    if ("ROLE".equals(table)) {
                        RoleBeanRemoteHome roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
                        list = roleHome.findAll();
                    }
                }
            }
            request.setAttribute("result", list);
        } catch (FinderException ex) {
            result = "Произошла ошибка поиска";
            request.setAttribute("result", result);
        } catch (RemoteException ex) {
            result = "Произошла ошибка поиска";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "Произошла ошибка поиска";
            request.setAttribute("result", result);
        } finally {
            rd = request.getRequestDispatcher("getFullList.jsp");
            rd.forward(request, response);
        }
    }

    protected void login(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        String result;
        try {

            String nik = request.getParameter("NIK");
            String password = request.getParameter("PASSWORD");
            if ("".equals(nik) || "".equals(password)) {
                throw new LoginException("Не указанны поля пользователя или пароля");
            }
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            UserBeanRemote usr = userHome.findByNikAndPassword(nik, password);

            session.setAttribute("user", usr);

            if (session.getAttribute("homepage") != null) {
                String homepage = session.getAttribute("homepage").toString();
                rd = request.getRequestDispatcher(homepage);
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        } catch (LoginException ex) {
            result = ex.getMessage();
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (FinderException ex) {
            result = "Не правильно указанно имя полльзователя";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (NamingException ex) {
            request.setAttribute("result", "произошла ошибка");
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void addComment(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(session);
        try {
            ProductBeanRemote product = (ProductBeanRemote) session.getAttribute("product");
            String text = request.getParameter("COMMENT");
            OpinionBeanRemoteHome opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
            OpinionBeanRemote obr = opinionHome.create(new Long(product.getId()), new Long(usr.getId()), text);
            obr.sendMessage(new Long(usr.getId()), "\"OPINION\"", "Добавлен коментарий о продукте " + product.getName(), new Long(obr.getIdOpinion()), 1);
            rd = request.getRequestDispatcher("getOpinion.jsp");
            request.setAttribute("result", product);
            rd.forward(request, response);
        } catch (CreateException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    protected void delComment(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {

        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(session);
        try {
            ProductBeanRemote product = (ProductBeanRemote) session.getAttribute("product");
            String id_op = request.getParameter("ID");
            OpinionBeanRemoteHome opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
            OpinionBeanRemote obr = null;
            opinionHome.remove(new Long(Long.parseLong(id_op)));

            usr.sendMessage(new Long(usr.getId()), "\"OPINION\"", "Удален комментарий", null, 2);

            rd = request.getRequestDispatcher("getOpinion.jsp");
            request.setAttribute("result", product);
            rd.forward(request, response);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (RemoveException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void getOpinionByProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;
        HttpSession session = request.getSession();
        String result = "Произошла ошибка перейдите на стартовую страницу";
        try {
            String id_pr = request.getParameter("ID");
            Long id_product = new Long(Long.parseLong(id_pr));
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            ProductBeanRemote prd = productHome.findByPrimaryKey(id_product);
            request.setAttribute("result", prd);
            rd = request.getRequestDispatcher("getOpinion.jsp");
            rd.forward(request, response);
        } catch (FinderException ex) {
            result = "Продукт не найден";
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    protected void addCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(session);
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "Добавление каталога прошло не успешно";
        try {
            String nameParent = request.getParameter("PARENTNAME");
            String name = request.getParameter("NAME");
            if ("".equals(name)) {
                throw new CatalogException("Название каталога не может быть пустым");
            }
            CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            CatalogBeanRemote ctg = catalogHome.create(nameParent, name);

            Long idu = new Long(usr.getId());
            Long ido = new Long(ctg.getId());
            ctg.sendMessage(idu, "\"CATALOG\"", "Добавлен каталог " + name, ido, 1);

            ctg.sendMessage(idu, "\"CATALOG\"", "Добавлен дочерний каталог название: " + name + "  id: " + ctg.getId(), new Long(ctg.getParentId()), 1);


            result = "Добавление каталога завершено";




        } catch (CreateException ex) {
            result = ex.getMessage();
        } catch (RemoteException ex) {
            result = "Ошибка при удалении";
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
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        RequestDispatcher rd;
        String result = "Произошла ошибка";
        try {
            if ("".equals(name)) {
                throw new CatalogException("Имя каталога не может быть пустым");
            }
            CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            CatalogBeanRemote ctg = catalogHome.findByName(name);
            Long idc = new Long(ctg.getId());
            Long idp = new Long(ctg.getParentId());
            Long idu = new Long(usr.getId());
            ctg.remove();
            CatalogBeanRemote parentCtg = catalogHome.findByPrimaryKey(idp);


            parentCtg.sendMessage(idu, "\"CATALOG\"", "Удален дочерний каталог название: " + name + "  id: " + idc, idp, 2);
            result = "Удаление завершено";
        } catch (FinderException ex) {
            result = ex.getMessage();
        } catch (RemoteException ex) {
            ex.printStackTrace();
            result = "Ошибка при удалении";
        } catch (RemoveException ex) {
            result = "Ошибка при удалении";
        } catch (CatalogException ex) {
            result = ex.getMessage();
        } catch (NamingException ex) {
            result = "Ошибка при удалении попробуйте еще раз";
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("del_catalog.jsp");
            rd.forward(request, response);
        }
    }

    protected void getProductsByCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        long i = 1;
        try {
            if (request.getParameter("ID") != null) {
                i = Long.parseLong(request.getParameter("ID"));
            }
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            List list = productHome.findByCatalog(new Long(i));

            request.setAttribute("result", list);
            rd = request.getRequestDispatcher("getCatalog.jsp");
            rd.forward(request, response);
        } catch (FinderException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void getFullCtgNew(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        String htmlOut = "";
      //  long i = 1;
        try {
            if (Menu.MenuHtml == null) {
                CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                List list = catalogHome.findAll();
                HashMap map = new HashMap();

                for (int j = 0; j < list.size(); j++) {
                    CatalogNode catalog = new CatalogNode((CatalogBeanRemote) list.get(j));
                    catalog.accept(map);
                }
                CatalogNode ctg = (CatalogNode) map.get(new Long(1));
                String html = "<li>" + ctg.ctg.getName() + "</li>" + "\r\n";
                for (int j = 0; j < ctg.children.size(); j++) {
                    CatalogNode ctg1 = (CatalogNode) ctg.children.get(j);
                    //     html = html+"<li><a href=\""+ "getProductsByCatalog?ID="+ctg1.ctg.getId() +"\">"+ctg1.ctg.getName()+"</a>"+"\r\n";
                    html = html + ctg1.getHtml();
                    //   html=html+"</li>"+"\r\n";
                }
                map = null;
                ctg = null;


                htmlOut = "<ul id=\"nav\"  class=\"dropdown dropdown-vertical\">\r\n"
                        + html
                        + "</ul>\r\n";
                Menu.MenuHtml = htmlOut;
            } else {
                htmlOut = Menu.MenuHtml;
            }
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            try {

                out.print(htmlOut);
            } finally {
                out.close();
            }

        } catch (FinderException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void addOrder(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        String result = "заказ не добавлен";
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        RequestDispatcher rd;
        String kol_vo = "";
        try {
            HttpSession session = request.getSession();
            kol_vo = request.getParameter("KOL");
            String status = request.getParameter("STATUS");
            String id_product = session.getAttribute("ID_PRODUCT").toString();
            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            OrderBeanRemote order = orderHome.create(new Long(usr.getId()), new Long(Long.parseLong(id_product)), new Boolean(Boolean.parseBoolean(status)), new Integer(Integer.parseInt(kol_vo)));
            order.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Добавлен заказ", new Long(order.getId()), 1);
            if ("false".equals(status)) {
                result = "Заказ добавлен в корзину";
            } else {
                result = "Заказ оформлен";
            }
        } catch (NumberFormatException ex) {
            result = "не правильный формат данных";
        } catch (CreateException ex) {
            result = "произошла ошибка при добавлении заказа";
        } catch (RemoteException ex) {
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
            HttpServletResponse response, boolean status) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(session);
        try {
            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            List list = orderHome.findByUserAndStatus(new Long(usr.getId()), status);
            request.setAttribute("result", list);
            rd = request.getRequestDispatcher("getBasket.jsp");
            rd.forward(request, response);
        } catch (FinderException ex) {
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void updateStatusOrders(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        String result2 = "Заказ не оформлен";
        try {
            String id_order = request.getParameter("id_order");
            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            OrderBeanRemote order = orderHome.findByPrimaryKey(new Long(Long.parseLong(id_order)));
            order.setStatus(true);
            result2 = "Заказ оформлен";
            order.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Заказ пользователя : " +order.getNameUser()+" на товар: " + order.getNameProduct() + " оформлен", new Long(Long.parseLong(id_order)), 3);


            request.setAttribute("result", orderHome.findByUserAndStatus(new Long(usr.getId()), false));

        } catch (FinderException ex) {
            result2 = "Произошла ошибка";
        } catch (RemoteException ex) {
            result2 = "Произошла ошибка";
        } catch (NamingException ex) {
            result2 = "Произошла ошибка";
        } finally {
            request.setAttribute("result2", result2);
            rd = request.getRequestDispatcher("getBasket.jsp");
            rd.forward(request, response);
        }

    }

    protected void selectProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "Продукт не найден вернитесь назад и введите верное название";
        String homepage;
        RequestDispatcher rd;

        String nameProduct = request.getParameter("nameProduct");
        try {
            if (nameProduct == null) {
                result = "введите название продукта";
                request.setAttribute("result", result);
            } else {

                // String nameProduct = request.getParameter("nameProduct");
                ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                ProductBeanRemote product = productHome.findByName(nameProduct);
                //  order.setStatus(true);
                request.getSession().setAttribute("product",product);
                request.setAttribute("result", "поиск завершен успешно");
            }
        } catch (FinderException ex) {
            result = "Продукт не найден вернитесь назад и введите верное название";
            request.setAttribute("result", result);
        } catch (RemoteException ex) {
            result = "Произошла ошибка";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "Произошла ошибка";
            request.setAttribute("result", result);
        } finally {

            rd = request.getRequestDispatcher("updateProduct.jsp");
            rd.forward(request, response);
        }

    }

    protected void updateProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "Продукт не обновлен";
        try {
            ProductBeanRemote product = (ProductBeanRemote) request.getSession().getAttribute("product");
         //   String g1 = product.getName();
            if (product == null) {
                result = "Продукт не обновлен";
                request.setAttribute("result", result);
            } else {
                String name = request.getParameter("NAME");

                if (name == null || "".equals(name)) {
                    result = "Название продукта введено не верно";
                    request.setAttribute("result", result);
                } else {
                    ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
           //         String g = product.getName();
                    if (!product.getName().equals(name)) {
                        try {
                            productHome.findByName(name);
                            result = "Такое имя уже используется";
                            request.setAttribute("result", result);
                            throw new Exception();
                        } catch (FinderException ex) {
                            product.setName(name);
                        }
                    }
                    String description = request.getParameter("DESCRIPTION");
                    product.setDescription(description);
                    String price = request.getParameter("PRICE");
                    double priceDouble = Double.parseDouble(price);
                    product.setPrice(new Double(priceDouble));
                    String nameCatalog = request.getParameter("NAME_CATALOG");

                    CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                    CatalogBeanRemote ctg = catalogHome.findByName(nameCatalog);
                    product.setIdCatalog(new Long(ctg.getId()));
                    result = "Продукт обновлен";
                    session.removeAttribute("product");
                    request.setAttribute("result", result);
                }


            }
        } catch (NumberFormatException ex) {
            result = "Не верно введена цена";
            request.setAttribute("result", result);
        } catch (FinderException ex) {
            result = "Не найден каталог";
            request.setAttribute("result", result);
        } catch (RemoteException ex) {
            result = "Произошла ошибка";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "Произошла ошибка";
            request.setAttribute("result", result);
        } catch (Exception ex) {
        } finally {

            rd = request.getRequestDispatcher("updateProduct.jsp");
            rd.forward(request, response);
        }

    }

    protected void deleteOrder(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        String result2 = "Заказ не удален";
        try {
            String id_order = request.getParameter("id_order");
            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            OrderBeanRemote order = orderHome.findByPrimaryKey(new Long(Long.parseLong(id_order)));
            orderHome.remove(new Long(order.getId()));
            usr.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Удален заказ", null, 2);

            request.setAttribute("result", orderHome.findByUserAndStatus(new Long(usr.getId()), false));
            result2 = "Заказ удален из корзины";
        } catch (RemoveException ex) {
            result2 = "Произошла ошибка";
        } catch (FinderException ex) {
            result2 = "Произошла ошибка";
        } catch (RemoteException ex) {
            result2 = "Произошла ошибка";
        } catch (NamingException ex) {
            result2 = "Произошла ошибка";
        } finally {
            request.setAttribute("result2", result2);
            rd = request.getRequestDispatcher("getBasket.jsp");
            rd.forward(request, response);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        String result, homepage, forwardAddress;
        String ee = request.getRequestURI();
        request.setCharacterEncoding("UTF-8");
        // response.setContentType("UTF-8");
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
             if (request.getRequestURI().equals("/ProShop-war/delProduct")) {
                delProduct(request, response);
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
            if (request.getRequestURI().equals("/ProShop-war/getFullUserList")) {
                getFullList(request, response, "IMAGE");
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
                //  getFullCtg(request, response);
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
            if (request.getRequestURI().equals("/ProShop-war/getProductsByCatalog")) {
                getProductsByCatalog(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getFull_catalogNew")) {
                getFullCtgNew(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/order")) {
                addOrder(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/basket")) {
                getOrders(request, response, false);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/updateStatusOrder")) {
                updateStatusOrders(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/getOrders")) {
                getOrders(request, response, true);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/deleteOrder")) {
                deleteOrder(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/logout")) {
                logout(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/addComment")) {
                addComment(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/selectProduct")) {
                selectProduct(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/updateProduct")) {
                updateProduct(request, response);
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
