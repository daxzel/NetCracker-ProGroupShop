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

    private static String userMenu = " <ul><li class=\"first\">Меню пользователя </li>   <li><a href=\"/ProShop-war/basket\">Корзина</a></li> <li><a href=\"/ProShop-war/getOrders\">История покупок</a></li><li><a href=\"/ProShop-war/updateUser.jsp?DO=upProf\">Редактирование профиля</a></li><li><a href=\"/ProShop-war/getUsersByRole.jsp\">Поиск пользователей</a></li><li><a href=\"/ProShop-war/getFullList.jsp\">Вывод всего</a></li><li class=\"last\"><a href=\"/ProShop-war/index.jsp\">На главную</a></li></ul>";
    private static String adminMenu = " <ul><li class=\"first\">Меню пользователя </li><li><a href=\"/ProShop-war/basket\">Корзина</a></li><li><a href=\"/ProShop-war/getOrders\">История покупок</a></li><li><a href=\"/ProShop-war/add_catalog.jsp\">Добавление каталога</a></li><li><a href=\"/ProShop-war/updateCatalog.jsp?DO=select\">Редактирование каталога</a></li><li><a href=\"/ProShop-war/del_catalog.jsp\">Удаление каталога</a></li><li><a href =\"/ProShop-war/addProduct.jsp\">Добавление продукта</a></li><li><a href=\"/ProShop-war/updateProduct.jsp\">Изменение продукта</a></li><li><a href =\"/ProShop-war/delProduct.jsp\">Удаление продукта</a></li><li><a href=\"/ProShop-war/updateUser.jsp?DO=upProf\">Редактирование профиля</a></li><li><a href=\"/ProShop-war/deleteUser.jsp\">Удалить пользователя</a></li><li><a href=\"/ProShop-war/updateUser.jsp?DO=upUser\">update user</a></li><li><a href=\"/ProShop-war/getUsersByRole.jsp\">Поиск пользователей</a></li><li><span>Экспорт</span><ul><li><a href=\"/ProShop-war/XML/exportProduct.jsp?poiskType=byPrice\" class=\"first\">Экспортировать продукты используя поиск по цене </a></li><li><a href=\"/ProShop-war/XML/exportProduct.jsp?poiskType=byName\">Экспортировать продукты используя поиск по имени</a></li><li><a href=\"/ProShop-war/XML/exportUser.jsp\">Экспорт пользователей</a></li></ul></li> <li><a href=\"/ProShop-war/XML/import.jsp\">Импорт</a></li> <li><a href=\"/ProShop-war/XML/history.jsp\">История (вывод в XML)</a></li><li><a href=\"/ProShop-war/getFullList.jsp\">Вывод всего</a></li><li class=\"last\"><a href=\"/ProShop-war/index.jsp\">На главную</a></li></ul>";
    private static String managerMenu = " <ul><li class=\"first\">Меню пользователя </li><li><a href=\"/ProShop-war/basket\">Корзина</a></li><li><a href=\"/ProShop-war/getOrders\">История покупок</a></li><li><a href=\"/ProShop-war/add_catalog.jsp\">Добавление каталога</a></li><li><a href=\"/ProShop-war/updateCatalog.jsp?DO=select\">Редактирование каталога</a></li><li><a href=\"/ProShop-war/del_catalog.jsp\">Удаление каталога</a></li><li><a href =\"/ProShop-war/addProduct.jsp\">Добавление продукта</a></li><li><a href=\"/ProShop-war/updateProduct.jsp\">Изменение продукта</a></li><li><a href =\"/ProShop-war/delProduct.jsp\">Удаление продукта</a></li><li><a href=\"/ProShop-war/updateUser.jsp?DO=upProf\">Редактирование профиля</a></li><li><a href=\"/ProShop-war/getUsersByRole.jsp\">Поиск пользователей</a></li><li><a href=\"/ProShop-war/getFullList.jsp\">Вывод всего</a></li><li class=\"last\"><a href=\"/ProShop-war/index.jsp\">На главную</a></li></ul>";
    private static String guestMenu = " <ul><li class=\"first\">Меню пользователя </li>   <li><a href=\"/ProShop-war/getFullList.jsp\">Вывод всего</a></li><li class=\"last\"><a href=\"/ProShop-war/index.jsp\">На главную</a></li></ul>";
    public static String getMenu(long role) {
        String menu = "";
        if (role == 1) {
            menu = adminMenu;
        } else {
            if (role == 2) {
                menu = managerMenu;
            } else {
                if (role == 3) {
                menu = userMenu;
            } else {
                menu = guestMenu;
                }
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
                if(usr.getRoleId()==4){
                    throw new LoginException("Вы не обладаете правами");
                }
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
