/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import SessionBeans.XmlBeanRemote;
import SessionBeans.XmlBeanRemoteHome;
import java.rmi.RemoteException;
import javax.xml.stream.XMLStreamReader;
import org.jdom.Document;
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


        request.setAttribute("NAME", name);
        request.setAttribute("SURNAME", surname);
        request.setAttribute("OTCHESTVO", otchestvo);
        request.setAttribute("NIK", nik);
        request.setAttribute("PASSWORD", password);
        request.setAttribute("PASSWORD2", password2);
        request.setAttribute("BORN", born);
        request.setAttribute("EMAIL", email);
        request.setAttribute("PHONE", phone);



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
            RoleBeanRemoteHome roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
            java.sql.Date sqlDate = new java.sql.Date(bornDate.getTime());
            //java.lang.Long idRole = new Long(Long.parseLong(role));
            password = JSPHelper.MD5(password);
            UserBeanRemote usr = userHome.create(name, surname, otchestvo, nik, password, sqlDate, phone, email, new Long(3));
            RoleBeanRemote rbr = roleHome.findByPrimaryKey(new Long(3));

            Long ido = new Long(usr.getId());
            usr.sendMessage(new Long(usr.getId()), "\"USER\"", "Зарегестрирован пользователь: " + nik + ". Статус: " + rbr.getName() + ". Дата регистрации: " + usr.getRegistrationDate(), ido, 1);


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
            rd = request.getRequestDispatcher("updateUser.jsp?DO=" + "updateUser");
            // rd = request.getRequestDispatcher(homepage);
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
            if (price <= 0) {
                throw new NegativeNumberException("Введите положительную цену продукта");
            }
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);

            // productHome.setParamMessage(usr.getId());
            // productHome.setParamMessage(usr.getId());
            ProductBeanRemote pbr = productHome.create(description, name_catalog, name, price);
            CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            CatalogBeanRemote ctg = catalogHome.findByPrimaryKey(new Long(pbr.getIdCatalog()));

            Long idu = new Long(usr.getId());
            // Long idu = new Long(usr.getId());
            Long ido = new Long(pbr.getId());
            pbr.sendMessage(idu, "PRODUCT", "Добавлен продукт: " + name + ", в каталог: " + pbr.getNameCatalog(), ido, 1);
            ctg.sendMessage(new Long(usr.getId()), "\"CATALOG\"", " Каталог: " + pbr.getNameCatalog() + " изменен. Добавлен продукт: " + pbr.getName(), new Long(pbr.getIdCatalog()), 2);
            ctg.sendMessage(new Long(usr.getId()), "\"CATALOG\"", " Каталог: " + ctg.getParentName() + " изменен. Изменен дочерний каталог: " + pbr.getNameCatalog() + ". Добавлен продукт: " + pbr.getName(), new Long(ctg.getParentId()), 2);


            result = "Продукт добавлен";
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            page = "addProduct.jsp";

        } catch (NegativeNumberException ex) {
            result = "Введите положительную цену продукта";
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
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");


        }
        try {

            String value = request.getParameter("VALUE");
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            ProductBeanRemote pr = productHome.findByName(value);
            CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            CatalogBeanRemote ctg = catalogHome.findByPrimaryKey(new Long(pr.getIdCatalog()));

            result = "Удаление завершено";
            pr.sendMessage(new Long(usr.getId()), "PRODUCT", "Удален продукт: " + pr.getName() + " из каталога: " + pr.getNameCatalog(), null, 2);
            ctg.sendMessage(new Long(usr.getId()), "\"CATALOG\"", " Каталог: " + pr.getNameCatalog() + " изменен. Удален продукт: " + pr.getName(), new Long(pr.getIdCatalog()), 2);
            ctg.sendMessage(new Long(usr.getId()), "\"CATALOG\"", " Каталог: " + ctg.getParentName() + " изменен. Изменен дочерний каталог: " + pr.getNameCatalog() + ". Удален продукт: " + pr.getName(), new Long(ctg.getParentId()), 2);

            productHome.remove(new Long(pr.getId()));




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
        Long id = null;
        String msg = ". ";

        if (type.equals("updateUser")) {
            if (usr.getRoleId() >= 2) {
                throw new LoginException("Вы не обладаете правами администратора");
            }
            id = new Long(usr.getId());
            usr = (UserBeanRemote) session.getAttribute("userOld");
        } else {
            if (type.equals("updateProfil")) {
                id = new Long(usr.getId());
            }
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
                if (usr.getPassword().equals(password)) {
                    password = JSPHelper.MD5(password);
                    usr.setPassword(password);
                } else {
                    msg = msg + "Пароль был изменен с " + usr.getPassword() + " на " + password + ". ";
                    password = JSPHelper.MD5(password);
                    usr.setPassword(password);
                }
            }
            if ((usr.getName().equals(name))) {
                usr.setName(name);
            } else {
                msg = msg + "Имя пользователя было изменено с " + usr.getName() + " на " + name + ". ";
                usr.setName(name);

            }

            if (usr.getSurname().equals(surname)) {
                usr.setSurname(surname);
            } else {
                msg = msg + "Фамилия пользователя была изменена с " + usr.getSurname() + " на " + surname + ". ";
                usr.setSurname(surname);

            }

            if (usr.getOtchestvo().equals(otchestvo)) {
                usr.setOtchestvo(otchestvo);
            } else {
                msg = msg + "Отчество пользователя было изменено с " + usr.getOtchestvo() + " на " + otchestvo + ". ";
                usr.setOtchestvo(otchestvo);
            }

            if (usr.getNik().equals(nik)) {
                usr.setNik(nik);
            } else {
                msg = msg + "Ник пользователя был изменен с " + usr.getNik() + " на " + nik + ". ";
                usr.setNik(nik);
            }

            if (usr.getBorn().equals(born)) {
                usr.setBorn(new java.sql.Date(born.getTime()));
            } else {
                msg = msg + "Дата рождения изменена с " + usr.getBorn() + " на " + brn + ". ";
                usr.setBorn(new java.sql.Date(born.getTime()));
            }

            if ((usr.getPhone() != null) && (usr.getPhone().equals(phone))) {
                usr.setPhone(phone);
            } else {
                msg = msg + "Номер телефона был изменен с " + usr.getPhone() + " на " + phone + ". ";
                usr.setPhone(phone);
            }

            if ((usr.getEmail() != null) && (usr.getEmail().equals(email))) {
                usr.setEmail(email);
            } else {
                msg = msg + "Электронная почта была изменена с " + usr.getEmail() + " на " + email + ". ";
                usr.setEmail(email);
            }

            RoleBeanRemoteHome roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
            RoleBeanRemote rbr = roleHome.findByPrimaryKey(new Long(usr.getRoleId()));

            if (type.equals("updateUser")) {
                long id_role = Long.parseLong(request.getParameter("ID_ROLE"));
                if (usr.getRoleId() == id_role) {
                    usr.setRoleId(new Long(id_role));
                } else {
                    String oldRole = rbr.getName();
                    usr.setRoleId(new Long(id_role));
                    msg = msg + "Права доступа были изменены с " + oldRole + " на " + rbr.getName();
                }
            }



            if (type.equals("updateUser")) {
                result = "пользователь отредактирован";

                session.removeAttribute("userOld");
            }
            if (type.equals("updateProfil")) {
                result = "профиль отредактирован";

            }
            //  rd = request.getRequestDispatcher("updateUser.jsp?DO=" + type);
            usr.sendMessage(id, "\"USER\"", "Отредактирован пользователь :" + usr.getNik() + msg, new Long(usr.getId()), 2);

        } catch (UpdateException ex) {
            result = ex.getMessage();
            if (type.equals("updateUser")) {

                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } catch (NamingException ex) {
            result = "неизвестная ошибка";
            if (type.equals("updateUser")) {

                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } catch (PasswordException ex) {
            result = ex.getMessage();
            if (type.equals("updateUser")) {

                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } catch (ParseException ex) {
            result = "ошибка ввода даты рождения";
            if (type.equals("updateUser")) {

                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } catch (Exception ex) {
            result = "ошибка";
            if (type.equals("updateUser")) {

                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("updateUser.jsp?DO=" + type);
            rd.forward(request, response);
        }
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
                    if ("block".equals(rolename)) {
                        role_id = 4;
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
        String result = "Блокировка не выполнена";
        if (usr.getRoleId() >= 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        try {
            String nik = request.getParameter("NIK");
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            UserBeanRemote user = userHome.findByNik(nik);
            Long id = new Long(user.getId());
            //userHome.remove(id);
            user.setRoleId(new Long(4));
            usr.sendMessage(new Long(usr.getId()), "\"USER\"", "Пользователь заблокирован: " + nik, id, 2);

            result = "Блокировка завершена";
        } catch (ObjectNotFoundException ex) {
            result = "Пользователя с таким ником не существует";
        } catch (RemoteException ex) {
            result = "Ошибка при блокировке";
            // } catch (RemoveException ex) {
            //    result = "Ошибка при удалении";
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
                throw new LoginException("Не указано имя пользователя или пароль");
            }
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            password = JSPHelper.MD5(password);
            UserBeanRemote usr = userHome.findByNikAndPassword(nik, password);
            if (usr.getRoleId() == 4) {
                request.setAttribute("result", "Ваш профиль заблокирован");
                throw new LoginException("Ваш профиль заблокирован");
            }
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
            result = "Не правильно указанно имя пользователя или пароль";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (NamingException ex) {
            request.setAttribute("result", "Произошла ошибка");
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
            obr.sendMessage(new Long(usr.getId()), "\"OPINION\"", "Добавлен коментарий о продукте " + product.getName() + " от пользователя: " + obr.getUserName(), new Long(obr.getIdOpinion()), 1);
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

            OpinionBeanRemote obr = opinionHome.findByPrimaryKey(new Long(Long.parseLong(id_op)));
            String usrName = obr.getUserName();
            opinionHome.remove(new Long(Long.parseLong(id_op)));

            usr.sendMessage(new Long(usr.getId()), "\"OPINION\"", "Удален комментарий о продукте: " + product.getName() + " от пользователя: " + usrName + ". ", null, 2);

            rd = request.getRequestDispatcher("getOpinion.jsp");
            request.setAttribute("result", product);
            rd.forward(request, response);
        } catch (FinderException ex) {
            ex.printStackTrace();
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
            ctg.sendMessage(idu, "\"CATALOG\"", "Добавлен каталог: " + name, ido, 1);

            ctg.sendMessage(idu, "\"CATALOG\"", "В каталог: " + ctg.getParentName() + " добавлен дочерний каталог: " + name, new Long(ctg.getParentId()), 1);


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
            CatalogBeanRemote parentCtg = catalogHome.findByPrimaryKey(idp);

            parentCtg.sendMessage(idu, "\"CATALOG\"", "Из каталога: " + parentCtg.getName() + " удален дочерний каталог: " + name, idp, 2);
            ctg.sendMessage(idu, "\"CATALOG\"", "Удален каталог: " + ctg.getName(), null, 2);
            ctg.remove();
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
                String html = "<li id=\"initialCatalog\">" + ctg.ctg.getName() + "</li>" + "\r\n";
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
        rd = request.getRequestDispatcher("addOrder.jsp");
        try {
            HttpSession session = request.getSession();

            kol_vo = request.getParameter("VOL");
            if (Integer.parseInt(kol_vo) <= 0) {
                throw new NegativeNumberException("Введите положительное кол-во товара");
            }
            Object obj = session.getAttribute("ID_PRODUCT");
            String id_product = null;
            if (obj != null) {
                id_product = obj.toString();
            } else {
                id_product = request.getParameter("ID_PRODUCT");
                ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                session.setAttribute("product",productHome.findByPrimaryKey(new Long(Long.parseLong(id_product))));
                if (id_product == null) {
                    throw new FinderException();
                }
            }

            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            OrderBeanRemote order = orderHome.create(new Long(usr.getId()), new Long(Long.parseLong(id_product)), new Boolean(false), new Integer(Integer.parseInt(kol_vo)));
            order.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Добавлен заказ на товар: " + order.getNameProduct() + " пользователем: " + order.getNameUser(), new Long(order.getId()), 1);
            if(session.getAttribute("list")!=null){
                rd=request.getRequestDispatcher("getCatalog.jsp");
            }
            result = "Продукт добавлен в корзину";
        } catch (FinderException ex) {
            result = "Произошла ошибка";
        } catch (NegativeNumberException ex) {
            result = "Введите положительное кол-во товара";
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
            rd = request.getRequestDispatcher("getBasket.jsp?status=" + status);
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
            //   String id_order = request.getParameter("id_order");
            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            // OrderBeanRemote order = orderHome.findByPrimaryKey(new Long(Long.parseLong(id_order)));
            List list = orderHome.findByUserAndStatus(new Long(usr.getId()), false);
            //  order.setStatus(true);
            for (int i = 0; i < list.size(); i++) {
                OrderBeanRemote ord = (OrderBeanRemote) list.get(i);
                ord.setStatus(true);
                java.sql.Date f = new java.sql.Date((new java.util.Date()).getTime());
                ord.setOrderByDate(f);
                ord.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Заказ " + ord.getId() + " пользователя : " + ord.getNameUser() + " на товар: " + ord.getNameProduct() + " оформлен. Дата оформления заказа: " + ord.getOrderByDate(), new Long(ord.getId()), 3);
            }
            result2 = "Заказ оформлен";
            request.setAttribute("result", orderHome.findByUserAndStatus(new Long(usr.getId()), false));
        } catch (FinderException ex) {
            result2 = "Произошла ошибка";
        } catch (RemoteException ex) {
            result2 = "Произошла ошибка";
        } catch (NamingException ex) {
            result2 = "Произошла ошибка";
        } finally {
            request.setAttribute("result2", result2);
            rd = request.getRequestDispatcher("getBasket.jsp?status=false");
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
        RequestDispatcher rd = request.getRequestDispatcher("updateProduct.jsp?DO=select");
        ;
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
                request.getSession().setAttribute("product", product);
                request.setAttribute("result", "поиск завершен успешно");
                rd = request.getRequestDispatcher("updateProduct.jsp?DO=update");
            }
        } catch (FinderException ex) {
            result = "Продукт не найден";
            request.setAttribute("result", result);
        } catch (RemoteException ex) {
            result = "Произошла ошибка";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "Произошла ошибка";
            request.setAttribute("result", result);
        } finally {

            rd.forward(request, response);
        }

    }

    protected void updateProduct(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd = request.getRequestDispatcher("updateProduct.jsp?DO=update");

        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "Продукт не обновлен";
        String message = "";
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
                            String str = " Изменено название продукта с " + product.getName() + " на " + name;
                            product.setName(name);
                            message = message + str;
                        }
                    }
                    String description = request.getParameter("DESCRIPTION");
                    if (!product.getDescription().equals(description)) {
                        String str = ". Изменено описание продукта";
                        product.setDescription(description);
                        message = message + str;
                    }
                    String price = request.getParameter("PRICE");
                    double priceDouble = Double.parseDouble(price);
                    if (priceDouble <= 0) {
                        throw new NegativeNumberException("Введите положительную цену продукта");
                    }
                    if (product.getPrice() != priceDouble) {
                        String str = ". Изменена цена продукта с " + product.getPrice() + " на " + priceDouble;
                        product.setPrice(new Double(priceDouble));
                        message = message + str;
                    }
                    String nameCatalog = request.getParameter("NAME_CATALOG");

                    CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                    CatalogBeanRemote ctg = catalogHome.findByName(nameCatalog);
                    //   CatalogBeanRemote ctg1 = catalogHome.findByPrimaryKey(new Long(product.getIdCatalog()));
                    if (ctg.getId() != product.getIdCatalog()) {
                        CatalogBeanRemote ctg1 = catalogHome.findByPrimaryKey(new Long(product.getIdCatalog()));
                        String str = ". Продукт перемещени из каталога " + ctg1.getName() + " в каталог " + ctg.getName();
                        product.setIdCatalog(new Long(ctg.getId()));
                        message = message + str;
                    }
                    result = "Продукт обновлен";
                    product.sendMessage(new Long(usr.getId()), "\"PRODUCT\"", "Отредактирован продукт " + product.getName() + ". " + message, new Long(product.getId()), 2);

                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("updateProduct.jsp?DO=select");
                    session.removeAttribute("product");
                }


            }

        } catch (NumberFormatException ex) {
            result = "Не верно введена цена";
            request.setAttribute("result", result);


        } catch (NegativeNumberException ex) {
            result = "Введите положительную цену продукта";
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
            result = "Произошла ошибка";
            request.setAttribute("result", result);

        } finally {

            rd.forward(request, response);
        }

    }

    protected void selectCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "Каталог не найден вернитесь назад и введите верное название";
        String homepage;
        RequestDispatcher rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
        rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
        String nameCatalog = request.getParameter("nameCatalog");
        try {
            if (nameCatalog == null) {
                result = "введите название каталога";
                request.setAttribute("result", result);

            } else {

                // String nameProduct = request.getParameter("nameProduct");
                CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                CatalogBeanRemote catalog = catalogHome.findByName(nameCatalog);
                //  order.setStatus(true);
                request.getSession().setAttribute("catalog", catalog);
                request.setAttribute("result", "поиск завершен успешно");
                rd = request.getRequestDispatcher("updateCatalog.jsp?DO=update");
            }
        } catch (FinderException ex) {
            result = "Каталог не найден, введите верное название";
            request.setAttribute("result", result);
            //rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
        } catch (RemoteException ex) {
            result = "Произошла ошибка";
            request.setAttribute("result", result);
            ///rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
        } catch (NamingException ex) {
            result = "Произошла ошибка";
            request.setAttribute("result", result);
            //rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
        } finally {
            rd.forward(request, response);
        }

    }

    protected void updateCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd = request.getRequestDispatcher("updateCatalog.jsp?DO=update");
        ;
        String msg = ". ";
        String msg2 = "";
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "Каталог не обновлен";
        try {
            CatalogBeanRemote catalog = (CatalogBeanRemote) request.getSession().getAttribute("catalog");
            //   String g1 = product.getName();
            if (catalog == null) {
                result = "Каталог не обновлен";
                request.setAttribute("result", result);
            } else {
                String name = request.getParameter("NAME");
                if (name == null || "".equals(name)) {
                    result = "Название каталога введено не верно";
                    request.setAttribute("result", result);
                } else {
                    CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                    //         String g = product.getName();
                    if (!catalog.getName().equals(name)) {
                        try {
                            catalogHome.findByName(name);
                            result = "Такое имя уже используется";
                            request.setAttribute("result", result);
                            throw new Exception();
                        } catch (FinderException ex) {
                            msg = msg + "Имя каталога было сменено с " + catalog.getName() + " на " + name + ". ";
                            catalog.setName(name);
                            catalog.sendMessage(new Long(usr.getId()), "\"CATALOG\"", "Дочерний каталог: " + catalog.getName() + " каталога: " + catalog.getParentName() + " обновлен" + msg, new Long(catalog.getParentId()), 2);

                        }
                    }
                    String nameParent = request.getParameter("PARENT");
                    CatalogBeanRemote parent = null;
                    if (!("".equals(nameParent))) {
                        // String nameCatalog = request.getParameter("NAME_CATALOG");
                        if (!catalog.getParentName().equals(nameParent)) {
                            try {
                                parent = catalogHome.findByName(nameParent);
                            } catch (FinderException ex) {
                                throw new UpdateException("Родительский каталог не найден");
                            }
                            String oldParent = catalog.getParentName();
                            long oldPid = catalog.getParentId();
                            msg = msg + "Родительский каталог был сменен с " + oldParent + " на " + nameParent + ". ";
                            catalog.setParentId(parent.getId());
                            catalog.sendMessage(new Long(usr.getId()), "\"CATALOG\"", "Дочерний каталог: " + catalog.getName() + " каталога: " + oldParent + " перенесен в каталог " + catalog.getParentName(), new Long(oldPid), 2);
                            catalog.sendMessage(new Long(usr.getId()), "\"CATALOG\"", "Добавлен дочерний каталог: " + catalog.getName(), new Long(catalog.getParentId()), 2);

                        }
                    } else {
                        throw new UpdateException("Введите имя родительского каталога");
                    }
                    result = "Каталог обновлен";
                    //  parent.sendMessage(new Long(usr.getId()), "CATALOG", "Дочерний каталог: " + catalog.getName() + " каталога: " + parent.getName() + " обновлен" + msg, new Long(catalog.getParentId()), 2);
                    catalog.sendMessage(new Long(usr.getId()), "\"CATALOG\"", "Каталог: " + catalog.getName() + " обновлен" + msg, new Long(catalog.getId()), 2);

                    session.removeAttribute("catalog");
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
                }
            }
        } catch (UpdateException ex) {
            request.setAttribute("result", ex.getMessage());
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
            ex.printStackTrace();
        } finally {


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

            order.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Удален заказ на товар: " + order.getNameProduct() + " пользователем: " + order.getNameUser(), new Long(order.getId()), 2);


            order.remove();
            request.setAttribute("result", orderHome.findByUserAndStatus(new Long(usr.getId()), false));
            result2 = "Продукт удален из корзины";

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

    protected void blockUsers(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result2 = "Пользователи не заблокированны";
        try {
            String id_users[] = request.getParameterValues("id_user");
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            UserBeanRemote user = null;
            List list = new ArrayList();
            for (int i = 0; i < id_users.length; i++) {
                user = userHome.findByPrimaryKey(new Long(id_users[i]));
                user.setRoleId(new Long(4));
                list.add(user);
                //    user.sendMessage(new Long(usr.getId()), "\"\"", "Удален заказ", null, 2);
            }

            //   List list = (List) userHome.findByRole(new Long(4));
            //  String id_order = request.getParameter("id_order");
            //     OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            //   orderHome.remove(new Long(order.getId()));
            //  usr.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Удален заказ", null, 2);
            result2 = "Пользователи заблокированны";
            request.setAttribute("result", list);
            //   request.setAttribute("result2", result2);
            //   result2 = "Заказ удален из корзины";
        } catch (FinderException ex) {
            result2 = "Произошла ошибка";
        } catch (RemoteException ex) {
            result2 = "Произошла ошибка";
        } catch (NamingException ex) {
            result2 = "Произошла ошибка";
        } finally {
            request.setAttribute("result2", result2);
            rd = request.getRequestDispatcher("getUsersByRole.jsp");
            rd.forward(request, response);
        }


    }

    protected void image(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        // UserBeanRemote usr = JSPHelper.getUser2(session);
        try {
            long id = Long.parseLong(request.getParameter("ID"));
            ImageBeanRemoteHome imageHome = (ImageBeanRemoteHome) EJBHelper.lookupHome("ejb/ImageBean", ImageBeanRemoteHome.class);
            ImageBeanRemote ibr = imageHome.findByPrimaryKey(new Long(id));
            //request.setAttribute("result", list);
            rd = request.getRequestDispatcher("Image/" + ibr.getName());
            rd.forward(request, response);
        } catch (FinderException ex) {
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
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


            if (request.getRequestURI().equals("/ProShop-war/image")) {
                image(request, response);
                return;
            }
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
            if (request.getRequestURI().equals("/ProShop-war/blockUsers")) {
                blockUsers(request, response);
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


            if (request.getRequestURI().equals("/ProShop-war/updateOrderStatus")) {
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
            if (request.getRequestURI().equals("/ProShop-war/selectCatalog")) {
                selectCatalog(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/updateCatalog")) {
                updateCatalog(request, response);
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
