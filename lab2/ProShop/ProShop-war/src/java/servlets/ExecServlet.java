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
import java.net.URISyntaxException;
import java.net.URL;
import javax.ejb.*;
import javax.servlet.ServletContext;
import menu.Menu;
import moreTools.CatalogNode;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.*;

import java.util.Random;

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
            if (name == null || name.trim().isEmpty()) {
                throw new RegistrationException("Поле имя не заполнено");
            }

            if (surname == null || surname.trim().isEmpty()) {
                throw new RegistrationException("Поле фамилия не заполнено");
            }

            if (otchestvo == null || otchestvo.trim().isEmpty()) {
                throw new RegistrationException("Поле отчество не заполнено");
            }

            if (nik == null || nik.trim().isEmpty()) {
                throw new RegistrationException("Поле ник не заполнено");
            }

            if ((password == null) || (password.trim().isEmpty()) || (!password.equals(password2))) {
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
            usr.sendMessage(new Long(usr.getId()), "\"USER\"", "Зарегестрирован пользователь " + "\"" + nik + "\"" + ". Статус " + "\"" + rbr.getName() + "\"" + ". Дата регистрации " + "\"" + usr.getRegistrationDate() + "\"" + ". ", ido, 1);
            result = "<div class=\"success\"><p align=\"center\">Пользователь зарегестрирован</p></div>";
        } catch (DuplicateKeyException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
        } catch (CreateException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при создании пользователя</p></div>";
        } catch (RegistrationException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
        }/* catch (NikNameException ex) {
        result = ex.getMessage();
        } */ catch (PasswordException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
        } catch (Exception ex) {
            result = "<div class=\"warning\"><p align=\"center\">Неизвестная ошибка</p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Пользователь не найден</p></div>";
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
            result = "<div class=\"warning\"><p align=\"center\">не существует пользователя с таким ником</p></div>";
            request.setAttribute("result", result);
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">ник введен не верно</p></div>";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">произошла ошибка</p></div>";
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
        HttpServletRequest request2 = request;
        String name = null;
        int width = 0;
        int height = 0;
        // String nameProduct = null;

        String nameImage = null;
        String description = null;
        String nameImages[] = new String[10];
        int widths[] = new int[10];
        int heights[] = new int[10];
        String priceS = null;
        String name_catalog = null;

        //String name = request.getParameter("NAME");
        //String description = request.getParameter("DESCRIPTION");
        //String priceS = request.getParameter("PRICE");
        //String name_catalog = request.getParameter("NAME_CATALOG");
        String result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении продукта</p></div>";
        String page = "addProduct.jsp";

        Random random = new Random();
        double price = 0;
        int id_catalog = 0;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024);
            File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(tempDir);

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(1024 * 1024 * 10);

            moreTools.SerializbleImage im = null;
            int i = 0;
            List items = upload.parseRequest(request);
            List items2 = items;
            Iterator iter = items.iterator();
            try {
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        if (fieldName.equals("NAME")) {
                            name = item.getString("UTF-8");

                        } else if (fieldName.equals("DESCRIPTION")) {
                            description = item.getString("UTF-8");
                        } else if (fieldName.equals("PRICE")) {
                            priceS = item.getString("UTF-8");

                        } else if (fieldName.equals("NAME_CATALOG")) {
                            name_catalog = item.getString("UTF-8");
                        }
                    } else {
                        File uploadetFile = null;
                        int sub1 = getServletContext().getRealPath("/").indexOf("Jurada");
                        // String path = getServletContext().getRealPath("/").substring(0, sub1) + "Jurada/lab2/ProShop/ProShop-war/web/Image/" + item.getName();
                        int rnd = random.nextInt();
                        do {
                            String path = getServletContext().getRealPath("/").substring(0, sub1) + "Jurada/lab2/ProShop/ProShop-war/web/Image/" + rnd + item.getName();
                            uploadetFile = new File(path);
                        } while (uploadetFile.exists());

                        uploadetFile.createNewFile();
                        item.write(uploadetFile);
                        im = new moreTools.SerializbleImage(item.getInputStream());
                        try {
                            widths[i] = im.getWidth();
                            heights[i] = im.getHeight();
                            nameImages[i] = new Integer(rnd).toString() + item.getName();
                            i++;
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            throw new ProductException("Колличество картинок должно быть меньше 10");
                            //пойматть тут или переделать на аррайлист или чот такое

                        }
                        // ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                        //ProductBeanRemote pbr = productHome.findByName(nameProduct);
                    }
                }
            } catch (NullPointerException ex) {
            }
            if (name == null || name.trim().isEmpty()) {
                throw new ProductException("Введите название продукта");
            }
            if (priceS == null || priceS.trim().isEmpty()) {
                throw new ProductException("Цена  продукта не может быть пустым полем");
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

            ImageBeanRemoteHome imageHome = (ImageBeanRemoteHome) EJBHelper.lookupHome("ejb/ImageBean", ImageBeanRemoteHome.class);
            if (i > 0) {
                for (int j = 0; j < i; j++) {
                    ImageBeanRemote imageBean = imageHome.create(pbr.getId(), nameImages[i], widths[i], heights[i]);
                    imageBean.sendMessage(new Long(usr.getId()), "\"IMAGE\"", "Добавлено изображение " + "\"" + nameImage + "\"" + " к товару " + "\"" + pbr.getName() + "\"" + ". ", new Long(imageBean.getId_img()), 2);
                    pbr.sendMessage(new Long(usr.getId()), "\"PRODUCT\"", "Изменен продукт " + "\"" + pbr.getName() + "\"" + ". Добавлено изображение " + "\"" + nameImage + "\"" + ". ", new Long(pbr.getId()), 2);
                }
            }
            /*ImageBeanRemote imageBean = imageHome.create(pbr.getId(), nameImage, width, height);
            imageBean.sendMessage(new Long(usr.getId()), "\"IMAGE\"", "Добавлено изображение " + "\"" + nameImage + "\"" + " к товару " + "\"" + pbr.getName() + "\"" + ". ", new Long(imageBean.getId_img()), 2);
            pbr.sendMessage(new Long(usr.getId()), "\"PRODUCT\"", "Изменен продукт " + "\"" + pbr.getName() + "\"" + ". Добавлено изображение " + "\"" + nameImage + "\"" + ". ", new Long(pbr.getId()), 2);
             */
            Long idu = new Long(usr.getId());
            // Long idu = new Long(usr.getId());
            Long ido = new Long(pbr.getId());
            pbr.sendMessage(idu, "\"PRODUCT\"", "Добавлен продукт " + "\"" + name + "\"" + ", в каталог " + "\"" + pbr.getNameCatalog() + "\".", ido, 1);
            ctg.sendMessage(new Long(usr.getId()), "\"CATALOG\"", " Каталог " + "\"" + pbr.getNameCatalog() + "\"" + " изменен. Добавлен продукт " + "\"" + pbr.getName() + "\"" + ". ", new Long(pbr.getIdCatalog()), 2);
            ctg.sendMessage(new Long(usr.getId()), "\"CATALOG\"", " Каталог " + "\"" + ctg.getParentName() + "\"" + " изменен. Изменен дочерний каталог " + "\"" + pbr.getNameCatalog() + "\"" + ". Добавлен продукт " + "\"" + pbr.getName() + "\"" + ". ", new Long(ctg.getParentId()), 2);
            result = "<div class=\"success\"><p align=\"center\">Продукт добавлен</p></div>";
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            page = "addProduct.jsp";
        } catch (FileUploadException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка в загрузке изображения на сервер</p></div>";
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            page = "addProduct.jsp";
        } catch (NegativeNumberException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Введите положительную цену продукта</p></div>";
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            page = "addProduct.jsp";
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Имя каталога указанно не верно</p></div>";
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            page = "addProduct.jsp";
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка</p></div>";
        } catch (CreateException ex) {
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
            page = "addProduct.jsp";
        } catch (NumberFormatException ex) {
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            result = "<div class=\"warning\"><p align=\"center\">Ошибка в указании цены</p></div>";
            page = "addProduct.jsp";
        } catch (ProductException ex) {
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
            page = "addProduct.jsp";
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("NAME", name);
            request.setAttribute("DESCRIPTION", description);
            request.setAttribute("PRICE", priceS);
            request.setAttribute("NAME_CATALOG", name_catalog);
            result = "<div class=\"warning\"><p align=\"center\"> Ошибка </p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Продукт не удален</p></div>";
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        try {
            String value = request.getParameter("VALUE");
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            ProductBeanRemote pr = productHome.findByName(value);
            CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            CatalogBeanRemote ctg = catalogHome.findByPrimaryKey(new Long(pr.getIdCatalog()));
            result = "<div class=\"success\"><p align=\"center\">Удаление завершено</p></div>";
            pr.sendMessage(new Long(usr.getId()), "\"PRODUCT\"", "Удален продукт " + "\"" + pr.getName() + "\"" + " из каталога " + "\"" + pr.getNameCatalog() + "\"" + ". ", null, 2);
            ctg.sendMessage(new Long(usr.getId()), "\"CATALOG\"", " Каталог " + "\"" + pr.getNameCatalog() + "\"" + " изменен. Удален продукт " + "\"" + pr.getName() + "\"" + ". ", new Long(pr.getIdCatalog()), 2);
            ctg.sendMessage(new Long(usr.getId()), "\"CATALOG\"", " Каталог " + "\"" + ctg.getParentName() + "\"" + " изменен. Изменен дочерний каталог " + "\"" + pr.getNameCatalog() + "\"" + ". Удален продукт " + "\"" + pr.getName() + "\"" + ". ", new Long(ctg.getParentId()), 2);
            productHome.remove(new Long(pr.getId()));
        } catch (ObjectNotFoundException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Продукта не существует</p></div>";
        } catch (RemoveException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при удалении</p></div>";
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при поиске</p></div>";
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("delProduct.jsp");
            rd.forward(request, response);
        }
    }

    protected void updateUser(HttpServletRequest request,
            HttpServletResponse response, String type) throws ServletException, ParseException, IOException, LoginException {
        String result = "<div class=\"warning\"><p align=\"center\">порофиль не отредактирован</p></div>";
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
                    msg = msg + "Пароль был изменен. ";
                    password = JSPHelper.MD5(password);
                    usr.setPassword(password);
                }
            }
            if ((usr.getName().equals(name))) {
                usr.setName(name);
            } else {
                msg = msg + "Имя пользователя было изменено с " + "\"" + usr.getName() + "\"" + " на " + "\"" + name + "\"" + ". ";
                usr.setName(name);

            }
            if (usr.getSurname().equals(surname)) {
                usr.setSurname(surname);
            } else {
                msg = msg + "Фамилия пользователя была изменена с " + "\"" + usr.getSurname() + "\"" + " на " + "\"" + surname + "\"" + ". ";
                usr.setSurname(surname);

            }
            if (usr.getOtchestvo().equals(otchestvo)) {
                usr.setOtchestvo(otchestvo);
            } else {
                msg = msg + "Отчество пользователя было изменено с " + "\"" + usr.getOtchestvo() + "\"" + " на " + "\"" + otchestvo + "\"" + ". ";
                usr.setOtchestvo(otchestvo);
            }
            if (usr.getNik().equals(nik)) {
                usr.setNik(nik);
            } else {
                msg = msg + "Ник пользователя был изменен с " + "\"" + usr.getNik() + "\"" + " на " + "\"" + nik + "\"" + ". ";
                usr.setNik(nik);
            }
            if (usr.getBorn().equals(born)) {
                usr.setBorn(new java.sql.Date(born.getTime()));
            } else {
                msg = msg + "Дата рождения изменена с " + "\"" + usr.getBorn() + "\"" + " на " + "\"" + brn + "\"" + ". ";
                usr.setBorn(new java.sql.Date(born.getTime()));
            }
            if ((usr.getPhone() != null) && (usr.getPhone().equals(phone))) {
                usr.setPhone(phone);
            } else {
                msg = msg + "Номер телефона был изменен с " + "\"" + usr.getPhone() + "\"" + " на " + "\"" + phone + "\"" + ". ";
                usr.setPhone(phone);
            }
            if ((usr.getEmail() != null) && (usr.getEmail().equals(email))) {
                usr.setEmail(email);
            } else {
                msg = msg + "Электронная почта была изменена с " + "\"" + usr.getEmail() + "\"" + " на " + "\"" + email + "\"" + ". ";
                usr.setEmail(email);
            }
            if (type.equals(
                    "updateUser")) {
                RoleBeanRemoteHome roleHome = (RoleBeanRemoteHome) EJBHelper.lookupHome("ejb/RoleBean", RoleBeanRemoteHome.class);
                RoleBeanRemote rbr = roleHome.findByPrimaryKey(new Long(usr.getRoleId()));
                long id_role = Long.parseLong(request.getParameter("ID_ROLE"));
                if (usr.getRoleId() == id_role) {
                    usr.setRoleId(new Long(id_role));
                } else {
                    String oldRole = rbr.getName();
                    usr.setRoleId(new Long(id_role));
                    rbr = roleHome.findByPrimaryKey(new Long(usr.getRoleId()));
                    msg = msg + "Права доступа были изменены с " + "\"" + oldRole + "\"" + " на " + "\"" + rbr.getName() + "\"" + ". ";
                }
            }
            if (type.equals(
                    "updateUser")) {
                result = "<div class=\"success\"><p align=\"center\">пользователь отредактирован</p></div>";
                usr.sendMessage(id, "\"USER\"", "Отредактирован пользователь " + "\"" + usr.getNik() + "\"" + msg, new Long(usr.getId()), 2);

                session.removeAttribute("userOld");
            }
            if (type.equals(
                    "updateProfil")) {
                result = "<div class=\"success\"><p align=\"center\">профиль отредактирован</p></div>";
                usr.sendMessage(id, "\"USER\"", "Профиль отредактирован " + msg, new Long(usr.getId()), 2);
                session.setAttribute("user", usr);
            }
            //  rd = request.getRequestDispatcher("updateUser.jsp?DO=" + type);
        } catch (UpdateException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
            if (type.equals("updateUser")) {
                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Неизвестная ошибка</p></div>";
            if (type.equals("updateUser")) {
                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } catch (PasswordException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
            if (type.equals("updateUser")) {
                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } catch (ParseException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка ввода даты рождения</p></div>";
            if (type.equals("updateUser")) {
                session.setAttribute("usrOld", usr);
            }
            if (type.equals("updateProfil")) {
                //  session.setAttribute("user",usr);
            }
        } catch (Exception ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка</p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Пользователи не найдены</p></div>";
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
            request.setAttribute("result", "<div class=\"warning\"><p align=\"center\">Ошибка поиска</p></div>");
        } catch (NamingException ex) {
            request.setAttribute("result", "<div class=\"warning\"><p align=\"center\">Ошибка поиска</p></div>");
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
        String result = "<div class=\"warning\"><p align=\"center\">Блокировка не выполнена</p></div>";
        if (usr.getRoleId() >= 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        try {
            String nik = request.getParameter("NIK");
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome(
                    "ejb/UserBean", UserBeanRemoteHome.class);
            UserBeanRemote user = userHome.findByNik(nik);
            Long id = new Long(user.getId());
            //userHome.remove(id);
            user.setRoleId(
                    new Long(4));
            usr.sendMessage(
                    new Long(usr.getId()), "\"USER\"", "Пользователь заблокирован " + "\"" + nik + "\"" + ". ", id, 2);

            result = "<div class=\"success\"><p align=\"center\">Блокировка завершена</p></div>";
        } catch (ObjectNotFoundException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Пользователя с таким ником не существует</p></div>";
        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при блокировке</p></div>";
            // } catch (RemoveException ex) {
            //    result = "Ошибка при удалении";
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при поиске</p></div>";
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка попробуйте еще раз)</p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Ошибка поиска</p></div>";
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
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка поиска</p></div>";
            request.setAttribute("result", result);
        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка поиска</p></div>";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка поиска</p></div>";
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
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);


        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Не правильно указанно имя пользователя или пароль</p></div>";
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);


        } catch (NamingException ex) {
            request.setAttribute("result", "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>");
            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);

        }
    }

    protected void addComment(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(session);
        ProductBeanRemote product = (ProductBeanRemote) session.getAttribute("product");


        try {
            //    ProductBeanRemote product = (ProductBeanRemote) session.getAttribute("product");
            String text = request.getParameter("COMMENT");
            if ("".equals(text.trim()) || text == null || text.trim().isEmpty()) {
                throw new MyException("Введите текст комментария.");
            }
            OpinionBeanRemoteHome opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome("ejb/OpinionBean", OpinionBeanRemoteHome.class);
            OpinionBeanRemote obr = opinionHome.create(new Long(product.getId()), new Long(usr.getId()), text);
            obr.sendMessage(
                    new Long(usr.getId()), "\"OPINION\"", "Добавлен комментарий о продукте " + "\"" + product.getName() + "\"" + ". ", new Long(obr.getIdOpinion()), 1);
            rd = request.getRequestDispatcher("getOpinion.jsp");

            request.setAttribute("result", product);


            rd.forward(request, response);
        } catch (MyException ex) {
            rd = request.getRequestDispatcher("getOpinion.jsp");
            request.setAttribute("result", product);
            request.setAttribute("result2", "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>");
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
            OpinionBeanRemoteHome opinionHome = (OpinionBeanRemoteHome) EJBHelper.lookupHome(
                    "ejb/OpinionBean", OpinionBeanRemoteHome.class);

            OpinionBeanRemote obr = opinionHome.findByPrimaryKey(new Long(Long.parseLong(id_op)));
            String usrName = obr.getUserName();
            opinionHome.remove(
                    new Long(Long.parseLong(id_op)));

            usr.sendMessage(
                    new Long(usr.getId()), "\"OPINION\"", "Удален комментарий о продукте " + "\"" + product.getName() + "\"" + ". ", null, 2);

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
        String result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка перейдите на стартовую страницу</p></div>";


        try {
            String id_pr = request.getParameter("ID");
            Long id_product = new Long(Long.parseLong(id_pr));
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            ProductBeanRemote prd = productHome.findByPrimaryKey(id_product);

            request.setAttribute(
                    "result", prd);
            rd = request.getRequestDispatcher("getOpinion.jsp");

            rd.forward(request, response);
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Продукт не найден</p></div>";


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
        String result = "<div class=\"warning\"><p align=\"center\">Добавление каталога прошло не успешно</p></div>";
        try {
            String nameParent = request.getParameter("PARENTNAME");
            String name = request.getParameter("NAME");
            if ("".equals(name) || name == null || name.trim().isEmpty()) {
                throw new CatalogException("Название каталога не может быть пустым");
            }
            CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
            CatalogBeanRemote ctg = catalogHome.create(nameParent, name);
            Long idu = new Long(usr.getId());
            Long ido = new Long(ctg.getId());
            ctg.sendMessage(idu, "\"CATALOG\"", "Добавлен каталог " + "\"" + name + "\"" + ". ", ido, 1);
            ctg.sendMessage(idu, "\"CATALOG\"", "В каталог " + "\"" + ctg.getParentName() + "\"" + " добавлен дочерний каталог " + "\"" + name + "\"" + ". ", new Long(ctg.getParentId()), 1);


            result = "<div class=\"success\"><p align=\"center\">Добавление каталога завершено</p></div>";
        } catch (CreateException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";


        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении каталога</p></div>";


        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении каталога</p></div>";


        } catch (CatalogException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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

            parentCtg.sendMessage(idu,
                    "\"CATALOG\"", "Из каталога " + "\"" + parentCtg.getName() + "\"" + " удален дочерний каталог " + "\"" + name + "\"" + ". ", idp, 2);
            ctg.sendMessage(idu,
                    "\"CATALOG\"", "Удален каталог " + "\"" + ctg.getName() + "\"" + ". ", null, 2);
            ctg.remove();
            result = "<div class=\"success\"><p align=\"center\">Удаление завершено</p></div>";
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";


        } catch (RemoteException ex) {
            ex.printStackTrace();
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при удалении</p></div>";


        } catch (RemoveException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при удалении</p></div>";


        } catch (CatalogException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";


        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при удалении попробуйте еще раз</p></div>";


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

            request.setAttribute(
                    "result", list);
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
                for (int j = 0;
                        j < list.size();
                        j++) {
                    CatalogNode catalog = new CatalogNode((CatalogBeanRemote) list.get(j));
                    catalog.accept(map);
                }
                CatalogNode ctg = (CatalogNode) map.get(new Long(1));
                String html = "<li id=\"initialCatalog\">" + ctg.ctg.getName() + "</li>" + "\r\n";
                for (int j = 0;
                        j < ctg.children.size();
                        j++) {
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
        String result = "Заказ не добавлен";
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        RequestDispatcher rd;
        String kol_vo = "";
        //   rd = request.getRequestDispatcher("addOrder.jsp");
        HttpSession session = request.getSession();
        String homepage = (String) session.getAttribute("homepage");


        if (homepage == null) {
            homepage = "index.jsp";
        }
        rd = request.getRequestDispatcher(homepage);
        try {
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
                session.setAttribute("product", productHome.findByPrimaryKey(new Long(Long.parseLong(id_product))));
                if (id_product == null) {
                    throw new FinderException();
                }
            }
            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            try {
                orderHome.findByUserProductAndSatatus(new Long(usr.getId()), new Long(Long.parseLong(id_product)), false);
                throw new MyException("Вы уже добавили этот продукт в свою корзину.");
            } catch (FinderException ex) {
                OrderBeanRemote order = orderHome.create(new Long(usr.getId()), new Long(Long.parseLong(id_product)), new Boolean(false), new Integer(Integer.parseInt(kol_vo)));
                order.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Добавлен заказ на товар " + "\"" + order.getNameProduct() + "\"" + ". ", new Long(order.getId()), 1);
                //  if (session.getAttribute("homepage") = null) {
                //     rd = request.getRequestDispatcher("getCatalog.jsp");
                //  }
            }
            result = "<div class=\"success\"><p align=\"center\">Продукт добавлен в корзину</p></div>";
        } catch (MyException ex) {
            result = "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>";


        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";


        } catch (NegativeNumberException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Введите положительное кол-во товара</p></div>";


        } catch (NumberFormatException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Не правильный формат данных</p></div>";


        } catch (CreateException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении заказа</p></div>";


        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении заказа</p></div>";


        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении заказа</p></div>";


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
        String result2 = "<div class=\"warning\"><p align=\"center\">Заказ не оформлен</p></div>";
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
                ord.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Заказ " + "\"" + ord.getId() + "\"" + " на товар " + "\"" + ord.getNameProduct() + "\"" + " оформлен. Дата оформления заказа " + "\"" + ord.getOrderByDate() + "\"" + ". ", new Long(ord.getId()), 3);
            }
            result2 = "<div class=\"success\"><p align=\"center\">Заказ оформлен</p></div>";
            request.setAttribute("result", orderHome.findByUserAndStatus(new Long(usr.getId()), false));
        } catch (FinderException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } catch (RemoteException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } catch (NamingException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Продукт не найден</p></div>";
        String homepage;
        RequestDispatcher rd = request.getRequestDispatcher("updateProduct.jsp?DO=select");
        String nameProduct = request.getParameter("nameProduct");
        try {
            if (nameProduct == null) {
                result = "<div class=\"warning\"><p align=\"center\">Введите название продукта</p></div>";
                request.setAttribute("result", result);
            } else {
                // String nameProduct = request.getParameter("nameProduct");
                ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                ProductBeanRemote product = productHome.findByName(nameProduct);
                //  order.setStatus(true);
                request.getSession().setAttribute("product", product);
                request.setAttribute(
                        "result", "<div class=\"success\"><p align=\"center\">Поиск завершен успешно</p></div>");
                rd = request.getRequestDispatcher("updateProduct.jsp?DO=update");
            }

        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Продукт не найден</p></div>";
            request.setAttribute("result", result);
        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Продукт не обновлен</p></div>";
        String message = "";
        try {
            ProductBeanRemote product = (ProductBeanRemote) request.getSession().getAttribute("product");
            //   String g1 = product.getName();
            if (product == null) {
                request.setAttribute("result", result);
            } else {
                String name = request.getParameter("NAME");
                if (name == null || "".equals(name) || name.trim().isEmpty()) {
                    result = "<div class=\"warning\"><p align=\"center\">Название продукта введено не верно</p></div>";
                    request.setAttribute("result", result);
                } else {
                    ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                    //         String g = product.getName();
                    if (!product.getName().equals(name)) {
                        try {
                            productHome.findByName(name);
                            result = "<div class=\"warning\"><p align=\"center\">Такое имя уже используется</p></div>";
                            request.setAttribute("result", result);
                            throw new Exception();
                        } catch (FinderException ex) {
                            String str = " Изменено название продукта с " + "\"" + product.getName() + "\"" + " на " + "\"" + name + "\"" + ". ";
                            product.setName(name);
                            message = message + str;
                        }
                    }
                    String description = request.getParameter("DESCRIPTION");
                    if (description == null) {
                        description = "";
                    }
                    if (!description.equals(product.getDescription())) {
                        String str = "Изменено описание продукта. ";
                        product.setDescription(description);
                        message = message + str;
                    }
                    String price = request.getParameter("PRICE");
                    double priceDouble = Double.parseDouble(price);
                    if (priceDouble <= 0) {
                        throw new NegativeNumberException("Введите положительную цену продукта");
                    }
                    if (product.getPrice() != priceDouble) {
                        String str = "Изменена цена продукта с " + "\"" + product.getPrice() + "\"" + " на " + "\"" + priceDouble + "\"" + ". ";
                        product.setPrice(new Double(priceDouble));
                        message = message + str;
                    }
                    String nameCatalog = request.getParameter("NAME_CATALOG");
                    CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                    CatalogBeanRemote ctg = catalogHome.findByName(nameCatalog);
                    //   CatalogBeanRemote ctg1 = catalogHome.findByPrimaryKey(new Long(product.getIdCatalog()));
                    if (ctg.getId()
                            != product.getIdCatalog()) {
                        CatalogBeanRemote ctg1 = catalogHome.findByPrimaryKey(new Long(product.getIdCatalog()));
                        String str = "Продукт перемещени из каталога " + "\"" + ctg1.getName() + "\"" + " в каталог " + "\"" + ctg.getName() + "\"" + ". ";
                        product.setIdCatalog(new Long(ctg.getId()));
                        message = message + str;
                    }
                    result = "<div class=\"success\"><p align=\"center\">Продукт обновлен</p></div>";

                    product.sendMessage(new Long(usr.getId()), "\"PRODUCT\"", "Отредактирован продукт " + "\"" + product.getName() + "\"" + ". " + message, new Long(product.getId()), 2);

                    request.setAttribute(
                            "result", result);
                    rd = request.getRequestDispatcher("updateProduct.jsp?DO=select");

                    session.removeAttribute("product");
                }

            }
        } catch (NumberFormatException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Не верно введена цена</p></div>";
            request.setAttribute("result", result);
        } catch (NegativeNumberException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Введите положительную цену продукта</p></div>";
            request.setAttribute("result", result);
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Не найден каталог</p></div>";
            request.setAttribute("result", result);
        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
        } catch (Exception ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Каталог не найден</p></div>";
        String homepage;
        RequestDispatcher rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
        rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
        String nameCatalog = request.getParameter("nameCatalog");
        try {
            if (nameCatalog == null) {
                result = "<div class=\"warning\"><p align=\"center\">Введите название каталога</p></div>";
                request.setAttribute("result", result);
            } else {

                // String nameProduct = request.getParameter("nameProduct");
                CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                CatalogBeanRemote catalog = catalogHome.findByName(nameCatalog);
                //  order.setStatus(true);
                request.getSession().setAttribute("catalog", catalog);
                request.setAttribute(
                        "result", "<div class=\"success\"><p align=\"center\">Поиск завершен успешно</p></div>");
                rd = request.getRequestDispatcher("updateCatalog.jsp?DO=update");
            }
        } catch (FinderException ex) {

            request.setAttribute("result", result);
            //rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");


        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
            ///rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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
        String result = "<div class=\"warning\"><p align=\"center\">Каталог не обновлен</p></div>";
        try {
            CatalogBeanRemote catalog = (CatalogBeanRemote) request.getSession().getAttribute("catalog");
            //   String g1 = product.getName();
            if (catalog == null) {
                request.setAttribute("result", result);
            } else {
                String name = request.getParameter("NAME");
                if (name == null || "".equals(name) || name.trim().isEmpty()) {
                    result = "<div class=\"warning\"><p align=\"center\">Название каталога введено не верно</p></div>";
                    request.setAttribute("result", result);
                } else {
                    CatalogBeanRemoteHome catalogHome = (CatalogBeanRemoteHome) EJBHelper.lookupHome("ejb/CatalogBean", CatalogBeanRemoteHome.class);
                    //         String g = product.getName();
                    if (!catalog.getName().equals(name)) {
                        try {
                            catalogHome.findByName(name);
                            result = "<div class=\"warning\"><p align=\"center\">Такое имя уже используется</p></div>";
                            request.setAttribute("result", result);
                            throw new MyException("");
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
                    result = "<div class=\"success\"><p align=\"center\">Каталог обновлен</p></div>";
                    //  parent.sendMessage(new Long(usr.getId()), "CATALOG", "Дочерний каталог: " + catalog.getName() + " каталога: " + parent.getName() + " обновлен" + msg, new Long(catalog.getParentId()), 2);
                    catalog.sendMessage(new Long(usr.getId()), "\"CATALOG\"", "Каталог: " + catalog.getName() + " обновлен" + msg, new Long(catalog.getId()), 2);
                    session.removeAttribute("catalog");
                    request.setAttribute("result", result);
                    rd = request.getRequestDispatcher("updateCatalog.jsp?DO=select");
                }
            }
        } catch (UpdateException ex) {
            request.setAttribute("result", "<div class=\"warning\"><p align=\"center\">" + ex.getMessage() + "</p></div>");
        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
        } catch (MyException ex) {
        } catch (Exception ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
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
        String result2 = "<div class=\"warning\"><p align=\"center\">Продукт не удален</p></div>";
        try {
            String id_order = request.getParameter("id_order");
            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            OrderBeanRemote order = orderHome.findByPrimaryKey(new Long(Long.parseLong(id_order)));
            order.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Удален заказ на товар " + "\"" + order.getNameProduct() + "\"" + ". ", new Long(order.getId()), 2);
            order.remove();
            request.setAttribute(
                    "result", orderHome.findByUserAndStatus(new Long(usr.getId()), false));
            result2 = "<div class=\"success\"><p align=\"center\">Продукт удален из корзины</p></div>";
        } catch (RemoveException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } catch (FinderException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } catch (RemoteException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } catch (NamingException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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
        String result2 = "<div class=\"warning\"><p align=\"center\">Пользователи не заблокированны</p></div>";
        try {
            String id_users[] = request.getParameterValues("id_user");
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            UserBeanRemote user = null;
            List list = new ArrayList();
            for (int i = 0; i < id_users.length; i++) {
                user = userHome.findByPrimaryKey(new Long(id_users[i]));
                user.setRoleId(new Long(4));
                list.add(user);
                user.sendMessage(new Long(usr.getId()), "\"USER\"", "Пользователь заблокирован " + "\"" + user.getNik() + "\"" + ". ", new Long(id_users[i]), 2);
                // user.sendMessage(new Long(usr.getId()), "\"\"", "Удален заказ", null, 2);
            }
            //   List list = (List) userHome.findByRole(new Long(4));
            //  String id_order = request.getParameter("id_order");
            //     OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);
            //   orderHome.remove(new Long(order.getId()));
            //  usr.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Удален заказ", null, 2);
            result2 = "<div class=\"success\"><p align=\"center\">Пользователи заблокированны</p></div>";
            request.setAttribute("result", list);
            //   request.setAttribute("result2", result2);
            //   result2 = "Заказ удален из корзины";
        } catch (FinderException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } catch (RemoteException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } catch (NamingException ex) {
            result2 = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
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
        File img = null;
        // UserBeanRemote usr = JSPHelper.getUser2(session);
        try {
            long id = Long.parseLong(request.getParameter("ID"));
            ImageBeanRemoteHome imageHome = (ImageBeanRemoteHome) EJBHelper.lookupHome("ejb/ImageBean", ImageBeanRemoteHome.class);
            ImageBeanRemote ibr = imageHome.findByPrimaryKey(new Long(id));
            int sub1 = getServletContext().getRealPath("/").indexOf("Jurada");
            String path = getServletContext().getRealPath("/").substring(0, sub1) + "Jurada/lab2/ProShop/ProShop-war/web/Image/" + ibr.getName();
            img = new File(path);
            if (img.exists()) {
                rd = request.getRequestDispatcher("Image/" + ibr.getName());
            } else {
                rd = request.getRequestDispatcher("Image/" + "noimg.jpg");
            }
            //
            //request.setAttribute("result", list);
            //  rd = request.getRequestDispatcher("Image/" + ibr.getName());
            rd.forward(request, response);
        } catch (FinderException ex) {
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(ExecServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void addImage(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        String result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении изображения</p></div>";
        String name = null;
        int width = 0;
        int height = 0;
        String page = "add_image.jsp";
        String nameProduct = null;
        Random random = new Random();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024);
            File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(tempDir);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024 * 1024 * 10);
            moreTools.SerializbleImage im = null;
            int i = 0;
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            Iterator iter2 = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    if (fieldName.equals("NAMEPRODUCT")) {
                        nameProduct = item.getString("UTF-8");
                    }
                    if ("".equals(nameProduct)) {
                        throw new ProductException("Введите название продукта");
                    }
                }
            }
            while (iter2.hasNext()) {
                FileItem item = (FileItem) iter2.next();
                if (!item.isFormField()) {
                    File uploadetFile = null;
                    int sub1 = getServletContext().getRealPath("/").indexOf("Jurada");
                    // String path = getServletContext().getRealPath("/").substring(0, sub1) + "Jurada/lab2/ProShop/ProShop-war/web/Image/" + item.getName();
                    int rnd = random.nextInt();
                    // uploadetFile = new File(path);
                    do {
                        String path = getServletContext().getRealPath("/").substring(0, sub1) + "Jurada/lab2/ProShop/ProShop-war/web/Image/" + rnd + item.getName();
                        uploadetFile = new File(path);
                    } while (uploadetFile.exists());
                    uploadetFile.createNewFile();
                    // uploadetFile.deleteOnExit();
                    item.write(uploadetFile);
                    im = new moreTools.SerializbleImage(item.getInputStream());
                    width = im.getWidth();
                    height = im.getHeight();
                    name = new Integer(rnd).toString() + item.getName();
                    i++;
                    ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
                    ProductBeanRemote pbr = productHome.findByName(nameProduct);
                    ImageBeanRemoteHome imageHome = (ImageBeanRemoteHome) EJBHelper.lookupHome("ejb/ImageBean", ImageBeanRemoteHome.class);
                    ImageBeanRemote imageBean = imageHome.create(pbr.getId(), name, width, height);
                    imageBean.sendMessage(new Long(usr.getId()), "\"IMAGE\"", "Добавлено изображение " + "\"" + item.getName() + "\"" + " к товару " + "\"" + pbr.getName() + "\"" + ". ", new Long(imageBean.getId_img()), 2);
                    pbr.sendMessage(new Long(usr.getId()), "\"PRODUCT\"", "Изменен продукт " + "\"" + pbr.getName() + "\"" + ". Добавлено изображение " + "\"" + item.getName() + "\"" + ". ", new Long(pbr.getId()), 2);
                }
            }
            if (i > 1) {
                result = "<div class=\"success\"><p align=\"center\">Изображения добавлены</p></div>";
            } else {
                result = "<div class=\"success\"><p align=\"center\">Изображение добавлено</p></div>";
            }
            page = "add_image.jsp";
        } catch (ProductException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Введите название продукта</p></div>";
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Продукт не найден</p></div>";
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении изображения</p></div>";
        } catch (CreateException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении изображения</p></div>";
        } catch (NumberFormatException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка при добавлении изображения</p></div>";
        } catch (Throwable ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }
    }

    protected void delImage(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, LoginException, RemoteException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        String result = "<div class=\"warning\"><p align=\"center\">Изображение не удалено</p></div>";
        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        try {
            String value = request.getParameter("VALUE");
            ImageBeanRemoteHome imageHome = (ImageBeanRemoteHome) EJBHelper.lookupHome("ejb/ImageBean", ImageBeanRemoteHome.class);
            ImageBeanRemote imageBean = imageHome.findByName(value);
            String name = imageBean.getName();
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            ProductBeanRemote pr = productHome.findByPrimaryKey(new Long(imageBean.getId_product()));
            pr.sendMessage(new Long(usr.getId()), "\"PRODUCT\"", "Изменен продукт " + "\"" + pr.getName() + "\"" + ". Удалено изображение " + "\"" + name + "\"" + ". ", new Long(pr.getId()), 2);
            imageBean.sendMessage(new Long(usr.getId()), "\"IMAGE\"", "Удалено изображение " + "\"" + name + "\"" + ". ", null, 2);
            imageHome.remove(new Long(imageBean.getId_img()));
            int sub1 = getServletContext().getRealPath("/").indexOf("Jurada");
            String path = getServletContext().getRealPath("/").substring(0, sub1) + "Jurada/lab2/ProShop/ProShop-war/web/Image/" + value;
            File uploadetFile = new File(path);
            uploadetFile.delete();
            result = "<div class=\"success\"><p align=\"center\">Удаление завершено</p></div>";
        } catch (ObjectNotFoundException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Продукта не существует</p></div>";
        } catch (RemoveException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при удалении</p></div>";
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Ошибка при поиске</p></div>";
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } finally {
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("del_image.jsp");
            rd.forward(request, response);
        }
    }

    protected void findProductsBySubstName(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        String result = "";
        rd = request.getRequestDispatcher("/errorPage.jsp");
        try {
            String subtr = request.getParameter("sabstrName");
            if ("".equals(subtr) || subtr == null) {
                result = "<div class=\"warning\"><p align=\"center\">Введите название продукта</p></div>";
                throw new ProductException(result);
            }
            ProductBeanRemoteHome productHome = (ProductBeanRemoteHome) EJBHelper.lookupHome("ejb/ProductBean", ProductBeanRemoteHome.class);
            List products = productHome.findBySubstrOfName(subtr);
            request.setAttribute("products", products);
            rd = request.getRequestDispatcher("/index.jsp");
            //  rd.forward(request, response);
        } catch (ProductException ex) {
            request.setAttribute("result", ex.getMessage());
            rd = request.getRequestDispatcher("/index.jsp");
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result", result);
        } finally {
            rd.forward(request, response);
        }
    }

    protected void changeAmount(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        String result = "произошла ошибка";
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        RequestDispatcher rd;
        String amount = "";
        //   rd = request.getRequestDispatcher("addOrder.jsp");
        HttpSession session = request.getSession();
        rd = request.getRequestDispatcher("getBasket.jsp");
        String strId_product[];
       // int int_amount[] = null;
        try {
            String array[] = request.getParameterValues("amount");
            int int_amount;//[] = new int[array.length];
            // amount = request.getParameter("amount");
            strId_product = request.getParameterValues("ID_ORDER");
            OrderBeanRemoteHome orderHome = (OrderBeanRemoteHome) EJBHelper.lookupHome("ejb/OrderBean", OrderBeanRemoteHome.class);

            for (int i = 0; i < strId_product.length; i++) {
                int_amount = Integer.parseInt(array[i]);
                if (int_amount <= 0) {
                    throw new NegativeNumberException("Введите положительное кол-во товара");
                }

                // Object obj = session.getAttribute("ID_PRODUCT");
                long id_order = Long.parseLong(strId_product[i]);
                OrderBeanRemote ord = orderHome.findByPrimaryKey(new Long(id_order));
                int oldKol = ord.getAmount();
                ord.setAmount(int_amount);
                ord.sendMessage(new Long(usr.getId()), "\"ORDER\"", "Заказ " + "\"" + ord.getId() + "\"" + " изменен. Изменено кол-во товара с " + "\"" + oldKol + "\"" + " на " + "\"" + ord.getAmount() + "\"" + ". ", new Long(ord.getId()), 2);
            }
            //  if (session.getAttribute("homepage") = null) {
            //     rd = request.getRequestDispatcher("getCatalog.jsp");
            //  }
            getOrders(request, response, false);
            //result = "<div class=\"success\"><p align=\"center\">Продукт добавлен в корзину</p></div>";
        } catch (FinderException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
        } catch (NegativeNumberException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Введите положительное кол-во товара</p></div>";
            request.setAttribute("result2", result);
            getOrders(request, response, false);


        } catch (NumberFormatException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Не правильный формат данных</p></div>";
            request.setAttribute("result2", result);
            getOrders(request, response, false);


        } catch (RemoteException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result2", result);
            getOrders(request, response, false);


        } catch (NamingException ex) {
            result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";
            request.setAttribute("result2", result);
            getOrders(request, response, false);
        } finally {
            //  request.setAttribute("kol_vo", kol_vo);
            //  request.setAttribute("result", result);
            //  rd.forward(request, response);
        }

    }

    protected void reloadCatalog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, ParseException, IOException, LoginException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        UserBeanRemote usr = JSPHelper.getUser2(request.getSession());
        // String result = "<div class=\"warning\"><p align=\"center\">Произошла ошибка</p></div>";

        if (usr.getRoleId() > 2) {
            throw new LoginException("Вы не обладаете правами администратора");
        }
        Menu.MenuHtml = null;
        rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        String result, homepage, forwardAddress;
        String ee = request.getRequestURI();
        request.setCharacterEncoding("UTF-8");
        // response.setContentType("UTF-8");
        try {
            if (request.getRequestURI().equals("/ProShop-war/delImage")) {
                delImage(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/addImage")) {
                addImage(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/image")
                    || request.getRequestURI().equals("/ProShop-war/image.jpg")) {
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
            if (request.getRequestURI().equals("/ProShop-war/changeAmount")) {
                changeAmount(request, response);
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
            if (request.getRequestURI().equals("/ProShop-war/reloadCatalog")) {
                reloadCatalog(request, response);
                return;
            }
            if (request.getRequestURI().equals("/ProShop-war/find")) {
                findProductsBySubstName(request, response);
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
