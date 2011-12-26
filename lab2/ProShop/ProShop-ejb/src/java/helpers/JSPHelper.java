/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.rmi.RemoteException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import exceptions.*;
import entityBeans.UserBeanRemote;

/**
 *
 * @author Admin
 */
public class JSPHelper {

    private static String userMenu = " <ul><li class=\"first\">Меню пользователя </li>   <li><a href=\"basket\">Корзина</a></li> <li><a href=\"getOrders\">Заказы</a></li><li><a href=\"updateUser.jsp?DO=upProf\">Редактирование профиля</a></li><li><a href=\"getUsersByRole.jsp\">Поиск пользователей</a></li><li class=\"last\"><a href=\"getFullList.jsp\">Вывод всего</a></li></ul>";
    private static String adminMenu = " <ul><li class=\"first\">Меню пользователя </li><li><a href=\"basket\">Корзина</a></li><li><a href=\"getOrders\">Заказы</a></li><li><a href=\"add_catalog.jsp\">Добавление нового каталога</a></li><li><a href=\"updateCatalog.jsp?DO=select\">Редактировать каталог</a></li><li><a href=\"del_catalog.jsp\">Удаление каталога</a></li><li><a href =\"addProduct.jsp\">Добавление продукта</a></li><li><a href=\"updateProduct.jsp\">Редактировать продукт</a></li><li><a href =\"addProduct.jsp\">Добавление продукта</a></li><li><a href=\"updateUser.jsp?DO=upProf\">Редактирование профиля</a></li><li><a href=\"deleteUser.jsp\">Удалить пользователя</a></li><li><a href=\"updateUser.jsp?DO=upUser\">update user</a></li><li><a href=\"getUsersByRole.jsp\">Поиск пользователей</a></li><li><a href=\"XML/export.jsp\">Экспорт</a></li> <li><a href=\"XML/import.jsp\">Импорт</a></li> <li><a href=\"XML/history.jsp\">История (вывод в XML)</a></li><li class=\"last\"><a href=\"getFullList.jsp\">Вывод всего</a></li></ul>";
    private static String managerMenu = " <ul><li class=\"first\">Меню пользователя </li><li><a href=\"basket\">Корзина</a></li><li><a href=\"getOrders\">Заказы</a></li><li><a href=\"add_catalog.jsp\">Добавление нового каталога</a></li><li><a href=\"updateCatalog.jsp?DO=select\">Редактировать каталог</a></li><li><a href=\"del_catalog.jsp\">Удаление каталога</a></li><li><a href =\"addProduct.jsp\">Добавление продукта</a></li><li><a href=\"updateProduct.jsp\">Редактировать продукт</a></li><li><a href =\"addProduct.jsp\">Добавление продукта</a></li><li><a href=\"updateUser.jsp?DO=upProf\">Редактирование профиля</a></li><li><a href=\"getUsersByRole.jsp\">Поиск пользователей</a></li><li class=\"last\"><a href=\"getFullList.jsp\">Вывод всего</a></li></ul>";

    public static String getMenu(long role) {
        String menu = "";
        if (role == 1) {
            menu = adminMenu;
        } else {
            if (role == 2) {
                menu = managerMenu;
            } else {
                menu = userMenu;
            }
        }
        return menu;
    }

    public static String getRequestOrEmpty(HttpServletRequest request, String name) {
        if (request.getAttribute(name) != null) {
            return request.getAttribute(name).toString();
        } else {
            return "";
        }
    }

    public static UserBeanRemote getUser2(HttpSession sess) throws LoginException {
        Object user = sess.getAttribute("user");

        if (user == null) {
            throw new LoginException();
        } else {
            try {
                UserBeanRemote usr = (UserBeanRemote) sess.getAttribute("user");
                if (usr.getRoleId() == 1) {
                    return usr;
                } else {
                    return usr;
                }
            } catch (RemoteException ex) {
                throw new LoginException("Произошла ошибка повторите попытку");
            }
        }

    }
}
